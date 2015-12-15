package com.mowitnow.supermower300.entities;

/**
 * A {@linkplain Lawn} with his size
 * 
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
        return this.height;
    }

    /**
     * @return width (coordinate x)
     */
    public int getWidth() {
        return this.width;
    }

    public Lawn(final int width, final int height) {

        if ((width < 0) || (height < 0)) {
            throw new IllegalArgumentException("La largeur et la hauteur de la pelouse doivent être des entiers positifs");
        }

        this.height = height;
        this.width = width;
    }
}
