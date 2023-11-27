package com.Janaina.laboration;

import com.Janaina.laboration.Game.Shop.ShopProducts;
import com.Janaina.laboration.Game.Storyline;
import com.Janaina.laboration.Game.Variables.Hero.Inventory;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        Inventory inventory = new Inventory();
        Storyline storyline = new Storyline();
        Scanners sc = new Scanners();
        List<ShopProducts> potionsProductList = new ArrayList<>();
        List<ShopProducts> weaponsProductList = new ArrayList<>();

        player.setAvailableLevels(2);

        storyline.mainGameMenu(player, sc, inventory, potionsProductList, weaponsProductList);

    }
}
