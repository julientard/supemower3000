package com.mowitnow.supermower300.constant;

public enum OrientationEnum {

	N(0, 0, 1), 
	S(180, 0, -1), 
	E(90, 1, 0), 
	O(270, -1, 0);

	private OrientationEnum(int degree, int gapX, int gapY) {
		this.degree = degree;
		this.gapX = gapX;
		this.gapY = gapY;
	}

	/**
	 * Orientation en degrés
	 */
	public int degree;

	/**
	 * Facteur à appliquer en X lors d'un déplacement
	 */
	public int gapX;

	/**
	 * Facteur à appliquer en Y lors d'un déplacement
	 */
	public int gapY;

	public int getGapX() {
		return gapX;
	}

	public int getGapY() {
		return gapY;
	}

	public int getDegree() {
		return degree;
	}

	
	private static OrientationEnum getByDegree(int degree) {
		for (OrientationEnum e : OrientationEnum.values()) {
			if (e.degree == degree) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Effectue une rotation
	 * @param mouvement valeur de la rotation en degrés
	 * @return la nouvelle {@linkplain OrientationEnum}
	 */
	public OrientationEnum turn(int mouvement) {

		int out = this.degree + mouvement;

		if (out < 0) {
			out = out + 360;
		} else if (out >= 360) {
			out = out - 360;
		}

		OrientationEnum o = OrientationEnum.getByDegree(out);

		return o;

	}

}
