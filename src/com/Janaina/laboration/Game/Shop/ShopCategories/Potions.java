package com.Janaina.laboration.Game.Shop.ShopCategories;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import java.util.List;
import java.util.Objects;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;

public class Potions {

    private int total = 0;

    public void shopPotions(Player player, Scanners sc, DBConnection db) {

        while (true) {
            System.out.println(BLACK_BACKGROUND + BOLD + PINK_DARK + " Available Potions: " + RESET + "\n"
                    + PINK + "Gold: " + YELLOW_DARK + player.getGold() + RESET);

            String potionName = db.selectFromPotionsShop(sc);

            if (Objects.equals(potionName, "exit")) {
                break;
            }

            int id = db.getIdFromChoice("potions", potionName);
            int potionPrice = db.getIntFromDb("price", "weapons", "id", id);

            if (total + potionPrice <= player.getGold()) {
                total += potionPrice;
                player.setGold(player.getGold() - potionPrice);
                System.out.println(RED_DARK + BOLD + "Gold: -" + potionPrice);
                System.out.println(WHITE + potionName + " has been added to your inventory." + RESET);
                db.addToPotionsInventory(id);
                sc.pressEnter();
            } else {
                System.out.println(RED + "Insufficient funds to buy " + potionName + RESET);
                sc.pressEnter();
            }
        }
    }
}