package com.cirilgroup.aventurier.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void testEqualsAndHashCode() {
        Position p1 = new Position(2, 3);
        Position p2 = new Position(2, 3);
        Position p3 = new Position(3, 2);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1, p3);
    }

    @Test
    void testMoveBy() {
        Position p = new Position(1, 1);
        Position north = p.moveBy(Direction.N);
        Position south = p.moveBy(Direction.S);
        Position east  = p.moveBy(Direction.E);
        Position west  = p.moveBy(Direction.O);

        assertEquals(new Position(1, 0), north);
        assertEquals(new Position(1, 2), south);
        assertEquals(new Position(2, 1), east);
        assertEquals(new Position(0, 1), west);
    }

    @Test
    void testToString() {
        Position p = new Position(5, 7);
        assertEquals("5,7", p.toString());
    }
}