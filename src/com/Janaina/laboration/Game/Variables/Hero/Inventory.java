package com.Janaina.laboration.Game.Variables.Hero;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Resources.Scanners;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.Janaina.laboration.Resources.Colors.*;

public class Inventory {
    public List<ShopProducts> weaponsList;
    public List<ShopProducts> potionsList;


    public Inventory() {
        weaponsList = new ArrayList<>();
        potionsList = new ArrayList<>();
    }

    public void playerInventory(Player player, Scanners sc, DBConnection db){
        boolean isPlaying = true;

        while (isPlaying) {
            System.out.println(BLACK_BACKGROUND + PURPLE_LIGHT + BOLD + "Inventory: " + RESET +
                    PURPLE_LIGHT + "\n1. Weapons\n2. Potions\n0. Go Back" + RESET);

            switch (sc.scannerNumber()) {
                case 1 -> displayItemsInWeaponsInventory(player, sc, db);
                case 2 -> displayItemsInPotionsInventory(player, sc, db);
                case 0 -> isPlaying = false;
                default -> System.out.println(RED_DARK + "Invalid input, please chose from the options presented" + RESET);

            }

        }
    }



    public void displayItemsInWeaponsInventory(Player player, Scanners sc, DBConnection db) {
        System.out.println(BLACK_BACKGROUND + RED + BOLD + "       Weapons:       " + RESET);

        while (true) {

            String equippedWeaponName = db.getStringFromDb("name", "weaponsInventory", "equipped", 1, player);
            int equippedWeaponId = db.getIdFromName("weaponsInventory", equippedWeaponName, "playerId", player);

            System.out.println(RED_DARK + "Equipped weapon: " + equippedWeaponName + RESET);

            int count = db.getCount("id", "weaponsInventory", player);

            if (count == 0){
                System.out.println(GRAY + ITALIC + "This inventory is empty" + RESET);
            }

            String weaponName = db.selectFromWeaponsInventory(sc, player);
            int weaponId = db.getIdFromName("weaponsInventory", weaponName, "playerId", player);
            int weaponStrength = db.getIntFromDb("strength", "weaponsInventory", "id", weaponId, player);

            db.equipWeapon(equippedWeaponId, weaponId, player);

            String defaultAttack = db.getStringFromDb("attackName", "weaponsInventory", "id", weaponId, player);


            player.setDefaultAttack(defaultAttack);
            System.out.println("Strength +" + weaponStrength);
            sc.pressEnter();
            break;

        }

    }


    public void displayItemsInPotionsInventory(Player player, Scanners sc, DBConnection db) {
        while (true) {

            System.out.println(BLACK_BACKGROUND + PINK_LIGHT + BOLD + "    Potions:    " + RESET);

            String potionName = db.selectFromPotionsInventory(sc, player);
            int potionId = db.getIdFromName("potionsInventory", potionName, "playerId", player);
            int count = db.getCount("id", "potionsInventory", player);

            if (count == 0){
                System.out.println(GRAY + ITALIC + "This inventory is empty" + RESET);
            }

            if (Objects.equals(potionName, "exit")) {
                break;
            }

            System.out.println(player.getName() + " drank " + potionName + "!");

            db.drinkPotion(potionId, player);

            if (player.getHealth() > 100){
                player.setHealth(100);
            }

            sc.pressEnter();

            break;
        }
    }


}

