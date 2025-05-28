package GameManager;

import GameManager.Nutria.*;
import java.util.ArrayList;

public class CombatManager {
    private MainNutria playerUnit;
    private ArrayList<Enemy> enemyList;
    private int currentEnemyIndex;
    private Enemy currentEnemy;

    /**
     * Constructs a CombatManager with the player unit and a list of enemies to fight.
     *
     * @param playerUnit the player's character
     * @param enemyList the list of enemies in the current battle sequence
     */
    public CombatManager(MainNutria playerUnit, ArrayList<Enemy> enemyList) {
        this.playerUnit = playerUnit;
        this.enemyList = enemyList;
        this.currentEnemyIndex = 0;
        this.currentEnemy = enemyList.get(currentEnemyIndex);
    }
    /**
     * Performs an attack from the player to the current enemy.
     *
     * @return a message describing the damage dealt
     */
    public String playerAttack() {
        int damage = playerUnit.getAttackPower();
        currentEnemy.takeDamage(damage);
        return "Nutrie zaútočila a dala " + damage + " dmg!";
    }
    /**
     * Performs an attack from the current enemy to the player.
     * If the enemy is already dead, nothing happens.
     *
     * @return a message describing the damage dealt, or empty string if enemy is dead
     */
    public String enemyAttack() {
        if (currentEnemy.getHealth() <= 0) return "";
        int damage = currentEnemy.getAttackPower();
        playerUnit.takeDamage(damage);
        return "Nepřítel zaútočil a dal " + damage + " dmg!";
    }
    /**
     * Checks if the battle with the current enemy is over.
     *
     * @return true if either the player or enemy is dead
     */
    public boolean isBattleOver() {
        return playerUnit.getHealth() <= 0 || currentEnemy.getHealth() <= 0;
    }

    /**
     * Checks if there are more enemies left after the current one.
     *
     * @return true if there are more enemies to fight
     */
    public boolean hasMoreEnemies() {
        return currentEnemyIndex < enemyList.size() - 1;
    }
    /**
     * Loads the next enemy in the list if available.
     * Increments the current enemy index and updates the current enemy reference.
     */
    public void loadNextEnemy() {
        currentEnemyIndex++;
        if (currentEnemyIndex < enemyList.size()) {
            currentEnemy = enemyList.get(currentEnemyIndex);
        }
    }

    /**
     * Checks if the player has been defeated.
     *
     * @return true if the player's health is zero or below
     */
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

    public void setPlayerUnit(MainNutria playerUnit) {
        this.playerUnit = playerUnit;
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(ArrayList<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

    public int getCurrentEnemyIndex() {
        return currentEnemyIndex;
    }

    public void setCurrentEnemyIndex(int currentEnemyIndex) {
        this.currentEnemyIndex = currentEnemyIndex;
    }

    public void setCurrentEnemy(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
    }
}