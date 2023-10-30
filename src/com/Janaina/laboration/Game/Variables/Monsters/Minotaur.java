package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;

public class Minotaur extends Characters {
    public Minotaur() {
        super("Minotaur", 3, 100, 10, 30, 30, 150, 70, 0, "Bestial Fury");
    }
    //Level 4


    @Override
    public void attack(Characters target) {

    }

    @Override
    public boolean flee(Characters target) {
        return true;
    }

    @Override
    public void dodge(Characters target) {

    }

    @Override
    public String getStats() {
        return BLACK_BACKGROUND + "          " + YELLOW_UNDERLINED + "MINOTAUR" + RESET + BLACK_BACKGROUND + "          " + RESET
                + "\n" + BLACK_BACKGROUND + " " + YELLOW_BOLD + "Health: " + getHealth() + "   Strength: " + getStrength() + RESET + BLACK_BACKGROUND + " " + RESET
                + "\n" + BLACK_BACKGROUND + "                            " + RESET
                + "\n" + YELLOW_BOLD + "1. Attack\n2. Flee\n3. Inventory";
    }

    @Override
    public boolean didDodge() {
        return false;
    }

    @Override
    public void receiveDamage(Characters target) {

    }


}
