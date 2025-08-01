package com.cirilgroup.aventurier.model;

import java.util.Objects;

/**
 * Représente une position sur la carte
 * avec des coordonnées x et y.
 * Cette classe est immuable et fournit des méthodes
 * pour accéder aux coordonnées.
 * 
 * @author Ciril Group
 * @version 1.0
 */
public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Déplace la position actuelle dans la direction spécifiée.
     * 
     * @param dir La direction dans laquelle déplacer la position.
     * @return Une nouvelle instance de Position avec les coordonnées mises à jour.
     */
    public Position moveBy(Direction dir) {
        return new Position(x + dir.getDx(), y + dir.getDy());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Position))
            return false;
        Position pos = (Position) o;
        return x == pos.x && y == pos.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}