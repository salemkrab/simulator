package com.cirilgroup.aventurier.model;

import java.util.Arrays;

/**
 * Représente la carte du jeu
 * Cette classe gère la grille de la carte, vérifie si une position est à
 * l'intérieur de la carte
 * et si une position est un obstacle.
 * La carte est représentée par une grille de caractères où '#' représente un
 * obstacle.
 * Les dimensions de la carte sont définies par sa largeur et sa hauteur.
 * La grille est initialisée lors de la création de l'objet Map.
 * Les méthodes fournies permettent de vérifier si une position donnée est à
 * l'intérieur de la carte
 * et si cette position est un obstacle.
 * 
 * @author Ciril Group
 * @version 1.0
 */
public class Map {
    private final char[][] grid;
    private final int width;
    private final int height;

    /**
     * Constructeur de la carte
     *
     * @param width  largeur de la carte
     * @param height hauteur de la carte
     * @param grid   grille représentant la carte
     */
    public Map(int width, int height, char[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = copyGrid(grid);
    }

    /** Copie en profondeur un tableau 2D de taille [height][width] */
    private static char[][] copyGrid(char[][] original) {
        char[][] copy = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            // Arrays.copyOf fait aussi une copie indépendante
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }

    /**
     * Vérifie si les coordonnées (x, y) sont à l'intérieur de la carte
     *
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si (x, y) est à l'intérieur de la carte, false sinon
     */
    public boolean isInside(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    /**
     * Vérifie si la position (x, y) est un obstacle sur la carte
     *
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si (x, y) est un obstacle, false sinon
     */
    public boolean isObstacle(int x, int y) {
        return grid[y][x] == '#';
    }

    /**
     * Retourne la largeur de la carte
     * 
     * @return la largeur de la carte
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retourne la hauteur de la carte
     * 
     * @return la hauteur de la carte
     */
    public int getHeight() {
        return height;
    }

}