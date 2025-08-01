package com.cirilgroup.aventurier.service.interfaces;

import com.cirilgroup.aventurier.model.Adventurer;
import com.cirilgroup.aventurier.model.MoveSequence;
import com.cirilgroup.aventurier.model.Position;

/**
 * Interface pour les services de simulation.
 * Fournit des méthodes pour exécuter des simulations d'aventuriers sur une carte.
 * 
 * @author Ciril Group
 * @version 1.0
 */
public interface ISimulationService {
    Position runSimulation(Adventurer adventurer, MoveSequence moves);
}
