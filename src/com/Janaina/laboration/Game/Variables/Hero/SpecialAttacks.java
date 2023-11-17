package com.Janaina.laboration.Game.Variables.Hero;

public class SpecialAttacks {

    public static void specialAttackSirens(Player player){
        player.addSpecialAttack(new Attacks("Sirens shell", 20));

    }

    public static void specialAttackMedusa(Player player){
        player.addSpecialAttack(new Attacks("Medusa's head", 30));

    }
    public static void specialAttackMinotaur(Player player){

    }


}
