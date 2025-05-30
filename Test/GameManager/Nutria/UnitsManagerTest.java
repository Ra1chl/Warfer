package GameManager.Nutria;

import GameManager.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UnitsManagerTest {
    private MainNutria nutria;
    private UnitsManager unitsManager;

    @BeforeEach
    void setUp() {
        nutria = new MainNutria(100, 20);
        nutria.addResource(ResourceType.NUT, 40); // Dostatek na 2 jednotky
        unitsManager = new UnitsManager(nutria);
    }

    @Test
    void buyMeleeUnit_successful() {
        boolean result = unitsManager.buyMeleeUnit();
        assertTrue(result);
        assertEquals(20, nutria.getResourceAmount(ResourceType.NUT));
        assertEquals(30, nutria.getAttackPower()); // původně 20 + 10 bonus
        assertEquals(1, unitsManager.getOwnedUnits().size());
        assertEquals(UnitType.MELEE, unitsManager.getOwnedUnits().get(0).getType());
    }

    @Test
    void buyTankUnit_successful() {
        boolean result = unitsManager.buyTankUnit();
        assertTrue(result);
        assertEquals(20, nutria.getResourceAmount(ResourceType.NUT)); // 40 - 20
        assertEquals(120, nutria.getMaxHealth()); // původně 100 + 20 bonus
        assertEquals(1, unitsManager.getOwnedUnits().size());
        assertEquals(UnitType.TANK, unitsManager.getOwnedUnits().get(0).getType());
    }

    @Test
    void buyMeleeUnit_notEnoughNuts() {
        nutria.removeResource(ResourceType.NUT, 40); // 0 nuts
        boolean result = unitsManager.buyMeleeUnit();
        assertFalse(result);
        assertEquals(0, nutria.getResourceAmount(ResourceType.NUT));
        assertEquals(0, unitsManager.getOwnedUnits().size());
    }

    @Test
    void buyTankUnit_notEnoughNuts() {
        nutria.removeResource(ResourceType.NUT, 40); // 0 nuts
        boolean result = unitsManager.buyTankUnit();
        assertFalse(result);
        assertEquals(0, nutria.getResourceAmount(ResourceType.NUT));
        assertEquals(0, unitsManager.getOwnedUnits().size());
    }
}
