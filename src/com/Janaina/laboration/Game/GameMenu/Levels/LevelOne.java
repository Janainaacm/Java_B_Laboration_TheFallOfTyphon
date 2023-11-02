package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;

import java.util.Objects;

public class LevelOne {
    //Fury attack

    public void playLevelOne(Player player, Inventory Inventory){
        Fury fury = new Fury();

        player.act(fury, Inventory);



    }

    public boolean levelOneComplete(Player player){
        if (player.isAlive()){
            return true;
        }else {
            return false;
        }

    }

}
