package com.Janaina.laboration;

import com.Janaina.laboration.Game.GameMenu.Levels.LevelMenu;
import com.Janaina.laboration.Game.GameMenu.Levels.LevelOne;
import com.Janaina.laboration.Game.Introduction;
import com.Janaina.laboration.Game.Shop.ShopCategories.Weapons;
import com.Janaina.laboration.Game.Storyline;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.*;
import com.Janaina.laboration.Resources.Storyteller;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.Scanners.pressEnter;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class Main {
    public static boolean playerIsAlive = true;
    public static boolean playerIsPlayingGame = true;

    public static void main(String[] args) {

        Storyline Storyline = new Storyline();
        while (playerIsAlive && playerIsPlayingGame) {
            Storyline.mainGameMenu();

            if (!playerIsAlive) {

                System.out.println("Game Over. You have been defeated.");
                break;
            }

        }
    }
}
