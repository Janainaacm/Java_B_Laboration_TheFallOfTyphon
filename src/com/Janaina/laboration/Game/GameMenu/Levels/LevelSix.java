package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;
import com.Janaina.laboration.Game.Variables.Monsters.Typhon;

import java.util.Objects;

import static com.Janaina.laboration.Resources.PrintHandler.println;

public class LevelSix {
    //Final boss Typhon

    public void playLevelSix(Player player){
        Typhon typhon = new Typhon();
        println("6");

        if (typhon.getHealth() <= 0){
            levelSixComplete("winner");
            player.setGold(player.getGold() + typhon.getGold());

        }

    }
    public boolean levelSixComplete(String text){

        if (Objects.equals(text, "winner")){
            return true;
        } else {
            return false;
        }
    }

}
