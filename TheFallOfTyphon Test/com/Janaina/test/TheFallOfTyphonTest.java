package com.Janaina.test;

import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Storyline;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Medusa;
import com.Janaina.laboration.Resources.Scanners;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TheFallOfTyphonTest {

    @Mock
    Scanners scanners;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPlayerLevelUpMethod() {
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
    public void testPlayerTakeDamage() {
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
    public void testIfFightEndsWhenMonsterDies() {
        Player player1 = new Player();
        Fury fury1 = new Fury();

        assertEquals(!fury1.isAlive(), player1.playerWins(fury1));

    }

    @Test
    public void playerGetsRewardAfterFight() {
        Player player1 = new Player();
        Fury fury1 = new Fury();
        //Player stats before
        int goldBefore = player1.getGold();
        int furyGold = fury1.getGold();

        boolean correctGold;
        int goldAfter = 0;

        if (player1.playerWins(fury1)) {
            goldAfter = player1.getGold() + fury1.getGold();
        }
        assertEquals(goldBefore, goldAfter);
    }


    @Test
    public void buyWeapon() {
        Player player = new Player();
        Inventory inventory = new Inventory();
        ShopProducts expectedWeapon = new ShopProducts("Cursed Scythe", "Reaper's Grasp", "▬ι══════ﺤ", 170, 10, 0, 0, 0);
        player.setGold(170);
        Storyline storyline = new Storyline();
        setShopWeaponBehave();
        storyline.mainGameMenu(player, scanners, inventory);

        Assert.assertEquals(inventory.weaponsList.size(), 1);
        Assert.assertEquals(inventory.weaponsList.get(0).getName(), expectedWeapon.getName());
        Assert.assertEquals(inventory.weaponsList.get(0).getStrength(), expectedWeapon.getStrength());
        Assert.assertEquals(inventory.weaponsList.get(0).getPrice(), expectedWeapon.getPrice());
        Assert.assertEquals(inventory.weaponsList.get(0).getAttackName(), expectedWeapon.getAttackName());

    }

    public void setShopWeaponBehave() {
        when(scanners.chooseFromMainMenu()).thenReturn(2, 0);
        when(scanners.chooseFromShopMenu()).thenReturn(1, 0);
        when(scanners.chooseFromWeapons()).thenReturn(3, 0);
    }


}
