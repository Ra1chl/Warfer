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

    /**
     * Upgrades the maximum health of the nutria if there are enough resources.
     * Consumes a set amount of wood and chestnuts, increases max health,
     * and raises the upgrade cost for the next upgrade.
     *
     * @return true if the upgrade was successful, false otherwise
     */
    public boolean upgradeHealth() {
        if (nutria.getResourceAmount(ResourceType.WOOD) >= upgradeCostWood && nutria.getResourceAmount(ResourceType.NUT) >= upgradeCostChestnut) {
            nutria.removeResource(ResourceType.WOOD, upgradeCostWood);
            nutria.removeResource(ResourceType.NUT, upgradeCostChestnut);
            nutria.increaseMaxHealth(10);
            upgradeCostWood += 2;
            upgradeCostChestnut += 2;
            return true;
        }
        return false;
    }

    /**
     * Upgrades the maximum attack power of the nutria if there are enough resources.
     * Consumes a set amount of wood and chestnuts, increases attack power,
     * and raises the upgrade cost for the next upgrade.
     *
     * @return true if the upgrade was successful, false otherwise
     */
    public boolean upgradeAttack() {
        if (nutria.getResourceAmount(ResourceType.WOOD) >= upgradeCostWood && nutria.getResourceAmount(ResourceType.NUT) >= upgradeCostChestnut) {
            nutria.removeResource(ResourceType.WOOD, upgradeCostWood);
            nutria.removeResource(ResourceType.NUT, upgradeCostChestnut);
            nutria.increaseMaxAttackPower(5);
            upgradeCostWood += 2;
            upgradeCostChestnut += 2;
            return true;
        }
        return false;
    }


    public int getUpgradeCostWood() {
        return upgradeCostWood;
    }

    public int getUpgradeCostChestnut() {
        return upgradeCostChestnut;
    }
}
