package com.cirilgroup.aventurier.exceptions;

/**
 * Exception jetée lorsque le format de la carte est invalide.
 * @author Ciril Group
 * @version 1.0
 */
public class MapFormatException extends Exception {
    public MapFormatException(String message) {
        super(message);
    }
}
