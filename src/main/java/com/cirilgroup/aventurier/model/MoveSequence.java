package com.cirilgroup.aventurier.model;

import java.util.List;
import java.util.Objects;

/**
 * Représente une séquence de mouvements dans le jeu
 * avec une position de départ et une liste de directions.
 * Cette classe est immuable et fournit des méthodes pour accéder
 * à la position de départ et aux directions.
 * Elle implémente les méthodes equals, hashCode et toString
 * pour une comparaison et une représentation appropriées.
 * 
 * @author Ciril Group
 * @version 1.0
 */
public class MoveSequence {
    private final Position start;
    private final List<Direction> directions;

    public MoveSequence(Position start, List<Direction> directions) {
        this.start = start;
        this.directions = List.copyOf(directions);
    }

    public Position getStart() {
        return start;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MoveSequence))
            return false;
        MoveSequence that = (MoveSequence) o;
        return Objects.equals(start, that.start) && Objects.equals(directions, that.directions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, directions);
    }

    @Override
    public String toString() {
        return "MoveSequence{start=" + start + ", directions=" + directions + '}';
    }
}
