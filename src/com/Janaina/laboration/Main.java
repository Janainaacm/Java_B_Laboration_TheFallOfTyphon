package com.Janaina.laboration;

import com.Janaina.laboration.Game.GameMenu.Levels.LevelMenu;
import com.Janaina.laboration.Game.GameMenu.Levels.LevelOne;
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
import static com.Janaina.laboration.Resources.TextDelay.*;

public class Main {
    public static boolean playerIsAlive = true;
    public static boolean playerIsPlayingGame = true;

    public static void main(String[] args) {
        LevelOne test = new LevelOne();
        Fury fury = new Fury();

        Storyline Storyline = new Storyline();
        while (playerIsAlive && playerIsPlayingGame) {
            Storyline.mainGameMenu();

            break;



            //Player player = new Player();
            //Weapons weapons = new Weapons();
            //Inventory inventory = new Inventory();



        /*
        fury.setAgility(70);


        if (fury.didDodge()){
            System.out.println("slay");
        }else { //Be able to press enter
            System.out.println("gay");
        }

         */
        /*
        System.out.println("Equipped weapon b4: " + player.getEquippedWeapon().getName() + " + strength " + player.getStrength());
        player.setGold(200);
        weapons.shopWeapons(player, inventory);
        inventory.equipWeapon(player);
                System.out.println("Equipped weapon after: " + player.getEquippedWeapon().getName() + " + strength: " + player.getStrength());

         */


        }
}}
