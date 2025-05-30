package GameManager.Nutria;

public class Units {
    private UnitType unitType;
    private int bonus;

    public Units(UnitType unitType, int damage) {
        this.unitType = unitType;
        this.bonus = damage;
    }


    public UnitType getUnitType() {
        return unitType;
    }

    public int getBonus() {
        return bonus;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public UnitType getType() {
        return UnitType.valueOf(unitType.name());
    }

}
