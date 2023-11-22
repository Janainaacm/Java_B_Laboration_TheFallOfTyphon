package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Variables.ACharacters;
import com.Janaina.laboration.Resources.Scanners;

import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;

public class Typhon extends ACharacters {
    public Typhon() {
        super("Typhon", 7, 200, 10, 20, 60, 300, 100, 0, "Apocalyptic Surge", 200);
    }

    //Final boss



    @Override
    public void attack(ACharacters target,  Scanners sc) {
        System.out.println(RED_BOLD_BRIGHT + getName() + " used " + getDefaultAttack() + " on you!" + RESET);
        sleepThread(RED + "・・*:・:・゜:==≡≡Σ=͟͟͞͞(✡)`Д´\uD80C\uDCF5" + "\n" + RESET );
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
        return RED_BACKGROUND + "            " + BLACK + BOLD + UNDERLINED + RED_BACKGROUND + "TYPHON" + RESET + RED_BACKGROUND + "            " + RESET + "\n" + RED + ITALIC + "Health: " + getHealth() + "     Max damage: " + (getStrength() * getBaseDamage());

    }

    @Override
    public void receiveDamage(ACharacters player, int damage) {

        setHealth(getHealth() - damage);
        System.out.println(YELLOW + "Your attack did " + damage + " damage!" + RESET);

        chillForASecond(500);

    }


}
