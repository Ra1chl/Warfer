package GameManager;

import GameManager.Nutria.*;
import GameManager.ResourceType;

public class UpgradeManager {
    private MainNutria nutria;
    private int upgradeCostWood = 5;
    private int upgradeCostChestnut = 5;

    public UpgradeManager(MainNutria nutria) {
        this.nutria = nutria;
    }

    public int getUpgradeCostWood() {
        return upgradeCostWood;
    }

    public int getUpgradeCostChestnut() {
        return upgradeCostChestnut;
    }

    public boolean upgradeHealth() {
        if (nutria.getResourceAmount(ResourceType.WOOD) >= upgradeCostWood &&
                nutria.getResourceAmount(ResourceType.NUT) >= upgradeCostChestnut) {
            nutria.removeResource(ResourceType.WOOD, upgradeCostWood);
            nutria.removeResource(ResourceType.NUT, upgradeCostChestnut);
            nutria.increaseMaxHealth(10);
            upgradeCostWood += 2;
            upgradeCostChestnut += 2;
            return true;
        }
        return false;
    }

    public boolean upgradeAttack() {
        if (nutria.getResourceAmount(ResourceType.WOOD) >= upgradeCostWood &&
                nutria.getResourceAmount(ResourceType.NUT) >= upgradeCostChestnut) {
            nutria.removeResource(ResourceType.WOOD, upgradeCostWood);
            nutria.removeResource(ResourceType.NUT, upgradeCostChestnut);
            nutria.increaseMaxAttackPower(5);
            upgradeCostWood += 2;
            upgradeCostChestnut += 2;
            return true;
        }
        return false;
    }
}
