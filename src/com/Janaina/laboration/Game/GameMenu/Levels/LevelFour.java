package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Shop.ShopCategories.Potions;
import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Minotaur;
import com.Janaina.laboration.Resources.Scanners;

import java.util.*;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.playerSpeaking;
import static com.Janaina.laboration.Resources.PrintHandler.sphinxSpeaking;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class LevelFour {
    private boolean foughtMinotaur = false;

    public void playLevelFour(Player player, Inventory inventory, Scanners sc) {

        sleepThread(GRAY + "Level Four." + RESET);
        chillForASecond(1500);

        sleepThread(YELLOW + """
                As the hero ventured into Daedalus's Labyrinth, a weathered sign warned of the dangers lurking in the shadows.\s
                The hero, fueled by courage and determination, embraced the challenge, ready to unravel the labyrinth's mysteries and face\s
                the legendary guardian within.
                """);
        chillForASecond(1000);
        sleepThread(PURPLE + ITALIC + """ 
                "Beware, brave soul, for the Minotaur roams these twisting passages. Only the one who navigates the labyrinth's secrets\s
                and reaches its elusive end shall be granted the desires of their heart,"
                """ + RESET);
        chillForASecond(1000);
        sleepThread(YELLOW + """
                The hero took a deep breath, absorbing the weight of the ancient promise. The labyrinth sprawled before him, a complex \s
                tapestry of stone and shadows. Each corridor seemed to hold a puzzle, and every turn whispered of the legendary Minotaur \s
                that guarded the heart of the maze.
                """ + RESET);

        suspensefulDots(".");
        sc.pressEnter();

        int rightPathsChosen = 0;
        int treasuresFound = 0;
        boolean isPlaying = true;
        List<Integer> randomizePaths = new ArrayList<>();

        randomizePaths.add(1);
        randomizePaths.add(2);
        randomizePaths.add(3);


        while (isPlaying) {
            Collections.shuffle(randomizePaths);

            sleepThread(ORANGE + BOLD + " Choose which path to follow:" + RESET);
            chillForASecond(700);
            System.out.println(YELLOW_DARK + """
                                 ↟
                    ↞ Left     Middle     Right ↠
                       1         2          3
                    """ + RESET);


            switch (randomizePaths.get(sc.scannerNumber() - 1)) {
                case 1 -> {
                    sleepThread(GRAY + "Walking...\n\uD83D\uDC63\n\uD83D\uDC63\n" + RESET);
                    chillForASecond(1500);

                    if (treasuresFound <= 5) {
                        boolean didFind = findTreasureChest(player, inventory, sc);
                        if (didFind) {
                            treasuresFound++;
                        }

                    } else {

                        System.out.println(PURPLE_ISH + "Dead end...");
                        sleepThread(GRAY + "Press enter to go back." + RESET);
                        sc.pressEnterToAttack();

                    }

                }

                case 2 -> {
                    sleepThread(GRAY + "Walking...\n\uD83D\uDC63\n\uD83D\uDC63\n" + RESET);
                    chillForASecond(1500);
                    rightPathsChosen++;
                    System.out.println(PURPLE_ISH + "You're faced with another choice...");
                }

                case 3 -> {
                    boolean didDefeatMonster = battleMonster(player, inventory, sc);

                    if (!didDefeatMonster && player.isAlive()){
                        sleepThread(PURPLE_LIGHT + "Better luck next time" + RESET);
                        suspensefulDots(PURPLE_LIGHT + "." + RESET);
                        isPlaying = false;
                    }

                }

                default -> System.out.println(RED + "Invalid input, try again" + RESET);
            }

            if (rightPathsChosen == 5) {
                sleepThread(YELLOW + """
                        As the hero triumphantly emerged from Daedalus's Labyrinth, the air was thick with anticipation. At the maze's exit, a majestic \s
                        figure awaited. The body and tail of a lion, the face of a woman, and the wings of a bird, The Sphinx. With a wise and enigmatic \s
                        gaze, the mythical creature acknowledged the hero's accomplishment, prepared to unveil a riddle that would determine the next \s
                        steps of the epic quest.
                        """ + RESET);
                chillForASecond(1000);

                if (sphinxMeeting(player, sc)) {
                    sphinxSpeaking("Impressive, indeed. You have proven your intellect and wit, worthy of the journey ahead.");
                    chillForASecond(500);
                    playerSpeaking("Thank you, Sphinx. Now I need not waste time, I must ensure my sister's freedom", player);
                    chillForASecond(500);
                    sphinxSpeaking("""
                            Certainly. Did you know, the young Persephone liked to gather flowers on the lower slopes of Etna and to dance with the nymphs \s
                            on the plain of Enna. However, when Hades abducted her, he tore open a crevice in Mount Etna, establishing an entrance to the \s
                            Underworld where both living and dead can pass.
                            It is your only chance""");
                    chillForASecond(500);
                    playerSpeaking("How will I know where to find it", player);
                    sphinxSpeaking("Search for the signs, and the gods will show you the way, " + player.getName() +
                            "\nAs a token of your victory, I present you with this Nymphic Dust. Cast it upon yourself while envisioning your desired \n" +
                            "destination, and it shall ensure your safe passage.");
                    chillForASecond(500);
                    playerSpeaking("I am forever grateful to you, Sphinx.", player);
                    chillForASecond(500);
                    sphinxSpeaking("May fortune favor your quest, and may you find what your heart seeks most. Now, go forth and confront the \n" +
                            "shadows that dwell within the gates of the underworld.");
                    chillForASecond(500);
                    suspensefulDots(GRAY + "." + RESET);
                    chillForASecond(1000);
                    sleepThread(GRAY + "Level four complete." + RESET);
                    player.unlockNewLevel();
                    chillForASecond(1000);
                    sc.pressEnter();

                } else {
                    sleepThread(PURPLE_LIGHT + "Better luck next time" + RESET);
                    suspensefulDots(PURPLE_LIGHT + "." + RESET);
                }
                isPlaying = false;
            }

        }


    }


    private boolean battleMonster(Player player, Inventory Inventory, Scanners sc) {
        Random random = new Random();
        int randomValue = random.nextInt(1, 10);

        sleepThread(GRAY + "Walking...\n\uD83D\uDC63\n\uD83D\uDC63\n" + RESET);
        chillForASecond(1500);

        if (randomValue < 7) {
            int secondRandomValue = random.nextInt(1, 10);

            System.out.println(RED + BOLD + "Oh no, a monster!" + RESET);
            chillForASecond(1000);

            if (secondRandomValue <= 3 && !foughtMinotaur) {
                Minotaur minotaur = new Minotaur();
                player.act(minotaur, Inventory, sc);
                if (minotaur.isAlive()) {
                    return false;
                } else {
                    foughtMinotaur = true;
                    return true;
                }


            } else {
                Fury fury = new Fury();
                player.act(fury, Inventory, sc);
                return !fury.isAlive();

            }

        } else {
            System.out.println(PURPLE_ISH + "Dead end...");
            sleepThread(GRAY + "Press enter to go back." + RESET);
            sc.pressEnterToAttack();
        }
        return true;

    }


    private boolean findTreasureChest(Player player, Inventory Inventory, Scanners sc) {
        Potions potions = new Potions();
        Random random = new Random();
        int randomValue = random.nextInt(1, 10);

        switch (randomValue) {
            case 1, 2 -> {
                System.out.println(BLUE_LIGHT + "You found a chest!" + RESET);
                chillForASecond(1000);
                System.out.println(GRAY + "Press enter to open." + RESET);
                sc.pressEnterToAttack();
                ShopProducts potionFound = potions.productList.get(random.nextInt(0, potions.productList.size()));
                System.out.println(WHITE + "You found a " + potionFound.getName() + " inside the chest!");
                sleepThread(GRAY + potionFound.getName() + " has been added to your inventory." + RESET);
                Inventory.addToPotionsInventory(potionFound);
                return true;
            }
            case 3, 4, 5, 6 -> {
                System.out.println(BLUE_LIGHT + "You found a chest!" + RESET);
                chillForASecond(1000);
                System.out.println(GRAY + "Press enter to open." + RESET);
                sc.pressEnterToAttack();
                int amount = random.nextInt(10, 20);
                System.out.println(WHITE + "You found " + amount + " gold coins inside the chest!\n" + YELLOW + BOLD + "+ " + amount + " Gold");
                player.setGold(player.getGold() + amount);
                return true;

            }
            case 7, 8 -> {
                System.out.println(BLUE_LIGHT + "You found a chest!" + RESET);
                chillForASecond(1000);
                System.out.println(GRAY + "Press enter to open." + RESET);
                sc.pressEnterToAttack();
                int amount = random.nextInt(20, 30);
                System.out.println("You found " + amount + " gold coins!\n" + YELLOW + BOLD + "+ " + amount + " Gold");
                player.setGold(player.getGold() + amount);
                return true;

            }
            case 9, 10 -> sleepThread(PURPLE_ISH + "Dead end..." + RESET);

        }
        return false;
    }

    private boolean sphinxMeeting(Player player, Scanners sc) {
        sphinxSpeaking("Hello " + player.getName() + ". I must express my admiration for the remarkable journey you have undertaken thus far.");
        chillForASecond(500);
        playerSpeaking("I have come here in search for that which my heart desires most.", player);
        chillForASecond(500);
        sphinxSpeaking("I am well aware of your quest " + player.getName() + ". Your quest to defeat the great Typhon is no trivial feat, and I possess the power to grant you any one wish. Are you resolute in your decision?");
        chillForASecond(500);
        playerSpeaking("No other desire holds sway over my heart, for the happiness of any other wish would pale in comparison to the life of Althea", player);
        chillForASecond(500);
        sphinxSpeaking("A noble decision indeed. However, bear in mind that for me to fulfill your wish, you must first succeed in solving my riddle.");
        chillForASecond(500);
        playerSpeaking("Very well, let me hear the riddle that guards the fulfillment of my destiny.", player);
        chillForASecond(1500);

        sphinxSpeaking("What has one voice but goes on four legs in the morning, two in the afternoon, and three in the evening?");
        System.out.println(GRAY + "Type 'q' to quit level" + RESET);

        while (true) {
            String playersAnswer = sc.scannerText();
            String answer = "human";

            if (playersAnswer.toLowerCase().contains(answer)) {
                System.out.println(PURPLE_LIGHT + BOLD + "Your answer is correct!" + RESET);
                return true;

            } else if (playersAnswer.equalsIgnoreCase("q")) {
                break;

            } else {
                System.out.println(RED + BOLD + "Your answer is incorrect. Try again." + RESET);

            }
        }

        return false;

    }

}
