package com.mowitnow.supermower300.entities;

/**
 * An immutable {@linkplain Action} is a forward mouvement and/or a rotation
 * Each vehicle is able to have his own actions
 * @author Julien
 *
 */
public final class Action {
	
	private char code;
	
	private int rotation;
	
	private int straight;

	/**
	 * 
	 * @return code to call the action
	 */
	public char getCode() {
		return code;
	}

	/**
	 * 
	 * @return angle in degree (0, 90, -90, 180,...)
	 */
	public int getRotation() {
		return rotation;
	}

	/**
	 * 
	 * @return value of forward mouvement (generally 1, but could be 2 or more)
	 */
	public int getStraight() {
		return straight;
	}
	
	public Action(char code, int rotation, int straight) {
		super();
		this.code = code;
		this.rotation = rotation;
		this.straight = straight;
	}
}
