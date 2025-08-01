package com.cirilgroup.aventurier.service.interfaces;

import java.io.IOException;
import java.nio.file.Path;

import com.cirilgroup.aventurier.exceptions.MoveFormatException;
import com.cirilgroup.aventurier.model.MoveSequence;

/**
 * Interface pour les services de gestion des mouvements.
 * Fournit des méthodes pour charger et manipuler les séquences de mouvements.
 * 
 * @author Ciril Group
 * @version 1.0
 */
public interface IMoveService {
    MoveSequence loadMoves(Path movesPath) throws IOException, MoveFormatException;
}
