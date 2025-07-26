package com.cirilgroup.aventurier.integration;

import com.cirilgroup.aventurier.controller.SimulationController;
import com.cirilgroup.aventurier.model.Position;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SimulationIntegrationTest {

    @Test
    void testFullFlowWithRealFiles() {
        SimulationController ctrl = new SimulationController();
        Position finalPos = ctrl.execute(
            Paths.get("src/test/resources/maps/valid_map.txt"),
            Paths.get("src/test/resources/movements/movement1.txt")
        );
        assertEquals(new Position(4,3), finalPos);
    }
}
