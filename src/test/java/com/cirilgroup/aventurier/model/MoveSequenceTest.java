package com.cirilgroup.aventurier.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoveSequenceTest {

    @Test
    void testGetters() {
        Position start = new Position(2, 4);
        List<Direction> dirs = Arrays.asList(Direction.N, Direction.E, Direction.S);
        MoveSequence seq = new MoveSequence(start, dirs);

        // Vérifie que getStart renvoie bien l'objet de départ
        assertEquals(start, seq.getStart());

        // Vérifie que la liste retournée correspond et est immuable
        List<Direction> returned = seq.getDirections();
        assertEquals(dirs, returned);

        // Muter la liste d'origine ne doit pas affecter MoveSequence
        dirs.set(0, Direction.S);
        assertEquals(Direction.N, seq.getDirections().get(0));

        // La liste retournée ne doit pas supporte add/remove
        assertThrows(UnsupportedOperationException.class, () -> returned.add(Direction.O));
    }

    @Test
    void testEqualsAndHashCode() {
        MoveSequence seq1 = new MoveSequence(
            new Position(1, 1),
            List.of(Direction.O, Direction.O)
        );
        MoveSequence seq2 = new MoveSequence(
            new Position(1, 1),
            List.of(Direction.O, Direction.O)
        );
        MoveSequence seq3 = new MoveSequence(
            new Position(1, 1),
            List.of(Direction.N, Direction.N)
        );

        assertEquals(seq1, seq2);
        assertEquals(seq1.hashCode(), seq2.hashCode());
        assertNotEquals(seq1, seq3);
    }

    @Test
    void testToString() {
        MoveSequence seq = new MoveSequence(
            new Position(0, 0),
            List.of(Direction.E, Direction.S)
        );
        String s = seq.toString();
        assertTrue(s.contains("start=0,0"));
        assertTrue(s.contains("directions=[E, S]") || s.contains("directions=[E,S]"));
    }
}
