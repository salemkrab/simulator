package com.cirilgroup.aventurier.exceptions;

/**
 * Exception jetée lorsque le format des mouvements est invalide.
 * @author Ciril Group
 * @version 1.0
 */
public class MoveFormatException extends Exception {
    public MoveFormatException(String message) {
        super(message);
    }
}
