package com.cirilgroup.aventurier.controller;

import com.cirilgroup.aventurier.model.Position;
import com.cirilgroup.aventurier.service.SimulationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimulationControllerTest {

    @Mock SimulationService service;

    @InjectMocks
    SimulationController controller; // injecte SimulationService mocké

    @Test
    void testExecuteHappyPath() {
        Path m = Path.of("map"), mv = Path.of("moves");
        Position expected = new Position(4,5);
        when(service.runSimulation()).thenReturn(expected);

        // On a besoin aussi de stubber loadMap/loadMoves pour éviter l'exception
        try {
            doNothing().when(service).loadMap(m);
            doNothing().when(service).loadMoves(mv);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Position result = controller.execute(m, mv);
        assertEquals(expected, result);
        try {
            verify(service).loadMap(m);
            verify(service).loadMoves(mv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        verify(service).runSimulation();
    }

    @Test
    void testExecuteServiceThrows() throws Exception {
        Path m = Path.of("map"), mv = Path.of("moves");
        doThrow(new RuntimeException("ko")).when(service).loadMap(m);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
            controller.execute(m, mv)
        );
        assertTrue(ex.getMessage().contains("Erreur pendant la simulation"));
    }
}
