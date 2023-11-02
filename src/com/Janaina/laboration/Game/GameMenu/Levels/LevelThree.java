package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Characters;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Medusa;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;

import java.util.Objects;

import static com.Janaina.laboration.Game.Variables.Hero.SpecialAttacks.specialAttackMedusa;
import static com.Janaina.laboration.Resources.PrintHandler.println;

public class LevelThree {
    //Medusa
    private boolean levelComplete = false;

    public void playLevelThree(Player player, Inventory Inventory) {
        Medusa medusa = new Medusa();
        Fury fury = new Fury();

        boolean playingLevel = true;
        while (playingLevel){







            if (levelThreeComplete() && !medusa.isAlive()) {

                specialAttackMedusa(player);

            }

        }



    }

    public boolean levelThreeComplete() {

        return levelComplete;
    }

}
