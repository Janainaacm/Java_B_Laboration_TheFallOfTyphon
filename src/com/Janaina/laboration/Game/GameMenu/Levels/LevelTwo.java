package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;

import java.util.Objects;

import static com.Janaina.laboration.Resources.PrintHandler.println;

public class LevelTwo {
    // Sirens

    public void playLevelTwo(Player player){
        Siren siren = new Siren();
        println("2");

        if (siren.getHealth() <= 0){
            levelTwoComplete("winner");
            player.setGold(player.getGold() + siren.getGold());

        }


    }

    public boolean levelTwoComplete(String text){

        if (Objects.equals(text, "winner")){
            return true;
        } else {
            return false;
        }
    }

}
