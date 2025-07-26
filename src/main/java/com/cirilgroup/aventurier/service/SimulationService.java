package com.cirilgroup.aventurier.service;

import com.cirilgroup.aventurier.exceptions.MapFormatException;
import com.cirilgroup.aventurier.exceptions.MoveFormatException;
import com.cirilgroup.aventurier.io.MapLoader;
import com.cirilgroup.aventurier.io.MoveLoader;
import com.cirilgroup.aventurier.model.Adventurer;
import com.cirilgroup.aventurier.model.Map;
import com.cirilgroup.aventurier.model.MoveSequence;
import com.cirilgroup.aventurier.model.Position;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class SimulationService {
    private final MapLoader mapLoader;
    private final MoveLoader moveLoader;
    private Map                 map          = new Map(0, 0, new char[0][0]);        // stub
    private MoveSequence        moves        = new MoveSequence(new Position(0, 0), List.of());   // séquence vide
    private Adventurer          adventurer   = new Adventurer(new Position(0,0), map); // stub
    private static final Logger LOGGER = LoggerFactory.getLogger(SimulationService.class);

    public SimulationService() {
        this.mapLoader = new MapLoader();
        this.moveLoader = new MoveLoader();
    }

    public SimulationService(MapLoader mapLoader, MoveLoader moveLoader) {
        this.mapLoader = mapLoader;
        this.moveLoader = moveLoader;
    }

    /**
     * Charge la carte à partir du chemin spécifié.
     * 
     * @param mapPath le chemin du fichier de la carte
     * @throws Exception si une erreur se produit lors du chargement de la carte
     */
    public void loadMap(Path mapPath) throws IOException, MapFormatException {
        this.map = mapLoader.loadMap(mapPath);
    }

    /**
     * Charge les mouvements de l'aventurier à partir du chemin spécifié.
     * 
     * @param movesPath le chemin du fichier des mouvements
     * @throws Exception si une erreur se produit lors du chargement des mouvements
     */
    public void loadMoves(Path movesPath) throws IOException, MoveFormatException {
        this.moves = moveLoader.loadMoves(movesPath);
        this.adventurer = new Adventurer(moves.getStart(), map);
    }

    /**
     * Exécute la simulation en déplaçant l'aventurier
     * selon les mouvements chargés.
     * 
     * @return la position finale de l'aventurier après la simulation
     */
    public Position runSimulation() {
        for (var dir : moves.getDirections()) {
            LOGGER.trace("Position avant : {}", adventurer.getPosition());
            adventurer.move(dir);
            LOGGER.trace("Direction {}, nouvelle position : {}", dir, adventurer.getPosition());
        }
        return adventurer.getPosition();
    }
}