package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;
import com.Janaina.laboration.Game.Variables.Monsters.Typhon;

import java.util.Objects;

import static com.Janaina.laboration.Resources.PrintHandler.println;

public class LevelSix {

    public void playLevelSix(Player player, Inventory inventory){
        Typhon typhon = new Typhon();
        Fury fury = new Fury();

        while (true){
            player.act(fury, inventory);
            if (fury.isAlive()) {
                break;
            }
            fury.revive();

            player.act(fury, inventory);
            if (fury.isAlive()) {
                break;
            }
            fury.revive();

            //Divide player agility by 2

            player.act(fury, inventory);
            if (fury.isAlive()) {
                break;
            }
            fury.revive();





        }
    }
}
