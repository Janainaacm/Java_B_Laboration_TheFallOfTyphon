package com.Janaina.laboration.Game;

import com.Janaina.laboration.Game.GameMenu.Levels.*;
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

    public void mainGameMenu(Player player) {

        Introduction intro = new Introduction();
        PlayTheGame PlayTheGame = new PlayTheGame();
        StoreFront StoreFront = new StoreFront();
        LevelMenu mn = new LevelMenu();
        GetPlayerStats PlayerStats = new GetPlayerStats();
        LevelMenu LevelMenu = new LevelMenu();
        GetPlayerAchievements playerAchievements = new GetPlayerAchievements();
        Inventory inventory = new Inventory();
        Fury fury = new Fury();
        LevelFour lf = new LevelFour();
        LevelTwo lt = new LevelTwo();
        LevelThree ltt = new LevelThree();
        LevelFive lff = new LevelFive();
        LevelSix ls = new LevelSix();
        playerAchievements.playerAchievements(player);
        //lt.playLevelTwo(player, inventory);
        //player.setGold(200);
        //StoreFront.mainStoreFront(player, inventory);
        //lf.playLevelFour(player, inventory);
       // mn.levelMenu(player, inventory);
        //ltt.playLevelThree(player, inventory);
        //lff.playLevelFive(player, inventory);
        //ls.playLevelSix(player, inventory);

        //intro.createPlayer(player);

        boolean mainGameMenuSwitch = true;
        do {

            println(BLACK_BACKGROUND + "     " + CYAN_BOLD + CYAN_UNDERLINED + "THE FALL OF TYPHON" + RESET + BLACK_BACKGROUND + "      " + RESET
                    + "\n" + BLACK_BACKGROUND + "          " + CYAN_BOLD + "Main Menu" + RESET + BLACK_BACKGROUND + "          " + RESET
                    + "\n" + CYAN_BOLD +
                    "1. Play\n2. Shop\n3. Read Game Lore\n4. View Tour\n0. Quit Game" + RESET);

            switch (scannerNumber()) {
                case 1 -> PlayTheGame.gameMenu(player, PlayerStats, LevelMenu, playerAchievements, inventory);
                case 2 -> StoreFront.mainStoreFront(player, inventory);
                case 3 -> readGameLore();
                case 4 -> intro.initialTourOfGame();
                case 0 -> {
                    PythiaSpeaking("Goodbye " + player.getName() + ", may we meet again");
                    suspensefulDots(PURPLE + "." + RESET);
                    mainGameMenuSwitch = false;
                }
                default -> printRed("Invalid input, please chose from the presented options");

            }
        }while (mainGameMenuSwitch);
    }


}
