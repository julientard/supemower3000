package com.mowitnow.supermower300.main;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.supermower300.constant.OrientationEnum;
import com.mowitnow.supermower300.entities.Action;
import com.mowitnow.supermower300.entities.Lawn;
import com.mowitnow.supermower300.entities.vehicles.Mower;
import com.mowitnow.supermower300.entities.vehicles.Vehicle;

/**
 * Drive a vehicle on a lawn
 * <p>
 * Implements {@linkplain Runnable} to be launch by a {@linkplain Executor}
 * <p>
 * 
 * @author Julien
 *
 */
public class VehicleRunner implements Runnable {

	final static Logger logger = LoggerFactory.getLogger(Mower.class);

	private Vehicle vehicle;

	private Lawn lawn;

	public VehicleRunner(Vehicle vehicle, Lawn lawn) {
		super();
		this.vehicle = vehicle;
		this.lawn = lawn;
	}

	/**
	 * Run the vehicle
	 */
	public void run() {

		logger.info("**********Démarrage du véhicule**********");
		char[] i = vehicle.getInstructions().toCharArray();
		for (char c : i) {
			Action a = Vehicle.getActions().get(c);
			if (a != null) {
				move(a);
			} else {
				logger.warn("Instruction {} non reconnue", c);
			}

		}
		logger.info("Position finale: {}", vehicle.toString());
		logger.info("**********Fin**********");
	}

	/**
	 * Move a vehicle 
	 * @param action action to move the vehicle
	 */
	public void move(Action action) {

		logger.info("Mouvement de type {}", action.getCode());
		logger.debug("Avant: {}", vehicle.toString());

		OrientationEnum o = vehicle.getOrientation();

		vehicle.setOrientation(o.turn(action.getRotation()));

		int newX = vehicle.getX() + action.getStraight() * o.gapX;
		int newY = vehicle.getY() + action.getStraight() * o.gapY;

		if (isWithinBoundaries(newX, newY)) {
			vehicle.setX(newX);
			vehicle.setY(newY);
		} else {
			logger.warn("Le véhicule a atteint la limite de la pelouse!");
		}

		logger.debug("Après: {}", vehicle.toString());

	}

	private boolean isWithinBoundaries(int x, int y) {

		if (x <= lawn.getWidth() && x>=0 && y <= lawn.getHeight() && y>=0) {
			return true;
		}

		return false;

	}
}