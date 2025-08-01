package com.cirilgroup.aventurier.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ConsoleView est responsable de l'affichage des messages dans la console.
 * Elle affiche les erreurs et la position finale de l'aventurier.
 * Elle est utilisée pour interagir avec l'utilisateur via la console.
 * 
 * @author Ciril Group
 * @version 1.0
 */
@Component
public class ConsoleView {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleView.class);

    /**
     * Affiche un message d'erreur dans la console.
     * 
     * @param message Le message d'erreur à afficher.
     */
    public void showError(String message) {
        LOGGER.error("Erreur: {}", message);
    }

    /**
     * Affiche la position finale de l'aventurier dans la console.
     * 
     * @param x La coordonnée x de la position finale.
     * @param y La coordonnée y de la position finale.
     */
    public void showPosition(int x, int y) {
        LOGGER.info("Position finale : {},{}", x, y);
    }
}