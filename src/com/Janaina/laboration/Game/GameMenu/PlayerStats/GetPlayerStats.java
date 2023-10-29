package com.Janaina.laboration.Game.GameMenu.PlayerStats;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Colors;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;
import static com.Janaina.laboration.Resources.TextDelay.suspensefulDots;

public class GetPlayerStats {

    public void currentStats(Player player) {

        while (true) {
            println(BLUE_UNDERLINED + BLUE_BOLD_BRIGHT + "Welcome " + player.getName() + RESET + CYAN + "\nCurrent stats:" + RESET);
            println(BLUE + "Strength: " + RESET + RED + player.getStrength() + RESET + BLUE +
                    "\nDamage: " + RESET + RED + player.getBaseDamage() + RESET + BLUE +
                    "\nAgility: " + RESET + RED + player.getAgility() + RESET + BLUE +
                    "\nIntelligence: " + RESET + RED + player.getIntelligence() + RESET + BLUE +
                    "\n Gold: " + RESET + RED + player.getGold() + RESET + BLUE +
                    "\n Experience Points: " + RESET + RED + player.getExperience() + " / 100" + RESET + BLUE +
                    "\n Special Attacks: ");


            suspensefulDots();
            printPurple("0 to go back");
            if (scannerNumber() == 0){
                break;
            }
        }



    }

}
