package GameManager.Nutria;

import GameManager.Inventory;
import GameManager.ResourceType;

public class MainNutria {
    private int health;
    private int maxHealth;
    private int attackPower;
    private int maxAttackPower;
    private int level;
    private Inventory inventory;
    /**
     * Constructor for MainNutria.
     * Initializes the Nutria with maximum health and attack power,
     * sets current health and attack power to their maximum values,
     * initializes the level to 1 and creates a new inventory.
     *
     * @param maxHealth Maximum health of the Nutria.
     * @param maxAttackPower Maximum attack power of the Nutria.
     */
    public MainNutria(int maxHealth, int maxAttackPower) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.maxAttackPower = maxAttackPower;
        this.attackPower = maxAttackPower;
        this.level = 1;
        this.inventory = new Inventory();
    }

    /**
     * Adds the specified amount of a resource type to the Nutria's inventory.
     *
     * @param type The type of resource to add.
     * @param amount The amount of resource to add.
     */
    public void addResource(ResourceType type, int amount) {
        inventory.addResource(type, amount);
    }
    /**
     * Attempts to remove the specified amount of a resource type from the Nutria's inventory.
     *
     * @param type The type of resource to remove.
     * @param amount The amount of resource to remove.
     * @return true if removal was successful, false otherwise.
     */
    public boolean removeResource(ResourceType type, int amount) {
        return inventory.removeResource(type, amount);
    }
    /**
     * Gets the current amount of the specified resource type in the inventory.
     *
     * @param type The resource type.
     * @return The amount of the resource currently held.
     */
    public int getResourceAmount(ResourceType type) {
        return inventory.getAmount(type);
    }

    /**
     * Returns the maximum health of the Nutria.
     *
     * @return Maximum health.
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    /**
     * Returns the maximum attack power of the Nutria.
     *
     * @return Maximum attack power.
     */
    public int getMaxAttackPower() {
        return maxAttackPower;
    }

    /**
     * Increases the maximum health by a given amount.
     * Also sets current health to the new maximum.
     *
     * @param amount Amount to increase max health by.
     */
    public void increaseMaxHealth(int amount) {
        this.maxHealth += amount;
        this.health = this.maxHealth; // restore health to max after upgrade
    }
    /**
     * Increases the maximum attack power by a given amount.
     * Also sets current attack power to the new maximum.
     *
     * @param amount Amount to increase max attack power by.
     */
    public void increaseMaxAttackPower(int amount) {
        this.maxAttackPower += amount;
        this.attackPower = this.maxAttackPower; // při upgrade se obnoví na max útok
    }
    /**
     * Gets the current health.
     *
     * @return Current health.
     */
    public int getHealth() {
        return health;
    }
    /**
     * Sets the current health, but caps it at maxHealth.
     *
     * @param health New health value.
     */
    public void setHealth(int health) {
        if (health > maxHealth) {
            this.health = maxHealth;
        } else {
            this.health = health;
        }
    }
    /**
     * Gets the current attack power.
     *
     * @return Current attack power.
     */
    public int getAttackPower() {
        return attackPower;
    }
    /**
     * Sets the current attack power, but caps it at maxAttackPower.
     *
     * @param attackPower New attack power value.
     */
    public void setAttackPower(int attackPower) {
        if (attackPower > maxAttackPower) {
            this.attackPower = maxAttackPower;
        } else {
            this.attackPower = attackPower;
        }
    }


    public void gainExperience(int xp) {

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

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setMaxAttackPower(int maxAttackPower) {
        this.maxAttackPower = maxAttackPower;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
