package com.Janaina.laboration.Game.Variables;

import com.Janaina.laboration.Resources.Scanners;

public interface Combat {

    void attack(ACharacters target, Scanners sc);
    boolean flee(ACharacters target);
    boolean dodge(ACharacters target);
    String getStats();
    void receiveDamage(ACharacters target, int damage);


}
