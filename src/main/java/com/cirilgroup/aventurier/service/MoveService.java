package com.cirilgroup.aventurier.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.cirilgroup.aventurier.exceptions.MoveFormatException;
import com.cirilgroup.aventurier.io.MoveLoader;
import com.cirilgroup.aventurier.model.MoveSequence;
import com.cirilgroup.aventurier.service.interfaces.IMoveService;

/**
 * Service de gestion des mouvements.
 * Implémente les méthodes pour charger et manipuler les séquences de
 * mouvements.
 * 
 * @author Ciril Group
 * @version 1.0
 */
@Service
public class MoveService implements IMoveService {
    private final MoveLoader movesLoader;

    public MoveService(MoveLoader movesLoader) {
        this.movesLoader = movesLoader;
    }

    /**
     * Charge une séquence de mouvements à partir du chemin spécifié.
     * 
     * @param movesPath Le chemin du fichier de la séquence de mouvements.
     * @return La séquence de mouvements chargée.
     * @throws IOException         Si une erreur d'entrée/sortie se produit.
     * @throws MoveFormatException Si le format de la séquence de mouvements est
     *                             invalide.
     */
    @Override
    public MoveSequence loadMoves(Path movesPath) throws IOException, MoveFormatException {
        return movesLoader.loadMoves(movesPath);
    }
}
