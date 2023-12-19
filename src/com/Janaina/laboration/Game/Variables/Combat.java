package com.Janaina.laboration.Game.Variables;

import com.Janaina.laboration.DBConnection;
import com.Janaina.laboration.Resources.Scanners;

public interface Combat {

    void attack(ACharacters target, Scanners sc, DBConnection db);
    boolean flee(ACharacters target);
    boolean dodge(ACharacters target);
    String getStats(DBConnection db);
    void receiveDamage(ACharacters target, int damage);


}
