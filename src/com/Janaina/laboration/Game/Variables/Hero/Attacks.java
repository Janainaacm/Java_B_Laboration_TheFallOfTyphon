package com.Janaina.laboration.Game.Variables.Hero;

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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
