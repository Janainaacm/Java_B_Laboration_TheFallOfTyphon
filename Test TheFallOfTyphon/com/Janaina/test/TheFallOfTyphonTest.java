package com.Janaina.test;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Medusa;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TheFallOfTyphonTest {

    @Test
    public void testPlayerLevelUpMethod(){
        Player testPlayer1 = new Player();
        Medusa medusa = new Medusa();
        Fury fury1 = new Fury();
        Fury fury2 = new Fury();

        System.out.println("Your initial level is: " + testPlayer1.getLevel());
        //Now say that you manage to kill Medusa and two furies
        testPlayer1.gainExperience(medusa.getExperience() + fury1.getExperience() + fury2.getExperience());

        System.out.println("Your level after defeating medusa and two furies: " + testPlayer1.getLevel());
        System.out.println("Your experience is currently at: " + testPlayer1.getExperience());

        assertEquals(2, testPlayer1.getLevel());
        assertEquals((medusa.getExperience() + fury1.getExperience() + fury2.getExperience()) - ((testPlayer1.getLevel() - 1) * 100), testPlayer1.getExperience());

    }

    @Test
    public void testPlayerTakeDamage(){
        Player testPlayer1 = new Player();
        Fury fury1 = new Fury();

        System.out.println("Initial health: " + testPlayer1.getHealth());

        testPlayer1.receiveDamage(fury1);

        System.out.println("Health after attack: " + testPlayer1.getHealth());

    }

    @Test
    public void testIfFightEndsWhenMonsterDies(){
        Player player1 = new Player();
        Fury fury1 = new Fury();
        Inventory Inventory = new Inventory();

        fury1.setHealth(0);
        player1.act(fury1, Inventory);

        //?????????????????

    }



}
