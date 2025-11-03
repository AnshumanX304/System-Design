#include<bits/stdc++.h>
using namespace std;

class Board; // Forward declaration

class Symbol{
    char symbol;
    public:
    Symbol(char symbol){
        this->symbol=symbol;
    }
    char get_symbol(){
        return this->symbol;
    }
};

class TicTacToeRules{
public:
    virtual bool is_winning(Board* board)=0; //pure virtual function i.e if its present TicTacToeRules class cannot be 
                                        //instantiated solely unless implemented by some other class via inheritence
    virtual bool valid_move(Board* board,int row,int col)=0;
    virtual bool is_draw(Board* board)=0;
    virtual ~TicTacToeRules() {}
};

class Board{
    private:
    int size;
    vector<vector<Symbol*>> board;
    Symbol* emptyCell;
    public:
    Board(int size){
        this->size=size;
        this->emptyCell=new Symbol('0');
        board.resize(size,vector<Symbol*>(size,this->emptyCell));
    }
    bool cellEmpty(int row,int col){
        if(row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        return board[row][col]==emptyCell;
    }
    bool placeSymbol(Symbol* symbol,int row,int col){
        if(row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        if(!cellEmpty(row,col)){
            return false;
        }
        board[row][col]=symbol;
        return true;
    }
    Symbol* getCell(int row,int col){
        if(row < 0 || row >= size || col < 0 || col >= size) {
            return emptyCell;
        }
        return board[row][col];
    }
    int get_size(){
        return board.size();
    }
    Symbol* get_symbol(int row,int col){
        if(row < 0 || row >= size || col < 0 || col >= size) {
            return emptyCell;
        }
        return board[row][col];
    }
    void display(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                cout<<board[i][j]->get_symbol()<<" ";
            }
            cout<<endl;
        }
        for(int i=0;i<size;i++){
            cout<<"==";
        }
        cout<<endl;
    }
    ~Board() {
        delete emptyCell; 
    }

};

class StandardRules: public TicTacToeRules{
    public:
    bool is_winning(Board *board)override{
        int size=board->get_size();

        for(int i=0;i<size;i++){
            set<char> s;
            for(int j=0;j<size;j++){
                s.insert(board->get_symbol(i,j)->get_symbol());
            }
            if(s.size()==1 && *s.begin() != '0'){
                return true;
            }
        }
        for(int j=0;j<size;j++){
            set<char> s;
            for(int i=0;i<size;i++){
                s.insert(board->get_symbol(i,j)->get_symbol());
            }
            if(s.size()==1 && *s.begin() != '0'){
                return true;
            }
        }
        set<char> s;
        for(int i=0;i<size;i++){
            s.insert(board->get_symbol(i,i)->get_symbol());
        }
        if(s.size()==1 && *s.begin() != '0') {
            return true;
        }

        s.clear();

        for(int i=0;i<size;i++){
            s.insert(board->get_symbol(i,size-i-1)->get_symbol());
        }
        if(s.size()==1 && *s.begin() != '0') {
            return true;
        }

        return false;
    }

    bool is_draw(Board* board) override{
        int size=board->get_size();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board->cellEmpty(i,j)){
                    return false; 
                }
            }
        }
        return true;
    }

    bool valid_move(Board* board,int row,int col)override{
        return board->cellEmpty(row,col);
    }
};

class Player{
    private:
    int id;
    string name;
    int score;
    Symbol* symbol;
    public:
    Player(int id,string name,Symbol* symbol){
        this->id=id;
        this->name=name;
        this->symbol=symbol;
        this->score=0;
    }
    Symbol* get_symbol(){
        return symbol;
    }
    void winning_Sentence(){
        cout<<"Player "<<name<<" with id "<<id<<" has won!!"<<endl;
    }
    void turnSentence(){
        cout<<"Player "<<name<<" please make your turn!!"<<endl;
    }
    void increaseScore(){
        this->score+=1;
    }
    ~Player() {
        delete symbol;  
    }
};

class TicTacToeGame{
    public:
    Board* board;
    deque<Player*> players;
    TicTacToeRules* rules;
    bool gameover;
    TicTacToeGame(int boardsize, TicTacToeRules* rules){
        this->board=new Board(boardsize);
        this->rules=rules;
        this->gameover = false;
    }

    void addPlayer(Player* player){
        players.push_back(player);
    }

    void play(){
        if(players.size() < 2) {
            cout << "Need at least 2 players!" << endl;
            return;
        }
        
        while(!gameover){
            Player * currentPlayer=players.front();
            board->display();
            currentPlayer->turnSentence();
            cout<<"Enter your cell row and column :"<<endl;
            int row,col;
            cin>>row>>col;

            while(!rules->valid_move(board,row,col)){
                cout<<"Enter a valid move !!"<<endl;
                cin>>row>>col;
            }

            board->placeSymbol(currentPlayer->get_symbol(),row,col);
            if(rules->is_winning(board)){
                gameover=true;
                currentPlayer->winning_Sentence();
                currentPlayer->increaseScore();
            }
            else if(rules->is_draw(board)){
                gameover=true;
                cout<<"The match has drawn !"<<endl;
            }
            else{
                players.pop_front();
                players.push_back(currentPlayer);
            }

        }
        board->display(); // Show final board state
    }
    ~TicTacToeGame(){
        delete board;
        delete rules;
    }

};

enum GameType {
    STANDARD
};

class GameFactory{
    public:
    static TicTacToeGame* getGame(int size, int ruletype){
        if(GameType::STANDARD == ruletype){
            return new TicTacToeGame(size, new StandardRules());
        }
        return NULL;
    }
};


int main(){

    GameType gametype=GameType::STANDARD;
    int boardsize;
    cout<<"Enter the boardsize ::"<<endl;
    cin>>boardsize;

    Player* player1 = new Player(1, "Luffy", new Symbol('X'));
    Player* player2 = new Player(2, "Naruto", new Symbol('#'));
    TicTacToeGame* game = GameFactory::getGame(boardsize, gametype);
   
    game->addPlayer(player1);
    game->addPlayer(player2);
    game->play();

    delete player1;
    delete player2;
    delete game;

    return 0;
}