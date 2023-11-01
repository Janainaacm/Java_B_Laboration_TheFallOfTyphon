package com.Janaina.laboration;

import com.Janaina.laboration.Game.GameMenu.Levels.LevelMenu;
import com.Janaina.laboration.Game.Introduction;
import com.Janaina.laboration.Game.Shop.ShopCategories.Weapons;
import com.Janaina.laboration.Game.Storyline;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.*;
import com.Janaina.laboration.Resources.Storyteller;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.Scanners.pressEnter;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;
import static com.Janaina.laboration.Resources.TextDelay.suspensefulDots;

public class Main {
    public static void main(String[] args) {

        Storyline Storyline = new Storyline();
        Player player = new Player();
        Weapons weapons = new Weapons();
        Inventory inventory = new Inventory();



        /*
        fury.setAgility(70);


        if (fury.didDodge()){
            System.out.println("slay");
        }else { //Be able to press enter
            System.out.println("gay");
        }

         */
        System.out.println("Equipped weapon b4: " + player.getEquippedWeapon().getName() + " + strength " + player.getStrength());
        player.setGold(200);
        weapons.shopWeapons(player, inventory);
        inventory.equipWeapon(player);

        System.out.println("Equipped weapon after: " + player.getEquippedWeapon().getName() + " + strength: " + player.getStrength());

        //Storyline.mainGameMenu();








}}
