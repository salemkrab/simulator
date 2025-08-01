package com.cirilgroup.aventurier.io;

import com.cirilgroup.aventurier.model.Direction;
import com.cirilgroup.aventurier.model.MoveSequence;
import com.cirilgroup.aventurier.model.Position;
import com.cirilgroup.aventurier.exceptions.MoveFormatException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Classe pour charger une séquence de mouvements à partir d'un fichier.
 * Le fichier doit contenir les coordonnées de départ sur la première ligne
 * et les directions sur la deuxième ligne.
 * Exemple de fichier :
 * 0,0
 * NNEE
 * où N, S, E, O représentent les directions Nord, Sud, Est, Ouest.
 * La première ligne contient les coordonnées de départ sous la forme "x,y".
 * La deuxième ligne contient une chaîne de caractères représentant les
 * directions.
 * Chaque caractère doit être l'une des lettres N, S, E, O.
 * Si le format du fichier est incorrect, une exception MoveFormatException est
 * levée.
 * Si le fichier est vide ou ne contient pas les informations nécessaires,
 * une exception MoveFormatException est également levée.
 * Cette classe est utilisée pour initialiser les mouvements d'un aventurier
 * dans le simulateur de l'aventurier.
 * 
 * @author Ciril Group
 * @version 1.0
 */
@Component
public class MoveLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoveLoader.class);

    /**
     * Charge une séquence de mouvements à partir d'un fichier.
     *
     * @param path le chemin du fichier à charger
     * @return une instance de MoveSequence contenant la position de départ et les
     *         directions
     * @throws IOException         si une erreur d'entrée/sortie se produit lors de
     *                             la lecture du fichier
     * @throws MoveFormatException si le format du fichier est incorrect ou
     *                             incomplet
     */
    public MoveSequence loadMoves(Path path) throws IOException, MoveFormatException {
        List<String> lines = Files.readAllLines(path);
        if (lines.size() < 2) {
            LOGGER.debug("Fichier de mouvements incomplet : {}", path);
            throw new MoveFormatException("Fichier de mouvements incomplet");
        }
        String[] coords = lines.get(0).trim().split(",");
        if (coords.length != 2) {
            LOGGER.debug("Format de coordonnées invalide dans le fichier : {}", path);
            throw new MoveFormatException("Format de coordonnées invalide");
        }
        Position start = new Position(
                Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
        List<Direction> directions = lines.get(1).chars()
                .mapToObj(c -> Direction.valueOf(String.valueOf((char) c)))
                .toList();
        LOGGER.debug("Chargement des mouvements depuis le fichier : {}", path);
        return new MoveSequence(start, directions);

        // des validations supplémentaires peuvent être ajoutées ici si nécessaire
        // Par exemple, vérifier les directions valides ou s'assurer que la position de
        // départ est valide.

    }
}