package com.Janaina.laboration.Game.Shop;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Shop.ShopCategories.Potions;
import com.Janaina.laboration.Game.Shop.ShopCategories.Weapons;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.printRed;

public class StoreFront {

    public void mainStoreFront(Player player, Scanners sc, DBConnection db) {
        Weapons Weapons = new Weapons();
        Potions Potions = new Potions();

        boolean isShopping = true;
        while (isShopping) {

            System.out.println(BLACK_BACKGROUND + "       " + YELLOW_BOLD_BRIGHT + YELLOW_UNDERLINED + "SHOP" + RESET + BLACK_BACKGROUND + "        " + RESET +
                    "\n" + YELLOW_DARK + "Gold: " + WHITE + player.getGold() + RESET +
                    YELLOW_DARK + "\nCategories:" + RESET +
                    "\n" + WHITE + "1. Weapons\n2. Potions" + RESET);
            System.out.println(WHITE + "0. Go back" + RESET);

            switch (sc.chooseFromShopMenu()) {
                case 1 -> Weapons.shopWeapons(player, sc, db);
                case 2 -> Potions.shopPotions(player, sc, db);
                case 0 -> isShopping = false;
                default -> printRed("Invalid input, please chose from the presented options");

            }
        }
    }
}


