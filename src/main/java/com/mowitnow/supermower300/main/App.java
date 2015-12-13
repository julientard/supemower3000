package com.mowitnow.supermower300.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.supermower300.exception.BusinessException;

public class App {

	private final static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		logger.info("********************* MowItNow 1.0 *********************");

		File f = null;
		if (args != null && args.length > 0) {
			f = new File(args[0]);
		}

		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String line = null;
			List<String> input = new ArrayList<String>();

			while ((line = br.readLine()) != null) {
				input.add(line);
			}

			DescriptionReader descriptionReader = new DescriptionReader();
			List<VehicleRunner> vehicles = descriptionReader
					.buildVehiclesRunner(input);

			// Instanciation de l'executor
			Executor executor = getExecutor();
			// Execution de chaque tondeuse
			for (VehicleRunner runner : vehicles) {
				executor.execute(runner);
			}
		} catch (IOException e) {
			logger.error("Problème d'accès au fichier");
		} catch (NumberFormatException | BusinessException e) {
			logger.error("Problème de parsing des informations: {}",
					e.getMessage());

			logger.info("********************* MowItNow 1.0 *********************");
		}

	}

	public static Executor getExecutor() {
		Executor executor = Executors.newSingleThreadExecutor();
		return executor;
	}

}
