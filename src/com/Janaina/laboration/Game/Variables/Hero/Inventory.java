package com.Janaina.laboration.Game.Variables.Hero;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Resources.Scanners;
import static com.Janaina.laboration.Resources.Colors.*;

public class Inventory {

    public void playerInventory(Player player, Scanners sc, DBConnection db) {
        boolean isPlaying = true;

        while (isPlaying) {
            System.out.println(BLACK_BACKGROUND + PURPLE_LIGHT + BOLD + "Inventory: " + RESET +
                    PURPLE_LIGHT + "\n1. Weapons\n2. Potions\n0. Go Back" + RESET);

            switch (sc.scannerNumber()) {
                case 1 -> displayItemsInWeaponsInventory(player, sc, db);
                case 2 -> displayItemsInPotionsInventory(player, sc, db);
                case 0 -> isPlaying = false;
                default ->
                        System.out.println(RED_DARK + "Invalid input, please chose from the options presented" + RESET);

            }

        }
    }


    public void displayItemsInWeaponsInventory(Player player, Scanners sc, DBConnection db) {
        db.weaponsInventory(player, sc);
        sc.pressEnter();

    }


    public void displayItemsInPotionsInventory(Player player, Scanners sc, DBConnection db) {
        db.potionsInventory(sc, player);
        sc.pressEnter();

    }


}

