package com.mowitnow.supermower300.entities.vehicles;

import java.util.HashMap;

import com.mowitnow.supermower300.entities.Action;

/**
 * Extends {@linkplain Vehicle} and describe his available actions: G, D and A
 * 
 * @author Julien
 *
 */
public class Mower extends Vehicle {

    static {
        actions = new HashMap<>();
        actions.put('G', new Action('G', -90, 0));
        actions.put('D', new Action('D', 90, 0));
        actions.put('A', new Action('A', 0, 1));
    }

    @Override
    public String toString() {
        return "Tondeuse en position x=" + this.x + ", y=" + this.y + " vers " + this.orientation.name();
    }

}
