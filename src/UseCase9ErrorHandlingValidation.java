import java.util.*;

class InvalidRoomTypeException extends Exception {
    public InvalidRoomTypeException(String message) {
        super(message);
    }
}

class NoInventoryException extends Exception {
    public NoInventoryException(String message) {
        super(message);
    }
}

class RoomInventory9 {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory9() {
        inventory.put("SINGLE", 2);
        inventory.put("DOUBLE", 1);
    }

    public void validateRequest(String roomType) throws InvalidRoomTypeException, NoInventoryException {
        if (!inventory.containsKey(roomType.toUpperCase())) {
            throw new InvalidRoomTypeException("Error: Room type '" + roomType + "' is not supported.");
        }
        if (inventory.get(roomType.toUpperCase()) <= 0) {
            throw new NoInventoryException("Error: No availability for " + roomType);
        }
    }

    public void reduceInventory(String roomType) {
        String type = roomType.toUpperCase();
        inventory.put(type, inventory.get(type) - 1);
    }

    public int getCount(String roomType) {
        return inventory.getOrDefault(roomType.toUpperCase(), 0);
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        System.out.println("=== Use Case 9: Error Handling & Validation ===\n");

        RoomInventory9 inventory = new RoomInventory9();
        String[] requests = {"SINGLE", "PENTHOUSE", "SINGLE", "SINGLE"};

        for (String type : requests) {
            System.out.println("Processing request for: " + type);
            try {
                inventory.validateRequest(type);
                inventory.reduceInventory(type);
                System.out.println("Status: SUCCESS | Remaining: " + inventory.getCount(type));
            } catch (InvalidRoomTypeException | NoInventoryException e) {
                System.out.println("Status: FAILED | Reason: " + e.getMessage());
            }
            System.out.println();
        }

        System.out.println("System remains stable after handling multiple error scenarios.");
    }
}
