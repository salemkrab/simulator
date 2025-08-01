package com.cirilgroup.aventurier.service.interfaces;

import java.io.IOException;
import java.nio.file.Path;

import com.cirilgroup.aventurier.exceptions.MapFormatException;
import com.cirilgroup.aventurier.model.Map;

/**
 * Interface pour les services de gestion de carte.
 * Fournit des méthodes pour charger et manipuler les cartes.
 * 
 * @author Ciril Group
 * @version 1.0
 */
public interface IMapService {
        Map loadMap(Path mapPath) throws IOException, MapFormatException;
}
