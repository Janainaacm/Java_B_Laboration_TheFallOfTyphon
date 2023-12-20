package com.Janaina.laboration.Game.Shop.ShopCategories;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Resources.Scanners;


public class Potions {


    public void potionsShop(Player player, Scanners sc, DBConnection db) {
        db.shopPotions(player, sc);
    }
}