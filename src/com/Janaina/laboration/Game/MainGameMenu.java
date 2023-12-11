package com.Janaina.laboration.Game;

import com.Janaina.laboration.Game.GameMenu.Levels.*;
import com.Janaina.laboration.Game.GameMenu.PlayTheGame;
import com.Janaina.laboration.Game.GameMenu.PlayerAchievements.GetPlayerAchievements;
import com.Janaina.laboration.Game.GameMenu.PlayerStats.GetPlayerStats;
import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Shop.StoreFront;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.Storyteller.readGameLore;
import static com.Janaina.laboration.Resources.TextDelay.suspensefulDots;

public class MainGameMenu {

    public void mainGameMenu(Player player, Scanners sc, Inventory inventory, List<ShopProducts> potionsProductList, List<ShopProducts> weaponsProductList) {

        Introduction intro = new Introduction();
        PlayTheGame playTheGame = new PlayTheGame();
        StoreFront storeFront = new StoreFront();
        GetPlayerStats playerStats = new GetPlayerStats();
        LevelMenu levelMenu = new LevelMenu();
        GetPlayerAchievements playerAchievements = new GetPlayerAchievements();
        addProductsToLists(potionsProductList, weaponsProductList);


        boolean mainGameMenuSwitch = true;

        do {

            println(BLACK_BACKGROUND + "     " + CYAN_BOLD + CYAN_UNDERLINED + "THE FALL OF TYPHON" + RESET + BLACK_BACKGROUND + "      " + RESET
                    + "\n" + BLACK_BACKGROUND + "          " + CYAN_BOLD + "Main Menu" + RESET + BLACK_BACKGROUND + "          " + RESET
                    + "\n" + CYAN_BOLD +
                    "1. Play\n2. Shop\n3. Read Game Lore\n4. View Tour\n0. Quit Game" + RESET);

            switch (sc.chooseFromMainMenu()) {
                case 1 -> playTheGame.gameMenu(player, playerStats, levelMenu, playerAchievements, inventory, sc, potionsProductList, weaponsProductList);
                case 2 -> storeFront.mainStoreFront(player, inventory, sc, potionsProductList, weaponsProductList);
                case 3 -> readGameLore(sc);
                case 4 -> intro.initialTourOfGame(sc, player);
                case 0 -> {
                    pythiaSpeaking("Goodbye " + player.getName() + ", may we meet again");
                    suspensefulDots(PURPLE + "." + RESET);
                    mainGameMenuSwitch = false;
                }
                default -> printRed("Invalid input, please chose from the presented options");

            }
        }while (mainGameMenuSwitch);
    }

    private void addProductsToLists(List<ShopProducts> potionsProductList, List<ShopProducts> weaponsProductList){
        weaponsProductList.add(new ShopProducts("Frostbite Dagger", "Frostbite Strike", "+—⟪═════>", 150, 2, 0,0,0));
        weaponsProductList.add(new ShopProducts("Shadowfang Blade", "Dark Eclipse", "▭▭ι═══════ﺤ", 160, 3, 0,0,0));
        weaponsProductList.add(new ShopProducts("Cursed Scythe", "Reaper's Grasp", "▬ι══════ﺤ", 170, 4, 0,0,0));
        weaponsProductList.add(new ShopProducts("Oceanic Trident", "Abyssal Torrent", "——————∈ ࿐ ࿔", 200, 5, 0, 0, 0));
        weaponsProductList.add(new ShopProducts("Phoenix Bow","Flaming Arrow Barrage", "ˎ-·˚ ༘₊· ͟͟͞͞➳", 250,7,0,0,0));
        weaponsProductList.add(new ShopProducts("Thunderstrike Hammer", "Lightning Hammerblow", "⌁˚⊹｡ﾟϟﾟ.｡⊹˚⌁", 300,8,0,0,0));
        weaponsProductList.add(new ShopProducts("Glock-19", "Kurdiska räven", "ᡕᠵ᠊ᡃ࡚ࠢ࠘ ⸝່ࠡࠣ᠊߯᠆ࠣ࠘ᡁࠣ࠘᠊᠊ࠢ࠘\uD802\uDC4F  \uD81A\uDCD3 \uD81A\uDCE8", 1000, 100, 0, 0, 0));

        potionsProductList.add(new ShopProducts("Small Health Potion", "", "⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆", 30, 0, 50, 0, 0));
        potionsProductList.add(new ShopProducts("Large Health Potion", "", "⋆｡୭⋆⁺.⋆｡˙⊹༺⋆｡˙⊹⋆", 50, 0, 100, 0, 0));
        potionsProductList.add(new ShopProducts("Flexibility Potion", "", "❥⁺⋆༺.*₊˚࿐༅", 50, 0, 0, 2, 0));
        potionsProductList.add(new ShopProducts("Strength Potion", "", "❥⁺⋆༺.*₊˚࿐༅", 50, 1, 0, 0, 0));
        potionsProductList.add(new ShopProducts("Intelligence Potion", "", "❥⁺⋆༺.*₊˚࿐༅", 50, 0, 0, 0, 5));

    }


}
