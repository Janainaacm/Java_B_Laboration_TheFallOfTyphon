package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;

public class LevelMenu {
    public void levelMenu(Player player, Inventory Inventory){
        LevelOne LevelOne = new LevelOne();
        LevelTwo LevelTwo = new LevelTwo();
        LevelThree LevelThree = new LevelThree();
        LevelFour LevelFour = new LevelFour();
        LevelFive LevelFive = new LevelFive();
        LevelSix LevelSix = new LevelSix();


        boolean isChoosingLevel = true;
        while (isChoosingLevel) {

        println(BLACK_BACKGROUND + "          " + CYAN_BOLD_BRIGHT + CYAN_UNDERLINED + "Available levels:" + RESET + BLACK_BACKGROUND + "          " + RESET);

        printCyan("1. Level one");

        if (LevelOne.levelOneComplete(player)){
            printCyan("2. Level two");
        } else {
            println("???");
        }

        if (LevelTwo.levelTwoComplete("Fix this")){
            printCyan("3. Level three");
        } else {
            println("???");
        }

        if (LevelThree.levelThreeComplete("Fix this")){
            printCyan("4. Level four");
        } else {
            println("???");
        }

        if (LevelFour.levelFourComplete("Fix this")){
            printCyan("5. Level five");
        } else {
            println("???");
        }

        if (LevelFive.levelFiveComplete("Fix this")){
            printCyan("6. Level six");
        } else {
            println("???");
        }

        printCyan("0.Go back");



            switch (scannerNumber()) {
                case 1 -> LevelOne.playLevelOne(player, Inventory);
                case 2 -> {
                    if (LevelOne.levelOneComplete(player)) {
                        LevelTwo.playLevelTwo(player);
                    } else {
                        printRed("Invalid input, please chose from the presented options");
                    }
                }
                case 3 -> {
                    if (LevelTwo.levelTwoComplete("Fix this")) {
                        LevelThree.playLevelThree(player);
                    } else {
                        printRed("Invalid input, please chose from the presented options");
                    }
                }
                case 4 -> {
                    if (LevelThree.levelThreeComplete("Fix this")) {
                        LevelFour.playLevelFour(player);
                    } else {
                        printRed("Invalid input, please chose from the presented options");
                    }
                }
                case 5 -> {
                    if (LevelFour.levelFourComplete("Fix this")) {
                        LevelFive.playLevelFive(player);
                    } else {
                        printRed("Invalid input, please chose from the presented options");
                    }
                }
                case 6 -> {
                    if (LevelFive.levelFiveComplete("Fix this")) {
                        LevelSix.playLevelSix(player);
                    } else {
                        printRed("Invalid input, please chose from the presented options");
                    }
                }
                case 0 -> isChoosingLevel = false;
                default -> printRed("Invalid input, please chose from the presented options");
            }
        }











    }
}
