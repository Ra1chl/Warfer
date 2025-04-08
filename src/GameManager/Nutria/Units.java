package GameManager.Nutria;

public abstract class Units {
    protected int attackPower;       // Kolik dá poškození
    protected int level;             // Level jednotky

    public Units(int attackPower, int level) {
        this.attackPower = attackPower;
        this.level = level;
    }

    // Abstraktní metoda pro útok
    public abstract void attack();

    // Metoda pro levelování
    public void levelUp() {
        level++;
        attackPower += 5;
        System.out.println(this.getClass().getSimpleName() + " leveled up to level " + level);
    }

    // Getter pro základní atributy
    public int getAttackPower() {
        return attackPower;
    }

    public int getLevel() {
        return level;
    }
}
