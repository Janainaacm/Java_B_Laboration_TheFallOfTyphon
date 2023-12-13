package com.Janaina.laboration;

import com.Janaina.laboration.Game.Introduction;
import com.Janaina.laboration.Game.MainGameMenu;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;


import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.println;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;
import static com.Janaina.laboration.Resources.TextDelay.suspensefulDots;

public class Start {

    public void startGame() {
        Inventory inventory = new Inventory();
        Player player = new Player();
        MainGameMenu mainGameMenu = new MainGameMenu();
        Introduction intro = new Introduction();
        Scanners sc = new Scanners();
        DBConnection db = new DBConnection();
        db.openConnection();

        db.createTables();

        println(BLACK_BACKGROUND + "                             " + RESET
                + "\n" + BLACK_BACKGROUND + "     " + PURPLE_LIGHT + BOLD + UNDERLINED + "THE FALL OF TYPHON" + RESET + BLACK_BACKGROUND + "      " + RESET
                + "\n" + BLACK_BACKGROUND + "    " + "\033[40;35m" + ITALIC + "A Quest for Vengeance" + RESET + BLACK_BACKGROUND + "    " + RESET
                + "\n" + BLACK_BACKGROUND + "                             " + RESET + "\n");
        sleepThread(PURPLE_ISH + "(This game is inspired by and based on greek mythology, for every creature you encounter you will get the chance to learn more about them)");
        suspensefulDots(".");
        sc.pressEnter();

        db.choosePlayer(sc, player);
        db.addItemsToTables(player);

        intro.introduction(player, sc);
        mainGameMenu.mainGameMenu(player, sc, inventory, db);

        db.closeConnection();

    }

}
