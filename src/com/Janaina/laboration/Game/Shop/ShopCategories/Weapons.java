package com.Janaina.laboration.Game.Shop.ShopCategories;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import java.util.Objects;

import static com.Janaina.laboration.Resources.Colors.*;

public class Weapons {

    private int total = 0;

    public void shopWeapons(Player player, Scanners sc, DBConnection db) {

        while (true) {
            System.out.println(BLACK_BACKGROUND + BOLD + RED + " Available weapons: " + RESET + "\n"
                    + RED + "Gold: " + YELLOW_DARK + player.getGold() + RESET);

            String weaponName = db.selectFromWeaponsShop(sc, player);

            if (Objects.equals(weaponName, "exit")) {
                break;
            }

            int id = db.getIdFromName("weapons", weaponName, "weaponsInventory", player);
            int weaponPrice = db.getIntFromDb("price", "weapons", "id", id, player);

            if (total + weaponPrice <= player.getGold()) {
                total += weaponPrice;
                player.setGold(player.getGold() - weaponPrice);
                System.out.println(RED_DARK + BOLD + "Gold: -" + weaponPrice);
                System.out.println(WHITE + weaponName + " has been added to your inventory." + RESET);
                db.addToWeaponsInventory(weaponName, player);
                sc.pressEnter();
            } else {
                System.out.println(RED + "Insufficient funds to buy " + weaponName + RESET);
                sc.pressEnter();
            }

        }
    }
}