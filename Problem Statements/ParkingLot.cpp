#include<bits/stdc++.h>
using namespace std;



//Vehicle code
enum VehicleType{
    CAR,
    BIKE,
    TRUCK
};
class Vehicle{
    protected:
    VehicleType vehicleType;
    string vehicleNumber;
    public:
    Vehicle(VehicleType vehicleType, string vehicleNumber):vehicleType(vehicleType),vehicleNumber(vehicleNumber){}
    VehicleType get_type(){
        return this->vehicleType;
    }
    string get_number(){
        return this->vehicleNumber;
    }
};

class Car: public Vehicle{
    public:
    Car(string vehicleNumber): Vehicle(VehicleType::CAR,vehicleNumber){}

};
class Bike: public Vehicle{
    public:
    Bike(string vehicleNumber): Vehicle(VehicleType::BIKE,vehicleNumber){}

};
class Truck: public Vehicle{
    public:
    Truck(string vehicleNumber): Vehicle(VehicleType::TRUCK,vehicleNumber){}

};


//Payment strategy implementations
class PaymentStrategy{
    public:
    virtual bool make_payment(int amount) = 0;
};
class CashPaymentStrategy: public PaymentStrategy{
    public:
    bool make_payment(int amount)override{
        cout<<"Paid Rs."<<amount<<" via cash"<<endl;
        return true;
    }
};
class CardPaymentStrategy: public PaymentStrategy{
    public:
    bool make_payment(int amount)override{
        cout<<"Paid $"<<amount<<" via card"<<endl;
        return true;
    }
};

class PaymentProcessor{
    private:
    PaymentStrategy* paymentstrategy;
    public:
    PaymentProcessor(PaymentStrategy* paymentstrategy){
        this->paymentstrategy=paymentstrategy;
    }
    bool make_payment(int amount){
        return paymentstrategy->make_payment(amount);
    }

};

//Ticket implementations
class Ticket{
    public:
    int id;
    string start_time;
    string floor_id;
    string spot_id;
    Vehicle* vehicle;
    bool payment_status;

    Ticket(int id, string start_time, string floor_id, string spot_id, Vehicle* vehicle):
        id(id),
        start_time(std::move(start_time)),
        floor_id(std::move(floor_id)),
        spot_id(std::move(spot_id)),
        vehicle(vehicle),
        payment_status(false) {}

    ~Ticket() = default;
};

//Pricing strategy implementation
class PricingStrategy {
    public:
    virtual int calculate_pricing(Ticket &ticket, const string &end_time) = 0;
    virtual ~PricingStrategy() {}
};

int timeToMinutes(const string &timeStr) {
    if (timeStr.size() < 5) throw invalid_argument("Invalid time string format");
    int hours = stoi(timeStr.substr(0, 2));
    int minutes = stoi(timeStr.substr(3, 2));
    return hours * 60 + minutes;
}

class TimeBasedStrategy : public PricingStrategy {
    int peak_start; 
    int peak_end; 
    int normal_price_per_min;
    int peak_price_per_min;

    public:
    TimeBasedStrategy(const string &peak_start_str, const string &peak_end_str, int normalPrice = 1, int peakPrice = 2)
        : peak_start(timeToMinutes(peak_start_str)),
          peak_end(timeToMinutes(peak_end_str)),
          normal_price_per_min(normalPrice),
          peak_price_per_min(peakPrice) {}

    int calculate_pricing(Ticket &ticket, const string &end_time) override {
        int start_min = timeToMinutes(ticket.start_time);
        int end_min = timeToMinutes(end_time);

        // allow overnight span (end < start)
        if (end_min < start_min) end_min += 24 * 60;

        int price = 0;

        for (int t = start_min; t < end_min; t++) {
            if (t >= peak_start && t < peak_end)
                price += peak_price_per_min;
            else
                price += normal_price_per_min;
        }
        return price;
    }
};
//Parking spot implementation
class ParkingSpot{
    public:
    string id;
    VehicleType allowedtype;
    string vehicle_number;
    bool occupied;
    ParkingSpot(string id, VehicleType allowedtype): id(id), allowedtype(allowedtype), occupied(false), vehicle_number("") {}
    ~ParkingSpot() = default;
    bool try_occupy(Vehicle* vehicle){
        if (vehicle == nullptr) {
            cout << "[ParkSpot] Received null vehicle pointer\n";
            return false;
        }

        if(!occupied && vehicle->get_type()==allowedtype){
            occupied=true;
            vehicle_number=vehicle->get_number();
            return true;
        }
        return false; 
    }
    bool is_parked(Vehicle* vehicle){
        if(occupied && vehicle_number==vehicle->get_number()){
            return true;
        }
        return false;
    }
    bool is_occupied()const{    //ask this
        return occupied;
    }
    void vacate(){
        occupied=false;
        vehicle_number.clear();
    }
};

//Parking floor implementation
class ParkingFloor{
    public:
    string id;
    vector<ParkingSpot*> floor_map;
    ParkingFloor(string id):id(id),floor_map({}){};
    ParkingSpot*find_available_spot(Vehicle* vehicle){
        for(auto&spot:floor_map){
            if(spot->try_occupy(vehicle)){
                return spot;
            }
            
        }
        return nullptr;
    }   
    void add_parking_spot(string floor_id,ParkingSpot* parkingspot){
        floor_map.push_back(parkingspot);
    }
    ~ParkingFloor() {
        for (auto sp : floor_map) {
            delete sp;
        }
        floor_map.clear();
    }


};

//Parking lot implementation
class ParkingLot{
private:
    vector<ParkingFloor*> lot_map;       // List of all floors in the parking lot
    map<string,Ticket*> active_tickets;  // Map vehicle_number -> Ticket for active vehicles
    PaymentProcessor* payment_processor;  // Used to handle payments

    mutex mtx;                           // Mutex to protect shared resources
                                         // Only one thread can lock this at a time
                                         // Prevents race conditions when multiple threads access lot_map or active_tickets
    atomic<int> next_ticket_id { 1 };    // Atomic counter for generating unique ticket IDs initial one
                                         // Atomic ensures thread-safe increments without needing a lock

    // Private constructor to enforce singleton pattern
    ParkingLot(PaymentProcessor* payment_processor)
        : lot_map(), active_tickets(), payment_processor(payment_processor) {}

    inline static ParkingLot* _INSTANCE = nullptr;  // Singleton instance of ParkingLot

public:
    // Singleton accessor function
    static ParkingLot* get_instance() {
        // Use std::call_once to ensure only one instance is created even if multiple threads call get_instance()
        static once_flag flag;
        call_once(flag, []() {
            _INSTANCE = new ParkingLot(new PaymentProcessor(new CashPaymentStrategy()));
        });
        return _INSTANCE;
    }

    // Destructor to cleanup dynamic memory
    ~ParkingLot() {
        // Lock mutex while cleaning up active tickets to prevent other threads from modifying them concurrently
        {
            lock_guard<mutex> lock(mtx);   // RAII (Resource Acquisition Is Initialization.): automatically locks the mutex here
            for (auto &kv : active_tickets) {
                delete kv.second;          // Free each Ticket
            }
            active_tickets.clear();
        }

        // No need to lock lot_map here if destructor is called when no other threads exist
        for (auto floor : lot_map) {
            delete floor;
        }
        lot_map.clear();
    }

    // Park a vehicle in the parking lot
    Ticket* park_vehicle(Vehicle* vehicle, string& entry_time) {
        if(vehicle == nullptr) return nullptr;

        lock_guard<mutex> lock(mtx);  // Lock mutex for thread-safe access to lot_map & active_tickets
                                      // Automatically unlocks when lock goes out of scope

        for(auto &floor : lot_map){
            for(auto &spot : floor->floor_map){
                if(spot->try_occupy(vehicle)) {
                    int id = next_ticket_id.fetch_add(1); // Atomic increment ensures unique ID even with multiple threads
                    Ticket* generated_ticket = new Ticket(
                        id,
                        entry_time,
                        floor->id,
                        spot->id,
                        vehicle
                    );

                    active_tickets[vehicle->get_number()] = generated_ticket; // Safe because mutex is locked

                    cout << "[ParkingLot] Issued ticket id=" << id
                         << " for vehicle=" << vehicle->get_number()
                         << " at " << floor->id << ":" << spot->id << "\n";

                    return generated_ticket;
                }
            }
        }

        return nullptr; // No spot available
    }

    // Unpark a vehicle
    bool unpark_vehicle(Ticket* ticket, int amount){
        if (ticket == nullptr) return false;

        // Handle payment first (outside of critical section)
        if (!ticket->payment_status) {
            cout << "Please pay " << amount << "Rs. to unpark your vehicle" << endl;
            cout << "Type yes/Yes to proceed" << endl;
            string payment_confirmation;
            cin >> payment_confirmation;
            if ((payment_confirmation == "yes" || payment_confirmation == "Yes") &&
                payment_processor->make_payment(amount)) {
                ticket->payment_status = true;
            } else {
                cout << "[ParkingLot] Payment failed/aborted\n";
                return false;
            }
        }

        // Lock mutex to safely modify lot_map and active_tickets
        lock_guard<std::mutex> lock(mtx);

        for(auto &floor: lot_map){
            for(auto &spot: floor->floor_map){
                // Find the matching spot for this ticket
                if(ticket->floor_id==floor->id && ticket->spot_id==spot->id && spot->is_parked(ticket->vehicle)){
                    // Remove ticket from active tickets
                    active_tickets.erase(ticket->vehicle->get_number());
                    spot->vacate();          // Free the parking spot
                    delete ticket;           // Free the ticket
                    cout << "[ParkingLot] Vehicle unparked and spot vacated: "
                         << floor->id << ":" << spot->id << "\n";
                    return true;
                }
            }
        }

        cout << "[ParkingLot] Could not find ticket/spot mapping during unpark\n";
        return false;
    }

    // Add a floor to the parking lot
    bool add_floor(ParkingFloor* parking_floor) {
        if (parking_floor == nullptr) return false;

        std::lock_guard<std::mutex> lock(mtx); // Lock while modifying lot_map
        lot_map.push_back(parking_floor);
        return true;
    }
};

//Gates implementation
enum GateType{
    ENTRY,
    EXIT
};

class Gate {
    protected:
    string id;

    public:
    Gate(const string& id) : id(id) {}
    string getId() const { return id; }
    virtual GateType getType() const = 0;
    virtual ~Gate() = default;
};

class EntryGate : public Gate {
    public:
    EntryGate(const string& id) : Gate(id) {}
    GateType getType() const override {
        return GateType::ENTRY;
    }
    Ticket* parkVehicle(Vehicle* vehicle,string entryTime){
        return ParkingLot::get_instance()->park_vehicle(vehicle, entryTime);
    }
};

class ExitGate : public Gate {
    public:
    ExitGate(const string& id) : Gate(id) {}
    GateType getType() const override {
        return GateType::EXIT;
    }
    bool unparkVehicle(Ticket* ticket,int amount){
        return ParkingLot::get_instance()->unpark_vehicle(ticket,amount);
    }
};  




int main() {
    ParkingLot* lot = ParkingLot::get_instance();

    ParkingFloor* floor1 = new ParkingFloor("F1");
    floor1->add_parking_spot("F1", new ParkingSpot("S1", VehicleType::CAR));
    floor1->add_parking_spot("F1", new ParkingSpot("S2", VehicleType::BIKE));
    floor1->add_parking_spot("F1", new ParkingSpot("S3", VehicleType::TRUCK));
    lot->add_floor(floor1);

    EntryGate entry("G1");
    ExitGate exit("G2");

    Car car1("CAR123");
    Bike bike1("BIKE456");
    Truck truck1("TRUCK789");

    Ticket* carTicket = entry.parkVehicle(&car1, "10:00");
    if (carTicket) {
        cout << "Car parked: Ticket ID " << carTicket->id
             << " Spot " << carTicket->spot_id << " Floor " << carTicket->floor_id << endl;
    }

    Ticket* bikeTicket = entry.parkVehicle(&bike1, "10:05");
    if (bikeTicket) {
        cout << "Bike parked: Ticket ID " << bikeTicket->id
             << " Spot " << bikeTicket->spot_id << " Floor " << bikeTicket->floor_id << endl;
    }

    Ticket* truckTicket = entry.parkVehicle(&truck1, "10:10");
    if (truckTicket) {
        cout << "Truck parked: Ticket ID " << truckTicket->id
             << " Spot " << truckTicket->spot_id << " Floor " << truckTicket->floor_id << endl;
    }

    if (exit.unparkVehicle(carTicket, 100)) {
        cout << "Car unparked successfully!" << endl;
    } else {
        cout << "Car could not be unparked." << endl;
    }


    return 0;
}

