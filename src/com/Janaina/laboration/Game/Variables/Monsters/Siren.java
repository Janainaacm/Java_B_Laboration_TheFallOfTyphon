package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;

public class Siren extends Characters{
    public Siren() {
        super("Siren", 2, 60, 10, 30, 15, 50, 40, 0, "Deadly Wail");
    }
    //Level 2
    //𓆝 𓆟 𓆞 𓆝 𓆟

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
        return BLACK_BACKGROUND + "           " + CYAN_UNDERLINED + "SIREN" + RESET + BLACK_BACKGROUND + "           " + RESET
                + "\n" + BLACK_BACKGROUND + " " + CYAN_BOLD + "Health: " + getHealth() + "   Strength: " + getStrength() + RESET + BLACK_BACKGROUND + " " + RESET
                + "\n" + BLACK_BACKGROUND + "                           " + RESET
                + "\n" + CYAN_BOLD + "1. Attack\n2. Flee\n3. Inventory";
    }

    @Override
    public boolean didDodge() {
        return false;
    }

    @Override
    public void receiveDamage(Characters target) {

    }



}
