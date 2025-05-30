package GameManager.Nutria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    @Test
    void takeDamage() {
        Enemy enemy = new Enemy("Bear", 100, 20,1,"bear.png"); // jméno, životy, útok
        enemy.takeDamage(30); // rána za 30
        assertEquals(70, enemy.getHealth());
    }
}