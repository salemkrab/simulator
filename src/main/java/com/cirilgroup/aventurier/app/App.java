package com.cirilgroup.aventurier.app;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cirilgroup.aventurier.controller.SimulationController;
import com.cirilgroup.aventurier.io.ConsoleView;

/** * Main application class for the Aventurier simulator.
 * This class initializes the console view and starts the simulation based on provided map and moves files.
 * @author Ciril Group
 * @version 1.0
 */
public class App {
    private static final Logger lOGGER = LoggerFactory.getLogger(App.class);
    
    /**
     * The main method to run the Aventurier simulator.
     * It expects two command line arguments: the path to the map file and the path to the moves file.
     *
     * @param args Command line arguments containing the map and moves file paths.
     */
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        if (args.length < 2) {
            view.showError("Usage: java -jar simulator.jar <mapFile> <movesFile>");
            System.exit(1);
        }
        var controller = new SimulationController();
        var result = controller.execute(
            Paths.get(args[0]), Paths.get(args[1])
        );
        view.showPosition(result.getX(), result.getY());
        lOGGER.info("Simulation terminée et réussie.");
    }
}
