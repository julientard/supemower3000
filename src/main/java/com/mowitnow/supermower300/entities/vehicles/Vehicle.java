package com.mowitnow.supermower300.entities.vehicles;

import java.util.HashMap;

import com.mowitnow.supermower300.constant.OrientationEnum;
import com.mowitnow.supermower300.entities.Action;

/**
 * Describe a Vehicle with position, orientation and available actions
 * @author Julien
 *
 */
public abstract class Vehicle {

	/**
	 * Position en x
	 */
	protected int x;
	
	/**
	 * Position en y
	 */
	protected int y;
	
	/**
	 * Orientation du véhicule
	 */
	protected OrientationEnum orientation;
	
	/**
	 * Liste des instructions (une instruction / charactère)
	 */
	protected String instructions;
	
	
	protected static HashMap<Character, Action> actions;
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public OrientationEnum getOrientation() {
		return orientation;
	}

	public void setOrientation(OrientationEnum orientation) {
		this.orientation = orientation;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	 * Liste of available actions for a vehicle
	 */
	public static HashMap<Character, Action> getActions() {
		return actions;
	}


			

}
