package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;

public class Siren extends Characters{
    public Siren() {
        super("Siren", 10, 60, 6, 30, 15, 50, 40, 0);
    }
    //Level 2


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
