package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Variables.Characters;

import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;

public class Cerberus extends Characters {
    public Cerberus() {
        super("Cerberus", 5, 100, 10, 10, 40, 200, 70, 0, "Underworld Carnage", 150);
    }
    //Level 5


    @Override
    public void attack(Characters target) {
        System.out.println(RED_BOLD_BRIGHT + getName() + " used " + getDefaultAttack() + " on you!" + RESET);
        sleepThread(RED + "⋆꙳•̩̩͙❅*̩̩͙‧͙ ‧͙*̩̩͙❆ ͙͛ ˚₊⋆" + "\n" + RESET );
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
        return BLACK_BACKGROUND + "           " + RED + BOLD + UNDERLINED + "Cerberus" + RESET + BLACK_BACKGROUND + "           " + RESET + "\n" + RED + ITALIC + "Health: " + getHealth() + "    Max damage: " + (getStrength() * getBaseDamage());
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
