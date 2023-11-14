package com.Janaina.laboration;

import com.Janaina.laboration.Game.Storyline;
import com.Janaina.laboration.Game.Variables.Hero.Player;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        Storyline Storyline = new Storyline();


        Storyline.mainGameMenu(player);

    }
}
