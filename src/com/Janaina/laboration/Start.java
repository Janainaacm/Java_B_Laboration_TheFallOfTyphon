package com.Janaina.laboration;

import com.Janaina.laboration.Game.Introduction;
import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.MainGameMenu;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.println;

public class Start {

    public void startGame() {
        Inventory inventory = new Inventory();
        MainGameMenu mainGameMenu = new MainGameMenu();
        Introduction intro = new Introduction();
        Scanners sc = new Scanners();
        DBConnection db = new DBConnection();
        db.openConnection();
        db.createTables();

        Player player = db.choosePlayer(sc);







        println(BLACK_BACKGROUND + "                             " + RESET
                + "\n" + BLACK_BACKGROUND + "     " + PURPLE_LIGHT + BOLD + UNDERLINED + "THE FALL OF TYPHON" + RESET + BLACK_BACKGROUND + "      " + RESET
                + "\n" + BLACK_BACKGROUND + "    " + "\033[40;35m" + ITALIC + "A Quest for Vengeance" + RESET + BLACK_BACKGROUND + "    " + RESET
                + "\n" + BLACK_BACKGROUND + "                             " + RESET + "\n");

        intro.createPlayer(player, sc);
        mainGameMenu.mainGameMenu(player, sc, inventory, db);

        db.closeConnection();

    }

}
