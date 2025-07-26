package com.cirilgroup.aventurier.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    static Map map;

    @BeforeAll
    static void init() {
        char[][] grid = {
            { ' ', '#', ' ' },
            { '#', ' ', '#' },
            { ' ', ' ', ' ' }
        };
        map = new Map(grid.length, grid[0].length, grid);
    }

    @Test
    void testIsInside() {
        assertTrue(map.isInside(0, 0));
        assertTrue(map.isInside(2, 2));
        assertFalse(map.isInside(-1, 0));
        assertFalse(map.isInside(0, -1));
        assertFalse(map.isInside(3, 0));
        assertFalse(map.isInside(0, 3));
    }

    @Test
    void testIsObstacle() {
        assertFalse(map.isObstacle(0, 0));
        assertTrue(map.isObstacle(1, 0));
        assertTrue(map.isObstacle(0, 1));
        assertFalse(map.isObstacle(2, 2));
    }
}
