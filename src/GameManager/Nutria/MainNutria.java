package GameManager.Nutria;

public class MainNutria {
    private int health;            // Kolik toho vydrží
    private int attackPower;       // Kolik dá poškození
    private int level;             // Level nutrie
    private int experience;        // XP pro levelování

    public MainNutria(int health, int attackPower) {
        this.health = health;
        this.attackPower = attackPower;
        this.level = 1;
        this.experience = 0;

    }

    public void attack(Enemy enemy) {
        enemy.takeDamage(attackPower);
        System.out.println("Main Nutria attacks for " + attackPower + " damage!");
    }

    public void receiveDamage(int damage) {
        health -= damage;
        System.out.println("Main Nutria received " + damage + " damage. Remaining health: " + health);
    }

    public void gainExperience(int xp) {
        experience += xp;
        System.out.println("Main Nutria gained " + xp + " XP.");
        checkLevelUp();
    }

    private void checkLevelUp() {
        int xpToLevel = 100 * level;
        if (experience >= xpToLevel) {
            experience -= xpToLevel;
            level++;
            health += 10;
            attackPower += 5;
            System.out.println("Main Nutria leveled up to level " + level + "!");
        }
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
        System.out.println("Unit took " + amount + " damage. Health now: " + health);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }
}
