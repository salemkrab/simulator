package com.cirilgroup.aventurier.io;

import com.cirilgroup.aventurier.model.Map;
import com.cirilgroup.aventurier.exceptions.MapFormatException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MapLoader est responsable du chargement d'une carte à partir d'un fichier.
 * Il lit le contenu du fichier, vérifie la validité du format,
 * et crée une instance de la classe Map.
 * Cette classe gère les exceptions liées au format de la carte,
 * notamment les lignes de taille inégale ou une carte vide.
 * @author Ciril Group
 * @version 1.0
 * 
 */
public class MapLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapLoader.class);
    /**
     * Charge une carte à partir du fichier spécifié.
     * 
     * @param path Le chemin du fichier de la carte.
     * @return Une instance de Map représentant la carte chargée.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture du fichier.
     * @throws MapFormatException Si le format de la carte est invalide, par exemple si les lignes sont de taille inégale ou si la carte est vide.
     */
    public Map loadMap(Path path) throws IOException, MapFormatException {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        int height = lines.size();
        if (height == 0) {
            LOGGER.debug("Le fichier de la carte est vide : {}", path);
            throw new MapFormatException("Carte vide");
        }
        int width = lines.get(0).length();
        char[][] grid = new char[height][width];
        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            if (line.length() != width) {
                LOGGER.debug("Ligne de taille inégale à la ligne {} : attendue {}, mais trouvée {}", y, width, line.length());
                throw new MapFormatException("Lignes de taille inégale à la ligne " + y);
            }
            grid[y] = line.toCharArray();
        }
        LOGGER.debug("Carte chargée avec succès : {}x{}", width, height);
        return new Map(width, height, grid);

        // des validations supplémentaires peuvent être ajoutées ici si nécessaire
        // Par exemple, vérifier les caractères valides dans la grille
        // ou s'assurer que la carte contient au moins un point de départ valide.
    }
    
}