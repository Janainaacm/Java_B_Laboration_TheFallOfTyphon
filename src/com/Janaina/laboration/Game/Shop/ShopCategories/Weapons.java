package com.Janaina.laboration.Game.Shop.ShopCategories;

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
    public void shopWeapons(Player player, Inventory inventory, Scanners sc, List<ShopProducts> weaponsProductList) {

        while (true) {
            System.out.println(BLACK_BACKGROUND + BOLD + RED + " Available weapons: " + RESET + "\n"
                                + RED + "Gold: " + YELLOW_DARK + player.getGold() + RESET);

            for (int i = 0; i < weaponsProductList.size(); i++) {
                ShopProducts product = weaponsProductList.get(i);

                if (Objects.equals(product.getName(), "Glock-19")) {
                    if (player.getAvailableLevels() >= 7){
                        System.out.println(RED + BOLD + (i + 1) + ". " + product.getName() + YELLOW_LIGHT + " - $" + product.getPrice() + RESET +
                                "\n" + BLACK + ITALIC + "Strength: +" + product.getStrength() + RESET);
                    }

                }else {
                    System.out.println(RED_LILDARKER + BOLD + (i + 1) + ". " + product.getName() + YELLOW_DARK + " - $" + product.getPrice() + RESET +
                            "\n" + BLACK + ITALIC + "Strength: +" + product.getStrength() + RESET);
                }

            }

            System.out.println(BLACK_BACKGROUND + RED_DARK + BOLD + "0. Go back" + RESET);

            chillForASecond(1000);
            System.out.println(GRAY + "Enter the number of the item you would like to purchase" + RESET);
            int choice = sc.chooseFromWeapons();

            if (choice == 0){
                break;
            }

            if (choice < 1 || choice > weaponsProductList.size()){
                System.out.println(BLACK + "Invalid choice, please try again" + RESET);
                continue;
            }

            ShopProducts selectedProduct = weaponsProductList.get(choice - 1);

            if (total + selectedProduct.getPrice() <= player.getGold()){
                total += selectedProduct.getPrice();
                player.setGold(player.getGold() - selectedProduct.getPrice());
                System.out.println(RED_DARK + BOLD + "Gold: -" + selectedProduct.getPrice());
                System.out.println(WHITE + selectedProduct.getName() + " has been added to your inventory." + RESET);
                inventory.addToWeaponsInventory(selectedProduct);
                weaponsProductList.remove(selectedProduct);
                sc.pressEnter();
            } else {
                System.out.println(RED + "Insufficient funds to buy " + selectedProduct.getName() + RESET);
                sc.pressEnter();
            }

        }
    }

}
