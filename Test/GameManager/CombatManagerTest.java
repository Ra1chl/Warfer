package GameManager;

import GameManager.Nutria.Enemy;
import GameManager.Nutria.MainNutria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CombatManagerTest {

    private MainNutria testPlayer;
    private Enemy testEnemy1;
    private Enemy testEnemy2;
    private ArrayList<Enemy> enemyList;
    private CombatManager combatManager;

    @BeforeEach
    void setUp() {
        testPlayer = new MainNutria(100, 20);
        testEnemy1 = new Enemy("Vlkač", 30, 10,1,"test.png");
        testEnemy2 = new Enemy("Medvěd", 40, 15,1,"test.png");

        enemyList = new ArrayList<>();
        enemyList.add(testEnemy1);
        enemyList.add(testEnemy2);

        combatManager = new CombatManager(testPlayer, enemyList);
    }

    @Test
    void playerAttack() {
        combatManager.playerAttack();
        assertEquals(10, testEnemy1.getHealth()); // 30 - 20
    }

    @Test
    void enemyAttack() {
        combatManager.enemyAttack();
        assertEquals(90, testPlayer.getHealth()); // 100 - 10
    }

    @Test
    void isBattleOver_whenPlayerDead() {
        testPlayer.takeDamage(100); // kill player
        assertTrue(combatManager.isBattleOver());
    }

    @Test
    void isBattleOver_whenEnemyDead() {
        testEnemy1.takeDamage(30); // kill enemy
        assertTrue(combatManager.isBattleOver());
    }

    @Test
    void isBattleOver() {
        assertTrue(combatManager.hasMoreEnemies());
    }

    @Test
    void hasMoreEnemies() {
        assertTrue(combatManager.hasMoreEnemies());
    }

    @Test
    void loadNextEnemy_false() {
        combatManager.loadNextEnemy(); // switch to second enemy
        assertFalse(combatManager.hasMoreEnemies());
    }

    @Test
    void loadNextEnemy() {
        combatManager.loadNextEnemy();
        assertEquals(testEnemy2, combatManager.getCurrentEnemy());
    }

    @Test
    void isPlayerDead() {
        testPlayer.takeDamage(100);
        assertTrue(combatManager.isPlayerDead());
    }

    @Test
    void isAllEnemiesDefeated() {
        testEnemy1.takeDamage(100);
        combatManager.loadNextEnemy();

        testEnemy2.takeDamage(100);
        assertTrue(combatManager.isAllEnemiesDefeated());
    }
}