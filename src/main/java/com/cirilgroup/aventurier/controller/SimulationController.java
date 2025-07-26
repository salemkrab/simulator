package com.cirilgroup.aventurier.controller;

import com.cirilgroup.aventurier.exceptions.SimulationException;
import com.cirilgroup.aventurier.model.Position;
import com.cirilgroup.aventurier.service.SimulationService;

import java.nio.file.Path;

/**
 * Contrôleur pour gérer la simulation de l'aventurier.
 * Il est responsable de l'exécution de la simulation en chargeant la carte et les mouvements,
 * puis en exécutant la simulation.
 * @author Ciril Group
 * @version 1.0
 */
public class SimulationController {
    private final SimulationService service;

    public SimulationController(SimulationService service) {
        this.service = service;
    }
    
    public SimulationController() {
        this.service = new SimulationService(); // Initialisation par défaut, peut être remplacée par un mock dans les tests
    }

    /**
     * Exécute la simulation en chargeant la carte et les mouvements, puis en exécutant la simulation.
     *
     * @param mapPath le chemin vers le fichier de la carte
     * @param movesPath le chemin vers le fichier des mouvements
     * @return la position finale après exécution de la simulation
     * @throws RuntimeException si une erreur survient pendant la simulation
     */

    public Position execute(Path mapPath, Path movesPath) {
        try {
            service.loadMap(mapPath);
            service.loadMoves(movesPath);
            return service.runSimulation();
        } catch (Exception e) {
            throw new SimulationException("Erreur pendant la simulation : " + e.getMessage(), e);
        }
    }

}