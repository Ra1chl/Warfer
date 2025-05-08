package GameManager.Nutria;

import GameManager.*;

import java.util.ArrayList;

public class UnitsManager {
    private MainNutria nutria;
    private ArrayList<Units> ownedUnits;

    private final int unitCost = 20;

    private  final Units meleeUnit = new Units(UnitType.MELEE, 10);
    private  final Units tankUnit = new Units(UnitType.TANK, 20);

    public UnitsManager(MainNutria nutria) {
        this.nutria = nutria;
        this.ownedUnits = new ArrayList<>();
    }

    public ArrayList<Units> getOwnedUnits() {
        return ownedUnits;
    }

    public boolean buyMeleeUnit() {
        if(nutria.getResourceAmount(ResourceType.NUT) >= unitCost){
            nutria.removeResource(ResourceType.NUT, unitCost);
            ownedUnits.add(meleeUnit);
            nutria.increaseMaxAttackPower(meleeUnit.getBonus());
            return true;
        }
        return false;
    }

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
