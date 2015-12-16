package com.mowitnow.supermower300.main;

import java.util.concurrent.Executor;

import com.mowitnow.supermower300.constant.OrientationEnum;
import com.mowitnow.supermower300.entities.*;
import com.mowitnow.supermower300.entities.vehicles.*;

import org.slf4j.*;

/**
 * Drive a {@linkplain Vehicle} vehicle on a {@linkplain Lawn}
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

    public VehicleRunner(final Vehicle vehicle, final Lawn lawn) {
        super();
        this.vehicle = vehicle;
        this.lawn = lawn;
    }

    /**
     * Run the vehicle
     */
    @Override
    public void run() {

        MDC.put("vehiclename", this.vehicle.getName());

        logger.info("**********Demarrage du vehicule**********");
        logger.info(" => Position initiale: {}", this.vehicle.toString());
        char[] i = this.vehicle.getInstructions().toCharArray();
        for (char c : i) {
            Action a = Vehicle.getActions().get(c);
            if (a != null) {
                move(a);
            }
            else {
                logger.warn("Instruction {} non reconnue", c);
            }
        }
        logger.info(" => Position finale: {}", this.vehicle.toString());
        logger.info("**********Fin**********");

        MDC.remove("vehiclename");
    }

    /**
     * Move a vehicle
     * 
     * @param action action to move the vehicle
     */
    public void move(final Action action) {

        logger.info("Mouvement de type {}", action.getCode());
        logger.debug("Avant: {}", this.vehicle.toString());

        OrientationEnum o = this.vehicle.getOrientation();

        this.vehicle.setOrientation(o.turn(action.getRotation()));

        int newX = this.vehicle.getX() + (action.getStraight() * o.gapX);
        int newY = this.vehicle.getY() + (action.getStraight() * o.gapY);

        if (isWithinBoundaries(newX, newY)) {
            this.vehicle.setX(newX);
            this.vehicle.setY(newY);
        }
        else {
            logger.warn("Le vehicule a atteint la limite de la pelouse!");
        }

        logger.debug("Apres: {}", this.vehicle.toString());

    }

    private boolean isWithinBoundaries(final int x, final int y) {

        if ((x <= this.lawn.getWidth()) && (x >= 0) && (y <= this.lawn.getHeight()) && (y >= 0)) {
            return true;
        }

        return false;

    }
}
