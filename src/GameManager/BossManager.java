
package GameManager;

import GameManager.Nutria.*;

public class BossManager {
    private MainNutria playerUnit;
    private Enemy boss;

    public BossManager(MainNutria playerUnit, Enemy boss) {
        this.playerUnit = playerUnit;
        this.boss = boss;
    }

    public String playerAttack() {
        int damage = playerUnit.getAttackPower();
        boss.takeDamage(damage);
        return "Nutrie zaútočila a dala " + damage + " dmg!";
    }

    public String enemyAttack() {
        if (boss.getHealth() <= 0) return "";
        int damage = boss.getAttackPower();
        playerUnit.takeDamage(damage);
        return "Boss zaútočil a dal " + damage + " dmg!";
    }

    public boolean isBattleOver() {
        return playerUnit.getHealth() <= 0 || boss.getHealth() <= 0;
    }

    public boolean isPlayerDead() {
        return playerUnit.getHealth() <= 0;
    }

    public Enemy getBoss() {
        return boss;
    }
}


