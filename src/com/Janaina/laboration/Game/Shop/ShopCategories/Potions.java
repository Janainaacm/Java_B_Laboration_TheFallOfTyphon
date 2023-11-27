package com.Janaina.laboration.Game.Shop.ShopCategories;

import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;

public class Potions {

    private int total = 0;

    public void shopPotions(Player player, Inventory Inventory, Scanners sc, List<ShopProducts> potionsProductList) {

        while (true) {
            System.out.println(BLACK_BACKGROUND + BOLD + PINK_DARK + " Available Potions: " + RESET + "\n"
                    + PINK + "Gold: " + YELLOW_DARK + player.getGold() + RESET);

            for (int i = 0; i < potionsProductList.size(); i++) {
                ShopProducts product = potionsProductList.get(i);
                System.out.println(PINK_LIGHT + BOLD + (i + 1) + ". " + product.getName() + RESET + YELLOW_DARK + " - $" + product.getPrice() + RESET);
                if (product.getHealth() > 0) {
                    System.out.println(GRAY + ITALIC + "Health: " + product.getHealth() + RESET);
                } else if (product.getStrength() > 0) {
                    System.out.println(GRAY + ITALIC + "Strength: " + product.getStrength() + RESET);
                } else if (product.getAgility() > 0) {
                    System.out.println(GRAY + ITALIC + "Agility: " + product.getAgility() + RESET);
                } else if (product.getIntelligence() > 0) {
                System.out.println(GRAY + ITALIC + "Intelligence: " + product.getIntelligence() + RESET);
            }

            }
            System.out.println(BLACK_BACKGROUND + PINK_DARK + BOLD + "0. Go back" + RESET);

            chillForASecond(500);
            System.out.println(GRAY + "Enter the number of the item you would like to purchase" + RESET);
            int choice = sc.chooseFromPotions();

            if (choice == 0) {
                break;
            }

            if (choice < 1 || choice > potionsProductList.size()) {
                System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                continue;
            }

            ShopProducts selectedProduct = potionsProductList.get(choice - 1);

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