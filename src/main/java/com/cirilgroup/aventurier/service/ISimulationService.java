package com.cirilgroup.aventurier.service;

import com.cirilgroup.aventurier.model.Position;
import java.nio.file.Path;

/**
 * Interface pour le service de simulation.
 */
public interface ISimulationService {
    void loadMap(Path mapPath) throws Exception;
    void loadMoves(Path movesPath) throws Exception;
    Position runSimulation();
}
