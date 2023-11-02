package com.Janaina.laboration.Game;

import com.Janaina.laboration.Game.GameMenu.Levels.LevelMenu;
import com.Janaina.laboration.Game.GameMenu.PlayTheGame;
import com.Janaina.laboration.Game.GameMenu.PlayerAchievements.GetPlayerAchievements;
import com.Janaina.laboration.Game.GameMenu.PlayerStats.GetPlayerStats;
import com.Janaina.laboration.Game.Shop.StoreFront;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Resources.Colors;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;
import static com.Janaina.laboration.Resources.Storyteller.readGameLore;
import static com.Janaina.laboration.Resources.TextDelay.suspensefulDots;

public class Storyline {

    public void mainGameMenu() {

        Introduction intro = new Introduction();
        PlayTheGame PlayTheGame = new PlayTheGame();
        StoreFront StoreFront = new StoreFront();
        Player player = new Player();
        LevelMenu mn = new LevelMenu();
        GetPlayerStats PlayerStats = new GetPlayerStats();
        LevelMenu LevelMenu = new LevelMenu();
        GetPlayerAchievements GetPlayerAchievements = new GetPlayerAchievements();
        Inventory Inventory = new Inventory();
        Fury fury = new Fury();
        //player.setGold(200);
        //StoreFront.mainStoreFront(player, Inventory);

        //mn.levelMenu(player);

        //intro.createPlayer(player);

        boolean mainGameMenuSwitch = true;
        do {

            println(BLACK_BACKGROUND + "     " + CYAN_BOLD + CYAN_UNDERLINED + "THE FALL OF TYPHON" + RESET + BLACK_BACKGROUND + "      " + RESET
                    + "\n" + BLACK_BACKGROUND + "          " + CYAN_BOLD + "Main Menu" + RESET + BLACK_BACKGROUND + "          " + RESET
                    + "\n" + CYAN_BOLD +
                    "1. Play\n2. Shop\n3. Read Game Lore\n4. View Tour\n0. Quit Game" + RESET);

            switch (scannerNumber()) {
                case 1 -> PlayTheGame.gameMenu(player, PlayerStats, LevelMenu, GetPlayerAchievements, Inventory);
                case 2 -> StoreFront.mainStoreFront(player, Inventory);
                case 3 -> readGameLore();
                case 4 -> intro.initialTourOfGame();
                case 0 -> {
                    PythiaSpeaking("Goodbye " + player.getName() + ", may we meet again");
                    suspensefulDots(PURPLE + "." + RESET);
                    mainGameMenuSwitch = false;
                }
                default -> printRed("Invalid input, please chose from the presented options");

            }
        }while (mainGameMenuSwitch && player.isAlive());
    }


}
