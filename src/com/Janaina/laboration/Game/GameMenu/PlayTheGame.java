package com.Janaina.laboration.Game.GameMenu;

import com.Janaina.laboration.Game.GameMenu.PlayerStats.GetPlayerStats;
import com.Janaina.laboration.Game.Variables.Hero.Player;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.println;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;

public class PlayTheGame {

    public void gameMenu(Player player){
        GetPlayerStats PlayerStats = new GetPlayerStats();

        println(BLACK_BACKGROUND + "          " + CYAN_BOLD + "Game Menu" + RESET + BLACK_BACKGROUND + "          " + RESET
                + "\n" + CYAN_BOLD +
                "1. Levels\n2. Player stats\n3. Achievements\n4. Equipment" + RESET);

        switch (scannerNumber()){
            case 1 -> println("levels");
            case 2 -> PlayerStats.currentStats(player);
            case 3 -> println("ach");
            case 4 -> println("equ");
        }

    }


}
