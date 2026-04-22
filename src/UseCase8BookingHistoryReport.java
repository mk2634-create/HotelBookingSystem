import java.util.*;

class Reservation8 {
    String guestName;
    String roomType;
    String roomId;

    public Reservation8(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + " | Room Type: " + roomType + " | Room ID: " + roomId;
    }
}

class BookingHistory {
    private List<Reservation8> history;

    public BookingHistory() {
        this.history = new ArrayList<>();
    }

    public void addBooking(Reservation8 reservation) {
        history.add(reservation);
    }

    public List<Reservation8> getHistory() {
        return history;
    }
}

class BookingReportService {
    public void generateReport(List<Reservation8> history) {
        System.out.println("=== Booking History Report ===");
        if (history.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + ". " + history.get(i));
            }
        }
        System.out.println("Total Bookings: " + history.size());
        System.out.println("==============================");
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        System.out.println("=== Use Case 8: Booking History & Reporting ===\n");

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        System.out.println("Recording confirmed bookings...");
        history.addBooking(new Reservation8("Alice", "Single Room", "SR_101"));
        history.addBooking(new Reservation8("Bob", "Double Room", "DR_202"));
        history.addBooking(new Reservation8("Charlie", "Suite Room", "SU_303"));

        System.out.println("\nGenerating reports...\n");
        reportService.generateReport(history.getHistory());
        
        System.out.println("\nAudit trail maintained in insertion order for operational visibility.");
    }
}
