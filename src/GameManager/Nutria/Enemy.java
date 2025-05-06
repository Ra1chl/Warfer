package GameManager.Nutria;

public class Enemy {
    private String type;
    private int maxHealth;
    private int health;
    private int attackPower;
    private int reward;
    private int difficulty;
    private String obrazek;

    public Enemy(String type, int health, int attackPower, int reward, int difficulty,String obrazek) {
        this.type = type;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
        this.reward = reward;
        this.difficulty = difficulty;
        this.obrazek = obrazek;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
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

    public int getDifficulty() {
        return difficulty;
    }
}
