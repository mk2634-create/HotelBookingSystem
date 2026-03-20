/**
 * Use Case 2: Basic Room Types & Static Availability
 * Demonstrates abstraction, inheritance, and basic object modeling.
 *
 * @author Muskan
 * @version 2.0
 */

// Abstract Class
abstract class Room {
    int beds;
    int size;
    double price;

    // Constructor
    Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    // Method to display details
    abstract void displayDetails();
}

// Single Room
class SingleRoom extends Room {

    SingleRoom() {
        super(1, 250, 1500.0);
    }

    void displayDetails() {
        System.out.println("Single Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
    }
}

// Double Room
class DoubleRoom extends Room {

    DoubleRoom() {
        super(2, 400, 2500.0);
    }

    void displayDetails() {
        System.out.println("\nDouble Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
    }
}

// Suite Room
class SuiteRoom extends Room {

    SuiteRoom() {
        super(3, 750, 5000.0);
    }

    void displayDetails() {
        System.out.println("\nSuite Room:");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
    }
}

// Main Class
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("=== Hotel Room Initialization ===\n");

        // Create objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display details
        single.displayDetails();
        System.out.println("Available: " + singleAvailable);

        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable);

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}
