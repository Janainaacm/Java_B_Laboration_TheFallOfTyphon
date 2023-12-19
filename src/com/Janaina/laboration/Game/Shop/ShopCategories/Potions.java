package com.Janaina.laboration.Game.Shop.ShopCategories;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import java.util.Objects;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;

public class Potions {

    private int total = 0;

    public void shopPotions(Player player, Scanners sc, DBConnection db) {

        while (true) {
            System.out.println(BLACK_BACKGROUND + BOLD + PINK_DARK + " Available Potions: " + RESET + "\n"
                    + PINK + "Gold: " + YELLOW_DARK + player.getGold() + RESET);

            String potionName = db.selectFromPotionsShop(sc, player);

            if (Objects.equals(potionName, "exit")) {
                break;
            }

            int id = db.getIdFromName("potions", potionName, "potionsInventory", player);
            int potionPrice = db.getIntFromDb("price", "weapons", "id", id, player);

            if (total + potionPrice <= player.getGold()) {
                total += potionPrice;
                player.setGold(player.getGold() - potionPrice);
                System.out.println(RED_DARK + BOLD + "Gold: -" + potionPrice);
                System.out.println(WHITE + potionName + " has been added to your inventory." + RESET);
                db.addToPotionsInventory(potionName, player);
                sc.pressEnter();
            } else {
                System.out.println(RED + "Insufficient funds to buy " + potionName + RESET);
                sc.pressEnter();
            }
        }
    }
}