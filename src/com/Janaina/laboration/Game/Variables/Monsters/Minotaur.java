package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;

public class Minotaur extends Characters {
    public Minotaur() {
        super("Minotaur", 25, 100, 15, 20, 20, 150, 70, 0);
    }
    //Level 4


    @Override
    public String attack() {
        return null;
    }

    @Override
    public void flee() {

    }

    @Override
    public String dodge() {
        return null;
    }

    @Override
    public String getStats() {
        return null;
    }

    @Override
    public boolean didDodge() {
        return false;
    }

    @Override
    public int recieveDamage() {
        return 0;
    }
}
