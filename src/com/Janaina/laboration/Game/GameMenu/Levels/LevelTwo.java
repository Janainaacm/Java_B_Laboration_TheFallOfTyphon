package com.Janaina.laboration.Game.GameMenu.Levels;

import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.Fury;
import com.Janaina.laboration.Game.Variables.Monsters.Siren;

import java.util.Objects;

import static com.Janaina.laboration.Resources.Colors.PURPLE_LIGHT;
import static com.Janaina.laboration.Resources.Colors.RESET;
import static com.Janaina.laboration.Resources.PrintHandler.println;
import static com.Janaina.laboration.Resources.TextDelay.sleepThread;
import static com.Janaina.laboration.Resources.TextDelay.suspensefulDots;

public class LevelTwo {
    // Sirens
    public void playLevelTwo(Player player, Inventory Inventory){
        Siren siren = new Siren();


        player.act(siren, Inventory);
        if (siren.isAlive()){
            sleepThread(PURPLE_LIGHT + "Better luck next time" + RESET);
            suspensefulDots(PURPLE_LIGHT + "..." + RESET);
        }
        siren.revive();




    }



}
