package com.Janaina.laboration.Game.Shop.ShopCategories;


import com.Janaina.laboration.Game.Shop.ShopProducts;

import java.util.ArrayList;
import java.util.List;

public class Attacks {
    private String name;
    private int damage;

    public Attacks(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
