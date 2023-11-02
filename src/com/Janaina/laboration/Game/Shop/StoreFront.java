package com.Janaina.laboration.Game.Shop;

import com.Janaina.laboration.Game.Shop.ShopCategories.Potions;
import com.Janaina.laboration.Game.Shop.ShopCategories.Weapons;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.printRed;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;

public class StoreFront {

    //Switch with diff types of categories
    public void mainStoreFront(Player player, Inventory Inventory) {
        Weapons Weapons = new Weapons();
        Potions Potions = new Potions();

        boolean isShopping = true;
        while (isShopping) {

            System.out.println(BLACK_BACKGROUND + "       " + YELLOW_BOLD_BRIGHT + YELLOW_UNDERLINED + "SHOP" + RESET + BLACK_BACKGROUND + "        " + RESET +
                    "\n" + YELLOW_DARK + "Gold: " + WHITE + player.getGold() + RESET +
                    YELLOW_DARK + "\nCategories:" + RESET +
                    "\n" + WHITE + "1. Weapons\n2. Potions" + RESET);
            System.out.println(WHITE + "0. Go back" + RESET);

            switch (scannerNumber()) {
                case 1 -> Weapons.shopWeapons(player, Inventory);
                case 2 -> Potions.shopPotions(player, Inventory);
                case 0 -> { isShopping = false;
                }
                default -> printRed("Invalid input, please chose from the presented options");

            }

        }


    }





}


