package com.Janaina.laboration.Game.Shop.ShopCategories;

import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Variables.Characters;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;

import java.util.ArrayList;
import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;

public class Weapons {

    public int total = 0;
    public int finalTotal = 0;
    public void shopWeapons(Characters player, Inventory Inventory) {
        //String name, int price, int strength, int health, int agility, int intelligence
        List<ShopProducts> productList = new ArrayList<>();
        productList.add(new ShopProducts("Frostbite Dagger", 120, 10, 0,0,0));
        productList.add(new ShopProducts("Shadowfang Blade", 140, 11, 0,0,0));
        productList.add(new ShopProducts("Cursed Scythe", 150, 12, 0,0,0));
        productList.add(new ShopProducts("Sunstrike", 180, 15, 0, 0, 0));
        productList.add(new ShopProducts("Phoenix Bow", 200,18,0,0,0));
        productList.add(new ShopProducts("Thunderstrike Hammer", 220,20,0,0,0));

        productList.add(new ShopProducts("Glock-19", 1000, 100, 0, 0, 0));

        List<ShopProducts> shoppingCart = new ArrayList<>();

        while (true) {
            System.out.println(YELLOW_BRIGHT + "SHOP" + RESET);
            System.out.println(YELLOW + "Available products:" + RESET);

            for (int i = 0; i < productList.size(); i++) {
                ShopProducts product = productList.get(i);
                System.out.println(CYAN + (i + 1) + ". " + product.getName() + " - $" + product.getPrice() + RESET);
            }
            System.out.println(CYAN + "0. View shopping cart and checkout" + RESET);

            System.out.println(YELLOW + "Enter the number of the item you would like to purchase" + RESET);
            int choice = scannerNumber();

            if (choice == 0){
                break;
            }

            if (choice < 1 || choice > productList.size()){
                System.out.println(BLUE + "Invalid choice, please try again" + RESET);
                continue;
            }

            ShopProducts selectedProduct = productList.get(choice - 1);

            if (total + selectedProduct.getPrice() <= player.getGold()){
                shoppingCart.add(selectedProduct);
                total += selectedProduct.getPrice();
                System.out.println(PURPLE + selectedProduct.getName() + " has been added to your cart." + RESET);
            } else {
                System.out.println(RED + selectedProduct.getName() + " is out of your budget \n(you do not have the capacity for that big man)" + RESET);
            }



        }

        System.out.println(YELLOW + "Items in your cart: " + RESET);
        for (ShopProducts product : shoppingCart){
            Inventory.addToWeaponsInventory(product);
            System.out.println(CYAN + product.getName() + " - $" + product.getPrice() + RESET);
        }
        System.out.println(YELLOW + "Your total is: " + total + RESET);





    }






}
