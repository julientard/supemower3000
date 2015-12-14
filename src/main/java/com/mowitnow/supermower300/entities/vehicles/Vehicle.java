package com.mowitnow.supermower300.entities.vehicles;

import java.util.HashMap;

import com.mowitnow.supermower300.constant.OrientationEnum;
import com.mowitnow.supermower300.entities.Action;

/**
 * Describe a Vehicle with position, orientation and available actions
 * 
 * @author Julien
 *
 */
public abstract class Vehicle {

    /**
     * x position
     */
    protected int x;

    /**
     * y position
     */
    protected int y;

    /**
     * vehicle orientation
     */
    protected OrientationEnum orientation;

    /**
     * Instructions list (one action per character)
     */
    protected String instructions;

    /**
     * Useful for logging
     */
    protected String name;


    protected static HashMap<Character, Action> actions;


    public int getX() {
        return this.x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public OrientationEnum getOrientation() {
        return this.orientation;
    }

    public void setOrientation(final OrientationEnum orientation) {
        this.orientation = orientation;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(final String instructions) {
        this.instructions = instructions;
    }

    /**
     * Liste of available actions for a vehicle
     */
    public static HashMap<Character, Action> getActions() {
        return actions;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }




}
