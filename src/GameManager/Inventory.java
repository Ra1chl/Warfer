package GameManager;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<ResourceType, Integer> resources;

    public Inventory() {
        resources = new HashMap<ResourceType, Integer>();

            // Inicializace s nulovými hodnotami
        for (ResourceType type : ResourceType.values()) {
            resources.put(type, 0);
        }
    }

    public void addResource(ResourceType type, Integer amount) {
        resources.put(type, resources.get(type) + amount);
    }

    public boolean removeResource(ResourceType type, Integer amount) {
        int current = resources.get(type);

        if (resources.containsKey(type)) {
            if (current > amount) {
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
