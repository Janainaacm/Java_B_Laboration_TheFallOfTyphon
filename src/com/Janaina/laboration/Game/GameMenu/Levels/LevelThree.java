package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Medusa;

import java.util.Random;

import static com.Janaina.laboration.Game.Variables.Hero.SpecialAttacks.specialAttackMedusa;
import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.Scanners.*;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class LevelThree {

    public void playLevelThree(Player player, Inventory Inventory) {
        Medusa medusa = new Medusa();
        Random random = new Random();
        /*

        sleepThread(YELLOW + """
                The garden exuded an eerie aura, its moonlit stone statues seemingly coming to life, their watchful eyes unsettling but alluring. The hero marveled at the artistry\s
                that gave these statues an uncanny realism. As he ventured deeper into the garden, an uneasy feeling of being observed never left him. The stone figures, like silent\s
                sentinels, held ancient secrets. Yet, he pressed on, driven by the hope of finding the map to rescue his sister.\s
                """ + RESET);
        suspensefulDots(GRAY + "." + RESET);

         */


        while (true) {
            /*
            System.out.println(PURPLE_DARK + "Pick a location to search:\n1\n2\n3" + RESET);

            switch (scannerNumber()) {
                case 1, 2, 3 -> sleepThread(GRAY + "Searching..." + RESET);
                default -> printRed("Invalid input, please chose from the presented options");
            }

            chillForASecond(2000);
            suspensefulDots(GREEN_LIGHT + ".");
            sleepThread(GREEN_DARK + ITALIC + "Do you like them?" + RESET);
            chillForASecond(1000);
            System.out.println(YELLOW + BOLD + "Turn around." + RESET);
            pressEnterToAttack();
            chillForASecond(1000);
            medusaSpeaking("Well? What do you think of my beloved collection " + player.getName() + "?");
            playerSpeaking("How do you know my name?", player);
            chillForASecond(1000);
            medusaSpeaking("I asked you a question, boy");
            suspensefulDots(GRAY + "." + RESET);
            chillForASecond(1000);
            System.out.println(YELLOW + "1. 'I'm not here for your collection of lifeless victims, beast'\n2. Stay quiet." + RESET);

            switch (scannerNumber()) {
                case 1 -> playerSpeaking("I'm not here for your collection of lifeless victims, beast", player);
                case 2 -> playerSpeaking("...", player);
            }

            medusaSpeaking("Well, no matter. Your pretty face will make a lovely addition");
            suspensefulDots(GRAY + "." + RESET);

            player.act(medusa, Inventory);
            if (medusa.isAlive()) {
                sleepThread(PURPLE_LIGHT + "Better luck next time" + RESET);
                suspensefulDots(PURPLE_LIGHT + "." + RESET);
                break;
            }

            sleepThread(YELLOW + """
                    With Medusa's dreadful presence banished from the garden, the hero's determination swelled.\s
                    The time had come to search for the hidden map within The Scaled Garden of Stone.
                    """);
            suspensefulDots("." + RESET);

             */


            boolean messageFound = false;
            boolean keyFound = false;
            boolean searching = true;
            int goldFound = 0;

            do {

                System.out.println(PURPLE_LIGHT + BOLD + "Pick where to search:\n" + RESET + PURPLE_DARK +
                        "1. In the plants\n2. In the conservatory\n3. By the fountain\n4. Medusa's dead body\n\n" +
                        "0. Give up" + RESET);


                switch (scannerNumber()) {
                    case 1 -> {
                        sleepThread(GRAY + "Searching..." + RESET);
                        chillForASecond(1500);
                        if (goldFound < 5) {
                            int amount = random.nextInt(2, 10);
                            System.out.println("You found " + amount + " gold coins!\n" + YELLOW + BOLD + "+ " + amount + " Gold");
                            player.setGold(player.getGold() + amount);
                            goldFound++;
                        } else {
                            System.out.println(PURPLE_DARK + "You didn't find anything");
                        }
                        pressEnter();
                    }

                    case 2 -> {
                        sleepThread(GRAY + "Walking into the conservatory..." + RESET);
                        chillForASecond(1500);
                        boolean insideConservatory = true;

                        do {
                            System.out.println(PURPLE_LIGHT + BOLD + "Pick where to search:\n" + PURPLE_DARK +
                                    "1. In the desk\n2. In the supply closet\n3. Beneath the loose floorboard\n4. By the window\n\n0. Go back" + RESET);

                            switch (scannerNumber()) {
                                case 1 -> {
                                    sleepThread(GRAY + "Searching..." + RESET);
                                    chillForASecond(1500);

                                    boolean decrypted = scytale();
                                    if (decrypted) {
                                        messageFound = true;
                                    }

                                    pressEnter();
                                }

                                case 2 -> {
                                    sleepThread(GRAY + "Searching..." + RESET);
                                    chillForASecond(1500);
                                    if (goldFound < 5) {
                                        int amount = random.nextInt(2, 10);
                                        System.out.println("You found " + amount + " gold coins!\n" + YELLOW + BOLD + "+ " + amount + " Gold");
                                        player.setGold(player.getGold() + amount);
                                        goldFound++;
                                    } else {
                                        System.out.println(PURPLE_DARK + "You didn't find anything");

                                    }
                                    pressEnter();
                                }

                                case 3 -> {
                                    if (keyFound) {
                                        System.out.println(GRAY + "Press enter to use key." + RESET);
                                        pressEnterToAttack();

                                        suspensefulDots(PURPLE_ISH + "." + RESET);
                                        System.out.println(PURPLE_LIGHT + BOLD + "You found the map!" + RESET);
                                        pressEnter();
                                        sleepThread(YELLOW + """
                                                With the coveted map in hand, the hero's purpose gained newfound clarity. The next destination beckoned, and with a resolute heart, the \s
                                                hero set forth on the path that would lead him one step closer to rescuing his sister from the clutches of darkness.
                                                """);

                                        suspensefulDots(GRAY + "." + RESET);
                                        sleepThread(GRAY + "Level three complete." + RESET);
                                        player.unlockNewLevel();
                                        chillForASecond(1000);
                                        pressEnter();
                                        insideConservatory = false;
                                        searching = false;

                                    } else {
                                        sleepThread(GRAY + "Searching..." + RESET);
                                        chillForASecond(1500);
                                        System.out.println(BLUE_LIGHT + "You found a chest!" + RESET);
                                        chillForASecond(1500);
                                        System.out.println("There is a strange phrase engraved above the keyhole");
                                        chillForASecond(1500);
                                        sleepThread(PURPLE_LIGHT + "T iehsd eads leekvnrea pytwe iintottonhs  i yineos yu terhh" + RESET);

                                        suspensefulDots(GRAY + ".");
                                        chillForASecond(1000);
                                        System.out.println(RED + "The chest is locked." + RESET);
                                        chillForASecond(1500);
                                        pressEnter();
                                    }


                                }
                                case 4 -> {
                                    sleepThread(GRAY + "Searching..." + RESET);
                                    chillForASecond(1500);
                                    sleepThread(PURPLE_DARK + "You didn't find anything besides four snakes engraved on the windshield" + RESET);
                                    chillForASecond(1500);
                                    pressEnter();

                                }
                                case 0 -> insideConservatory = false;
                                default -> printRed("Invalid input, please chose from the presented options");

                            }

                        } while (insideConservatory);


                    }

                    case 3 -> {
                        sleepThread(GRAY + "Searching..." + RESET);
                        chillForASecond(1500);

                        if (goldFound < 5) {
                            sleepThread(PURPLE_ISH + "Something shimmering from the bottom of the fountain caught your attention" + RESET);
                            suspensefulDots(GRAY + "." + RESET);
                            int amount = random.nextInt(2, 10);
                            System.out.println("You found " + amount + " gold coins!\n" + YELLOW + BOLD + "+ " + amount + " Gold");
                            player.setGold(player.getGold() + amount);
                            goldFound++;
                            chillForASecond(1000);
                        }

                        sleepThread(PURPLE_LIGHT + "A depiction of four keys can be seen engraved in the cement, underneath the flowing water" + RESET);
                        pressEnter();

                    }

                    case 4 -> {
                        if (messageFound) {
                            suspensefulDots(GRAY + "." + RESET);
                            sleepThread(PURPLE_ISH + "What are you waiting for?");
                            chillForASecond(1000);
                            sleepThread(RED + "Chop the bitch's head off" + RESET);
                            chillForASecond(500);

                            pressEnter();
                            suspensefulDots(GRAY + "." + RESET);
                            chillForASecond(500);
                            sleepThread(PURPLE_ISH + "* gruesome details *");
                            suspensefulDots(GRAY + "." + RESET);
                            chillForASecond(1500);

                            System.out.println(PURPLE_LIGHT + "You have the key." + RESET);
                            chillForASecond(1500);

                            sleepThread(PURPLE_ISH + "You have unlocked a new special attack." + RESET);
                            specialAttackMedusa(player);
                            chillForASecond(1500);

                            keyFound = true;

                        } else {
                            sleepThread(GRAY + "Searching..." + RESET);
                            chillForASecond(1500);
                            System.out.println(PURPLE_DARK + "You didn't find anything");
                            chillForASecond(500);
                        }
                        pressEnter();

                    }

                    case 0 -> {
                        sleepThread(PURPLE_LIGHT + "Better luck next time" + RESET);
                        suspensefulDots(PURPLE_LIGHT + "." + RESET);
                        searching = false;
                    }
                    default -> printRed("Invalid input, please chose from the presented options");

                }
            } while (searching);
            break;
        }
    }

    private boolean scytale() {

        boolean usingScytale = true;
        System.out.println(BLUE_PASTEL + "You found a Scytale!" + RESET);

        do {
            chillForASecond(1000);
            System.out.println(BLUE_PASTEL + "1. Use\n0. Go back" + RESET);


            switch (scannerNumber()) {
                case 1 -> {
                    System.out.println(PURPLE_ISH + "Enter text: " + RESET);
                    String encryptedMessage = scannerText();

                    System.out.println(PURPLE_ISH + "Enter key: " + RESET);
                    int key = scannerOnlyOneNumber();

                    String decryptedMessage = decryptScytale(encryptedMessage, key);
                    System.out.println(PURPLE_ISH + "Decrypted message: " + PURPLE_DARK + decryptedMessage + RESET);


                    if (decryptedMessage.equals("The key to your salvation is hidden within the serpents eye")) {
                        return true;
                    } else {
                        usingScytale = false;
                    }

                }
                case 0 -> usingScytale = false;
                default -> printRed("Invalid input, please chose from the presented options");
            }

        } while (usingScytale);

        return false;
    }

    private String decryptScytale(String encryptedMessage, int key) {
        int numRows = (int) Math.ceil((double) encryptedMessage.length() / key);
        char[][] matrix = new char[numRows][key];
        int index = 0;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < key; col++) {
                if (index < encryptedMessage.length()) {
                    matrix[row][col] = encryptedMessage.charAt(index);
                    index++;
                }
            }
        }

        StringBuilder decryptedMessage = new StringBuilder();
        for (int col = 0; col < key; col++) {
            for (int row = 0; row < numRows; row++) {
                if (matrix[row][col] != 0) {
                    decryptedMessage.append(matrix[row][col]);
                }
            }
        }

        return decryptedMessage.toString();
    }

}
