package GameManager.Nutria;

public class Enemy {
    private String type;
    private int maxHealth;
    private int health;
    private int attackPower;
    private int reward;

    private String obrazek;

    public Enemy(String type, int health, int attackPower, int reward, String obrazek) {
        this.type = type;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
        this.reward = reward;
        this.obrazek = obrazek;
    }
    /**
     * Reduces the Nutria's current health by the specified damage amount.
     * If the resulting health is less than 0, it is set to 0.
     *
     * @param damage The amount of damage to apply.
     */
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }


    public void setType(String type) {
        this.type = type;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void setObrazek(String obrazek) {
        this.obrazek = obrazek;
    }

    public void reset() {
        this.health = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public String getType() {
        return type;
    }

    public String getObrazek() {
        return obrazek;
    }

    public int getReward() {
        return reward;
    }
}
