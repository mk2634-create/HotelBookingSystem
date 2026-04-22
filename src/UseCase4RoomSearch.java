import java.util.HashMap;

abstract class Room4 {
    int beds;
    int size;
    double price;

    Room4(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    abstract void displayDetails();
}

class SingleRoom4 extends Room4 {
    SingleRoom4() {
        super(1, 250, 1500.0);
    }

    void displayDetails() {
        System.out.println("Single Room | Beds: " + beds + " | Size: " + size + " sqft | Price: " + price);
    }
}

class DoubleRoom4 extends Room4 {
    DoubleRoom4() {
        super(2, 400, 2500.0);
    }

    void displayDetails() {
        System.out.println("Double Room | Beds: " + beds + " | Size: " + size + " sqft | Price: " + price);
    }
}

class SuiteRoom4 extends Room4 {
    SuiteRoom4() {
        super(3, 750, 5000.0);
    }

    void displayDetails() {
        System.out.println("Suite Room | Beds: " + beds + " | Size: " + size + " sqft | Price: " + price);
    }
}

class RoomInventory4 {
    private HashMap<String, Integer> inventory;

    public RoomInventory4() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

class RoomSearchService4 {
    public void searchRooms(RoomInventory4 inventory) {
        System.out.println("=== Available Rooms ===\n");
        Room4 single = new SingleRoom4();
        Room4 doubleRoom = new DoubleRoom4();
        Room4 suite = new SuiteRoom4();

        if (inventory.getAvailability("Single Room") > 0) {
            single.displayDetails();
            System.out.println("Available: " + inventory.getAvailability("Single Room") + "\n");
        }
        if (inventory.getAvailability("Double Room") > 0) {
            doubleRoom.displayDetails();
            System.out.println("Available: " + inventory.getAvailability("Double Room") + "\n");
        }
        if (inventory.getAvailability("Suite Room") > 0) {
            suite.displayDetails();
            System.out.println("Available: " + inventory.getAvailability("Suite Room") + "\n");
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {
        RoomInventory4 inventory = new RoomInventory4();
        RoomSearchService4 searchService = new RoomSearchService4();
        searchService.searchRooms(inventory);
    }
}
