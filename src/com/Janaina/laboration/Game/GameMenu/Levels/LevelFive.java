package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Cerberus;

import java.util.Objects;
import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.Scanners.*;
import static com.Janaina.laboration.Resources.TextDelay.*;


public class LevelFive {

    private final String[] CORRECT_SEQUENCE = {"zeus", "hestia", "poseidon", "hera", "demeter"};

    private final String[] SYMBOL_MEANINGS = {"God of Thunder", "Goddess of the Earth", "God of the Sea", "God of Women and Family", "Goddess of Harvest"};


    public void playLevelFive(Player player, Inventory inventory) {
        Cerberus cerberus = new Cerberus();

        while (true) {

            sleepThread(YELLOW + """
                    Upon arriving at the fiery slopes of Mount Etna, the hero felt the ancient stones beneath their feet resonate with tales of trials and challenges. Amidst \s
                    the rugged terrain, a shimmering figure emerged—a mystical guardian, its form flickering like the flames within the volcano.
                    """);
            playerSpeaking("My name is " + player.getName() + ", I wish to gain passage into the underworld through the crevice", player);
            chillForASecond(1000);
            guardianSpeaking("Brave traveler, you stand before the Guardian of the Crevice. To earn passage to the \nUnderworld, you must prove your understanding of balance.");
            chillForASecond(1000);
            playerSpeaking("I will do whatever it takes.", player);
            chillForASecond(1000);
            sleepThread(YELLOW + """
                    \s
                    In the guardian's outstretched hand appeared an array of tokens, each radiating a magical glow and marked with values ranging from 1 to 10. \s
                    """);
            chillForASecond(1000);
            guardianSpeaking("Your task is simple, divide these tokens into two piles until the scale is neutral, ensuring that the weight on each side \nremained perfectly in equilibrium.");
            chillForASecond(1000);

            System.out.println(PURPLE_ISH + "Remember to keep track of both piles, only evaluate when you are sure they are the same" + RESET);
            pressEnter();



            if (!challenge()) {
                break;
            }

             player.setStrength(100);

            guardianSpeaking("You have displayed wisdom, and the scales now rest in perfect balance. Proceed, brave one, to the \nrealm below. May your journey be guided by the equilibrium you have mastered.");
            chillForASecond(1000);
            sleepThread(YELLOW + """
                    With these words, the crevice before the hero began to shiver, unveiling the passage to the Underworld. Advancing into the mountain's depths, \s
                    drawings could be seen carved into the cold stone walls of the crevice, telling a tale old as time: Chronicles of the great titans Cronus and Rhea, \s
                    the fateful uprising where their six children emerged triumphant in the defeat of their own father, and marking the beginning of the great Olympus.
                    """);
            suspensefulDots(GRAY + "." + RESET);
            chillForASecond(1000);
            sleepThread(RED + "Is that mortal flesh i smell? It has been ages since my last.");
            playerSpeaking("Allow me passage through the gates! I will not repeat myself.", player);
            chillForASecond(1000);
            sleepThread(YELLOW + "A horrid sight emerged from the shadows. The three-headed beast, entrusted with guarding the gates");
            chillForASecond(1000);
            cerberusSpeaking("I will enjoy ripping you apart, and devouring you piece by piece");
            chillForASecond(1000);

            player.act(cerberus, inventory);
            if (cerberus.isAlive()) {
                sleepThread(PURPLE_LIGHT + "Better luck next time" + RESET);
                suspensefulDots(PURPLE_LIGHT + "." + RESET);
                break;

            }

            suspensefulDots(GRAY + "." + RESET);

            sleepThread(YELLOW + """
                    Surrounding the colossal gates, five majestic statues stood tall, each radiating the magical energy sealing the entrance. In the center stood a small podium \s
                    bearing five crystals, each inscribed with a word: Hera, Poseidon, Zeus, Demeter, and Hestia – the names of the celestial children of Cronus and Rhea, excluding \s
                    the banished Hades. At the base of each statue, concise descriptions posed a challenge demanding the names to be matched with their rightful bearers."
                    """ + RESET);

            suspensefulDots(GRAY + "." + RESET);
            chillForASecond(1000);
            openTheGates();


            sleepThread(YELLOW + "\nThe gates yielded open with a loud creek, exposing a wall covered in shiny black\n" +
                    "sludge. Without hesitation, the hero lunged towards the unknown, allowing the substance to devour\n" +
                    "him.");

            suspensefulDots(GRAY + ".");
            sleepThread("You have completed level five." + RESET);
            player.unlockNewLevel();
            pressEnter();
            break;
        }


    }

    private boolean challenge() {
        Random random = new Random();

        int virtuousWeight = 0;
        int shadowWeight = 0;

        System.out.println(GREEN_DIRTY + ITALIC + "Your first number is:");

        while (true) {
            boolean choosing = true;

            suspensefulDots(".");
            int randomWeight = random.nextInt(1, 10);
            System.out.println(GREEN_LIGHT + randomWeight + "!" + RESET);
            chillForASecond(1000);
            System.out.println(GREEN_LIGHT + BOLD + "Place it: \n" + RESET + GREEN_LIGHT + "1. Place virtuous token\n2. Place shadowy token" + RESET);


            while (choosing) {

                switch (scannerNumber()) {
                    case 1 -> {
                        System.out.println(GRAY + ITALIC + randomWeight + " has been added on the virtuous scale" + RESET);
                        virtuousWeight += randomWeight;
                        choosing = false;
                    }

                    case 2 -> {
                        System.out.println(GRAY + ITALIC + randomWeight + " has been added on the shadowy scale" + RESET);
                        shadowWeight += randomWeight;
                        choosing = false;
                    }

                    default -> System.out.println(RED + BOLD + "Invalid choice. Please choose again." + RESET);
                }
            }

            chillForASecond(1500);
            choosing = true;

            System.out.println(GREEN_LIGHT + "1. Get a new token\n2. Evaluate balance\n0. Quit level" + RESET);

            while (choosing) {
                switch (scannerNumber()) {
                    case 1 -> {
                        System.out.println(GREEN_DIRTY + ITALIC + "Your number is:");
                        choosing = false;
                    }

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

    private void openTheGates() {
        String[] playerCombination = new String[CORRECT_SEQUENCE.length];

        while (true) {

        System.out.println(GRAY + "Enter the correct Gods name:" + RESET);

            for (int i = 0; i < CORRECT_SEQUENCE.length; i++) {
                System.out.println(PURPLE + SYMBOL_MEANINGS[i] + ": " + RESET);
                playerCombination[i] = scannerText();
            }

            if (isSigilCombinationCorrect(playerCombination)) {
                System.out.println(PINK_PASTEL + ITALIC + "Correct!" + RESET);
                chillForASecond(1000);
                break;

            } else {
                System.out.println(GRAY + "Incorrect combination. The ciphers remain unsolved." + RESET);

            }

        }
    }

    private boolean isSigilCombinationCorrect(String[] sigilCombination) {
        for (int i = 0; i < CORRECT_SEQUENCE.length; i++) {
            if (!sigilCombination[i].toLowerCase().equals(CORRECT_SEQUENCE[i])) {
                return false;
            }
        }
        return true;
    }

}




