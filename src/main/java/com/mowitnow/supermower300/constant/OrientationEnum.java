package com.mowitnow.supermower300.constant;

public enum OrientationEnum {

    N(0, 0, 1),
    S(180, 0, -1),
    E(90, 1, 0),
    O(270, -1, 0);

    private OrientationEnum(final int degree, final int gapX, final int gapY) {
        this.degree = degree;
        this.gapX = gapX;
        this.gapY = gapY;
    }

    /**
     * Angle in degree
     */
    public int degree;

    /**
     * mouvement on x axe
     */
    public int gapX;

    /**
     * mouvement on y axe
     */
    public int gapY;


    public int getGapX() {
        return this.gapX;
    }

    public int getGapY() {
        return this.gapY;
    }

    public int getDegree() {
        return this.degree;
    }

    private static OrientationEnum getByDegree(final int degree) {
        for (OrientationEnum e : OrientationEnum.values()) {
            if (e.degree == degree) {
                return e;
            }
        }
        return null;
    }

    /**
     * Make a turn from this {@linkplain OrientationEnum}
     * 
     * @param rotation angle in degree
     * @return the new {@linkplain OrientationEnum}
     */
    public OrientationEnum turn(final int rotation) {

        int out = this.degree + rotation;

        if (out < 0) {
            out = out + 360;
        }
        else if (out >= 360) {
            out = out - 360;
        }

        OrientationEnum o = OrientationEnum.getByDegree(out);

        return o;

    }

}
