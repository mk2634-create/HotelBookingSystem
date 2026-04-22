import java.util.*;

class Reservation10 {
    String id;
    String type;
    String roomId;

    public Reservation10(String id, String type, String roomId) {
        this.id = id;
        this.type = type;
        this.roomId = roomId;
    }
}

class CancellationService {
    private Map<String, Reservation10> bookings = new HashMap<>();
    private Map<String, Integer> inventory = new HashMap<>();
    private Stack<String> releasedRooms = new Stack<>();

    public CancellationService() {
        inventory.put("Double Room", 1);
        bookings.put("RES101", new Reservation10("RES101", "Double Room", "DR-777"));
        inventory.put("Double Room", 0);
    }

    public void cancelBooking(String resId) {
        System.out.println("Initiating cancellation for: " + resId);
        
        if (!bookings.containsKey(resId)) {
            System.out.println("Error: Reservation " + resId + " not found.");
            return;
        }

        Reservation10 res = bookings.remove(resId);
        releasedRooms.push(res.roomId);
        inventory.put(res.type, inventory.get(res.type) + 1);

        System.out.println("SUCCESS: Reservation cancelled.");
        System.out.println("Room " + res.roomId + " returned to pool.");
        System.out.println("New Inventory for " + res.type + ": " + inventory.get(res.type));
    }

    public void showRollbackStack() {
        System.out.println("\nReleased Rooms Stack (LIFO): " + releasedRooms);
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        System.out.println("=== Use Case 10: Booking Cancellation & Rollback ===\n");

        CancellationService service = new CancellationService();

        service.cancelBooking("RES101");
        service.cancelBooking("RES999");

        service.showRollbackStack();
        
        System.out.println("\nSystem state restored consistently using LIFO rollback logic.");
    }
}
