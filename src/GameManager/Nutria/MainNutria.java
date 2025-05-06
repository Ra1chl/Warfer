package GameManager.Nutria;

import GameManager.Inventory;
import GameManager.ResourceType;

public class MainNutria {
    private int health;
    private int maxHealth;         // Nové pole pro maximální životy
    private int attackPower;
    private int maxAttackPower;    // Nové pole pro maximální útok
    private int level;
    private Inventory inventory;    // Přidání inventáře

    public MainNutria(int maxHealth, int maxAttackPower) {
        this.maxHealth = maxHealth;
        this.health = maxHealth; // aktuální zdraví je na max
        this.maxAttackPower = maxAttackPower;
        this.attackPower = maxAttackPower; // aktuální útok je na max
        this.level = 1;
        this.inventory = new Inventory(); // Inicializace inventáře
    }

    // Metody pro manipulaci se surovinami
    public void addResource(ResourceType type, int amount) {
        inventory.addResource(type, amount);
    }

    public boolean removeResource(ResourceType type, int amount) {
        return inventory.removeResource(type, amount);
    }

    public int getResourceAmount(ResourceType type) {
        return inventory.getAmount(type);
    }

    // Nové metody pro získání max hodnot
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxAttackPower() {
        return maxAttackPower;
    }

    // Metody pro zvýšení max hodnot (pro upgrade)
    public void increaseMaxHealth(int amount) {
        this.maxHealth += amount;
        this.health = this.maxHealth; // při upgrade se obnoví na max životy
    }

    public void increaseMaxAttackPower(int amount) {
        this.maxAttackPower += amount;
        this.attackPower = this.maxAttackPower; // při upgrade se obnoví na max útok
    }

    // Metody pro aktuální životy a útok
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health > maxHealth) {
            this.health = maxHealth;
        } else {
            this.health = health;
        }
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        if (attackPower > maxAttackPower) {
            this.attackPower = maxAttackPower;
        } else {
            this.attackPower = attackPower;
        }
    }

    // Zbytek metod zůstává stejný (např. attack, receiveDamage, isAlive, atd.)

    public void attack(Enemy enemy) {
        enemy.takeDamage(attackPower);
        System.out.println("Main Nutria attacks for " + attackPower + " damage!");
    }

    public void receiveDamage(int damage) {
        health -= damage;
        System.out.println("Main Nutria received " + damage + " damage. Remaining health: " + health);
    }

    public void gainExperience(int xp) {
        // pokud nechcete XP, můžete tento kód odstranit nebo ignorovat
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
        System.out.println("Unit took " + amount + " damage. Health now: " + health);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return 0; // XP ignorujeme
    }
}
