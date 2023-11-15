package com.Janaina.laboration.Game.Shop.ShopCategories;

import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Variables.ACharacters;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;

import java.util.ArrayList;
import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Scanners.pressEnter;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;

public class Weapons {

    public int total = 0;
    public void shopWeapons(ACharacters player, Inventory Inventory) {
        //String name, int price, int strength, int health, int agility, int intelligence
        List<ShopProducts> productList = new ArrayList<>();
        productList.add(new ShopProducts("Frostbite Dagger", "Frostbite Strike", 120, 10, 0,0,0));
        productList.add(new ShopProducts("Shadowfang Blade", "Dark Eclipse", 140, 11, 0,0,0));
        productList.add(new ShopProducts("Cursed Scythe", "Reaper's Grasp", 150, 12, 0,0,0));
        productList.add(new ShopProducts("Oceanic Trident", "Abyssal Torrent", 180, 15, 0, 0, 0));
        productList.add(new ShopProducts("Phoenix Bow","Flaming Arrow Barrage", 200,18,0,0,0));
        productList.add(new ShopProducts("Thunderstrike Hammer", "Lightning Hammerblow", 220,20,0,0,0));

        if (player.getLevel() >= 10){
            productList.add(new ShopProducts("Glock-19", "Kurdiska räven", 1000, 100, 0, 0, 0));

        }

        while (true) {
            System.out.println(BLACK_BACKGROUND + BOLD + RED + " Available weapons: " + RESET + "\n"
                                + RED + "Gold: " + YELLOW_DARK + player.getGold() + RESET);

            for (int i = 0; i < productList.size(); i++) {
                ShopProducts product = productList.get(i);
                System.out.println(RED + BOLD + (i + 1) + ". " + product.getName() + YELLOW_DARK + " - $" + product.getPrice() + RESET +
                                    "\n" + BLACK + ITALIC + "Strength: +" + product.getStrength() + RESET);

            }
            System.out.println(BLACK_BACKGROUND + RED_DARK + BOLD + "0. Go back" + RESET);

            chillForASecond(1000);
            System.out.println(GRAY + "Enter the number of the item you would like to purchase" + RESET);
            int choice = scannerNumber();

            if (choice == 0){
                break;
            }

            if (choice < 1 || choice > productList.size()){
                System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                continue;
            }

            ShopProducts selectedProduct = productList.get(choice - 1);

            if (total + selectedProduct.getPrice() <= player.getGold()){
                total += selectedProduct.getPrice();
                player.setGold(player.getGold() - selectedProduct.getPrice());
                System.out.println(RED_DARK + BOLD + "Gold: -" + selectedProduct.getPrice());
                System.out.println(WHITE + selectedProduct.getName() + " has been added to your Inventory." + RESET);
                Inventory.addToWeaponsInventory(selectedProduct);
                productList.remove(selectedProduct);
                pressEnter();
            } else {
                System.out.println(RED + "Insufficient funds to buy " + selectedProduct.getName() + RESET);
                pressEnter();
            }



        }








    }






}
