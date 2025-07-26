package com.cirilgroup.aventurier.service;

import com.cirilgroup.aventurier.io.MapLoader;
import com.cirilgroup.aventurier.io.MoveLoader;
import com.cirilgroup.aventurier.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimulationServiceTest {

    @Mock MapLoader mapLoader;
    @Mock MoveLoader moveLoader;

    @InjectMocks
    SimulationService service; // injecte mapLoader & moveLoader

    Path dummyMap = Path.of("any");
    Path dummyMoves = Path.of("any");

    @Test
    void testRunSimulationHappyPath() throws Exception {
        // Préparer la carte 3×3 sans obstacle
        char[][] grid = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        Map carte = new Map(grid.length, grid[0].length, grid);
        // Mouvement : partir de (1,1) aller Nord puis Est
        MoveSequence seq = new MoveSequence(
            new Position(1,1),
            List.of(Direction.N, Direction.E)
        );

        when(mapLoader.loadMap(dummyMap)).thenReturn(carte);
        when(moveLoader.loadMoves(dummyMoves)).thenReturn(seq);

        // Exécuter
        service.loadMap(dummyMap);
        service.loadMoves(dummyMoves);
        Position result = service.runSimulation();

        assertEquals(new Position(2,0), result);

        // Vérifier que nos mocks ont bien été appelés
        verify(mapLoader).loadMap(dummyMap);
        verify(moveLoader).loadMoves(dummyMoves);
    }

    @Test
    void testRunSimulationWhenLoaderThrows() throws Exception {
        when(mapLoader.loadMap(dummyMap))
            .thenThrow(new RuntimeException("fichier manquant"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.loadMap(dummyMap);
            service.loadMoves(dummyMoves);
            service.runSimulation();
        });

        assertTrue(ex.getMessage().contains("fichier manquant"));
    }
}
