package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;

public class Typhon extends Characters{
    public Typhon() {
        super("Typhon", 30, 100, 20, 20, 30, 300, 100, 0);
    }

    //Final boss



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
