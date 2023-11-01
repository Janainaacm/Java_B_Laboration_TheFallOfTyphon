package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Variables.Characters;

import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;

public class Fury extends Characters {
    public Fury() {
        super("Fury", 2, 50, 10, 10, 10, 15, 30, 0, "Rage of the Erinyes");
    }
    //Level 1 monster




    @Override
    public void attack(Characters player) {
        System.out.println(RED_BOLD_BRIGHT + getName() + " used " + getDefaultAttack() + " on you!" + RESET);
        sleepThread(RED + "ﮩ٨ـﮩﮩ٨ـﮩ٨ـﮩﮩ٨" + "\n" + RESET );
        chillForASecond(200);


    }

    @Override
    public boolean flee(Characters player) {
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

        String nameStats = BLACK_BACKGROUND + "           " + RED_BOLD_BRIGHT + RED_UNDERLINED + "FURY" + RESET + BLACK_BACKGROUND + "           " + RESET;
        String healthStats = null;
        String strengthStats = null;
        if (getHealth() >50){
            healthStats = RED_BOLD + "Health: " + RED + getHealth() + RESET;

        }else if (getHealth() <= 50 && getHealth() > 20){
            healthStats = RED_BOLD + "Health: " + YELLOW + getHealth() + RESET;

        }else if (getHealth() <= 20 && getHealth() >= 1) {
            healthStats = RED_BOLD + "Health: " + BLACK + getHealth() + RESET;

        }
        if (getStrength() >50){
            strengthStats = RED_BOLD + "Strength: " + RED + getStrength() + RESET;

        }else if (getStrength() <= 50 && getStrength() > 20){
            strengthStats = RED_BOLD + "Strength: " + YELLOW + getStrength() + RESET;

        }else if (getStrength() <= 20 && getStrength() >= 1) {
            strengthStats = RED_BOLD + "Strength: " + BLACK + getStrength() + RESET;

        }

        return nameStats + "\n" + healthStats + "    " + strengthStats;

    }



    @Override
    public void receiveDamage(Characters player) {
        Random random = new Random();
        int acquiredStrength = random.nextInt(1, player.getStrength());
        int damageFromAttack = acquiredStrength * player.getBaseDamage();

        setHealth(getHealth() - damageFromAttack);
        System.out.println(YELLOW + "Your attack did " + damageFromAttack + " damage!" + RESET);

        chillForASecond(200);

    }

}



