#include <bits/stdc++.h>
using namespace std;

class VendingMachine;
class ItemInfo;
class TransactionContext;

//
// ---------------- Base State Class ----------------
//
class VendingMachineState {
public:
    virtual VendingMachineState* insert_coin(VendingMachine* vmContext, vector<int>& coins) = 0;
    virtual VendingMachineState* return_coin(VendingMachine* vmContext) = 0;
    virtual VendingMachineState* select_item(VendingMachine* vmContext, string code, int qty) = 0;
    virtual VendingMachineState* dispense_item(VendingMachine* vmContext) = 0;
    virtual VendingMachineState* refill_item(VendingMachine* vmContext, vector<ItemInfo> iteminfo) = 0;
    virtual ~VendingMachineState() = default;
};

//
// ---------------- Supporting Classes ----------------
//
class ItemInfo {
public:
    string code;
    string name;
    int price;
    int qty;
};

class TransactionContext {
public:
    string code;
    int targetQty = 0;
    vector<int> currcoins;
};

//
// Forward declare other states so they can reference each other
//
class HasCoinState;
class InsertCoinState;
class DispenseItemState;
class OutofStockState;

//
// ---------------- InsertCoinState ----------------
//
class InsertCoinState : public VendingMachineState {
public:
    static InsertCoinState& instance() {
        static InsertCoinState s;
        return s;
    }

    VendingMachineState* insert_coin(VendingMachine* vm, vector<int>& coins) override;
    VendingMachineState* select_item(VendingMachine* vm, string code, int qty) override;
    VendingMachineState* dispense_item(VendingMachine* vm) override;
    VendingMachineState* return_coin(VendingMachine* vm) override;
    VendingMachineState* refill_item(VendingMachine* vm, vector<ItemInfo> iteminfo) override;
};

//
// ---------------- HasCoinState ----------------
//
class HasCoinState : public VendingMachineState {
public:
    static HasCoinState& instance() {
        static HasCoinState s;
        return s;
    }

    VendingMachineState* insert_coin(VendingMachine* vm, vector<int>& coins) override;
    VendingMachineState* return_coin(VendingMachine* vm) override;
    VendingMachineState* select_item(VendingMachine* vm, string code, int qty) override;
    VendingMachineState* dispense_item(VendingMachine* vm) override;
    VendingMachineState* refill_item(VendingMachine* vm, vector<ItemInfo> iteminfo) override;
};

//
// ---------------- DispenseItemState ----------------
//
class DispenseItemState : public VendingMachineState {
public:
    static DispenseItemState& instance() {
        static DispenseItemState s;
        return s;
    }

    VendingMachineState* insert_coin(VendingMachine* vm, vector<int>& coins) override;
    VendingMachineState* return_coin(VendingMachine* vm) override;
    VendingMachineState* select_item(VendingMachine* vm, string code, int qty) override;
    VendingMachineState* dispense_item(VendingMachine* vm) override;
    VendingMachineState* refill_item(VendingMachine* vm, vector<ItemInfo> iteminfo) override;
};

//
// ---------------- OutOfStockState ----------------
//
class OutofStockState : public VendingMachineState {
public:
    static OutofStockState& instance() {
        static OutofStockState s;
        return s;
    }

    VendingMachineState* insert_coin(VendingMachine* vm, vector<int>& coins) override;
    VendingMachineState* return_coin(VendingMachine* vm) override;
    VendingMachineState* select_item(VendingMachine* vm, string code, int qty) override;
    VendingMachineState* dispense_item(VendingMachine* vm) override;
    VendingMachineState* refill_item(VendingMachine* vm, vector<ItemInfo> iteminfo) override;
};

//
// ---------------- VendingMachine ----------------
//
class VendingMachine {
private:
    VendingMachineState* currState;
    map<string, ItemInfo*> iteminfoMap;
    TransactionContext* currContext;
    vector<int> storedCoins;
public:
    VendingMachine() {
        currState = &InsertCoinState::instance();
        currContext = new TransactionContext();
    }

    ~VendingMachine() {
        delete currContext;
        for (auto& pair : iteminfoMap) {
            delete pair.second;
        }
    }

    void insert_coin(vector<int> coins) {
        currState = currState->insert_coin(this, coins);
    }

    void return_coin() {
        currState->return_coin(this);
    }

    void select_item(string code, int qty) {
        currState = currState->select_item(this, code, qty);
    }

    void dispense_item() {
        currState->dispense_item(this);
    }

    void refill_item(vector<ItemInfo> items) {
        currState->refill_item(this, items);
    }

    int add_coins(vector<int>& coins){
        int sum=0;
        for (auto& it : coins){
            currContext->currcoins.push_back(it);
            sum+=it;
        }
        return sum;
    }
    void check_and_delete_iteminfomap(string key){
        if (iteminfoMap.find(key) != iteminfoMap.end()) {
            delete iteminfoMap[key];
        }
    }
    void add_item_info_map(string key,ItemInfo* value){
        iteminfoMap[key] = value;
    }

    int get_currcontext_coins(){
        int sum=accumulate(currContext->currcoins.begin(),currContext->currcoins.end(),0);
        return sum;
    }

    void clear_currcontext_coins(){
        currContext->currcoins.clear();
    }


    ItemInfo* get_item_by_key(const string& key) {
        auto it = iteminfoMap.find(key);
        if (it != iteminfoMap.end()) {
            return it->second;
        }
        return nullptr;
    }
    void setCodeQtyContext(string code,int qty){
        currContext->code = code;
        currContext->targetQty = qty;
    }
    bool check_empty_map(){
        bool allempty=true;
        for (auto& iteminfo : iteminfoMap) {
            if (iteminfo.second->qty > 0) allempty = false;
        }
        return allempty;
    }

    void decreaseProductQty(string code,int todec){
        iteminfoMap[code]->qty -= todec;
    }
    TransactionContext* getCurrContext(){
        return this->currContext;
    }

    
   
    
};

//
// ---------------- InsertCoinState Implementation ----------------
//
VendingMachineState* InsertCoinState::insert_coin(VendingMachine* vm, vector<int>& coins) {
    int sum=vm->add_coins(coins);
    cout << "Coins of value Rs. " << sum << " collected.\n";
    return &HasCoinState::instance();
}

VendingMachineState* InsertCoinState::select_item(VendingMachine* vm, string code, int qty) {
    cout << "Please insert coins before selecting an item.\n";
    return this; // No transition
}

VendingMachineState* InsertCoinState::dispense_item(VendingMachine* vm) {
    cout << "Cannot dispense yet.\n";
    return this;
}

VendingMachineState* InsertCoinState::return_coin(VendingMachine* vm) {
    cout << "No coins to return.\n";
    return this;
}

VendingMachineState* InsertCoinState::refill_item(VendingMachine* vm, vector<ItemInfo> iteminfo) {
    for (auto& i : iteminfo) {
        vm->check_and_delete_iteminfomap(i.code);
        vm->add_item_info_map(i.code,new ItemInfo(i));
    }
    cout << "Machine refilled successfully.\n";
    return this;
}

//
// ---------------- HasCoinState Implementation ----------------
//
VendingMachineState* HasCoinState::insert_coin(VendingMachine* vm, vector<int>& coins) {
    cout << "Machine already has coins. Please select an item.\n";
    return this; // Stay in same state
}

VendingMachineState* HasCoinState::return_coin(VendingMachine* vm) {
    int currcost = vm->get_currcontext_coins();
    if (currcost == 0) {
        cout << "No coins to return.\n";
        return this;
    }
    cout << "Returning Rs. " << currcost << " back to user.\n";
    vm->clear_currcontext_coins();
    return &InsertCoinState::instance();
}

VendingMachineState* HasCoinState::select_item(VendingMachine* vm, string code, int qty) {
    // auto it = vm->iteminfoMap.find(code);
    auto it=vm->get_item_by_key(code);
    if (it== nullptr) {
        cout << "Item not found for code " << code << endl;
        return this;
    }

    ItemInfo* selectedItem = it;
    if (selectedItem->qty < qty) {
        cout << "Requested quantity not available. Please choose less.\n";
        return this;
    }

    int totalCost = selectedItem->price * qty;
    int inserted = vm->get_currcontext_coins();

    if (inserted != totalCost) {
        cout << "Please insert exact Rs. " << totalCost << " (currently Rs. " << inserted << ")\n";
        cout << "Returning coins...\n";
        return this->return_coin(vm);
    }

    cout << "Item selected: " << selectedItem->name << " (" << qty << "x)\n";
    cout << "Proceeding to dispense...\n";

    // FIX: Set transaction context before transitioning!
    vm->setCodeQtyContext(code,qty);
    return &DispenseItemState::instance();
}

VendingMachineState* HasCoinState::dispense_item(VendingMachine* vm) {
    cout << "Please select an item first.\n";
    return this;
}

VendingMachineState* HasCoinState::refill_item(VendingMachine* vm, vector<ItemInfo> iteminfo) {
    cout << "Cannot refill while a transaction is active.\n";
    return this;
}


//
// ---------------- DispenseItemState Implementation ----------------
//

VendingMachineState* DispenseItemState::insert_coin(VendingMachine* vm, vector<int>& coins) {
    return this;
}

VendingMachineState* DispenseItemState::return_coin(VendingMachine* vm) {
    return this;
}

VendingMachineState* DispenseItemState::select_item(VendingMachine* vm, string code, int qty) {
    return this;
}

VendingMachineState* DispenseItemState::dispense_item(VendingMachine* vm) {
    TransactionContext* currcontext = vm->getCurrContext();

    string currItemCode = currcontext->code;
    int currQty = currcontext->targetQty;

    vm->decreaseProductQty(currItemCode,currQty);

    cout << currQty << " units of item with code: " << currItemCode << " has been dispensed" << endl;

    currcontext->code = "";
    currcontext->targetQty = 0;
    currcontext->currcoins.clear();

    bool allempty = vm->check_empty_map();

    if (allempty) {
        return &OutofStockState::instance();
    }
    return &InsertCoinState::instance();
}

VendingMachineState* DispenseItemState::refill_item(VendingMachine* vm, vector<ItemInfo> iteminfo) {
    return this;
}

//
// ---------------- OutofStockState Implementation ----------------
//

VendingMachineState* OutofStockState::insert_coin(VendingMachine* vm, vector<int>& coins) {
    cout << "The machine is out of stock please refill it to proceed any operation" << endl;
    return this;
}

VendingMachineState* OutofStockState::return_coin(VendingMachine* vm) {
    cout << "The machine is out of stock please refill it to proceed any operation" << endl;
    return this;
}

VendingMachineState* OutofStockState::select_item(VendingMachine* vm, string code, int qty) {
    cout << "The machine is out of stock please refill it to proceed any operation" << endl;
    return this;
}

VendingMachineState* OutofStockState::dispense_item(VendingMachine* vm) {
    cout << "The machine is out of stock please refill it to proceed any operation" << endl;
    return this;
}

VendingMachineState* OutofStockState::refill_item(VendingMachine* vm, vector<ItemInfo> iteminfo) {
    for (auto& i : iteminfo) {
        // FIX: Delete old item if exists to prevent memory leak
        vm->check_and_delete_iteminfomap(i.code);
        vm->add_item_info_map(i.code,new ItemInfo(i));
    }
    cout << "Machine refilled successfully.\n";
    return &InsertCoinState::instance();
}

//
// ---------------- Main ----------------
//
int main() {
    VendingMachine vm;

    // FIX: Allocate on heap, not stack
    vm.refill_item({new ItemInfo{"A1", "Lays Chips", 15, 5},new ItemInfo{"B1", "Pepsi", 20, 3}});
    // Test flow
    vector<int> coins = {10, 5};
    vm.insert_coin(coins);
    vm.select_item("A1", 1);
    vm.dispense_item();  // FIX: Actually dispense!
}