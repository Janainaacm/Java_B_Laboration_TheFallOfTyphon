package com.Janaina.laboration.Game.GameMenu;

import com.Janaina.laboration.Game.GameMenu.Equipment.PlayerEquipment;
import com.Janaina.laboration.Game.GameMenu.Levels.LevelMenu;
import com.Janaina.laboration.Game.GameMenu.PlayerAchievements.GetPlayerAchievements;
import com.Janaina.laboration.Game.GameMenu.PlayerStats.GetPlayerStats;
import com.Janaina.laboration.Game.Variables.Hero.Player;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.printRed;
import static com.Janaina.laboration.Resources.PrintHandler.println;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;

public class PlayTheGame {

    public void gameMenu(Player player){
        GetPlayerStats PlayerStats = new GetPlayerStats();
        LevelMenu LevelMenu = new LevelMenu();
        GetPlayerAchievements GetPlayerAchievements = new GetPlayerAchievements();
        PlayerEquipment PlayerEquipment = new PlayerEquipment();


        boolean isChoosingFromGameMenu = true;
        do {

            println(BLACK_BACKGROUND + "          " + CYAN_BOLD + "Game Menu" + RESET + BLACK_BACKGROUND + "          " + RESET
                    + "\n" + CYAN_BOLD +
                    "1. Levels\n2. Player stats\n3. Achievements\n4. Equipment\n0. Back" + RESET);

            switch (scannerNumber()) {
                case 1 -> LevelMenu.levelMenu(player);
                case 2 -> PlayerStats.currentStats(player);
                case 3 -> GetPlayerAchievements.playerAchievements(player);
                case 4 -> PlayerEquipment.playerEquipment(player);
                case 0 -> isChoosingFromGameMenu = false;
                default -> printRed("Invalid input, please chose from the presented options");
            }

        }while (isChoosingFromGameMenu);

    }


}
