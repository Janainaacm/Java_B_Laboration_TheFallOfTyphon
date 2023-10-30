package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;

import java.util.Objects;

import static com.Janaina.laboration.Resources.PrintHandler.println;

public class LevelOne {
    //Fury attack

    public void playLevelOne(Player player){
        Fury fury = new Fury();
        println("1");


        if (fury.getHealth() <= 0){
            levelOneComplete("winner");
            player.setGold(player.getGold() + fury.getGold());

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
