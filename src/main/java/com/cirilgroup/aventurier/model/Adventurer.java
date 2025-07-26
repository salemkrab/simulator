package com.cirilgroup.aventurier.model;

/**
 * Représente un aventurier dans le jeu.
 * Il peut se déplacer sur une carte et éviter les obstacles grâce à move(Direction dir).
 * Il est initialisé avec une position de départ et une carte.
 * La position de l'aventurier peut être récupérée avec getPosition().
 * La carte est utilisée pour vérifier si le mouvement est possible.
 * L'aventurier ne peut pas se déplacer en dehors des limites de la carte ou sur des obstacles.
 * @author Ciril Group
 * @version 1.0
 */
public class Adventurer {
    private Position position;
    private final Map map;

    public Adventurer(Position start, Map map) {
        this.position = start;
        this.map = map;
    }

    public Position getPosition() {
        return position;
    }

    /**
     * Déplace l'aventurier dans la direction spécifiée si le mouvement est possible.
     * Vérifie que la nouvelle position est à l'intérieur de la carte et n'est pas un obstacle.
     * Si le mouvement est valide, met à jour la position de l'aventurier.
     * Si le mouvement n'est pas valide, la position de l'aventurier reste inchangée.
     * @param dir La direction dans laquelle l'aventurier doit se déplacer.
     */
    public void move(Direction dir) {
        Position next = position.moveBy(dir);
        if (map.isInside(next.getX(), next.getY()) && !map.isObstacle(next.getX(), next.getY())) {
            this.position = next;
        }
    }
}