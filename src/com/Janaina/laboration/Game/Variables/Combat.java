package com.Janaina.laboration.Game.Variables;

public interface Combat {

    void attack(Characters target);
    void flee(Characters target);
    void dodge(Characters target);
    String getStats();
    boolean didDodge();
    void receiveDamage(Characters target);


}
