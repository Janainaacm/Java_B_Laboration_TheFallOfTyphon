package com.Janaina.laboration;

import com.Janaina.laboration.Game.GameMenu.Levels.LevelMenu;
import com.Janaina.laboration.Game.Introduction;
import com.Janaina.laboration.Game.Storyline;
import com.Janaina.laboration.Game.Variables.Hero.Player;
import com.Janaina.laboration.Game.Variables.Monsters.*;
import com.Janaina.laboration.Resources.Storyteller;

import static com.Janaina.laboration.Resources.Colors.*;
import static com.Janaina.laboration.Resources.PrintHandler.*;
import static com.Janaina.laboration.Resources.Scanners.pressEnter;
import static com.Janaina.laboration.Resources.TextDelay.suspensefulDots;

public class Main {
    public static void main(String[] args) {

        Storyline Storyline = new Storyline();
        Player fury = new Player();
        System.out.println(BLACK_BACKGROUND + " " + WHITE_BOLD_BRIGHT + "HEJ" + RESET + BLACK_BACKGROUND + " " + RESET);

        /*
        fury.setAgility(70);


        if (fury.didDodge()){
            System.out.println("slay");
        }else { //Be able to press enter
            System.out.println("gay");
        }

         */

        //Storyline.mainGameMenu();



}}
