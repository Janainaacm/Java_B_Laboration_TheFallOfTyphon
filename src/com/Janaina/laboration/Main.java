package com.Janaina.laboration;

import com.Janaina.laboration.Game.Storyline;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.Colors.RESET;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        Inventory inventory = new Inventory();
        Storyline storyline = new Storyline();
        Scanners sc = new Scanners();

        storyline.mainGameMenu(player, sc, inventory);

    }
}
