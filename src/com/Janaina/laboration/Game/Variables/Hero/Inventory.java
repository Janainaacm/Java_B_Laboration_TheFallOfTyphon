package com.Janaina.laboration.Game.Variables.Hero;

import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.ArrayList;
import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;

public class Inventory {
    public List<ShopProducts> weaponsList;
    public List<ShopProducts> potionsList;


    public Inventory() {
        weaponsList = new ArrayList<>();
        potionsList = new ArrayList<>();



    }


    public void addToWeaponsInventory(ShopProducts item) {
        weaponsList.add(item);
    }

    public void displayItemsInWeaponsInventory() {
        System.out.println("Inventory: ");
        for (int i = 0; i < weaponsList.size(); i++) {
            System.out.println((i + 1) + ". " + weaponsList.get(i).getName());
        }

    }

    public void addToPotionsInventory(ShopProducts item) {
        potionsList.add(item);
    }

    public void displayItemsInPotionsInventory() {
        System.out.println("Inventory: ");
        for (int i = 0; i < potionsList.size(); i++) {
            System.out.println((i + 1) + ". " + potionsList.get(i).getName());
        }

    }

    public void playerInventory() {


    }

    public void playerWeaponInventory() {


    }

    public void equipWeapon(Player player) {
        while (true) {
            for (int i = 0; i < weaponsList.size(); i++) {
                ShopProducts weapon = weaponsList.get(i);
                System.out.println(CYAN + (i + 1) + ". " + weapon.getName() + " - " + weapon.getStrength() + RESET);
            }
            System.out.println(YELLOW + "Enter the number of the item you would like to equip" + RESET);
            int choice = scannerNumber();

            if (choice == 0) {
                break;
            }

            if (choice < 1 || choice > weaponsList.size()) {
                System.out.println(BLUE + "Invalid choice, please try again" + RESET);
                continue;
            }

            player.equippedWeapon = weaponsList.get(choice - 1);
            System.out.println(player.getName() + " equipped " + player.equippedWeapon.getName() + "!");
            player.setStrength(player.getStrength() + player.equippedWeapon.getStrength());
            System.out.println("Strength +" + player.equippedWeapon.getStrength());
            break;
        }

    }
}