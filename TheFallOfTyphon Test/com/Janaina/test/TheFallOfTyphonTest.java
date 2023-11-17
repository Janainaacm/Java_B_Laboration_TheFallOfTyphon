package com.Janaina.test;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Medusa;
import com.Janaina.laboration.Resources.Scanners;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TheFallOfTyphonTest {

    @Mock
    Scanners scanners;

    @Mock
    Player player;



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

        int initialHealth = testPlayer1.getHealth();
        System.out.println("Initial health: " + testPlayer1.getHealth());

        testPlayer1.receiveDamage(fury1, 0);

        int resultHealth = testPlayer1.getHealth();
        System.out.println("Health after attack: " + testPlayer1.getHealth());

        assertNotEquals(initialHealth, resultHealth);
    }

    @Test
    public void testIfFightEndsWhenMonsterDies(){
        Player player1 = new Player();
        Fury fury1 = new Fury();

        assertEquals(!fury1.isAlive(), player1.playerWins(fury1));

    }

    @Test
    public void playerGetsRewardAfterFight(){
        Fury fury1 = new Fury();
        Inventory inventory = new Inventory();
        //Player stats before
        int goldBefore = player.getGold();
        int xpBefore = player.getExperience();
        when(player.playerWins(fury1)).thenReturn(true);

    }


    @Test
    public void setShopWeaponsMenu() {
        when(scanners.chooseFromMainMenu()).thenReturn(2);
        when(scanners.chooseFromShopMenu()).thenReturn(2);

    }
}
