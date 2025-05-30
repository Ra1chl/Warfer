package GameManager.Nutria;

import GameManager.Inventory;
import GameManager.ResourceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainNutriaTest {

    @Test
    void addResource() {
        MainNutria nutria = new MainNutria(100, 20);
        nutria.addResource(ResourceType.WOOD, 10);
        assertEquals(10, nutria.getResourceAmount(ResourceType.WOOD));
    }

    @Test
    void removeResource() {
        MainNutria nutria = new MainNutria(100, 20);
        nutria.addResource(ResourceType.WOOD, 10);
        boolean removed = nutria.removeResource(ResourceType.WOOD, 5);
        assertTrue(removed);
        assertEquals(5, nutria.getResourceAmount(ResourceType.WOOD));
    }

    @Test
    void increaseMaxHealth() {
        MainNutria nutria = new MainNutria(100, 20);
        nutria.increaseMaxHealth(50);
        assertEquals(150, nutria.getMaxHealth());
        assertEquals(150, nutria.getHealth());
    }

    @Test
    void increaseMaxAttackPower() {
        MainNutria nutria = new MainNutria(100, 20);
        nutria.increaseMaxAttackPower(10);
        assertEquals(30, nutria.getMaxAttackPower());
        assertEquals(30, nutria.getAttackPower());
    }
}