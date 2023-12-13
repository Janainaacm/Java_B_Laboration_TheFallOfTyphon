package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;

public class LevelMenu {
    public void levelMenu(Player player, Inventory Inventory, Scanners sc, DBConnection db) {
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

            if (player.getAvailableLevels() > 1) {
                printCyan("2. Level two");
            } else {
                println("???");
            }

            if (player.getAvailableLevels() > 2) {
                printCyan("3. Level three");
            } else {
                println("???");
            }

            if (player.getAvailableLevels() > 3) {
                printCyan("4. Level four");
            } else {
                println("???");
            }

            if (player.getAvailableLevels() > 4) {
                printCyan("5. Level five");
            } else {
                println("???");
            }

            if (player.getAvailableLevels() > 5) {
                printCyan("6. Level six");
            } else {
                println("???");
            }

            printCyan("0. Go back");


            switch (sc.chooseFromLevelMenu()) {
                case 1 -> LevelOne.playLevelOne(player, Inventory, sc);
                case 2 -> {
                    if (player.getAvailableLevels() > 1) {
                        LevelTwo.playLevelTwo(player, Inventory, sc, db);
                    } else {
                        printRed("Invalid input, please chose from the presented options");
                    }
                }
                case 3 -> {
                    if (player.getAvailableLevels() > 2) {
                        LevelThree.playLevelThree(player, Inventory, sc, db);
                    } else {
                        printRed("Invalid input, please chose from the presented options");
                    }
                }
                case 4 -> {
                    if (player.getAvailableLevels() > 3) {
                        LevelFour.playLevelFour(player, Inventory, sc, db);
                    } else {
                        printRed("Invalid input, please chose from the presented options");
                    }
                }
                case 5 -> {
                    if (player.getAvailableLevels() > 4) {
                        LevelFive.playLevelFive(player, Inventory, sc, db);
                    } else {
                        printRed("Invalid input, please chose from the presented options");
                    }
                }
                case 6 -> {
                    if (player.getAvailableLevels() > 5) {
                        LevelSix.playLevelSix(player, Inventory, sc, db);
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
