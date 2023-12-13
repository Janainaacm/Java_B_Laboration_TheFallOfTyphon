package com.Janaina.laboration.Game.GameMenu.PlayerStats;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class GetPlayerStats {

    public void currentStats(Player player, Scanners sc, DBConnection db) {

        System.out.println(BLACK_BACKGROUND + LILAC + BOLD + "      " + UNDERLINED + player.getName().toUpperCase() + RESET + BLACK_BACKGROUND + "      " + RESET +
                "\n" + BLACK_BACKGROUND + LILAC + ITALIC + " Level " + player.getLevel() + "        " + RESET + "\n");
        System.out.println(LILAC + ITALIC + "✧ Health: " + GREEN_LIGHT + player.getHealth() + LILAC +
                "\n✧ Equipped Weapon: " + ORANGE + player.equippedWeaponName() + LILAC +
                "\n✧ Base Damage: " + RED + player.getBaseDamage() + LILAC +
                "\n✧ Strength: " + RED + player.getStrength() +  LILAC + " + " + ORANGE +  player.equippedWeaponStrength() + LILAC +
                "\n✧ Agility: " + ORANGE + player.getAgility() + LILAC +
                "\n✧ Gold: " + YELLOW_LIGHT +  player.getGold() + LILAC +
                "\n✧ XP: " + CYAN + player.getExperience() + " / " + player.getLevel() * 100 + RESET);

        if (db.getCount("name", "specialAttacks", player) == 0){
            System.out.println(GRAY + ITALIC + "✧ Special Attacks:");
        } else {
            db.specialAttackList(player);
        }

        chillForASecond(2000);
        sleepThread(GRAY + "\nPress enter to go back" + RESET);
        sc.pressEnterNoText();



    }

}
