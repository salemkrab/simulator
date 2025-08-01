package com.cirilgroup.aventurier.controller;

import com.cirilgroup.aventurier.exceptions.SimulationException;
import com.cirilgroup.aventurier.model.Adventurer;
import com.cirilgroup.aventurier.model.Map;
import com.cirilgroup.aventurier.model.MoveSequence;
import com.cirilgroup.aventurier.model.Position;
import com.cirilgroup.aventurier.service.interfaces.IMapService;
import com.cirilgroup.aventurier.service.interfaces.IMoveService;
import com.cirilgroup.aventurier.service.interfaces.ISimulationService;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Contrôleur pour gérer la simulation de l'aventurier.
 * Il est responsable de l'exécution de la simulation en chargeant la carte et
 * les mouvements,
 * puis en exécutant la simulation.
 * 
 * @author Ciril Group
 * @version 1.0
 */
@Component
public class SimulationController {
    private final ISimulationService simulationService;
    private final IMoveService moveService;
    private final IMapService mapService;

    @Autowired
    public SimulationController(ISimulationService service, IMoveService moveService, IMapService mapService) {
        this.simulationService = service;
        this.moveService = moveService;
        this.mapService = mapService;
    }

    /**
     * Exécute la simulation en chargeant la carte et les mouvements, puis en
     * exécutant la simulation.
     *
     * @param mapPath   le chemin vers le fichier de la carte
     * @param movesPath le chemin vers le fichier des mouvements
     * @return la position finale après exécution de la simulation
     * @throws RuntimeException si une erreur survient pendant la simulation
     */

    public Position execute(Path mapPath, Path movesPath) {
        try {
            final Map map = mapService.loadMap(mapPath);
            final MoveSequence moves = moveService.loadMoves(movesPath);

            final Adventurer adventurer = new Adventurer(moves.getStart(), map);

            return simulationService.runSimulation(adventurer, moves);
        } catch (Exception e) {
            throw new SimulationException("Erreur pendant la simulation : " + e.getMessage(), e);
        }
    }

}