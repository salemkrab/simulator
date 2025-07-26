package com.cirilgroup.aventurier.exceptions;

/**
 * Exception jetée lorsque la simulation échoue.
 * @author Ciril Group
 * @version 1.0
 */
public class SimulationException extends RuntimeException {
    public SimulationException(String message) {
        super(message);
    }

    public SimulationException(String message, Throwable cause) {
        super(message, cause);
    }
}
