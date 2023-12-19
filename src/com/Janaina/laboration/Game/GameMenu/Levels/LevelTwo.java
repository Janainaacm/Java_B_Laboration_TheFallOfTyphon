package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;
import com.Janaina.laboration.Resources.Scanners;

import java.util.*;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class LevelTwo {
    private static final int LEVEL_THREE = 3;

    public void playLevelTwo(Player player, Inventory inventory, Scanners sc, DBConnection db) {
        Siren siren = new Siren();

        while (true){

        sleepThread(YELLOW + "As the sun begins to rise over the endless horizon of the Sea of Serene Whispers, " + player.getName() + " stands at the serene beach, surrounded by the gentle lapping of the\n waves and the sweet scent of saltwater in the air. The soft, melodic hum of the Sirens resonates in the background, enticing unwary travelers with their enchanting song.\nClose to the edge of the sea a silhouette can be seen roaming around in the sand" + RESET);
        chillForASecond(1000);
        playerSpeaking("Good day sir! You wouldn't by any chance know the easiest way to get across the sea?", player);

        System.out.println(GREEN_DARK + BOLD + UNDERLINED + "Krille:" + RESET);
        sleepThread(GREEN_DARK + "Ahoy adventurer, I'm Krille! Well you could always try to build a raft, \nthough I would consider allowing you passage across the waters on my boat in exchange for your help with something" + RESET);
        playerSpeaking("What is it you need help with, fisherman?", player);
        System.out.println(GREEN_DARK + BOLD + UNDERLINED + "Krille:" + RESET);
        sleepThread(GREEN_DARK + "32 years ago, My father buried my mothers old wedding ring somewhere on this \nbeach after her passing. Now I need to find it in order to ask the love of my life for her hand");
        suspensefulDots(".");
        sleepThread("Though i can't quite seem to remember the exact location");
        suspensefulDots(".");
        sleepThread("hehe\n" + RESET);
        chillForASecond(1000);
        System.out.println(WHITE + "Would you like to help the old fisherman Krille find his wedding ring?" + RESET);

        if (Objects.equals(sc.scannerYesOrNo().toLowerCase(), "yes")) {
            playerSpeaking("Alright fisherman, I'll find your ring for you", player);
            System.out.println(GREEN_DARK + BOLD + UNDERLINED + "Krille:" + RESET);
            sleepThread(GREEN_DARK + "I would be eternally grateful" + RESET);

            helpKrille(sc);
            System.out.println(GREEN_DARK + BOLD + UNDERLINED + "Krille:" + RESET);
            sleepThread(GREEN_DARK + "My mothers ring! You found it! Now I can be with my love forever!" + RESET);
            chillForASecond(1000);
            sleepThread(GREEN_DARK + "As promised, here is my boat" + RESET);
            playerSpeaking("Thank you, I shall return it after finishing my quest of defeating Typhon!", player);
            suspensefulDots(GREEN_DARK + ".");
            System.out.println(GREEN_DARK + BOLD + UNDERLINED + "Krille:" + RESET);
            sleepThread(GREEN_DARK + "Defeating who now-" + RESET);
            suspensefulDots(GREEN_DARK + ".");
            sleepThread(GREEN_DARK + "Yeah I wont hold my breath" + RESET);
            chillForASecond(500);
            sleepThread(GREEN_DARK + "Good luck with that bud\nI must insist that you continue your travels before this weather gets worse, may we meet again adventurer" + RESET);
            playerSpeaking("May we meet again fisherman", player);
            suspensefulDots(GRAY + "." + RESET);

        } else {
            buildBoat(player, sc);

            playerSpeaking("Finally! That took forever!\nThere is no more time to waste, time to face the unknown", player);
            suspensefulDots(".");
        }

        System.out.println(BLUE + BOLD + "Setting sail" + RESET);
        sleepThread(BLUE + " 〰〰〰\uD80C\uDE9D〰〰〰\n\uD80C\uDD9B ˚｡ ° ˚｡ ° ˚｡ ° \uD80C\uDD9E\n" +
                " ˚｡ °  ˚｡ ° ˚｡ ° \uD80C\uDD9D      \n" +
                " ˚｡ °\uD80C\uDD9D ˚｡ °\uD80C\uDD9E˚｡ °");
        suspensefulDots(BLUE + "." + RESET);
        sleepThread(YELLOW + """
                As the hero sets sail across the Sea of Serene Whispers, all seems calm and promising. The sun casts a warm, golden glow, and hope fills the air.\s
                 But suddenly, the serene sea turns turbulent. Dark clouds gather, the wind howls, and waves surge.\s
                Amid the tempest, the haunting songs of sirens fill the air, enchanting yet perilous. Their melodies beckon the hero towards treacherous rocks. \s
                Alone at the helm, the hero battles the storm, struggling to stay on course. The sirens' songs become a cacophony of danger.\s
                """ + RESET);

        sirenSpeaking("Oh, brave traveler, come hither to us. We are the guardians of these waters, the keepers of ancient secrets.");
        playerSpeaking("I seek passage across these treacherous seas! Your songs won't sway me.", player);
        sirenSpeaking("Oh, mortal soul, what treasure do you carry? What dreams and hopes do you keep? Share them with us. Reveal your deepest desires. We can make them come true.");
        playerSpeaking("My only treasure is the love for my sister, lost to the monstrous Typhon. Your promises won't deter me from my quest!", player);
        sirenSpeaking("You dare defy us, mortal? We are the daughters of the sea, and we decide your fate!");
        playerSpeaking("So be it! If it's a battle you want, it's a battle you'll get! Prepare to face my wrath, sirens!", player);


            player.act(siren, inventory, sc, db);
            if (siren.isAlive()) {
                break;
            }
            siren.revive();

            player.act(siren, inventory, sc, db);
            if (siren.isAlive()) {
                break;
            }
            siren.revive();

            player.act(siren, inventory, sc, db);
            if (siren.isAlive()) {
                break;
            }

            db.addSpecialAttack(player, siren.getName());

            sc.pressEnter();
            sleepThread(YELLOW + """
                    Having defeated the sirens in a fierce battle, the hero sails onward, determined to reach the Scaled Garden of Stone. \s
                    With the mystical sea guardians now vanquished, his boat plows through the turbulent waves, bringing him one step closer to rescuing his sister from Typhon's clutches. \s
                    The hero's resolve remains unbroken as he presses forward, the map to Althea's location still a distant goal on the horizon.
                    """);

            chillForASecond(1000);
            sc.pressEnter();

            player.setAvailableLevels(LEVEL_THREE);
            break;

        }


    }

    private void buildBoat(Player player, Scanners sc) {
        System.out.println(GRAY + "rude, but whatever" + RESET);
        chillForASecond(1000);
        Random random = new Random();
        List<Integer> sandPiles = new ArrayList<>();

        sandPiles.add(1);
        sandPiles.add(2);
        sandPiles.add(3);
        int correctPile = sandPiles.get(random.nextInt(sandPiles.size()));

        System.out.println(YELLOW + "Dig up the correct sand pile to find the materials you need for the raft" + RESET);

        while (true) {
            System.out.println(YELLOW + BOLD + "Select which sand pile you want to dig up" + RESET);
            for (int i = 0; i < sandPiles.size(); i++) {
                System.out.println(YELLOW + (i + 1) + RESET);
            }

            int choice = sc.scannerNumber();

            if (choice < 1 || choice > sandPiles.size()) {
                System.out.println(GRAY + "Invalid choice, please try again" + RESET);
            } else {
                int chosenPile = sandPiles.get(choice - 1); // Adjust the index

                System.out.print(GRAY + "Press enter to dig: " + RESET);
                digging(20, sc);

                if (chosenPile == correctPile) {
                    System.out.println(GRAY + "Correct!" + RESET);
                    break;
                } else {
                    sandPiles.remove(Integer.valueOf(chosenPile));
                    System.out.println(GRAY + "Wrong pile" + RESET);
                }
            }
        }

        System.out.println(YELLOW + "You have the materials needed!\n Now since you refused to help an old man you get extra work.");
        sleepThread("Build your boat, " + player.getName());
        System.out.print(GRAY + "Press enter to build: " + RESET);
        digging(50, sc);
    }

    private void helpKrille(Scanners sc) {
        Random random = new Random();
        List<Integer> sandPiles = new ArrayList<>();

        sandPiles.add(1);
        sandPiles.add(2);
        sandPiles.add(3);
        int correctPile = sandPiles.get(random.nextInt(sandPiles.size()));

        System.out.println(YELLOW + "Dig up the correct sand pile to find Krille's wedding ring" + RESET);

        while (true) {
            System.out.println(YELLOW + BOLD + "Select which sand pile you want to dig up" + RESET);
            for (int i = 0; i < sandPiles.size(); i++) {
                System.out.println(YELLOW + (i + 1) + RESET);
            }

            int choice = sc.scannerNumber();

            if (choice < 1 || choice > sandPiles.size()) {
                System.out.println(GRAY + "Invalid choice, please try again" + RESET);
            } else {
                int chosenPile = sandPiles.get(choice - 1); // Adjust the index

                System.out.print(GRAY + "Press enter to dig: " + RESET);
                digging(20, sc);

                if (chosenPile == correctPile) {
                    System.out.println(GRAY + "Correct!" + RESET);
                    break;
                } else {
                    sandPiles.remove(Integer.valueOf(chosenPile));
                    System.out.println(GRAY + "Wrong pile" + RESET);
                }
            }
        }

    }


    private void digging(int requiredMaterials, Scanners sc) {
        int materialsCollected = 0;

        while (materialsCollected < requiredMaterials) {
            sc.pressEnterNoText();
            materialsCollected++;

            if (materialsCollected == 2){
                System.out.println(GRAY + "Good job" + RESET);
            }

            if (materialsCollected == 5){
                System.out.println(GRAY + "Just like that" + RESET);
            }

            if (materialsCollected == 10){
                System.out.println(GRAY + "Keep going" + RESET);
            }

            if (materialsCollected == 15){
                System.out.println(GRAY + "Almost there" + RESET);
            }

            if (materialsCollected == 21){
                System.out.println(GRAY + "Or...?" + RESET);
            }

            if (materialsCollected == 30){
                System.out.println(GRAY + "Lalalala" + RESET);
            }

            if (materialsCollected == 40){
                System.out.println(GRAY + "Being a nice person gets you further in life fyi" + RESET);
            }

            if (materialsCollected == 49){
                System.out.println(GRAY + "Okay enough." + RESET);
            }
        }
    }



}
