package com.Janaina.laboration.Game.Variables.Hero;

public class SpecialAttacks {

    private String name;
    private int damage;
    private boolean isAcquired;

    public SpecialAttacks(String name, int damage, boolean isAcquired) {
        this.name = name;
        this.damage = damage;
        this.isAcquired = isAcquired;
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

    public boolean isAcquired() {
        return isAcquired;
    }

    public void setAcquired(boolean acquired) {
        isAcquired = acquired;
    }
}
