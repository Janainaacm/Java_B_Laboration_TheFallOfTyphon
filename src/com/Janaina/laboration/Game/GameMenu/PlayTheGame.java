package com.Janaina.laboration.Game.GameMenu;

import com.Janaina.laboration.Game.GameMenu.Levels.LevelMenu;
import com.Janaina.laboration.Game.GameMenu.PlayerAchievements.GetPlayerAchievements;
import com.Janaina.laboration.Game.GameMenu.PlayerStats.GetPlayerStats;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.printRed;
import static com.Janaina.laboration.Resources.PrintHandler.println;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;

public class PlayTheGame {

    public void gameMenu(Player player, GetPlayerStats PlayerStats, LevelMenu LevelMenu, GetPlayerAchievements GetPlayerAchievements, Inventory Inventory){



        boolean isChoosingFromGameMenu = true;
        do {

            println(BLACK_BACKGROUND + "          " + CYAN_BOLD + "Game Menu" + RESET + BLACK_BACKGROUND + "          " + RESET
                    + "\n" + CYAN_BOLD +
                    "1. Levels\n2. Player stats\n3. Achievements\n4. Inventory\n0. Back" + RESET);

            switch (scannerNumber()) {
                case 1 -> LevelMenu.levelMenu(player, Inventory);
                case 2 -> PlayerStats.currentStats(player);
                case 3 -> GetPlayerAchievements.playerAchievements(player);
                case 4 -> Inventory.playerInventory();
                case 0 -> isChoosingFromGameMenu = false;
                default -> printRed("Invalid input, please chose from the presented options");
            }

        }while (isChoosingFromGameMenu);

    }


}
