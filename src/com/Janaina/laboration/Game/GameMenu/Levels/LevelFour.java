package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Minotaur;

import java.util.*;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.sphinxSpeaking;
import static com.Janaina.laboration.Resources.Scanners.*;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class LevelFour {
    private boolean foughtMinotaur = false;

    public void playLevelFour(Player player, Inventory Inventory){

        Random random = new Random();

        sleepThread(GRAY + "Level Four." + RESET);
        chillForASecond(1500);

        sleepThread(YELLOW + """
                As the hero ventured into Daedalus's Labyrinth, a weathered sign warned of the dangers lurking in the shadows.\s
                The hero, fueled by courage and determination, embraced the challenge, ready to unravel the labyrinth's mysteries and face the legendary guardian within.
                """);
        chillForASecond(1000);
        System.out.println(PURPLE + ITALIC + """ 
                "Beware, brave soul, for the Minotaur roams these twisting passages. Only the one who navigates the labyrinth's secrets\s
                and reaches its elusive end shall be granted the desires of their heart,"
                """ + RESET);

        sleepThread(YELLOW + """
                The hero took a deep breath, absorbing the weight of the ancient promise. The labyrinth sprawled before him, a complex \s
                tapestry of stone and shadows. Each corridor seemed to hold a puzzle, and every turn whispered of the legendary Minotaur that guarded the heart of the maze.
                """ + RESET);

        suspensefulDots(".");

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


            switch (randomizePaths.get(scannerNumber() - 1)) {
                case 1 ->{
                    sleepThread(GRAY + "Walking...\n\uD83D\uDC63\n\uD83D\uDC63\n" + RESET);
                    chillForASecond(1500);

                    if (treasuresFound <= 5){
                        boolean didFind = findTreasureChest(player, Inventory);
                        if (didFind){treasuresFound ++;}

                    } else {

                        System.out.println(PURPLE_ISH + "Dead end...");
                        sleepThread(GRAY + "Press enter to go back." + RESET);
                        pressEnterToAttack();

                    }

                }

                case 2 -> {
                    sleepThread(GRAY + "Walking...\n\uD83D\uDC63\n\uD83D\uDC63\n" + RESET);
                    chillForASecond(1500);
                    rightPathsChosen++;
                    System.out.println("slay");
                }

                case 3 -> isPlaying = battleMonster(player, Inventory);

                default -> System.out.println(RED + "Invalid input, try again" + RESET);
            }

            if (rightPathsChosen == 5) {
                break;
            }

        }

        System.out.println("you won yay");
        sleepThread(YELLOW + """
                As the hero triumphantly emerged from Daedalus's Labyrinth, the air was thick with anticipation. At the maze's exit, a majestic figure awaited. The body and tail of a lion, \s
                the face of a woman, and the wings of a bird, The Sphinx. With a wise and enigmatic gaze, the mythical creature acknowledged the hero's accomplishment, prepared to unveil\s
                a riddle that would determine the next steps of the epic quest.
                """ + RESET);
        sphinxMeeting(player, Inventory);

    }




    private boolean battleMonster(Player player, Inventory Inventory){
        Random random = new Random();
        int randomValue = random.nextInt(1, 10);

        sleepThread(GRAY + "Walking...\n\uD83D\uDC63\n\uD83D\uDC63\n" + RESET);
        chillForASecond(1500);

        if (randomValue < 7) {
            int secondRandomValue = random.nextInt(1, 10);

            System.out.println(RED + BOLD + "Oh no, a monster!" + RESET);
            chillForASecond(1000);

            if (secondRandomValue <=3 && !foughtMinotaur) {
                Minotaur minotaur = new Minotaur();
                player.act(minotaur, Inventory);
                if (minotaur.isAlive()){
                    foughtMinotaur = true;
                    return false;
                } else {
                    return true;
                }


            } else {
                Fury fury = new Fury();
                player.act(fury, Inventory);
                return !fury.isAlive();

            }

        } else {
            System.out.println(PURPLE_ISH + "Dead end...");
            sleepThread(GRAY + "Press enter to go back." + RESET);
            pressEnterToAttack();
        }
        return true;

    }



   private boolean findTreasureChest(Player player, Inventory Inventory){

       Random random = new Random();
       int randomValue = random.nextInt(1,10);

           switch (randomValue){
               case 1 -> {
                   System.out.println(BLUE_LIGHT + "You found a chest!" + RESET);
                   chillForASecond(1000);
                   System.out.println(GRAY + "Press enter to open." + RESET);
                   pressEnterToAttack();
                   System.out.println(WHITE + "You found a large health potion inside the chest!");
                   sleepThread(GRAY + "Large Health Potion has been added to your inventory." + RESET);
                   Inventory.addToPotionsInventory(new ShopProducts("Large Health Potion","", 50,0,100,0,0));
                   return true;
               }
               case 2, 3 -> {
                   System.out.println(BLUE_LIGHT + "You found a chest!" + RESET);
                   chillForASecond(1000);
                   System.out.println(GRAY + "Press enter to open." + RESET);
                   pressEnterToAttack();
                   System.out.println(WHITE + "You found a small health potion inside the chest!");
                   sleepThread(GRAY + "Small Health Potion has been added to your inventory." + RESET);
                   Inventory.addToPotionsInventory(new ShopProducts("Small Health Potion", "", 30,0,50,0,0));
                   return true;

               }
               case 4, 5, 6 -> {
                   System.out.println(BLUE_LIGHT + "You found a chest!" + RESET);
                   chillForASecond(1000);
                   System.out.println(GRAY + "Press enter to open." + RESET);
                   pressEnterToAttack();
                   int amount = random.nextInt(10, 20);
                   System.out.println(WHITE + "You found " + amount + " gold coins inside the chest!\n" + YELLOW + BOLD + "+ " + amount + " Gold");
                   player.setGold(player.getGold() + amount);
                   return true;

               }
               case 7, 8 -> {
                   System.out.println(BLUE_LIGHT + "You found a chest!" + RESET);
                   chillForASecond(1000);
                   System.out.println(GRAY + "Press enter to open." + RESET);
                   pressEnterToAttack();
                   int amount = random.nextInt(20, 30);
                   System.out.println("You found " + amount + " gold coins!\n" + YELLOW + BOLD + "+ " + amount + " Gold");
                   player.setGold(player.getGold() + amount);
                   return true;

               }
               case 9, 10 -> {
                   System.out.println(PURPLE_ISH + "Dead end...");
                   break;
               }

           }
       return false;
    }

    private void sphinxMeeting(Player player, Inventory inventory){
        sphinxSpeaking("Hello " + player.getName() +  ". I must say, the ");

        System.out.println("""
                What has one voice but goes on four legs in the morning, two in the afternoon, and three in the evening?\s
                """);

        while (true) {
            String playersAnswer = scannerText();
            String answer = "human";

            if (playersAnswer.toLowerCase().contains(answer)) {
                System.out.println("Your answer is correct!");
                player.unlockNewLevel();
                break;

            } else {
                System.out.println("That's not correct. Try again.");

            }
        }

    }

}
