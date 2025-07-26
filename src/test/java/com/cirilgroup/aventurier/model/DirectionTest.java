package com.cirilgroup.aventurier.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void testDeltas() {
        assertEquals(0, Direction.N.getDx());
        assertEquals(-1, Direction.N.getDy());
        assertEquals(1, Direction.E.getDx());
        assertEquals(0, Direction.E.getDy());
        assertEquals(0, Direction.S.getDx());
        assertEquals(1, Direction.S.getDy());
        assertEquals(-1, Direction.O.getDx());
        assertEquals(0, Direction.O.getDy());
    }

    @Test
    void testValueOf() {
        assertEquals(Direction.N, Direction.valueOf("N"));
        assertEquals(Direction.S, Direction.valueOf("S"));
        assertEquals(Direction.E, Direction.valueOf("E"));
        assertEquals(Direction.O, Direction.valueOf("O"));
    }
}
