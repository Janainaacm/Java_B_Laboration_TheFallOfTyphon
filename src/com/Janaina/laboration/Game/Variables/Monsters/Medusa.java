package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Variables.Characters;

import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class Medusa extends Characters {
    public Medusa() {
        super("Medusa", 3, 100, 10, 10, 30, 100, 60, 0, "Serpents Curse", 100);
    }
    //Level 3



    @Override
    public void attack(Characters target) {
        System.out.println(GREEN_LIGHT + BOLD + UNDERLINED + getName() + " used " + getDefaultAttack() + " on you!" + RESET);
        sleepThread(GREEN_LIGHT + "\uD80C\uDD9A\uD80C\uDD97\uD80C\uDD9A\uD80C\uDD97\uD80C\uDD9A\uD80C\uDD97" + "\n" + RESET);
        chillForASecond(200);
    }

    @Override
    public boolean flee(Characters target) {
        return true;
    }

    @Override
    public boolean dodge(Characters player) {

        Random random = new Random();
        int randomValue = random.nextInt(1, 100);

        return randomValue < getAgility();

    }

    @Override
    public String getStats() {

        String nameStats = BLACK_BACKGROUND + "           " + GREEN_LIGHT + BOLD + UNDERLINED + "MEDUSA" + RESET + BLACK_BACKGROUND + "           " + RESET;
        String healthStats = null;
        String strengthStats = null;
        if (getHealth() >50){
            healthStats = GREEN_LIGHT + BOLD + "Health: " + RED + getHealth() + RESET;

        }else if (getHealth() <= 50 && getHealth() > 20){
            healthStats = GREEN_LIGHT + BOLD + "Health: " + YELLOW + getHealth() + RESET;

        }else if (getHealth() <= 20 && getHealth() >= 1) {
            healthStats = GREEN_LIGHT + BOLD + "Health: " + BLACK + getHealth() + RESET;

        }
        if (getStrength() >50){
            strengthStats = GREEN_LIGHT + BOLD + "Strength: " + RED + getStrength() + RESET;

        }else if (getStrength() <= 50 && getStrength() > 20){
            strengthStats = GREEN_LIGHT + BOLD + "Strength: " + YELLOW + getStrength() + RESET;

        }else if (getStrength() <= 20 && getStrength() >= 1) {
            strengthStats = GREEN_LIGHT + BOLD + "Strength: " + BLACK + getStrength() + RESET;

        }

        return nameStats + "\n" + healthStats + "    " + strengthStats;
    }


    @Override
    public void receiveDamage(Characters player, int damage) {
        Random random = new Random();
        int acquiredStrength = random.nextInt(1, player.getStrength());
        int damageFromAttack = acquiredStrength * player.getBaseDamage();

        setHealth(getHealth() - damageFromAttack);
        System.out.println(YELLOW + "Your attack did " + damageFromAttack + " damage!" + RESET);

        chillForASecond(200);

    }
}
