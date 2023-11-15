package com.Janaina.laboration.Game.Variables;

public interface Combat {

    void attack(ACharacters target);
    boolean flee(ACharacters target);
    boolean dodge(ACharacters target);
    String getStats();
    void receiveDamage(ACharacters target, int damage);


}
