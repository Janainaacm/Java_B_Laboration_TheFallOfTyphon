package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Minotaur;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;

import java.util.Objects;

import static com.Janaina.laboration.Resources.PrintHandler.println;

public class LevelFour {
    //Labyrinth + minotaur
    private boolean levelComplete = false;

    public void playLevelFour(Player player){
        Minotaur minotaur = new Minotaur();
        Fury fury = new Fury();
        println("4");

        if (minotaur.getHealth() <= 0){
            levelFourComplete();
            player.setGold(player.getGold() + minotaur.getGold());

        }
    }

    public boolean levelFourComplete(){

        return levelComplete;
    }

}
