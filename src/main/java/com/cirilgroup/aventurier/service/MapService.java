package com.cirilgroup.aventurier.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.cirilgroup.aventurier.exceptions.MapFormatException;
import com.cirilgroup.aventurier.io.MapLoader;
import com.cirilgroup.aventurier.model.Map;
import com.cirilgroup.aventurier.service.interfaces.IMapService;

/**
 * Service de gestion de carte.
 * Implémente les méthodes pour charger et manipuler les cartes.
 * 
 * @author Ciril Group
 * @version 1.0
 */
@Service
 public class MapService implements IMapService {
    private final MapLoader mapLoader;

    public MapService(MapLoader mapLoader) {
        this.mapLoader = mapLoader;
    }

    /**
     * Charge une carte à partir du chemin spécifié.
     * 
     * @param mapPath Le chemin du fichier de la carte.
     * @return La carte chargée.
     * @throws IOException        Si une erreur d'entrée/sortie se produit.
     * @throws MapFormatException Si le format de la carte est invalide.
     */
    @Override
    public Map loadMap(Path mapPath) throws IOException, MapFormatException {
        return mapLoader.loadMap(mapPath);
    }

}
