package com.cirilgroup.aventurier.integration;

import com.cirilgroup.aventurier.controller.SimulationController;
import com.cirilgroup.aventurier.io.MapLoader;
import com.cirilgroup.aventurier.io.MoveLoader;
import com.cirilgroup.aventurier.model.Position;
import com.cirilgroup.aventurier.service.MapService;
import com.cirilgroup.aventurier.service.MoveService;
import com.cirilgroup.aventurier.service.SimulationService;
import com.cirilgroup.aventurier.service.interfaces.IMapService;
import com.cirilgroup.aventurier.service.interfaces.IMoveService;
import com.cirilgroup.aventurier.service.interfaces.ISimulationService;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SimulationIntegrationTest {

    @Test
    void testFullFlowWithRealFiles() {
        final MapLoader mapLoader = new MapLoader();
        final MoveLoader moveLoader = new MoveLoader();
        final ISimulationService simulationService = new SimulationService();;
        final IMoveService moveService = new MoveService(moveLoader);
        final IMapService mapService = new MapService(mapLoader);

        SimulationController ctrl = new SimulationController(simulationService, moveService, mapService);
        Position finalPos = ctrl.execute(
            Paths.get("src/test/resources/maps/valid_map.txt"),
            Paths.get("src/test/resources/movements/movement1.txt")
        );
        assertEquals(new Position(4,3), finalPos);
    }
}
