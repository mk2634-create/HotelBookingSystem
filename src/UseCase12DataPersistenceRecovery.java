import java.io.*;
import java.util.*;

class PersistenceService {
    private Map<String, Integer> inventory;

    public PersistenceService() {
        this.inventory = new HashMap<>();
        this.inventory.put("SINGLE", 5);
        this.inventory.put("DOUBLE", 3);
    }

    public void save(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this.inventory);
            System.out.println("System state serialized and saved to: " + filename);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void load(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            this.inventory = (Map<String, Integer>) ois.readObject();
            System.out.println("System state recovered from: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Recovery failed or file not found. Starting with default state.");
        }
    }

    public void display() {
        System.out.println("Current Inventory State: " + inventory);
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        System.out.println("=== Use Case 12: Data Persistence & Recovery ===\n");
        PersistenceService service = new PersistenceService();
        String file = "system_state.ser";

        service.display();
        service.save(file);

        System.out.println("\n--- Simulating System Restart ---\n");

        PersistenceService recoveredService = new PersistenceService();
        recoveredService.load(file);
        recoveredService.display();

        new File(file).delete();
        System.out.println("\nPersistence test completed.");
    }
}
