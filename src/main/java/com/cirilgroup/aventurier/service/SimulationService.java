package com.cirilgroup.aventurier.service;

import com.cirilgroup.aventurier.model.Adventurer;
import com.cirilgroup.aventurier.model.MoveSequence;
import com.cirilgroup.aventurier.model.Position;
import com.cirilgroup.aventurier.service.interfaces.ISimulationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service de simulation pour gérer la carte et les mouvements
 * de l'aventurier.
 * Cette classe est responsable de charger la carte,
 * charger les mouvements, et exécuter la simulation.
 * Elle utilise des loaders pour charger les données
 * et gère l'état de l'aventurier.
 * 
 * @author Ciril Group
 * @version 1.0
 */
@Service
public class SimulationService implements ISimulationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationService.class);

    /**
     * Exécute la simulation en déplaçant l'aventurier
     * selon les mouvements chargés.
     * 
     * @return la position finale de l'aventurier après la simulation
     */
    public Position runSimulation(Adventurer adventurer, MoveSequence moves) {
        for (var dir : moves.getDirections()) {
            LOGGER.trace("Position avant : {}", adventurer.getPosition());
            adventurer.move(dir);
            LOGGER.trace("Direction {}, nouvelle position : {}", dir, adventurer.getPosition());
        }
        return adventurer.getPosition();
    }
}