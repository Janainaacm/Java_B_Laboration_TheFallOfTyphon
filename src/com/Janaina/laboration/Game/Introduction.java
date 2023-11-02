package com.Janaina.laboration.Game;

import com.Janaina.laboration.Game.Variables.Hero.Player;

import java.util.Objects;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.Scanners.*;
import static com.Janaina.laboration.Resources.Storyteller.pythia;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;
import static com.Janaina.laboration.Resources.TextDelay.suspensefulDots;

public class Introduction {

    public void createPlayer(Player player) {

        println(BLACK_BACKGROUND + "                             " + RESET
                + "\n" + BLACK_BACKGROUND + "     " + RED_BOLD + RED_UNDERLINED + "THE FALL OF TYPHON" + RESET + BLACK_BACKGROUND + "      " + RESET
                + "\n" + BLACK_BACKGROUND + "    " + RED_BOLD + "A Quest for Vengeance" + RESET + BLACK_BACKGROUND + "    " + RESET
                + "\n" + BLACK_BACKGROUND + "                             " + RESET + "\n");

        printYellow("(This game is inspired by and based of greek mythology, for every creature you encounter you will get the choice to learn more about them)");
        suspensefulDots(".");
        pressEnter();


        PythiaSpeaking("""
                Hello player,\s
                My name is Pythia, but you might formally know me as The Oracle of Delphi.\s
                What is your name?
                """);

        player.setName(scannerText());

        PythiaSpeaking("Welcome to the land of Elathriya " + player.getName() + ", I will be your guide on this journey\n");
        suspensefulDots(PURPLE + "." + RESET);

        printYellow("Do you wish to know more about Pythia? yes or no\n");
        String knowMoreAboutPythia = scannerYesOrNo();

        if (Objects.equals(knowMoreAboutPythia, "yes")) {
            pythia();

        }

        PythiaSpeaking("Would you like a little tour to get you comfortable?\n");
        System.out.println("yes or no");

        if (Objects.equals(scannerYesOrNo(), "yes")) {

            initialTourOfGame();
            PythiaSpeaking("That concludes our tour. In case you would need a rerun, i have added a fifth option of repeating this tour to the main menu.\n");

        } else {
            PythiaSpeaking("In case you change your mind, there is an option to view the tour in the main menu.\n");
        }

        sleepThread("Here are 50 gold coins for you to start with");
        player.setGold(player.getGold() + 50);
        System.out.println(YELLOW_DARK + "Gold + 50");
        sleepThread(PURPLE + "Best of luck to you " + player.getName() + ", may we meet again");
        suspensefulDots(".");


    }

    public void initialTourOfGame() {
        println(BLACK_BACKGROUND + "     " + CYAN_BOLD + CYAN_UNDERLINED + "THE FALL OF TYPHON" + RESET + BLACK_BACKGROUND + "      " + RESET
                + "\n" + BLACK_BACKGROUND + "          " + CYAN_BOLD + "Main Menu" + RESET + BLACK_BACKGROUND + "          " + RESET
                + "\n" + CYAN_BOLD +
                "1. Play\n2. Shop\n3. Read game lore\n0. Quit Game" + RESET);

        PythiaSpeaking("This is the main game menu");
        suspensefulDots(PURPLE + "." + RESET);
        sleepThread(PURPLE + "Let's explore it a bit deeper:\n" + RESET);

        //Show Game Lore
        println("1. Play\n2. Shop\n" + CYAN_BOLD + "3. Read game lore\n" + RESET + "0. Quit Game");
        PythiaSpeaking("If you press 3, the storyteller will tell you the tale of this battle. Though it is a most \nfascinating tale, it is not mandatory, therefore reading it will remain your choice.");
        suspensefulDots(PURPLE + "." + RESET);

        //Show Play the Game
        println(CYAN_BOLD + "1. Play\n" + RESET + "2. Shop\n3. Read game lore\n0. Quit Game");
        PythiaSpeaking("Type 1 to enter the game");

        while (true) {
            if (scannerNumber() == 1) {
                //Show Game menu
                break;
            } else {
                PythiaSpeaking("You need to type '1'");
            }
        }


        //Show Shop
        println("1. Play\n" + CYAN_BOLD + "2. Shop\n" + RESET + "3. Read game lore\n0. Quit Game");

        PythiaSpeaking("Type 2 to enter the shop");

        while (true) {
            if (scannerNumber() == 2) {
                //Show shop
                break;
            } else {
                PythiaSpeaking("You need to type '2'");
            }
        }

        println("1. Play\n2. Shop\n3. Read game lore\n" + CYAN_BOLD + "0. Quit Game" + RESET);
        PythiaSpeaking("Lastly, you type '0' in order to quit game");
        suspensefulDots(PURPLE + "." + RESET);


    }


}

