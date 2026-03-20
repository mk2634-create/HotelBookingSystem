import java.util.*;

/**
 * Use Case 6: Reservation Confirmation & Room Allocation
 * Demonstrates Queue + HashMap + Set for safe booking.
 *
 * @author Muskan
 * @version 6.0
 */

// -------- RESERVATION --------
class Reservation6 {
    String guestName;
    String roomType;

    public Reservation6(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// -------- INVENTORY --------
class RoomInventory6 {

    private HashMap<String, Integer> inventory;

    public RoomInventory6() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void reduceAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

// -------- BOOKING SERVICE --------
class BookingService {

    private Queue<Reservation6> queue;
    private HashMap<String, Set<String>> allocatedRooms;

    public BookingService(Queue<Reservation6> queue) {
        this.queue = queue;
        this.allocatedRooms = new HashMap<>();
    }

    // Generate unique room ID
    private String generateRoomId(String roomType) {
        return roomType.substring(0, 2).toUpperCase() + "_" + UUID.randomUUID().toString().substring(0, 5);
    }

    public void processBookings(RoomInventory6 inventory) {

        System.out.println("=== Processing Bookings ===\n");

        while (!queue.isEmpty()) {

            Reservation6 request = queue.poll();
            String type = request.roomType;

            System.out.println("Processing: " + request.guestName + " -> " + type);

            if (inventory.getAvailability(type) > 0) {

                String roomId = generateRoomId(type);

                // Ensure uniqueness using Set
                allocatedRooms.putIfAbsent(type, new HashSet<>());
                allocatedRooms.get(type).add(roomId);

                // Update inventory
                inventory.reduceAvailability(type);

                System.out.println("Booking CONFIRMED | Room ID: " + roomId + "\n");

            } else {
                System.out.println("Booking FAILED | No rooms available\n");
            }
        }
    }

    // Display allocated rooms
    public void displayAllocations() {
        System.out.println("=== Allocated Rooms ===\n");

        for (String type : allocatedRooms.keySet()) {
            System.out.println(type + ": " + allocatedRooms.get(type));
        }
    }
}

// -------- MAIN CLASS --------
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        // Create queue
        Queue<Reservation6> queue = new LinkedList<>();

        queue.offer(new Reservation6("Alice", "Single Room"));
        queue.offer(new Reservation6("Bob", "Single Room"));
        queue.offer(new Reservation6("Charlie", "Single Room")); // should fail
        queue.offer(new Reservation6("David", "Suite Room"));

        // Create inventory
        RoomInventory6 inventory = new RoomInventory6();

        // Process bookings
        BookingService service = new BookingService(queue);
        service.processBookings(inventory);

        // Show allocations
        service.displayAllocations();
    }
}
