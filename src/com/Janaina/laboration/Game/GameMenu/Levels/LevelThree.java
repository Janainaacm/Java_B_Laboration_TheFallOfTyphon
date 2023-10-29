package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Medusa;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;

import java.util.Objects;

import static com.Janaina.laboration.Resources.PrintHandler.println;

public class LevelThree {
    //Medusa

    public void playLevelThree(Player player){
        Medusa medusa = new Medusa();
        Fury fury = new Fury();
        println("3");

        if (medusa.getHealth() <= 0){
            levelThreeComplete("winner");
            player.setGold(player.getGold() + medusa.getGold());

        }

    }
    public boolean levelThreeComplete(String text){

        if (Objects.equals(text, "winner")){
            return true;
        } else {
            return false;
        }
    }

}
