package com.Janaina.laboration.Game.Shop;

public class ShopProducts {

    private String name;
    private int price;
    private int strength;
    private int health;
    private int agility;
    private int intelligence;

    public ShopProducts(String name, int price, int strength, int health, int agility, int intelligence) {
        this.name = name;
        this.price = price;
        this.strength = strength;
        this.health = health;
        this.agility = agility;
        this.intelligence = intelligence;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}
