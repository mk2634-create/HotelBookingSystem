import java.util.LinkedList;
import java.util.Queue;


// -------- RESERVATION CLASS --------
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Requested: " + roomType);
    }
}

// -------- BOOKING QUEUE --------
class BookingQueue {

    private Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    // Add request
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request Added: ");
        reservation.display();
    }

    // View all requests
    public void displayQueue() {
        System.out.println("\n=== Booking Request Queue ===\n");

        if (queue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation r : queue) {
            r.display();
        }
    }
}

// -------- MAIN CLASS --------
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        BookingQueue bookingQueue = new BookingQueue();

        // Add booking requests (FIFO order)
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        // Display queue
        bookingQueue.displayQueue();
    }
}
