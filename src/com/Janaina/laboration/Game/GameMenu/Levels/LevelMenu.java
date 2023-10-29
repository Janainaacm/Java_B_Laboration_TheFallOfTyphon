package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Player;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.printCyan;
import static com.Janaina.laboration.Resources.PrintHandler.println;

public class LevelMenu {
    public void levelMenu(Player player){
        LevelOne LevelOne = new LevelOne();
        LevelTwo LevelTwo = new LevelTwo();
        LevelThree LevelThree = new LevelThree();


        println(BLACK_BACKGROUND + "          " + CYAN_BOLD_BRIGHT + CYAN_UNDERLINED + "Available levels:" + RESET + BLACK_BACKGROUND + "          " + RESET);

        printCyan("1. Level one");

        if (LevelOne.levelOneComplete(player)){
            printCyan("2. Level two");
        } else {
            println("???");
        }

        if (LevelTwo.levelTwoComplete(player)){
            printCyan("3. Level three");
        } else {
            println("???");
        }

        if (LevelThree.levelThreeComplete(player)){
            printCyan("4. Level four");
        } else {
            println("???");
        }






    }
}
