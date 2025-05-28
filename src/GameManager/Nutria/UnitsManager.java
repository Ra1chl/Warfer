package GameManager.Nutria;

import GameManager.*;

import java.util.ArrayList;

public class UnitsManager {
    private MainNutria nutria;
    private ArrayList<Units> ownedUnits;

    private final int unitCost = 20;

    // Predefined units (melee and tank) with their respective bonuses
    private  final Units meleeUnit = new Units(UnitType.MELEE, 10);
    private  final Units tankUnit = new Units(UnitType.TANK, 20);

    // Constructor: initializes with a reference to the player (Nutria) and an empty list of owned units
    public UnitsManager(MainNutria nutria) {
        this.nutria = nutria;
        this.ownedUnits = new ArrayList<>();
    }

    // Returns the list of units owned by the player
    public ArrayList<Units> getOwnedUnits() {
        return ownedUnits;
    }

    // Buys a melee unit if the player has enough nuts.
    // Deducts the resource, adds the unit, and increases the player's attack power.
    public boolean buyMeleeUnit() {
        if(nutria.getResourceAmount(ResourceType.NUT) >= unitCost){
            nutria.removeResource(ResourceType.NUT, unitCost);
            ownedUnits.add(meleeUnit);
            nutria.increaseMaxAttackPower(meleeUnit.getBonus());
            return true;
        }
        return false;
    }

    // Buys a tank unit if the player has enough nuts.
    // Deducts the resource, adds the unit, and increases the player's max health.
    public boolean buyTankUnit() {
        if (nutria.getResourceAmount(ResourceType.NUT) >= unitCost){
            nutria.removeResource(ResourceType.NUT, unitCost);
            ownedUnits.add(tankUnit);
            nutria.increaseMaxHealth(tankUnit.getBonus());
            return true;
        }
        return false;
    }
}
