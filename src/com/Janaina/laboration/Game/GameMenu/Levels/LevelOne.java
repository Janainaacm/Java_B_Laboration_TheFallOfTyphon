package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;

import java.util.Objects;

import static com.Janaina.laboration.Resources.PrintHandler.println;

public class LevelOne {
    //Fury attack

    public void playLevelOne(Player player, Inventory Inventory){
        Fury fury = new Fury();
        println("1");


        while (player.isAlive() && fury.isAlive()){
            player.act(fury, Inventory);
        }

    }

    public boolean levelOneComplete(String text){

        if (Objects.equals(text, "winner")){
            return true;
        } else {
            return false;
        }
    }

}
