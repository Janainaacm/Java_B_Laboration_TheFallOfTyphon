package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;

public class Cerberus extends Characters {
    public Cerberus() {
        super("Cerberus", 5, 100, 10, 10, 40, 200, 70, 0, "Underworld Carnage");
    }
    //Level 5


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
        return BLACK_BACKGROUND + "          " + RED_UNDERLINED + "CERBERUS" + RESET + BLACK_BACKGROUND + "          " + RESET
                + "\n" + BLACK_BACKGROUND + " " + RED_BOLD + "Health: " + getHealth() + "   Strength: " + getStrength() + RESET + BLACK_BACKGROUND + " " + RESET
                + "\n" + BLACK_BACKGROUND + "                            " + RESET
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
