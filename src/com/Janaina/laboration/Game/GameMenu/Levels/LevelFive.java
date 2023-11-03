package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Cerberus;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;

import java.util.Objects;

import static com.Janaina.laboration.Resources.PrintHandler.println;

public class LevelFive {
    //Gates of hell + Cerberus
    private boolean levelComplete = false;

    public void playLevelFive(Player player, Inventory Inventory){
        Cerberus cerberus = new Cerberus();
        println("5");
        if (cerberus.getHealth() <= 0){
            levelFiveComplete();
            player.setGold(player.getGold() + cerberus.getGold());

        }
    }

    public boolean levelFiveComplete(){

        return levelComplete;
    }

}
