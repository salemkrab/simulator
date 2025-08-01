package com.cirilgroup.aventurier.service;

import com.cirilgroup.aventurier.model.Adventurer;
import com.cirilgroup.aventurier.model.Direction;
import com.cirilgroup.aventurier.model.Map;
import com.cirilgroup.aventurier.model.MoveSequence;
import com.cirilgroup.aventurier.model.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationServiceTest {

    @Test
    void testRunSimulationMovesCorrectly() {
        Map map = new Map(3, 3, new char[][]{
            {' ', ' ', ' '},
            {' ', '#', ' '},
            {' ', ' ', ' '}
        });
        Adventurer adventurer = new Adventurer(new Position(0, 0), map);
        MoveSequence moves = new MoveSequence(new Position(0, 0), List.of(Direction.E, Direction.S, Direction.E));
        SimulationService service = new SimulationService();

        Position result = service.runSimulation(adventurer, moves);
        // Après E (1,0), S (1,1) [obstacle, reste (1,0)], E (2,0)
        assertEquals(new Position(2, 0), result);
    }

    @Test
    void testRunSimulationNoMoves() {
        Map map = new Map(2, 2, new char[][]{{' ', ' '}, {' ', ' '}});
        Adventurer adventurer = new Adventurer(new Position(1, 1), map);
        MoveSequence moves = new MoveSequence(new Position(1, 1), List.of());
        SimulationService service = new SimulationService();

        Position result = service.runSimulation(adventurer, moves);
        assertEquals(new Position(1, 1), result);
    }
}