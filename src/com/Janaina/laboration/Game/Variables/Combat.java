package com.Janaina.laboration.Game.Variables;

public interface Combat {

    void attack(Characters target);
    boolean flee(Characters target);
    boolean dodge(Characters target);
    String getStats();
    void receiveDamage(Characters target);


}
