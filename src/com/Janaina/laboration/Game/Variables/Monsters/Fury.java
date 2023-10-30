package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Characters;
import com.Janaina.laboration.Game.Variables.Hero.Player;

import java.util.List;
import java.util.Random;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.TextDelay.chillForASecond;

public class Fury extends Characters {
    public Fury() {
        super("Fury", 1, 50, 10, 10, 10, 15, 30, 0, "Rage of the Erinyes");
    }
    //Level 1 monster




    @Override
    public void attack(Characters player) {
        player.dodge(this);

    }

    @Override
    public boolean flee(Characters player) {
        return true;
    }

    @Override
    public void dodge(Characters player) {
        if (didDodge()){
            System.out.println(RED + getName() + " dodged your attack!");
            chillForASecond(200);
            attack(player);
        }else {
            receiveDamage(player);
        }


    }

    @Override
    public String getStats() {
        return BLACK_BACKGROUND + "           " + RED_BOLD_BRIGHT + RED_UNDERLINED + "FURY" + RESET + BLACK_BACKGROUND + "           " + RESET
                + "\n" + BLACK_BACKGROUND + " " + RED_BOLD + "Health: " + getHealth() + "   Strength: " + getStrength() + RESET + BLACK_BACKGROUND + " " + RESET
                + "\n" + BLACK_BACKGROUND + "                          " + RESET
                + "\n" + RED_BOLD + "1. Attack\n2. Flee\n3. Inventory";
    }

    @Override
    public boolean didDodge() {
        Random random = new Random();
        int randomValue = random.nextInt(1, 100);

         return randomValue < getAgility();
    }

    @Override
    public void receiveDamage(Characters player) {
        Random random = new Random();
        int acquiredStrength = random.nextInt(1, player.getStrength());
        int damageFromAttack = acquiredStrength * player.getBaseDamage();

        setHealth(getHealth() - damageFromAttack);
        System.out.println(CYAN + "Your attack did " + damageFromAttack + " damage!" + RESET);

        chillForASecond(200);
        attack(player);
    }

}



