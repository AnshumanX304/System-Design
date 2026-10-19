# Bad code, violates srp
class BakerClass:
    def bake_bread(self) -> None:
        print("Baking your bread !!!")

    def manage_inventory(self) -> None:
        print("Mananging inventory !!!")

    def order_supplies(self) -> None:
        print("Ordering supplies...")

    def serve_customer(self) -> None:
        print("Serving customers...")

    def clean_bakery(self) -> None:
        print("Cleaning the bakery...")



# Good Code, follows SRP

class BreadBaker:
    def bake_bread(self) -> None:
        print("Baking high-quality bread...")


class InventoryManager:
    def manage_inventory(self) -> None:
        print("Managing inventory...")


class SupplyManager:
    def order_supplies(self) -> None:
        print("Ordering supplies...")


class CustomerService:
    def serve_customer(self) -> None:
        print("Serving customers...")


class BakeryCleaner:
    def clean_bakery(self) -> None:
        print("Cleaning the bakery...")


    




def main() -> None:

    baker = BakerClass()
    baker.bake_bread()
    baker.clean_bakery()
    baker.manage_inventory()
    baker.order_supplies()
    baker.serve_customer()
    baker = BreadBaker()
    inventory_manager = InventoryManager()
    supply_manager = SupplyManager()
    customer_service = CustomerService()
    cleaner = BakeryCleaner()

    baker.bake_bread()
    inventory_manager.manage_inventory()
    supply_manager.order_supplies()
    customer_service.serve_customer()
    cleaner.clean_bakery()


if __name__ == "__main__":
    main()

