package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Variables.ACharacters;

import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;

public class Siren extends ACharacters {
    public Siren() {
        super("Siren", 2, 60, 10, 40, 15, 50, 40, 0, "Deadly Wail", 60);
    }
    //Level 2


    @Override
    public void attack(ACharacters target) {
        System.out.println(CYAN_BOLD_BRIGHT + getName() + " used " + getDefaultAttack() + " on you!" + RESET);
        sleepThread(CYAN + "࿐ ࿔*:･ﾟ࿐ ࿔*:･ﾟ࿐ ࿔*:･ﾟ" + "\n" + RESET);
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
        return BLACK_BACKGROUND + "            " + CYAN_BOLD_BRIGHT + CYAN_UNDERLINED + "Siren" + RESET + BLACK_BACKGROUND + "            " + RESET + "\n" + CYAN + ITALIC + "Health: " + getHealth() + "     Max damage: " + (getStrength() * getBaseDamage());

    }


    @Override
    public void receiveDamage(ACharacters player, int damage) {
        Random random = new Random();
        int acquiredStrength = random.nextInt(1, player.getStrength());
        int damageFromAttack = acquiredStrength * player.getBaseDamage();

        setHealth(getHealth() - damageFromAttack);
        System.out.println(YELLOW + "Your attack did " + damageFromAttack + " damage!" + RESET);

        chillForASecond(200);

    }



}
