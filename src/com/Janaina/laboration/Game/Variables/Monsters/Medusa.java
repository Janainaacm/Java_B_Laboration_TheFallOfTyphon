package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Variables.ACharacters;
import com.Janaina.laboration.Resources.Scanners;

import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.TextDelay.*;

public class Medusa extends ACharacters {
    private int id;

    public Medusa() {
        super("Medusa", 3, 100, 10, 10, 30, 100, 60, 0, "Serpents Curse", 100);
    }
    //Level 3


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void attack(ACharacters target,  Scanners sc) {
        System.out.println(GREEN_LIGHT + BOLD + getName() + " used " + getDefaultAttack() + " on you!" + RESET);
        sleepThread(GREEN_LIGHT + "\uD80C\uDD9A\uD80C\uDD97\uD80C\uDD9A\uD80C\uDD97\uD80C\uDD9A\uD80C\uDD97" + "\n" + RESET);
        chillForASecond(200);
    }

    @Override
    public boolean flee(ACharacters target) {
        return true;
    }

    @Override
    public boolean dodge(ACharacters player) {

        Random random = new Random();
        int randomValue = random.nextInt(1, 100);

        return randomValue < getAgility();

    }

    @Override
    public String getStats() {

        return BLACK_BACKGROUND + "            " + GREEN_DARK + BOLD + UNDERLINED + "Medusa" + RESET + BLACK_BACKGROUND + "            " + RESET + "\n" + GREEN_LIGHT + ITALIC + "Health: " + getHealth() + "     Max damage: " + (getStrength() * getBaseDamage());

    }


    @Override
    public void receiveDamage(ACharacters player, int damage) {

        setHealth(getHealth() - damage);
        System.out.println(YELLOW + "Your attack did " + damage + " damage!" + RESET);

        chillForASecond(500);

    }
}
