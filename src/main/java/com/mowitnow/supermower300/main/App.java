package com.mowitnow.supermower300.main;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import com.mowitnow.supermower300.exception.BusinessException;

import org.apache.commons.io.FileUtils;
import org.slf4j.*;

public class App {

    private final static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) {

        logger.info("********************* MowItNow 1.0 *********************");

        // Get the file path from console or args
        String f;
        if ((args == null) || (args.length == 0)) {
            try (Scanner scanner = new Scanner(System.in)) {
                logger.info("Entrez le chemin d'un fichier: ");
                f = scanner.next();
                scanner.close();
            }
        }

        else {
            f = args[0];
        }

        // Read file and execute mowers
        try {
            List<String> input = FileUtils.readLines(new File(f));

            DescriptionReader descriptionReader = new DescriptionReader();
            List<VehicleRunner> vehicles = descriptionReader.buildVehiclesRunner(input);

            // New single executorservice
            ExecutorService executor = getExecutor();
            // Run each mower
            for (VehicleRunner runner : vehicles) {
                executor.execute(runner);
            }

            executor.shutdown();
            executor.awaitTermination(30, TimeUnit.SECONDS);
        }
        catch (IOException e) {
            logger.error("Problème d'accès au fichier: {}", e.getMessage());
        }
        catch (NumberFormatException | BusinessException e) {
            logger.error("Problème de parsing des informations: {}", e.getMessage());

            logger.info("********************* MowItNow 1.0 *********************");
        }
        catch (InterruptedException e) {
            logger.error("Problème d'execution d'une tâche: {}", e.getMessage());
        }

    }

    public static ExecutorService getExecutor() {
        ExecutorService exe = Executors.newSingleThreadExecutor();
        return exe;
    }

}
