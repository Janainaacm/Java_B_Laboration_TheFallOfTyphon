package com.Janaina.laboration.Game.GameMenu.PlayerStats;

import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class GetPlayerStats {

    public void currentStats(Player player, Scanners sc) {

        System.out.println(BLACK_BACKGROUND + LILAC + BOLD + "      " + UNDERLINED + player.getName().toUpperCase() + RESET + BLACK_BACKGROUND + "      " + RESET +
                "\n" + BLACK_BACKGROUND + LILAC + ITALIC + " Level " + player.getLevel() + "        " + RESET + "\n");
        System.out.println(LILAC + ITALIC + "✧ Health: " + GREEN_LIGHT + player.getHealth() + LILAC +
                "\n✧ Equipped Weapon: " + ORANGE + player.equippedWeapon.getName() + LILAC +
                "\n✧ Min Damage: " + RED + player.getBaseDamage() + LILAC +
                "\n✧ Max Damage: " + RED + player.getBaseDamage() * player.getStrength() * player.equippedWeapon.getStrength() + LILAC +
                "\n✧ Agility: " + ORANGE + player.getAgility() + LILAC +
                "\n✧ Gold: " + YELLOW_LIGHT +  player.getGold() + RESET);

        if (player.specialAttackList.isEmpty()){
            System.out.println(GRAY + ITALIC + "✧ Special Attacks:");
        } else {
            System.out.println(LILAC + ITALIC + "✧ Special Attacks:");
            for (int i = 0; i < player.specialAttackList.size(); i++) {
                System.out.println(LILAC + ITALIC + "     ⋆ " + player.specialAttackList.get(i).getName() + "  -≫  Damage: " + RED + player.specialAttackList.get(i).getDamage() + RESET);

            }
        }

        System.out.println("\n");
        chillForASecond(2000);
        sleepThread(GRAY + "Press enter to go back" + RESET);
        sc.pressEnterToAttack();

    }

}
