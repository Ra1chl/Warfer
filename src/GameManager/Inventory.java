package GameManager;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<ResourceType, Integer> resources;

    /**
     * Constructor for the Inventory class.
     *
     * Initializes a HashMap with all resource types, each set to zero.
     */
    public Inventory() {
        resources = new HashMap<ResourceType, Integer>();

        // Initialize with zero values
        for (ResourceType type : ResourceType.values()) {
            resources.put(type, 0);
        }
    }

    /**
     * Adds a specified amount of a given resource type to the inventory.
     *
     * @param type   The type of resource to add.
     * @param amount The amount of resource to add.
     */
    public void addResource(ResourceType type, Integer amount) {
        resources.put(type, resources.get(type) + amount);
    }

    /**
     * Removes a specified amount of a given resource type from the inventory.
     *
     * @param type   The type of resource to remove.
     * @param amount The amount of resource to remove.
     * @return true if the resource was successfully removed, false otherwise.
     */
    public boolean removeResource(ResourceType type, Integer amount) {
        if (resources.containsKey(type)) {

        int current = resources.get(type);

            if (current >= amount) {
                resources.put(type, current-amount);
                return true;
            }
        }
        return false;
    }

    public int getAmount(ResourceType type) {
        return resources.get(type);
    }


}
