package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Cerberus;

import java.util.Objects;
import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.guardianSpeaking;
import static com.Janaina.laboration.Resources.PrintHandler.playerSpeaking;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;
import static com.Janaina.laboration.Resources.Scanners.scannerYesOrNo;
import static com.Janaina.laboration.Resources.TextDelay.*;


public class LevelFive {

    public void playLevelFive(Player player, Inventory Inventory) {
        Cerberus cerberus = new Cerberus();

        System.out.println("\uD80C\uDF5D");
        boolean playingLevel = true;
        while (true) {
            /*

            sleepThread(YELLOW + """
                    Upon arriving at the fiery slopes of Mount Etna, the hero felt the ancient stones beneath their feet resonate with tales \s
                    of trials and challenges. Amidst the rugged terrain, a shimmering figure emerged—a mystical guardian, its form flickering \s
                    like the flames within the volcano.
                    """);
            playerSpeaking("My name is " + player.getName() + ", I wish to gain passage into the underworld through the crevice", player);
            chillForASecond(1000);
            guardianSpeaking("Brave traveler, you stand before the Guardian of the Crevice. To earn passage to the \nUnderworld, you must prove your understanding of balance.");
            chillForASecond(1000);
            playerSpeaking("I will do whatever it takes.", player);
            chillForASecond(1000);
            sleepThread(YELLOW + """
                    In the guardian's outstretched hand appeared an array of tokens, each radiating a magical glow and marked with values ranging from 1 to 10. \s
            
                    """);
            chillForASecond(1000);
            guardianSpeaking("Your task is simple, divide these tokens into two piles until the scale is neutral, ensuring that the weight on each side \nremained perfectly in equilibrium.");
            chillForASecond(1000);

            System.out.println(PURPLE_ISH + "Remember to keep track of both piles, only evaluate when you are sure they are the same" + RESET);

            if (!challenge()) {
                System.out.println("you lost");
                playingLevel = false;
            }

            guardianSpeaking("You have displayed wisdom, and the scales now rest in perfect balance. Proceed, brave one, to the \nrealm below. May your journey be guided by the equilibrium you have mastered.");
            chillForASecond(1000);
            sleepThread(YELLOW + """
                    With these words, the crevice before the hero began to shimmer, unveiling the passage to the Underworld. Stepping forward, \s
                    marked by the met challenge and achieved balance, the hero descended into the unknown depths below.
                    """);

             */

            player.act(cerberus, Inventory);
            if (cerberus.isAlive() && player.isAlive()) {
                sleepThread(PURPLE_LIGHT + "Better luck next time" + RESET);
                suspensefulDots(PURPLE_LIGHT + "." + RESET);
                playingLevel = false;
            }

            //He made it in yay


        }


    }

    private boolean challenge() {
        Random random = new Random();

        int virtuousWeight = 0;
        int shadowWeight = 0;

        System.out.println(GREEN_DIRTY + ITALIC + "Your first number is:");

        while (true) {

            suspensefulDots(".");
            int randomWeight = random.nextInt(1, 10);
            System.out.println(GREEN_LIGHT + randomWeight + "!" + RESET);
            chillForASecond(1000);

            System.out.println(GREEN_LIGHT + BOLD + "Place it: \n" + RESET + GREEN_LIGHT + "1. Place virtuous token\n2. Place shadowy token" + RESET);

            switch (scannerNumber()) {
                case 1 -> {
                    System.out.println(GRAY + ITALIC + randomWeight + " has been added on the virtuous scale" + RESET);
                    virtuousWeight += randomWeight;
                }

                case 2 -> {
                    System.out.println(GRAY + ITALIC + randomWeight + " has been added on the shadowy scale" + RESET);
                    shadowWeight += randomWeight;
                }

                default -> System.out.println(RED + BOLD + "Invalid choice. Please choose again." + RESET);
            }
            chillForASecond(1500);

            System.out.println(GREEN_LIGHT + "1. Get a new token\n2. Evaluate balance\n0. Quit level" + RESET);

            switch (scannerNumber()) {
                case 1 -> System.out.println(GREEN_DIRTY + ITALIC + "Your number is:");

                case 2 -> {
                    System.out.println(GREEN_LIGHT + ITALIC + "Are you sure?" + RESET);
                    String choice = scannerYesOrNo();
                    if (Objects.equals(choice, "yes")) {
                        chillForASecond(1000);
                        return evaluateBalance(virtuousWeight, shadowWeight);
                    }
                }
                case 0 -> {
                    sleepThread(GRAY + ITALIC + "Quitting level..." + RESET);
                    return false;
                }
                default -> System.out.println(RED + BOLD + "Invalid choice. Please choose again." + RESET);

            }


        }

    }

    private boolean evaluateBalance(int virtuousWeight, int shadowWeight) {
        int difference = Math.abs(virtuousWeight - shadowWeight);

        if (difference == 0) {
            System.out.println(PURPLE + "Perfect balance! You can now enter the gates to the Underworld." + RESET);
            return true;
        } else {
            sleepThread(RED + ITALIC + "Imbalance detected. You failed." + RESET);
            return false;
        }
    }

}




