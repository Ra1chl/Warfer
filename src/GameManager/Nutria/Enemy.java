package GameManager.Nutria;

public class Enemy {
    private String type;           // Typ nepřítele (např. Fox, Rat, Boss)
    private int health;            // Kolik toho vydrží
    private int attackPower;       // Kolik dá poškození
    private int rewardXP;          // Kolik XP dostane hráč za zabití
    private int rewardCoins;       // Kolik peněz dostane hráč za zabití

    public Enemy(String type, int health, int attackPower, int rewardXP, int rewardCoins) {
        this.type = type;
        this.health = health;
        this.attackPower = attackPower;
        this.rewardXP = rewardXP;
        this.rewardCoins = rewardCoins;
    }

    public void attack(MainNutria nutria) {
        nutria.receiveDamage(attackPower);
        System.out.println("Enemy " + type + " attacks for " + attackPower + " damage!");
    }

    public void receiveDamage(int damage) {
        health -= damage;
        System.out.println("Enemy " + type + " received " + damage + " damage. Remaining health: " + health);
    }

    public void dropLoot(MainNutria nutria) {
        if (health <= 0) {
            // Pokud je nepřítel mrtvý, hráč dostane odměnu
            nutria.gainExperience(rewardXP);
            // Pro přidání peněz nebo nějakého jiného lootu bys mohl mít podobné metody
            System.out.println("Enemy " + type + " dropped " + rewardCoins + " coins.");
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getRewardXP() {
        return rewardXP;
    }

    public int getRewardCoins() {
        return rewardCoins;
    }
}
