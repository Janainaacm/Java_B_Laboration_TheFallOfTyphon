package com.Janaina.laboration.Game.Variables;

import com.Janaina.laboration.DBConnection;

public abstract class ACharacters implements Combat {

    private String name;
    private int strength;
    //Styrka * baseDamage
    private int health;
    //Hur mycket liv den har
    private int baseDamage;
    //Hur mycket skada
    private int agility;
    //Högre agility = högre chans att dodge
    private int intelligence;
    //Smartare monster har större chans att göra mer skada
    private int gold;
    //Hur mycket man får för varje monster man dödar
    private int experience;
    private int level;
    private String defaultAttack;
    private final int defaultHealth;
    private int id;



    public ACharacters(String name, int strength, int health, int baseDamage, int agility, int intelligence, int gold, int experience, int level, String defaultAttack, int defaultHealth) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.baseDamage = baseDamage;
        this.agility = agility;
        this.intelligence = intelligence;
        this.gold = gold;
        this.experience = experience;
        this.level = level;
        this.defaultAttack = defaultAttack;
        this.defaultHealth = defaultHealth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDefaultAttack() {
        return defaultAttack;
    }

    public void setDefaultAttack(String defaultAttack) {
        this.defaultAttack = defaultAttack;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (this.health > defaultHealth){
            this.health = defaultHealth;
        }
    }

    public int getBaseDamage() {
        return baseDamage;
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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void revive(){
        this.health = defaultHealth;
    }

}
