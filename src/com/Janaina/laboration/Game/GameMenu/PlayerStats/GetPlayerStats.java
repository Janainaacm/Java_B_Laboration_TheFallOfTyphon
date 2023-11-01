package com.Janaina.laboration.Game.GameMenu.PlayerStats;

import com.Janaina.laboration.Game.Variables.Hero.Player;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.Scanners.scannerNumber;
import static com.Janaina.laboration.Resources.TextDelay.suspensefulDots;

public class GetPlayerStats {

    public void currentStats(Player player) {

        while (true) {
            println(BLUE_UNDERLINED + BLUE_BOLD_BRIGHT + "Welcome " + player.getName() + RESET + CYAN + "\nCurrent stats:" + RESET);
            println(BLUE + "Strength: " + RESET + GREEN + player.getStrength() + RESET + BLUE +
                    "\nDamage: " + RESET + GREEN + player.getBaseDamage() + RESET + BLUE +
                    "\nAgility: " + RESET + GREEN + player.getAgility() + RESET + BLUE +
                    "\nIntelligence: " + RESET + GREEN + player.getIntelligence() + RESET + BLUE +
                    "\nGold: " + RESET + GREEN + player.getGold() + RESET + BLUE +
                    "\nExperience Points: " + RESET + GREEN + player.getExperience() + " / " + player.getLevel() * 100 + RESET + BLUE +
                    "\nSpecial Attacks: ");


            printPurple("0 to go back");
            suspensefulDots(".");
            if (scannerNumber() == 0){
                break;
            } else {
                printRed("Invalid input");
            }
        }



    }

}
