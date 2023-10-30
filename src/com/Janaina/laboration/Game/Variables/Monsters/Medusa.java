package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.println;
import static com.Janaina.laboration.Resources.TextDelay.suspensefulDots;

public class Medusa extends Characters {
    public Medusa() {
        super("Medusa", 3, 100, 10, 10, 30, 100, 60, 0, "Gorgon's Gazing Curse");
    }
    //Level 3



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

        return BLACK_BACKGROUND + "           " + GREEN_BOLD_BRIGHT + GREEN_UNDERLINED + "MEDUSA" + RESET + BLACK_BACKGROUND + "           " + RESET
                + "\n" + BLACK_BACKGROUND + " " + GREEN_BOLD + "Health: " + getHealth() + "   Strength: " + getStrength() + RESET + BLACK_BACKGROUND + " " + RESET
                + "\n" + BLACK_BACKGROUND + "                            " + RESET
                + "\n" + GREEN_BOLD + "1. Attack\n2. Flee\n3. Inventory";
    }

    @Override
    public boolean didDodge() {
        return false;
    }

    @Override
    public void receiveDamage(Characters target) {

    }
}
