package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;

public class Fury extends Characters {
    public Fury() {
        super("Fury", 3, 50, 3, 10, 10, 10, 30, 0);
    }
    //Level 1 monster




    @Override
    public String attack() {

        return "meow";
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
