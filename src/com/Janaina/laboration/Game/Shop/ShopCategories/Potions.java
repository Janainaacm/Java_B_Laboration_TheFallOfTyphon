package com.Janaina.laboration.Game.Shop.ShopCategories;

import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Variables.ACharacters;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Resources.Scanners;

import java.util.ArrayList;
import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;

public class Potions {

    public int total = 0;
    public List<ShopProducts> productList = new ArrayList<>();

    public void shopPotions(ACharacters player, Inventory Inventory, Scanners sc) {

        //String name, int price, int strength, int health, int agility, int intelligence
        productList.add(new ShopProducts("Small Health Potion", "", "⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆", 30, 0, 50, 0, 0));
        productList.add(new ShopProducts("Large Health Potion", "", "⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆", 50, 0, 100, 0, 0));
        productList.add(new ShopProducts("Flexibility potion", "", "❥⁺⋆༺.*₊˚࿐༅", 50, 0, 0, 5, 0));
        productList.add(new ShopProducts("Strength potion", "", "❥⁺⋆༺.*₊˚࿐༅", 50, 5, 0, 0, 0));


        while (true) {
            System.out.println(BLACK_BACKGROUND + BOLD + PINK_DARK + " Available Potions: " + RESET + "\n"
                    + PINK + "Gold: " + YELLOW_DARK + player.getGold() + RESET);

            for (int i = 0; i < productList.size(); i++) {
                ShopProducts product = productList.get(i);
                System.out.println(PINK_LIGHT + BOLD + (i + 1) + ". " + product.getName() + RESET + YELLOW_DARK + " - $" + product.getPrice() + RESET);
                if (product.getHealth() > 0) {
                    System.out.println(GRAY + ITALIC + "Health: " + product.getHealth() + RESET);
                } else if (product.getStrength() > 0) {
                    System.out.println(GRAY + ITALIC + "Strength: " + product.getStrength() + RESET);
                } else if (product.getAgility() > 0) {
                    System.out.println(GRAY + ITALIC + "Agility: " + product.getAgility() + RESET);
                }

            }
            System.out.println(BLACK_BACKGROUND + PINK_DARK + BOLD + "0. Go back" + RESET);

            chillForASecond(500);
            System.out.println(GRAY + "Enter the number of the item you would like to purchase" + RESET);
            int choice = sc.chooseFromPotions();

            if (choice == 0) {
                break;
            }

            if (choice < 1 || choice > productList.size()) {
                System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                continue;
            }

            ShopProducts selectedProduct = productList.get(choice - 1);

            if (total + selectedProduct.getPrice() <= player.getGold()) {
                total += selectedProduct.getPrice();
                player.setGold(player.getGold() - selectedProduct.getPrice());
                System.out.println(PINK_DARK + BOLD + "Gold: -" + selectedProduct.getPrice());
                System.out.println(WHITE + selectedProduct.getName() + " has been added to your Inventory." + RESET);
                Inventory.addToPotionsInventory(selectedProduct);
                sc.pressEnter();
            } else {
                System.out.println(RED + "Insufficient funds to buy " + selectedProduct.getName() + RESET);
            }
        }
    }

}