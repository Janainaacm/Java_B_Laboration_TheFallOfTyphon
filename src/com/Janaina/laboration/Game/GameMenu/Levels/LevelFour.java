package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Minotaur;

import java.util.*;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;
import static com.Janaina.laboration.Resources.Scanners.scannerText;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class LevelFour {
    //Labyrinth + minotaur
    private boolean foughtMinotaur = false;

    public void playLevelFour(Player player, Inventory Inventory){
        player.setStrength(100);
        int rightPathsChosen = 0;
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

            int choice = scannerNumber();
            int chosenPath = randomizePaths.get(choice - 1);

            switch (chosenPath) {
                case 1 -> findTreasureChest(player, Inventory);
                case 2 -> {
                    rightPathsChosen++;
                    System.out.println("slay");
                }
                case 3 -> isPlaying = battleMonster(player, Inventory);

                default -> System.out.println(RED + "Invalid input, try again" + RESET);
            }

            if (rightPathsChosen == 1) {
                break;
            }

        }

        System.out.println("you won yay");
        sphinxMeeting(player, Inventory);

    }






    private boolean battleMonster(Player player, Inventory Inventory){
        Random random = new Random();
        double randomValue = random.nextDouble();

        if (randomValue < 0.7) {
            double secondRandomValue = random.nextDouble();

            if (secondRandomValue < 0.7) {
                Fury fury = new Fury();
                player.act(fury, Inventory);
                return !fury.isAlive();


            } else {
                if (!foughtMinotaur){
                    Minotaur minotaur = new Minotaur();
                    player.act(minotaur, Inventory);
                    if (minotaur.isAlive()){
                        foughtMinotaur = true;
                        return false;
                    } else {
                        return true;
                    }
                }


            }
        } else {
            System.out.println("Wandering on");
            sleepThread("\uD83D\uDC63\uD83D\uDC63\uD83D\uDC63\uD83D\uDC63");
        }
        return true;

    }



   private void findTreasureChest(Player player, Inventory Inventory){
        //You can find money, potions or items you can exchange with the merchant
       System.out.println("Treasure slay");
    }

    private void sphinxMeeting(Player player, Inventory inventory){
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
