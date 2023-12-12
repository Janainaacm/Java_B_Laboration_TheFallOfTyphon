package com.Janaina.laboration.Game;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import java.util.Objects;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.Storyteller.pythiaBackstory;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class Introduction {

    public void introduction(Player player, Scanners sc) {

        pythiaSpeaking("Welcome to the land of Elathriya " + player.getName() + ", I will be your guide on this journey");
        suspensefulDots(PURPLE + "." + RESET);

        printYellow("Do you wish to know more about Pythia? yes or no");
        String knowMoreAboutPythia = sc.scannerYesOrNo();

        if (Objects.equals(knowMoreAboutPythia, "yes")) {
            pythiaBackstory();
        }

        pythiaSpeaking("Would you like a little tour of the game?");
        System.out.println("yes or no");

        if (Objects.equals(sc.scannerYesOrNo(), "yes")) {

            initialTourOfGame(sc, player);
            pythiaSpeaking("That concludes our tour. In case you would need a rerun, i have added a fourth option of repeating this tour to the main menu.\n");

        } else {
            pythiaSpeaking("In case you change your mind, there is an option to view tour in the main menu.\n");
        }

        sleepThread("Here are 50 gold coins for you to start with");
        player.setGold(player.getGold() + 50);
        System.out.println(YELLOW_DARK + "Gold + 50");
        sleepThread(PURPLE + "Best of luck to you " + player.getName() + ", may we meet again");
        suspensefulDots(".");


    }

    public void initialTourOfGame(Scanners sc, Player player) {
        println(BLACK_BACKGROUND + "     " + CYAN_BOLD + CYAN_UNDERLINED + "THE FALL OF TYPHON" + RESET + BLACK_BACKGROUND + "      " + RESET
                + "\n" + BLACK_BACKGROUND + "          " + CYAN_BOLD + "Main Menu" + RESET + BLACK_BACKGROUND + "          " + RESET
                + "\n" + CYAN_BOLD +
                "1. Play\n2. Shop\n3. Read game lore\n0. Quit Game" + RESET);

        pythiaSpeaking("This is the main game menu");
        suspensefulDots(PURPLE + "." + RESET);
        sleepThread(PURPLE + "Let's explore it a bit deeper:" + RESET);

        //Show Game Lore
        println("1. Play\n2. Shop\n" + CYAN_BOLD + "3. Read game lore\n" + RESET + "0. Quit Game");
        pythiaSpeaking("If you press 3, the storyteller will tell you the tale of this battle. Though it is a most \nfascinating tale, it is not mandatory, therefore reading it will remain your choice.");
        suspensefulDots(PURPLE + "." + RESET);

        //Show Play the Game
        println(CYAN_BOLD + "1. Play\n" + RESET + "2. Shop\n3. Read game lore\n0. Quit Game");
        pythiaSpeaking("Type 1 to enter the game");

        while (true) {
            if (sc.scannerNumber() == 1) {
                //Show Game menu

                println(BLACK_BACKGROUND + "          " + PURPLE_BOLD_BRIGHT + "Game Menu" + RESET + BLACK_BACKGROUND + "          " + RESET
                        + "\n" + PURPLE_DARK + BOLD +
                        "1. Levels\n2. Player stats\n3. Achievements\n4. Inventory\n0. Back" + RESET);

                pythiaSpeaking("This is the game menu:");

                println(PURPLE_DARK + BOLD + "1. Levels\n" + RESET + "2. Player stats\n3. Achievements\n4. Inventory\n0. Back" + RESET);

                pythiaSpeaking("There are six levels to this game, some are harder than others, each with it's own monster.\nYou will not be able to play a level if you have not completed the level previous.");
                sc.pressEnter();

                println("1. Levels\n" + PURPLE_DARK + BOLD + "2. Player stats\n" + RESET + "3. Achievements\n4. Inventory\n0. Back" + RESET);
                pythiaSpeaking("Here you can see your stats, including how much xp you need to reach the next level, your special attacks and equipped weapon");
                sc.pressEnter();

                println("1. Levels\n2. Player stats\n" + PURPLE_DARK + BOLD + "3. Achievements\n" + RESET + "4. Inventory\n0. Back" + RESET);
                pythiaSpeaking("In your achievements you will be able to view all the games monsters, if you have defeated them, their stats\nand backstory will be visible to you.");
                sc.pressEnter();
                println("1. Levels\n2. Player stats\n3. Achievements\n" + PURPLE_DARK + BOLD + "4. Inventory\n" + RESET + "0. Back");
                pythiaSpeaking("In your inventory you will find all your potions and weapons. In your inventory you can pick which weapon you want to equip\nor which potion you would like to drink. Your inventory is also available to you during fights.");
                sc.pressEnter();

                break;
            } else {
                pythiaSpeaking("You need to type '1'");
            }
        }


        //Show Shop
        println("1. Play\n" + CYAN_BOLD + "2. Shop\n" + RESET + "3. Read game lore\n0. Quit Game");

        pythiaSpeaking("Type 2 to enter the shop");

        while (true) {
            if (sc.scannerNumber() == 2) {
                //Show shop
                System.out.println(BLACK_BACKGROUND + "       " + YELLOW_BOLD_BRIGHT + YELLOW_UNDERLINED + "SHOP" + RESET + BLACK_BACKGROUND + "        " + RESET +
                        "\n" + YELLOW_DARK + "Gold: " + WHITE + player.getGold() + RESET +
                        YELLOW_DARK + "\nCategories:" + RESET +
                        "\n" + WHITE + "1. Weapons\n2. Potions\n0. Go back" + RESET);

                pythiaSpeaking("This is the shop. Here you can use the gold you win from slaying monsters to buy either potions or weapons");
                sc.pressEnter();

                System.out.println(WHITE + "1. Weapons\n" + RESET + "2. Potions\n0. Go back");
                pythiaSpeaking("All weapons increase your strength, which multiplied with your base damage determines how much damage you do.");

                System.out.println("1. Weapons\n" + WHITE +  "2. Potions\n" + RESET + "0. Go back");
                pythiaSpeaking("Potions on the other hand can increase your intelligence: making it easier to flee a fight, your \nagility: giving you more time to dodge an attack, or restore your health.");
                sc.pressEnter();

                break;
            } else {
                pythiaSpeaking("You need to type '2'");
            }
        }

        println("1. Play\n2. Shop\n3. Read game lore\n" + CYAN_BOLD + "0. Quit Game" + RESET);
        pythiaSpeaking("Lastly, you type '0' in order to quit game");
        suspensefulDots(PURPLE + "." + RESET);


    }


}

