#include<bits/stdc++.h>
using namespace std;

class User;
enum SplitType{
    EQUAL,
    PERCENTAGE
};
class Split;
class BalanceSheet;
class Expense;
class Group;
class GroupRepo;
class DebtSimplifierService;
class GroupService;
class SplitStrategy{
    public:
    virtual vector<Split> getSplit(
        int amount,
        vector<User*>,
        map<int,int> metaData
    ) = 0;
};

class EqualSplitStrategy:public SplitStrategy{

    public:
    virtual vector<Split>getSplit(
        int amount,
        vector<User*>,
        map<int,int> metaData
    ) override{

    }

};

class PercentageSplitStrategy:public SplitStrategy{

    public:
    virtual vector<Split>getSplit(
        int amount,
        vector<User*>,
        map<int,int> metaData
    ) override{

    }

};

class SplitStrategyFactory{
    public:
    SplitStrategy* getSplitStrategy(SplitType type){
        if(type==SplitType::EQUAL){
            return new EqualSplitStrategy();
        }
        else if(type==SplitType::PERCENTAGE){
            return new PercentageSplitStrategy();
        }
        else{
            return nullptr;
        }

    }   
};
class User{
    private:
    int id;
    string name;
    public:
    User(int id,string name):id(id),name(name){}
};

class Split{
    private:
    User* user;
    int amount;
};

class Expense{
    private:
    string name;
    vector<Split*> splits;
    User* lend_by;
    int amount;
    SplitType splitType;
};
class BalanceSheet{
    int totalPaid;
    int totalExpense;
    map<User*,double> balances;
};
class Group{
    private:
    int id;
    string name;
    vector<User*> members;
    vector<Expense*> grpExpenses;
    map<User*,BalanceSheet*> grpBalanceSheet;

};


class GroupRepo{
    private:
    map<int,Group*> repo;
};

class DebtSimplificationService{
    public:
    void SimplifyDebt(Group*){

    }
};

class ExpenseService{
    public:
    void AddExpense(){

    }
    void UpdateBalance(){

    }
};

class GroupService{
    private:
    GroupRepo* grouprepo;
    ExpenseService* expenseService;
    DebtSimplificationService* dbtsimplificationService;

    public:

};




int main(){
    return 0;
}



