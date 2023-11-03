package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Variables.Characters;

import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;

public class Siren extends Characters{
    public Siren() {
        super("Siren", 2, 60, 10, 40, 15, 50, 40, 0, "Deadly Wail", 60);
    }
    //Level 2


    @Override
    public void attack(Characters target) {
        System.out.println(CYAN_BOLD_BRIGHT + getName() + " used " + getDefaultAttack() + " on you!" + RESET);
        sleepThread(CYAN + "࿐ ࿔*:･ﾟ࿐ ࿔*:･ﾟ࿐ ࿔*:･ﾟ" + "\n" + RESET);
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
        String nameStats = BLACK_BACKGROUND + "           " + CYAN_BOLD_BRIGHT + CYAN_UNDERLINED + "SIREN" + RESET + BLACK_BACKGROUND + "           " + RESET;
        String healthStats = null;
        String strengthStats = null;
        if (getHealth() >50){
            healthStats = CYAN_BOLD + "Health: " + RED + getHealth() + RESET;

        }else if (getHealth() <= 50 && getHealth() > 20){
            healthStats = CYAN_BOLD + "Health: " + YELLOW + getHealth() + RESET;

        }else if (getHealth() <= 20 && getHealth() >= 1) {
            healthStats = CYAN_BOLD + "Health: " + BLACK + getHealth() + RESET;

        }
        if (getStrength() >50){
            strengthStats = CYAN_BOLD + "Strength: " + RED + getStrength() + RESET;

        }else if (getStrength() <= 50 && getStrength() > 20){
            strengthStats = CYAN_BOLD + "Strength: " + YELLOW + getStrength() + RESET;

        }else if (getStrength() <= 20 && getStrength() >= 1) {
            strengthStats = CYAN_BOLD + "Strength: " + BLACK + getStrength() + RESET;

        }

        return nameStats + "\n" + healthStats + "    " + strengthStats;}


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
