package GameManager;

import GameManager.Nutria.*;
import java.util.ArrayList;

public class CombatManager {
    private MainNutria playerUnit;
    private ArrayList<Enemy> enemyList;
    private int currentEnemyIndex;
    private Enemy currentEnemy;

    public CombatManager(MainNutria playerUnit, ArrayList<Enemy> enemyList) {
        this.playerUnit = playerUnit;
        this.enemyList = enemyList;
        this.currentEnemyIndex = 0;
        this.currentEnemy = enemyList.get(currentEnemyIndex);
    }

    public String playerAttack() {
        int damage = playerUnit.getAttackPower();
        currentEnemy.takeDamage(damage);
        return "Nutrie zaútočila a dala " + damage + " dmg!";
    }

    public String enemyAttack() {
        if (currentEnemy.getHealth() <= 0) return "";
        int damage = currentEnemy.getAttackPower();
        playerUnit.takeDamage(damage);
        return "Nepřítel zaútočil a dal " + damage + " dmg!";
    }

    public boolean isBattleOver() {
        return playerUnit.getHealth() <= 0 || currentEnemy.getHealth() <= 0;
    }

    // Přidávám správnou definici metody hasMoreEnemies()
    public boolean hasMoreEnemies() {
        return currentEnemyIndex < enemyList.size() - 1;
    }

    public void loadNextEnemy() {
        currentEnemyIndex++;
        if (currentEnemyIndex < enemyList.size()) {
            currentEnemy = enemyList.get(currentEnemyIndex);
        }
    }

    public void resetBattle() {
        this.playerUnit = new MainNutria(100, 10);
        this.currentEnemyIndex = 0;
        for (Enemy e : enemyList) {
            e.reset();
        }
        this.currentEnemy = enemyList.get(currentEnemyIndex);
    }

    public boolean isPlayerDead() {
        return playerUnit.getHealth() <= 0;
    }

    public boolean isAllEnemiesDefeated() {
        return currentEnemyIndex >= enemyList.size() - 1 && currentEnemy.getHealth() <= 0;
    }

    public MainNutria getPlayerUnit() {
        return playerUnit;
    }

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }
}