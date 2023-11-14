package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Variables.Characters;

import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;

public class Fury extends Characters {
    public Fury() {
        super("Fury", 2, 50, 10, 10, 10, 15, 30, 0, "Rage of the Erinyes", 50);
    }
    //Level 1 monster




    @Override
    public void attack(Characters player) {
        System.out.println(RED + BOLD + getName() + " used " + getDefaultAttack() + " on you!" + RESET);
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

        return BLACK_BACKGROUND + "            " + RED + BOLD + UNDERLINED + "FURY" + RESET + BLACK_BACKGROUND + "            " + RESET + "\n" + RED + ITALIC + "Health: " + getHealth() + "    Max damage: " + (getStrength() * getBaseDamage());

    }



    @Override
    public void receiveDamage(Characters player, int damage) {

        setHealth(getHealth() - damage);
        System.out.println(YELLOW + "Your attack did " + damage + " damage!" + RESET);

        chillForASecond(200);

    }







}



