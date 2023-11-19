package com.Janaina.laboration.Game.Variables.Hero;

import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Resources.Scanners;

import java.util.ArrayList;
import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;

public class Inventory {
    public List<ShopProducts> weaponsList;
    public List<ShopProducts> potionsList;


    public Inventory() {
        weaponsList = new ArrayList<>();
        potionsList = new ArrayList<>();

    }

    public void playerInventory(Player player, Scanners sc){
        boolean isPlaying = true;

        while (isPlaying) {
            System.out.println(BLACK_BACKGROUND + PURPLE_LIGHT + BOLD + "Inventory: " + RESET +
                    PURPLE_LIGHT + "\n1. Weapons\n2. Potions\n0. Go Back" + RESET);

            switch (sc.scannerNumber()) {
                case 1 -> displayItemsInWeaponsInventory(player, sc);
                case 2 -> displayItemsInPotionsInventory(player, sc);
                case 0 -> isPlaying = false;
                default -> System.out.println(RED_DARK + "Invalid input, please chose from the options presented" + RESET);

            }

        }
    }



    public void addToWeaponsInventory(ShopProducts item) {
        weaponsList.add(item);
    }

    public void addToPotionsInventory(ShopProducts item) {
        potionsList.add(item);
    }

    public void displayItemsInWeaponsInventory(Player player, Scanners sc) {
        System.out.println(BLACK_BACKGROUND + RED + BOLD + "       Weapons:       " + RESET);
        while (true) {

            System.out.println(RED_DARK + "Equipped weapon: " + player.getEquippedWeapon().getName() + RESET);

            ShopProducts currentWeapon = player.getEquippedWeapon();
            
            if (weaponsList.isEmpty()){
                System.out.println(GRAY + ITALIC + "This inventory is empty" + RESET);
            }

            for (int i = 0; i < weaponsList.size(); i++) {
                ShopProducts weapon = weaponsList.get(i);
                System.out.println(RED + ITALIC + (i + 1) + ". " + weapon.getName() + " - " + weapon.getStrength() + RESET);
            }
            System.out.println("0. Go Back");
            System.out.println(WHITE + "Enter the number of the weapon you would like to equip" + RESET);
            int choice = sc.chooseFromWeaponsInventory();

            if (choice == 0) {
                break;
            }

            if (choice < 1 || choice > weaponsList.size()) {
                System.out.println(BLUE + "Invalid choice, please try again" + RESET);
                continue;
            }

            player.setEquippedWeapon(weaponsList.get(choice - 1));
            weaponsList.add(currentWeapon);
            weaponsList.remove(player.getEquippedWeapon());
            System.out.println(player.getName() + " equipped " + player.getEquippedWeapon().getName() + "!");
            player.setStrength(player.getStrength() + player.getEquippedWeapon().getStrength());
            player.setDefaultAttack(player.getEquippedWeapon().getAttackName());
            System.out.println("Strength +" + player.getEquippedWeapon().getStrength());
            sc.pressEnter();
            break;

        }

    }


    public void displayItemsInPotionsInventory(Player player, Scanners sc) {
        while (true) {

            System.out.println(BLACK_BACKGROUND + PINK_LIGHT + BOLD + "    Potions:    " + RESET);

            if (potionsList.isEmpty()){
                System.out.println(GRAY + ITALIC + "This inventory is empty" + RESET);
            }

            for (int i = 0; i < potionsList.size(); i++) {
                ShopProducts potion = potionsList.get(i);
                System.out.println(PINK_LIGHT + BOLD + (i + 1) + ". " + potion.getName() + RESET);
                if (potion.getHealth() > 0){
                    System.out.println(GRAY + ITALIC + "Health: " + potion.getHealth()+ RESET);
                }else if (potion.getStrength() > 0){
                    System.out.println(GRAY + ITALIC + "Strength: " + potion.getStrength()+ RESET);
                }else if (potion.getAgility() > 0 ){
                    System.out.println(GRAY + ITALIC + "Agility: " + potion.getAgility()+ RESET);
                }

            }
            System.out.println("0. Go Back");
            System.out.println(WHITE + "Enter the number of the potion you would like to drink" + RESET);
            int choice = sc.chooseFromPotionsInventory();

            if (choice == 0) {
                break;
            }

            if (choice < 1 || choice > potionsList.size()) {
                System.out.println(BLUE + "Invalid choice, please try again" + RESET);
                continue;
            }

            ShopProducts drinkPotion = potionsList.get(choice - 1);
            System.out.println(player.getName() + " drank " + drinkPotion.getName() + "!");

            chillForASecond(500);
            if (drinkPotion.getHealth() > 0){
                System.out.println(GRAY + ITALIC + "Health +" + drinkPotion.getHealth()+ RESET);
                player.setHealth(player.getHealth() + drinkPotion.getHealth());

            }else if (drinkPotion.getStrength() > 0){
                System.out.println(GRAY + ITALIC + "Strength +" + drinkPotion.getStrength()+ RESET);
                player.setStrength(player.getStrength() + drinkPotion.getStrength());

            }else if (drinkPotion.getAgility() > 0 ){
                System.out.println(GRAY + ITALIC + "Agility +" + drinkPotion.getAgility()+ RESET);
                player.setAgility(player.getAgility() + drinkPotion.getAgility());
            }

            if (player.getHealth() > 100){
                player.setHealth(100);
            }

            potionsList.remove(drinkPotion);
            sc.pressEnter();

            break;
        }
    }


}

