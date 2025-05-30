package GameManager;

import GameManager.Nutria.MainNutria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpgradeManagerTest {
    private MainNutria nutria;
    private UpgradeManager upgradeManager;

    @BeforeEach
    void setUp() {
        nutria = new MainNutria(100, 20);
        nutria.addResource(ResourceType.WOOD, 10);
        nutria.addResource(ResourceType.NUT, 10);
        upgradeManager = new UpgradeManager(nutria);
    }

    @Test
    void upgradeHealth_successful() {
        boolean result = upgradeManager.upgradeHealth();
        assertTrue(result);
        assertEquals(5, nutria.getResourceAmount(ResourceType.WOOD));
        assertEquals(5, nutria.getResourceAmount(ResourceType.NUT));
        assertEquals(110, nutria.getMaxHealth());
    }

    @Test
    void upgradeHealth_notEnoughResources() {
        nutria.removeResource(ResourceType.WOOD, 10); // now only 0 wood
        boolean result = upgradeManager.upgradeHealth();
        assertFalse(result);
        assertEquals(0, nutria.getResourceAmount(ResourceType.WOOD));
        assertEquals(10, nutria.getResourceAmount(ResourceType.NUT));
        assertEquals(100, nutria.getMaxHealth());
    }

    @Test
    void upgradeAttack_successful() {
        boolean result = upgradeManager.upgradeAttack();
        assertTrue(result);
        assertEquals(5, nutria.getResourceAmount(ResourceType.WOOD));
        assertEquals(5, nutria.getResourceAmount(ResourceType.NUT));
        assertEquals(25, nutria.getAttackPower());
    }

    @Test
    void upgradeAttack_notEnoughResources() {
        nutria.removeResource(ResourceType.NUT, 10); // now only 0 nuts
        boolean result = upgradeManager.upgradeAttack();
        assertFalse(result);
        assertEquals(10, nutria.getResourceAmount(ResourceType.WOOD));
        assertEquals(0, nutria.getResourceAmount(ResourceType.NUT));
        assertEquals(20, nutria.getAttackPower());
    }

    @Test
    void upgradeCostsIncreaseAfterSuccess() {
        upgradeManager.upgradeHealth();
        // Další upgrade by měl vyžadovat 7 WOOD a 7 NUT
        nutria.addResource(ResourceType.WOOD, 2);
        nutria.addResource(ResourceType.NUT, 2);
        boolean result = upgradeManager.upgradeHealth();
        assertTrue(result); // 7 WOOD a 7 NUT = 5+2

        assertEquals(110 + 10, nutria.getMaxHealth());
        assertEquals(0, nutria.getResourceAmount(ResourceType.WOOD));
        assertEquals(0, nutria.getResourceAmount(ResourceType.NUT));
    }
}
