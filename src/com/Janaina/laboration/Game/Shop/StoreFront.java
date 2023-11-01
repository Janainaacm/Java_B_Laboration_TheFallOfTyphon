package com.Janaina.laboration.Game.Shop;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books;
import com.Janaina.laboration.Game.Shop.ShopCategories.Potions;
import com.Janaina.laboration.Game.Shop.ShopCategories.Weapons;
import com.Janaina.laboration.Game.Variables.Characters;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;

public class StoreFront {

    //Switch with diff types of categories
    public void mainStoreFront(Characters player, Inventory Inventory){
        Weapons Weapons = new Weapons();
        Potions Potions = new Potions();
        Books Books = new Books();

        System.out.println(BLACK_BACKGROUND + "         " + YELLOW_BOLD_BRIGHT + YELLOW_UNDERLINED + "SHOP" + RESET + BLACK_BACKGROUND + "         " + RESET +
                "\n" + BLACK_BACKGROUND + " " + YELLOW_BOLD + "Categories:" + RESET + BLACK_BACKGROUND + "          " + RESET +
                "\n" + WHITE_BOLD_BRIGHT + "1. Weapons\n2. Potions\n3. Books\n" + RESET + GRAY + "Gold: " + player.getGold() + RESET);

            switch (scannerNumber()){
                case 1 -> Weapons.shopWeapons(player, Inventory);
                case 2 -> Potions.shopPotions(player, Inventory);
                case 3 -> Books.shopBooks(player, Inventory);

            }

    }

}
