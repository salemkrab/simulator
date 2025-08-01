package com.cirilgroup.aventurier.app;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cirilgroup.aventurier.config.SpringConfig;
import com.cirilgroup.aventurier.controller.SimulationController;
import com.cirilgroup.aventurier.io.ConsoleView;
import com.cirilgroup.aventurier.io.MapLoader;
import com.cirilgroup.aventurier.io.MoveLoader;
import com.cirilgroup.aventurier.service.MapService;
import com.cirilgroup.aventurier.service.MoveService;
import com.cirilgroup.aventurier.service.interfaces.IMapService;
import com.cirilgroup.aventurier.service.interfaces.IMoveService;
import com.cirilgroup.aventurier.service.interfaces.ISimulationService;

/**
 * * Main application class for the Aventurier simulator.
 * This class initializes the console view and starts the simulation based on
 * provided map and moves files.
 * 
 * @author Ciril Group
 * @version 1.0
 */
public class App {
    private static final Logger lOGGER = LoggerFactory.getLogger(App.class);

    /**
     * The main method to run the Aventurier simulator.
     * It expects two command line arguments: the path to the map file and the path
     * to the moves file.
     *
     * @param args Command line arguments containing the map and moves file paths.
     */
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(SpringConfig.class)) {
            ConsoleView view = context.getBean(ConsoleView.class);
            MapLoader mapLoader = context.getBean(MapLoader.class);
            MoveLoader moveLoader = context.getBean(MoveLoader.class);

            IMapService mapService = new MapService(mapLoader);
            IMoveService moveService = new MoveService(moveLoader);
            ISimulationService service = context.getBean(ISimulationService.class);

            if (args.length < 2) {
                view.showError("Usage: java -jar simulator.jar <mapFile> <movesFile>");
                System.exit(1);
            }
            var controller = new SimulationController(service, moveService, mapService);
            var result = controller.execute(
                    Paths.get(args[0]), Paths.get(args[1]));
            view.showPosition(result.getX(), result.getY());
            lOGGER.info("Simulation terminée et réussie.");
        }
    }
}
