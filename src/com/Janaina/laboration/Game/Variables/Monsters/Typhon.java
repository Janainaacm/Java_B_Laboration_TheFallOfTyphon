package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Variables.Characters;

import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;

public class Typhon extends Characters{
    public Typhon() {
        super("Typhon", 7, 100, 10, 20, 60, 300, 100, 0, "Apocalyptic Surge", 200);
    }

    //Final boss



    @Override
    public void attack(Characters target) {
        System.out.println(RED_BOLD_BRIGHT + getName() + " used " + getDefaultAttack() + " on you!" + RESET);
        sleepThread(RED + "・・*:・:・゜:==≡≡Σ=͟͟͞͞(✡)`Д´\uD80C\uDCF5" + "\n" + RESET );
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
        return RED_BACKGROUND + "            " + BLACK + BOLD + UNDERLINED + RED_BACKGROUND + "TYPHON" + RESET + RED_BACKGROUND + "            " + RESET + "\n" + RED + ITALIC + "Health: " + getHealth() + "     Max damage: " + (getStrength() * getBaseDamage());

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
