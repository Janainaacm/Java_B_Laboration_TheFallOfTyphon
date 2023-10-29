package com.Janaina.laboration.Game.Variables.Monsters;

import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;

public class Siren extends Characters{
    public Siren(String name, int strength, int health, int baseDamage, int agility, int intelligence, int gold, int experience, int level, List<Attacks> specialAttackList) {
        super(name, strength, health, baseDamage, agility, intelligence, gold, experience, level);
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
