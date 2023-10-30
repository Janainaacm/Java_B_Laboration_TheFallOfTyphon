package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;

public class Fury extends Characters {
    public Fury() {
        super("Fury", 1, 50, 10, 10, 10, 15, 30, 0, "Rage of the Erinyes");
    }
    //Level 1 monster




    @Override
    public void attack(Characters target) {


    }

    @Override
    public void flee(Characters target) {

    }

    @Override
    public void dodge(Characters target) {

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

        return false;
    }

    @Override
    public void receiveDamage(Characters target) {

    }
}
