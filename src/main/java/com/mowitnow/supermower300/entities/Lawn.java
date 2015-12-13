package com.mowitnow.supermower300.entities;

/**
 * A {@linkplain Lawn} with his size
 * @author Julien
 *
 */
public class Lawn {
	
	private int height;

	private int width;

	/**
	 * @return height (coordinate y)
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return width (coordinate x)
	 */
	public int getWidth() {
		return width;
	}

	public Lawn(int width, int height) {
		super();
		this.height = height;
		this.width = width;
	}
}
