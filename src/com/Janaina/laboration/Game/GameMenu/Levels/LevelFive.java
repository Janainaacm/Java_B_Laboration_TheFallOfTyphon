package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Cerberus;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;

import java.util.Objects;

import static com.Janaina.laboration.Resources.PrintHandler.println;

public class LevelFive {
    //Gates of hell + Cerberus

    public void playLevelFive(Player player){
        Cerberus cerberus = new Cerberus();
        println("5");
        if (cerberus.getHealth() <= 0){
            levelFiveComplete("winner");
            player.setGold(player.getGold() + cerberus.getGold());

        }
    }

    public boolean levelFiveComplete(String text){

        if (Objects.equals(text, "winner")){
            return true;
        } else {
            return false;
        }
    }

}
