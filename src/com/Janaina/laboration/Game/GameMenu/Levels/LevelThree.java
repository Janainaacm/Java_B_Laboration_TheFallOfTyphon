package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Characters;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Medusa;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;

import java.util.Objects;
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
        After defeating medusa, search the room.
        You can search in 4 spots, in one you find the chest
        in another you find some sort of cipher, maybe something to do with the greek alphabet
        to open the chest you need to use the cipher to decipher something

        "Serpent" = ΟΦΙΣ
        Φίδι

        The key to your salvation lies within the eye of the snake.
        Το κλειδί για τη σωτηρία σας βρίσκεται στα μάτια των φιδιών.

        OR

        use cipher to decode: T iehsd eads leekvnrea pytwe iintottonhs  i yineos yu terhh
        key: 4



         */

        scytale();
        sleepThread(YELLOW + """
                The garden exuded an eerie aura, its moonlit stone statues seemingly coming to life, their watchful eyes unsettling but alluring. The hero marveled at the artistry\s
                that gave these statues an uncanny realism. As he ventured deeper into the garden, an uneasy feeling of being observed never left him. The stone figures, like silent\s
                sentinels, held ancient secrets. Yet, he pressed on, driven by the hope of finding the map to rescue his sister.\s
                """ + RESET);
        suspensefulDots(GRAY + "." + RESET);

        while (true) {

            System.out.println(PURPLE_DARK + "Pick a location to search:\n1\n2\n3" + RESET);

            switch (scannerNumber()) {
                case 1, 2, 3 -> sleepThread(GRAY + "Searching..." + RESET);
                default -> printRed("Invalid input, please chose from the presented options");
            }

            chillForASecond(2000);
            suspensefulDots(GREEN_LIGHT + ".");
            sleepThread(GREEN_LIGHT + ITALIC + "Do you like them?" + RESET);
            chillForASecond(1000);
            System.out.println(YELLOW + BOLD + "Turn around." + RESET);
            pressEnterToAttack();
            chillForASecond(1000);
            medusaSpeaking("Well? What do you think of my beloved collection " + player.getName() + "?");
            playerSpeaking("How do you know my name?", player);
            chillForASecond(1000);
            medusaSpeaking("I asked you a question, boy");
            sleepThread(GRAY + "...");
            System.out.println("1. 'I'm not here for your collection of lifeless victims, beast'\n2. Stay quiet.");

            switch (scannerNumber()) {
                case 1 -> playerSpeaking("I'm not here for your collection of lifeless victims, beast", player);
                case 2 -> playerSpeaking("...", player);
            }

            medusaSpeaking("Well, no matter. Your pretty face will make a lovely addition");
            suspensefulDots(GRAY + "." + RESET);

            player.act(medusa, Inventory);
            if (medusa.isAlive()) {
                sleepThread(PURPLE_LIGHT + "Better luck next time" + RESET);
                suspensefulDots(PURPLE_LIGHT + "..." + RESET);
                break;
            }

            sleepThread(PURPLE_ISH + "You have unlocked a new special attack." + RESET);
            specialAttackMedusa(player);
            pressEnter();

            //Lore

            System.out.println(PURPLE_DARK + "Pick a location to search:\n1. In the plants\n2. In the conservatory\n3. By the fountain\n4. " + RESET);

            boolean searching = true;
            int goldFound = 0;
            do {
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
                            System.out.println(PURPLE_ISH + "You didn't find anything :(");
                        }

                    }
                    case 2 -> {
                        //Walking inside the conservatory
                        System.out.println();

                    }
                    case 3 -> {

                    }
                    case 4 -> {

                    }
                    case 0 -> searching = false;
                    default -> printRed("Invalid input, please chose from the presented options");

                }
            } while (searching);


        }


    }

    private void scytale() {
        //T iehsd eads leekvnrea pytwe iintottonhs  i yineos yu terhh
        boolean usingScytale = true;
        do {
            System.out.println(BLUE_PASTEL + "You found a Scytale!\n1. Use\n0. Go back" + RESET);

            switch (scannerNumber()) {
                case 1 -> {
                    System.out.println(PURPLE_ISH + "Enter text: " + RESET);
                    String encryptedMessage = scannerText();

                    System.out.println(PURPLE_ISH + "Enter key: " + RESET);
                    int key = scannerNumber();

                    String decryptedMessage = decryptScytale(encryptedMessage, key);
                    System.out.println(PURPLE_ISH + "Decrypted message: " + PURPLE_DARK + decryptedMessage + RESET);
                    pressEnter();
                    usingScytale = false;

                }
                case 0 -> usingScytale = false;
                default -> printRed("Invalid input, please chose from the presented options");
            }

        } while (usingScytale);
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
