package com.Janaina.laboration.Game.Shop.ShopCategories;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import java.util.List;
import java.util.Objects;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;

public class Weapons {

    private int total = 0;

    public void shopWeapons(Player player, Scanners sc, DBConnection db) {

        while (true) {
            System.out.println(BLACK_BACKGROUND + BOLD + RED + " Available weapons: " + RESET + "\n"
                    + RED + "Gold: " + YELLOW_DARK + player.getGold() + RESET);

            String weaponName = db.selectFromWeaponsShop(  sc);

            if (Objects.equals(weaponName, "exit")) {
                break;
            }

            int id = db.getIdFromChoice("weapons", weaponName);
            int weaponPrice = db.getIntFromDb("price", "weapons", "id", id);

            if (total + weaponPrice <= player.getGold()) {
                total += weaponPrice;
                player.setGold(player.getGold() - weaponPrice);
                System.out.println(RED_DARK + BOLD + "Gold: -" + weaponPrice);
                System.out.println(WHITE + weaponName + " has been added to your inventory." + RESET);
                db.addToWeaponsInventory(id);
                sc.pressEnter();
            } else {
                System.out.println(RED + "Insufficient funds to buy " + weaponName + RESET);
                sc.pressEnter();
            }

        }
    }
}