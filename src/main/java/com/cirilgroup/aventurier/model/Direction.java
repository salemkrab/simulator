package com.cirilgroup.aventurier.model;

/**
 * Représente les directions de déplacement possibles
 * dans le jeu.
 * N = Nord, S = Sud, E = Est, O = Ouest.
 * Chaque direction est associée à un vecteur de déplacement
 * (dx, dy) qui indique comment les coordonnées changent
 * lorsqu'on se déplace dans cette direction.
 * Par exemple, N correspond à (0, -1) ce qui signifie
 * qu'on ne change pas la coordonnée x mais on diminue
 * la coordonnée y de 1.
 * 
 * @author Ciril Group
 * @version 1.0
 */
public enum Direction {
    N(0, -1), S(0, 1), E(1, 0), O(-1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}