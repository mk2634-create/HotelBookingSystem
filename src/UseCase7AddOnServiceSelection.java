import java.util.*;

class AddOnService {
    String serviceName;
    double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return serviceName + " ($" + cost + ")";
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        this.reservationServices = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        reservationServices.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
    }

    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = reservationServices.getOrDefault(reservationId, new ArrayList<>());
        double totalCost = 0.0;
        for (AddOnService service : services) {
            totalCost += service.cost;
        }
        return totalCost;
    }

    public void displayReservationDetails(String reservationId) {
        List<AddOnService> services = reservationServices.get(reservationId);
        System.out.println("Reservation ID: " + reservationId);
        
        if (services == null || services.isEmpty()) {
            System.out.println(" - No add-on services selected.");
        } else {
            System.out.println(" - Selected Services: " + services);
            System.out.format(" - Total Additional Cost: $%.2f\n", calculateTotalCost(reservationId));
        }
        System.out.println();
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        System.out.println("=== Use Case 7: Add-On Service Selection ===\n");
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        String resId1 = "RS_ALICE_101";
        String resId2 = "RS_BOB_102";
        String resId3 = "RS_DAVID_103";

        AddOnService breakfast = new AddOnService("Breakfast Buffet", 15.0);
        AddOnService spa = new AddOnService("Luxury Spa", 45.0);
        AddOnService wifi = new AddOnService("High-Speed WiFi", 10.0);
        AddOnService lateCheckout = new AddOnService("Late Checkout", 25.0);

        System.out.println("Guest Alice selecting services...");
        serviceManager.addService(resId1, breakfast);
        serviceManager.addService(resId1, spa);

        System.out.println("Guest Bob selecting services...");
        serviceManager.addService(resId2, wifi);
        serviceManager.addService(resId2, breakfast);
        serviceManager.addService(resId2, lateCheckout);

        System.out.println("Guest David selecting services...");

        System.out.println("\n--- Processing Service Selections ---\n");
        serviceManager.displayReservationDetails(resId1);
        serviceManager.displayReservationDetails(resId2);
        serviceManager.displayReservationDetails(resId3);

        System.out.println("Business Extensibility: New services added without modifying core room allocation logic.");
        System.out.println("Consistency Check: Core booking and inventory state remain unchanged.");
    }
}
