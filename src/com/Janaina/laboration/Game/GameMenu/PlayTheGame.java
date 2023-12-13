package com.Janaina.laboration.Game.GameMenu;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.GameMenu.Levels.LevelMenu;
import com.Janaina.laboration.Game.GameMenu.PlayerAchievements.GetPlayerAchievements;
import com.Janaina.laboration.Game.GameMenu.PlayerStats.GetPlayerStats;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.printRed;
import static com.Janaina.laboration.Resources.PrintHandler.println;


public class PlayTheGame {

    public void gameMenu(Player player, GetPlayerStats PlayerStats, LevelMenu LevelMenu, GetPlayerAchievements GetPlayerAchievements, Inventory Inventory, Scanners sc, DBConnection db){

        boolean isChoosingFromGameMenu = true;
        do {

            println(BLACK_BACKGROUND + "          " + PURPLE_BOLD_BRIGHT + "Game Menu" + RESET + BLACK_BACKGROUND + "          " + RESET
                    + "\n" + PURPLE_DARK + BOLD +
                    "1. Levels\n2. Player stats\n3. Achievements\n4. Inventory\n0. Back" + RESET);

            switch (sc.chooseFromGameMenu()) {
                case 1 -> LevelMenu.levelMenu(player, Inventory, sc, db);
                case 2 -> PlayerStats.currentStats(player, sc, db);
                case 3 -> GetPlayerAchievements.playerAchievements(player, sc, db);
                case 4 -> Inventory.playerInventory(player, sc, db);
                case 0 -> isChoosingFromGameMenu = false;
                default -> printRed("Invalid input, please chose from the presented options");
            }

        }while (isChoosingFromGameMenu && player.isAlive());

    }


}
