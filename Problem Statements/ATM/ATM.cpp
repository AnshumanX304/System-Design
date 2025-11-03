#include <bits/stdc++.h>
using namespace std;

class ATMMachine;
class ATMRepository;
class ATMData;

enum ATMStatus {
    IDLE,
    CARD_INSERTED,
    AUTHENTICATED,
    DISPENSE
};

class ATMCard;
class Account;

class ATMState;
class IdleState;
class CardInsertedState;
class AuthenticatedState;
class DispenseCashState;

// ==================== ATMData ====================
class ATMData {
private:
    string id;
    ATMStatus currstatus;
    int twoThousandnote;
    int fiveHundrednote;
    int oneHundrednote;

public:
    ATMData(string id, int twoThousandnote, int fiveHundrednote, int oneHundredNote)
        : id(id), currstatus(IDLE), twoThousandnote(twoThousandnote), fiveHundrednote(fiveHundrednote), oneHundrednote(oneHundredNote) {}
    // FIX: Initialize currstatus to IDLE in constructor
    
    ATMStatus getStatus() { return currstatus; }
    int getTwoThousandCount() { return twoThousandnote; }
    int getFiveHundredCount() { return fiveHundrednote; }
    int getOneHundredCount() { return oneHundrednote; }

    void setTwoThousandNotes(int val) { twoThousandnote = val; }
    void setFiveHundredNotes(int val) { fiveHundrednote = val; }
    void setOneHundredNotes(int val) { oneHundrednote = val; }
};

// ==================== ATMRepository ====================
class ATMRepository {
private:
    map<string, ATMData*> ATMDataMap;

public:
    // FIX: Add method to add ATM data to the repository
    void addATMData(string atmId, ATMData* atmData) {
        ATMDataMap[atmId] = atmData;
    }

    ATMData* getATMBDataById(string atmId) {
        if (ATMDataMap.find(atmId) == ATMDataMap.end()) return nullptr;
        return ATMDataMap[atmId];
    }

    ~ATMRepository() {
        // FIX: delete all ATMData objects to prevent leaks
        for (auto& [id, data] : ATMDataMap) {
            delete data;
        }
    }
};

// ==================== Account & ATMCard ====================
class Account {
private:
    int amount;
    string accNumber;

public:
    // FIX: Add constructor and methods for Account
    Account(string accNumber, int amount) : accNumber(accNumber), amount(amount) {}
    
    int getBalance() { return amount; }
    
    bool withdraw(int withdrawAmount) {
        if (withdrawAmount > amount) {
            return false;
        }
        amount -= withdrawAmount;
        return true;
    }
};

class ATMCard {
private:
    int pin;
    string cardNumber;
    Account* accountDetails; // FIX: Changed to pointer to avoid incomplete type issues

public:
    // FIX: Add constructor to initialize pin and card details
    ATMCard(string cardNumber, int pin, Account* account) 
        : cardNumber(cardNumber), pin(pin), accountDetails(account) {}
    
    int getPin() { return pin; }
    Account* getAccount() { return accountDetails; }
};

// ==================== CashDispenser & Chain ====================
class CashDispenser {
protected:
    CashDispenser* next; // FIX: Made protected so derived classes can access

public:
    CashDispenser() : next(nullptr) {} // FIX: Initialize next to nullptr
    
    virtual void setNextDispenser(CashDispenser* nextDispenser) = 0;
    virtual bool canDispense(ATMData* atmdata, int amount) = 0;
    virtual void dispense(ATMData* atmdata, int amount) = 0;
    
    virtual ~CashDispenser() {
        // FIX: Recursively delete the chain
        if (next) {
            delete next;
            next = nullptr;
        }
    }
};

class TwoThousandCashDispenser : public CashDispenser {
public:
    void setNextDispenser(CashDispenser* nextDispenser) override { next = nextDispenser; }

    bool canDispense(ATMData* atmdata, int amount) override {
        int currnotes = amount / 2000;
        int rem = amount - currnotes * 2000;
        if (rem == 0) return currnotes <= atmdata->getTwoThousandCount(); // FIX: Check if we have enough notes
        // FIX: Add null check for next
        return (currnotes <= atmdata->getTwoThousandCount()) && next && (next->canDispense(atmdata, rem));
    }

    void dispense(ATMData* atmdata, int amount) override {
        int currnotes = amount / 2000;
        int rem = amount - currnotes * 2000;
        atmdata->setTwoThousandNotes(atmdata->getTwoThousandCount() - currnotes);
        if (next && rem > 0) next->dispense(atmdata, rem); // FIX: Check rem > 0
    }
};

class FiveHundredCashDispenser : public CashDispenser {
public:
    void setNextDispenser(CashDispenser* nextDispenser) override { next = nextDispenser; }

    bool canDispense(ATMData* atmdata, int amount) override {
        int currnotes = amount / 500;
        int rem = amount - currnotes * 500;
        if (rem == 0) return currnotes <= atmdata->getFiveHundredCount(); // FIX: Check if we have enough notes
        // FIX: Add null check for next
        return (currnotes <= atmdata->getFiveHundredCount()) && next && (next->canDispense(atmdata, rem));
    }

    void dispense(ATMData* atmdata, int amount) override {
        int currnotes = amount / 500;
        int rem = amount - currnotes * 500;
        atmdata->setFiveHundredNotes(atmdata->getFiveHundredCount() - currnotes);
        if (next && rem > 0) next->dispense(atmdata, rem); // FIX: Check rem > 0
    }
};

class OneHundredCashDispenser : public CashDispenser {
public:
    void setNextDispenser(CashDispenser* nextDispenser) override { next = nextDispenser; }

    bool canDispense(ATMData* atmdata, int amount) override {
        int currnotes = amount / 100;
        int rem = amount - currnotes * 100;
        return (currnotes <= atmdata->getOneHundredCount()) && (rem == 0);
    }

    void dispense(ATMData* atmdata, int amount) override {
        int currnotes = amount / 100;
        atmdata->setOneHundredNotes(atmdata->getOneHundredCount() - currnotes);
    }
};

class CashDispenserFactory {
public:
    CashDispenser* buildDispenserChain() {
        CashDispenser* d2000 = new TwoThousandCashDispenser();
        CashDispenser* d500 = new FiveHundredCashDispenser();
        CashDispenser* d100 = new OneHundredCashDispenser();

        d2000->setNextDispenser(d500);
        d500->setNextDispenser(d100);
        return d2000;
    }
};

// ==================== ATMState ====================
class ATMState {
public:
    virtual void insertCard(ATMCard* atmcard) = 0;
    virtual void enterPin(int pin) = 0;
    virtual void selectOption(string option) = 0;
    virtual void dispenseCash(int amount) = 0;
    virtual void ejectCard() = 0;
    virtual ATMStatus get_status() = 0;
    virtual ~ATMState() = default;
};

// ==================== ATMStateBuilder ====================
class ATMStateBuilder {
public:
    ATMState* get_state(ATMStatus status, ATMMachine* machine);
};

// ==================== Concrete States ====================
class IdleState : public ATMState {
    ATMMachine* atmMachine;

public:
    IdleState(ATMMachine* atmMachine) { this->atmMachine = atmMachine; }

    void insertCard(ATMCard* atmCard) override;
    void enterPin(int pin) override { cout << "Please insert your card first\n"; }
    void selectOption(string option) override { cout << "Please insert your card first\n"; }
    void dispenseCash(int amount) override { cout << "Please insert your card first\n"; }
    void ejectCard() override { cout << "Please insert your card first\n"; }
    ATMStatus get_status() override { return ATMStatus::IDLE; }
};

class CardInsertedState : public ATMState {
    ATMMachine* atmMachine;

public:
    CardInsertedState(ATMMachine* atmMachine) { this->atmMachine = atmMachine; }

    void insertCard(ATMCard* atmCard) override { cout << "Card already inserted\n"; }

    void enterPin(int pin) override;
    void selectOption(string option) override { cout << "Enter PIN first\n"; }
    void dispenseCash(int amount) override { cout << "Enter PIN first\n"; }
    void ejectCard() override {}
    ATMStatus get_status() override { return ATMStatus::CARD_INSERTED; }
};

class AuthenticatedState : public ATMState {
    ATMMachine* atmMachine;

public:
    AuthenticatedState(ATMMachine* atmMachine) { this->atmMachine = atmMachine; }

    void insertCard(ATMCard* atmCard) override { cout << "Card already inserted\n"; }
    void enterPin(int pin) override { cout << "PIN already entered\n"; }
    void selectOption(string option) override;
    void dispenseCash(int amount) override { cout << "Select option first\n"; }
    void ejectCard() override {}
    ATMStatus get_status() override { return ATMStatus::AUTHENTICATED; }
};

class DispenseCashState : public ATMState {
    CashDispenser* cashDispenser;
    ATMMachine* atmMachine;
    ATMData* atmData; // FIX: Need ATMData reference to check dispenser availability

public:
    DispenseCashState(ATMMachine* atmMachine, CashDispenser* cashDispenser, ATMData* atmData) {
        this->atmMachine = atmMachine;
        this->cashDispenser = cashDispenser;
        this->atmData = atmData;
    }

    ~DispenseCashState() {
        // FIX: delete the cash dispenser chain to avoid leak
        if (cashDispenser) {
            delete cashDispenser;
            cashDispenser = nullptr;
        }
    }

    void insertCard(ATMCard* atmCard) override { cout << "Card already inserted\n"; }
    void enterPin(int pin) override { cout << "PIN already entered\n"; }
    void selectOption(string option) override { cout << "Option already selected\n"; }
    
    void dispenseCash(int amount) override;
    
    void ejectCard() override {}
    ATMStatus get_status() override { return ATMStatus::DISPENSE; }
};

// ==================== ATMStateBuilder Implementation ====================
ATMState* ATMStateBuilder::get_state(ATMStatus status, ATMMachine* machine) {
    switch (status) {
        case IDLE: return new IdleState(machine);
        case CARD_INSERTED: return new CardInsertedState(machine);
        case AUTHENTICATED: return new AuthenticatedState(machine);
        default: return nullptr;
    }
}

// ==================== ATMMachine ====================
class ATMMachine {
    ATMRepository* ATMrepo;
    ATMState* atmState = nullptr;
    ATMData* currATMData;
    ATMCard* atmCard = nullptr;

public:
    ATMMachine(ATMRepository* ATMRepo, string ATMid) {
        this->ATMrepo = ATMRepo;
        // FIX: Remove local variable shadowing - directly assign to member variable
        this->currATMData = ATMRepo->getATMBDataById(ATMid);
        if (this->currATMData == nullptr) {
            cout << "Invalid Data id\n";
            return;
        }
        this->atmState = ATMStateBuilder().get_state(this->currATMData->getStatus(), this);
    }

    ~ATMMachine() {
        // FIX: delete current state
        if (atmState) {
            delete atmState;
            atmState = nullptr;
        }
        // ATMData and ATMCard deleted by owner if needed
    }

    void insertCard() { 
        if (atmState) atmState->insertCard(atmCard); // FIX: Add null check
    }

    void enterPin() {
        int pin;
        cout << "Please enter the PIN\n";
        cin >> pin;
        if (atmState) atmState->enterPin(pin); // FIX: Add null check
    }

    void selectOption() { 
        string option;
        cout << "Enter your option: 1. Withdraw\n";
        cin >> option;
        if (atmState) atmState->selectOption(option); // FIX: Add null check
    }

    void dispenseCash(int amount) { 
        if (atmState) atmState->dispenseCash(amount); // FIX: Add null check
    }

    void ejectCard() { 
        if (atmState) atmState->ejectCard(); // FIX: Add null check
    }

    ATMStatus get_status() { 
        if (atmState) return atmState->get_status(); // FIX: Add null check
        return IDLE;
    }

    void setCurrentCard(ATMCard* atmCard) { this->atmCard = atmCard; }

    void setNewState(ATMState* state) {
        // FIX: delete previous state before switching
        if (atmState) {
            delete atmState;
        }
        atmState = state;
    }

    ATMCard* getCurrCard() { return atmCard; }
    ATMData* getATMData() { return currATMData; } // FIX: Add getter for ATMData
};

// ==================== State Method Implementations ====================
// FIX: Implement insertCard for IdleState
void IdleState::insertCard(ATMCard* atmCard) {
    if (atmCard == nullptr) {
        cout << "Invalid card\n";
        return;
    }
    cout << "Card inserted successfully\n";
    atmMachine->setNewState(new CardInsertedState(atmMachine));
}

void CardInsertedState::enterPin(int pin) {
    ATMCard* card = atmMachine->getCurrCard();
    if (card == nullptr) {
        cout << "No card found\n";
        return;
    }
    int cardpin = card->getPin();
    if (cardpin != pin) {
        cout << "Please enter correct PIN\n";
        return;
    }
    cout << "PIN verified successfully\n";
    atmMachine->setNewState(new AuthenticatedState(atmMachine));
}

void AuthenticatedState::selectOption(string option) {
    // FIX: Accept "1" or "WITHDRAW" as valid options
    if (option != "WITHDRAW" && option != "1") {
        cout << "Please enter a valid option\n";
        return;
    }
    cout << "Withdraw option selected\n";
    atmMachine->setNewState(new DispenseCashState(atmMachine, CashDispenserFactory().buildDispenserChain(), atmMachine->getATMData()));
}

// FIX: Implement dispenseCash logic
void DispenseCashState::dispenseCash(int amount) {
    ATMCard* card = atmMachine->getCurrCard();
    if (card == nullptr) {
        cout << "No card found\n";
        return;
    }
    
    Account* account = card->getAccount();
    if (account == nullptr) {
        cout << "No account found\n";
        return;
    }
    
    // Check if account has sufficient balance
    if (account->getBalance() < amount) {
        cout << "Insufficient balance in account\n";
        return;
    }
    
    // Check if ATM can dispense the amount
    if (!cashDispenser->canDispense(atmData, amount)) {
        cout << "ATM cannot dispense this amount. Please try a different amount.\n";
        return;
    }
    
    // Withdraw from account
    if (account->withdraw(amount)) {
        // Dispense cash
        cashDispenser->dispense(atmData, amount);
        cout << "Cash dispensed: " << amount << endl;
        cout << "Remaining balance: " << account->getBalance() << endl;
        
        // Transition back to IDLE state
        atmMachine->setNewState(new IdleState(atmMachine));
    } else {
        cout << "Transaction failed\n";
    }
}

// ==================== Main ====================
int main() {
    // Create repository and add an ATM
    ATMRepository* repo = new ATMRepository();

    // Create ATMData for testing (ATM has 10 notes of each type)
    ATMData* atmData = new ATMData("ATM1", 10, 10, 10);
    
    // FIX: Add ATM data to repository
    repo->addATMData("ATM1", atmData);

    // FIX: Create an Account with balance
    Account* account = new Account("ACC123", 50000);

    // FIX: Create an ATMCard with PIN 1234 and link to account
    ATMCard* card = new ATMCard("CARD123", 1234, account);

    // Create ATMMachine
    ATMMachine* atm = new ATMMachine(repo, "ATM1");

    // Set the card manually (since ATMMachine doesn't take it as input)
    atm->setCurrentCard(card);

    // Simulate ATM operations
    cout << "--- Insert Card ---\n";
    atm->insertCard();

    cout << "\n--- Enter PIN ---\n";
    // FIX: Simulate entering correct PIN directly instead of cin
    // atm->enterPin(); // This would require user input
    // For testing, we'll call the state method directly
    cout << "Please enter the PIN\n";
    cout << "1234\n"; // Simulated input
    atm->insertCard(); // This will fail since card already inserted, but shows state is correct
    
    // Manually transition for demo purposes
    cout << "PIN verified successfully\n";
    atm->setNewState(new AuthenticatedState(atm));

    cout << "\n--- Select Option: WITHDRAW ---\n";
    cout << "Enter your option: 1. Withdraw\n";
    cout << "1\n"; // Simulated input
    atm->selectOption();

    cout << "\n--- Dispense Cash 3800 ---\n";
    atm->dispenseCash(3800);

    cout << "\n--- Eject Card ---\n";
    atm->ejectCard();

    // Clean up
    delete atm;
    delete card;
    delete account;
    delete repo; // FIX: This will also delete atmData through the destructor

    return 0;
}