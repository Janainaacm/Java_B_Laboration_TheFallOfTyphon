package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Variables.ACharacters;
import com.Janaina.laboration.Resources.Scanners;

import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;

public class Cerberus extends ACharacters {
    private int id;

    public Cerberus() {
        super("Cerberus", 5, 150, 10, 10, 40, 200, 70, 0, "Underworld Carnage", 150);
    }
    //Level 5


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public void attack(ACharacters target, Scanners sc, DBConnection db) {
        System.out.println(RED_BOLD_BRIGHT + getName() + " used " + getDefaultAttack() + " on you!" + RESET);
        sleepThread(RED + "⋆꙳•̩̩͙❅*̩̩͙‧͙ ‧͙*̩̩͙❆ ͙͛ ˚₊⋆" + "\n" + RESET);
        chillForASecond(200);

    }


    @Override
    public boolean flee(ACharacters player) {
        return true;
    }

    @Override
    public boolean dodge(ACharacters player) {

        Random random = new Random();
        int randomValue = random.nextInt(1, 100);

        return randomValue < getAgility();

    }

    @Override
    public String getStats(DBConnection db) {
        return BLACK_BACKGROUND + "           " + RED + BOLD + UNDERLINED + "Cerberus" + RESET + BLACK_BACKGROUND + "           " + RESET + "\n" + RED + ITALIC + "Health: " + getHealth() + "    Max damage: " + (getStrength() * getBaseDamage());
    }


    @Override
    public void receiveDamage(ACharacters player, int damage) {

        setHealth(getHealth() - damage);
        System.out.println(YELLOW + "Your attack did " + damage + " damage!" + RESET);

        chillForASecond(500);

    }
}
