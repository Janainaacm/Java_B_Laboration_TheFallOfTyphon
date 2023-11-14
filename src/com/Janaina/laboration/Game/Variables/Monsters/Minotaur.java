package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Variables.Characters;

import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;

public class Minotaur extends Characters {
    public Minotaur() {
        super("Minotaur", 3, 100, 10, 30, 30, 150, 70, 0, "Bestial Fury", 100);
    }
    //Level 4


    @Override
    public void attack(Characters target) {
        System.out.println(BLUE + BOLD + getName() + " used " + getDefaultAttack() + " on you!" + RESET);
        sleepThread(BLUE + "炎炎炎炎炎炎" + "\n" + RESET);
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
        return BLACK_BACKGROUND + "           " + BLUE_DARK + BOLD + UNDERLINED + "Minotaur" + RESET + BLACK_BACKGROUND + "           " + RESET + "\n" + BLUE_DARK + ITALIC + "Health: " + getHealth() + "     Max damage: " + (getStrength() * getBaseDamage());

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
