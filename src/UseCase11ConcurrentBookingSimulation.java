import java.util.*;

class InventoryManager11 {
    private int availableRooms = 2;

    public synchronized void allocateRoom(String guestName) {
        if (availableRooms > 0) {
            System.out.println("Processing: " + guestName + " | Rooms left before: " + availableRooms);
            availableRooms--;
            System.out.println("Status: SUCCESS for " + guestName + " | Rooms left after: " + availableRooms);
        } else {
            System.out.println("Status: FAILED for " + guestName + " | No rooms available.");
        }
    }
}

class BookingThread extends Thread {
    private InventoryManager11 manager;
    private String guestName;

    public BookingThread(InventoryManager11 manager, String guestName) {
        this.manager = manager;
        this.guestName = guestName;
    }

    @Override
    public void run() {
        manager.allocateRoom(guestName);
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {
        System.out.println("=== Use Case 11: Concurrent Booking Simulation ===\n");
        InventoryManager11 manager = new InventoryManager11();

        Thread t1 = new BookingThread(manager, "Alice");
        Thread t2 = new BookingThread(manager, "Bob");
        Thread t3 = new BookingThread(manager, "Charlie");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {}

        System.out.println("\nFinal system state maintained correctly under concurrent load.");
    }
}
