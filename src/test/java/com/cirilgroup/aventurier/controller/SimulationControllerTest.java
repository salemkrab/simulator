package com.cirilgroup.aventurier.controller;

import com.cirilgroup.aventurier.exceptions.SimulationException;
import com.cirilgroup.aventurier.model.Map;
import com.cirilgroup.aventurier.model.MoveSequence;
import com.cirilgroup.aventurier.model.Position;
import com.cirilgroup.aventurier.service.interfaces.IMapService;
import com.cirilgroup.aventurier.service.interfaces.IMoveService;
import com.cirilgroup.aventurier.service.interfaces.ISimulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimulationControllerTest {

    @Mock
    private ISimulationService simulationService;

    @Mock
    private IMoveService moveService;

    @Mock
    private IMapService mapService;

    @InjectMocks
    private SimulationController controller;

    @Captor
    private ArgumentCaptor<Path> pathCaptor;

    private Path fakeMapPath;
    private Path fakeMovesPath;
    private Map dummyMap;
    private MoveSequence dummyMoves;
    private Position expectedPosition;

    @BeforeEach
    void setUp() {
        fakeMapPath = Path.of("dummyMap.txt");
        fakeMovesPath = Path.of("dummyMoves.txt");
        dummyMap = mock(Map.class);
        dummyMoves = mock(MoveSequence.class);
        expectedPosition = new Position(5, 3);
    }

    @Test
    void execute_ShouldReturnFinalPosition_WhenEverythingGoesWell() throws Exception {
        // Arrange
        when(mapService.loadMap(fakeMapPath)).thenReturn(dummyMap);
        when(moveService.loadMoves(fakeMovesPath)).thenReturn(dummyMoves);
        when(simulationService.runSimulation(any(), eq(dummyMoves))).thenReturn(expectedPosition);

        // Act
        Position result = controller.execute(fakeMapPath, fakeMovesPath);

        // Assert
        assertThat(result).isSameAs(expectedPosition);

        // Vérifier qu’on a bien appelé les services avec les bons arguments
        verify(mapService).loadMap(pathCaptor.capture());
        assertThat(pathCaptor.getValue()).isEqualTo(fakeMapPath);

        verify(moveService).loadMoves(pathCaptor.capture());
        assertThat(pathCaptor.getValue()).isEqualTo(fakeMovesPath);

        // On ne vérifie pas le contenu exact de l’adventurier, mais qu’on a bien passé un objet non null
        verify(simulationService).runSimulation(any(), eq(dummyMoves));
        verifyNoMoreInteractions(mapService, moveService, simulationService);
    }

    @Test
    void execute_ShouldWrapException_WhenMapServiceFails() throws Exception {
        // Arrange
        when(mapService.loadMap(fakeMapPath)).thenThrow(new RuntimeException("IO error"));

        // Act & Assert
        SimulationException ex = assertThrows(
            SimulationException.class,
            () -> controller.execute(fakeMapPath, fakeMovesPath)
        );
        assertThat(ex.getMessage()).contains("Erreur pendant la simulation");
        assertThat(ex.getCause()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void execute_ShouldWrapException_WhenMoveServiceFails() throws Exception {
        // Arrange
        when(mapService.loadMap(fakeMapPath)).thenReturn(dummyMap);
        when(moveService.loadMoves(fakeMovesPath)).thenThrow(new IllegalArgumentException("Format invalide"));

        // Act & Assert
        SimulationException ex = assertThrows(
            SimulationException.class,
            () -> controller.execute(fakeMapPath, fakeMovesPath)
        );
        assertThat(ex.getMessage()).contains("Erreur pendant la simulation");
        assertThat(ex.getCause()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void execute_ShouldWrapException_WhenSimulationServiceFails() throws Exception {
        // Arrange
        when(mapService.loadMap(fakeMapPath)).thenReturn(dummyMap);
        when(moveService.loadMoves(fakeMovesPath)).thenReturn(dummyMoves);
        when(simulationService.runSimulation(any(), eq(dummyMoves)))
            .thenThrow(new NullPointerException("Bug interne"));

        // Act & Assert
        SimulationException ex = assertThrows(
            SimulationException.class,
            () -> controller.execute(fakeMapPath, fakeMovesPath)
        );
        assertThat(ex.getMessage()).contains("Erreur pendant la simulation");
        assertThat(ex.getCause()).isInstanceOf(NullPointerException.class);
    }
}
