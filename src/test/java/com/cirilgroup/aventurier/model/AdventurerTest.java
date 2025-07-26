package com.cirilgroup.aventurier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {

    Map map;
    Adventurer adventurer;

    @BeforeEach
    void setUp() {
        char[][] grid = {
            { ' ', '#' },
            { ' ', ' ' }
        };
        map = new Map(grid.length, grid[0].length, grid);
        adventurer = new Adventurer(new Position(0, 0), map);
    }

    @Test
    void testMoveIntoFreeCell() {
        adventurer.move(Direction.S);
        assertEquals(new Position(0, 1), adventurer.getPosition());
    }

    @Test
    void testMoveIntoObstacle() {
        adventurer.move(Direction.E);
        // obstacle at (1,0), position must stay (0,0)
        assertEquals(new Position(0, 0), adventurer.getPosition());
    }

    @Test
    void testMoveOutOfBounds() {
        adventurer.move(Direction.N);
        // out of bounds, stays
        assertEquals(new Position(0, 0), adventurer.getPosition());
    }

    @Test
    void testMultipleMoves() {
        adventurer.move(Direction.S);
        adventurer.move(Direction.E);
        // now at (1,1) which is free
        assertEquals(new Position(1, 1), adventurer.getPosition());
    }
}
