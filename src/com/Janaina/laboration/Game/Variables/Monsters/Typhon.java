package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;

public class Typhon extends Characters{
    public Typhon() {
        super("Typhon", 7, 100, 10, 20, 30, 300, 100, 0, "Apocalyptic Surge");
    }

    //Final boss



    @Override
    public void attack(Characters target) {

    }

    @Override
    public void flee(Characters target) {

    }

    @Override
    public void dodge(Characters target) {
        //ducka han ja eller nej - recieveDamage
    }

    @Override
    public String getStats() {
        return RED_BACKGROUND + "           " + BLACK_BOLD + "TYPHON" + RESET + RED_BACKGROUND + "           " + RESET
                + "\n" + RED_BACKGROUND + " " + BLACK_BOLD + "Health: " + getHealth() + "   Strength: " + getStrength() + RESET + RED_BACKGROUND + " " + RESET
                + "\n" + RED_BACKGROUND + "         " + BLACK_BOLD_BRIGHT + "FINAL BOSS" + RESET + RED_BACKGROUND + "         " + RESET
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
