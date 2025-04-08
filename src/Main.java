import java.util.Scanner;
import java.util.Random;

import GameManager.*;
import GameManager.Nutria.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Inventory inv = new Inventory();
        inv.addResource(ResourceType.CARROT, 10);
        inv.addResource(ResourceType.IRON, 500);

        System.out.println("Using 3 CARROT: " + inv.removeResource(ResourceType.CARROT, 3));


        System.out.println("Trying to use 100 IRON: " + inv.removeResource(ResourceType.IRON, 100));

        MainNutria mainNutria = new MainNutria(100, 25);
        Enemy enemy = new Enemy("Fox", 50, 15, 50, 10);

        while (mainNutria.isAlive() && enemy.isAlive()) {
            mainNutria.attack(enemy);  // Nutria útočí
            if (enemy.isAlive()) {
                enemy.attack(mainNutria);  // Enemy útočí
            }
        }

        if (!enemy.isAlive()) {
            enemy.dropLoot(mainNutria);  // Pokud nepřítel umře, hráč dostane odměnu
        }

    }
}


