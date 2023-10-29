package com.Janaina.laboration.Game.Variables.Hero;

import com.Janaina.laboration.Game.Shop.ShopCategories.Armor.Armor;
import com.Janaina.laboration.Game.Shop.ShopCategories.Books.Attacks;
import com.Janaina.laboration.Game.Shop.ShopCategories.Weapons.Weapons;
import com.Janaina.laboration.Game.Variables.Characters;

import java.util.List;

public class Player extends Characters {

    public List<Attacks> specialAttackList;
    public List<Armor> equipmentList;
    public List<Weapons> weaponsList;

    public Player() {
        super("name", 10, 100, 5, 20, 20, 0, 0, 0);
    }

    public Player(String name, int strength, int health, int baseDamage, int agility, int intelligence, int gold, int experience, int level, List<Attacks> specialAttackList, List<Attacks> specialAttackList1, List<Armor> equipmentList, List<Weapons> weaponsList) {
        super(name, strength, health, baseDamage, agility, intelligence, gold, experience, level);
        this.specialAttackList = specialAttackList1;
        this.equipmentList = equipmentList;
        this.weaponsList = weaponsList;
    }

    @Override
    public List<Attacks> getSpecialAttackList() {
        return specialAttackList;
    }

    @Override
    public void setSpecialAttackList(List<Attacks> specialAttackList) {
        this.specialAttackList = specialAttackList;
    }

    public List<Armor> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Armor> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<Weapons> getWeaponsList() {
        return weaponsList;
    }

    public void setWeaponsList(List<Weapons> weaponsList) {
        this.weaponsList = weaponsList;
    }

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
