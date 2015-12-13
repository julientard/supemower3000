package com.mowitnow.supermower300.main;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mowitnow.supermower300.constant.OrientationEnum;
import com.mowitnow.supermower300.entities.Lawn;
import com.mowitnow.supermower300.entities.vehicles.Mower;
import com.mowitnow.supermower300.entities.vehicles.Vehicle;
import com.mowitnow.supermower300.exception.BusinessException;

/**
 * Builds {@linkplain VehicleRunner} from a list of instructions
 * @author Julien
 *
 */
public class DescriptionReader {

	private static final String REGEX_LAWN = "^(\\d)\\s(\\d)$";
	
	private static final String REGEX_MOWER = "^(\\d)\\s(\\d)\\s([news])$";
	
	private final static Logger logger = LoggerFactory.getLogger(Mower.class);
		
	/**
	 * Builds {@linkplain VehicleRunner} containing a lawn and a vehicle, able to be ran by a {@linkplain Executor}
	 * @param input information list to build a lawn (first line) and vehicles (two lines by two lines)
	 * <p>
	 * For instance <br/>
	 * 5 5 lawn size <br/>
	 * 1 2 N vehicle 1 initial position<br/>
	 * GAGAGAGAA instructions <br/>
	 * 3 3 E vehicle 2 initial position<br/>
	 * AADAADADDA instructions<br/>
	 * 
	 * </p> 
	 * @return a list of {@linkplain VehicleRunner}
	 * @throws NumberFormatException if impossible to parse a number
	 * @throws BusinessException if regex not respected
	 */
	public List<VehicleRunner> buildVehiclesRunner(List<String> input) throws NumberFormatException, BusinessException {
		
		Lawn lawn = buildLawn(input.get(0));
		List<Vehicle> vehicles = buildMowers(input.subList(1, input.size()));	
		
		List<VehicleRunner> vehiclesRunner = new ArrayList<VehicleRunner>();
		for (Vehicle v : vehicles) {
			vehiclesRunner.add(new VehicleRunner(v, lawn));
		}
		return vehiclesRunner;
		
	}

	/**
	 * Build a lawn 
	 * @param dataLawn with two integer separated by a space (e.g. "5 5")
	 * @return a {@linkplain Lawn}
	 * @throws BusinessException if regex not respected
	 * @throws NumberFormatException if not a number
	 */
	public Lawn buildLawn(String dataLawn) throws BusinessException,
			NumberFormatException {

		Pattern p = Pattern.compile(REGEX_LAWN, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(dataLawn);
		boolean b = m.matches();

		if (b) {
			int width = Integer.parseInt(m.group(1));
			int height = Integer.parseInt(m.group(2));
			logger.info("Construction d'une pelouse de taille (x,y) ({},{})", width, height);
			Lawn l = new Lawn(width, height);
			return l;
		} else {
			throw new BusinessException(
					"La description de la pelouse ne respecte pas le bon format");
		}
	}
	
	
	private List<Vehicle> buildMowers(List<String> instructions) {
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		ListIterator<String> iter = instructions.listIterator();
		
		while(iter.hasNext()) {
			try {
				vehicles.add(buildMower(iter.next(), iter.next()));
			} catch (BusinessException e) {
				logger.warn(e.getMessage());
			}
		}
		return vehicles;
	}

	/**
	 * Build a mower
	 * @param dataMower describe the initial position (e.g. "1 2 N")
	 * @param instructions describe a list of actions (e.g. GAGAGAGAA)
	 * @return a {@linkplain Mower}
	 * @throws BusinessException if regex not respected
	 */
	public Vehicle buildMower(String dataMower, String instructions)
			throws BusinessException {

		Pattern p = Pattern.compile(REGEX_MOWER, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(dataMower);
		boolean b = m.matches();

		if (b) {
			Mower mower = new Mower();
			int x = Integer.parseInt(m.group(1));
			int y = Integer.parseInt(m.group(2));
			
			mower.setX(x);
			mower.setY(y);
			String orientation = m.group(3);
			logger.info("Construction d'une tondeuse de position initiale (x,y) ({},{}) direction {} ayant pour instructions", x,y,orientation,instructions);
			mower.setOrientation(OrientationEnum.valueOf(orientation));
			mower.setInstructions(instructions);
			return mower;
		} else {
			throw new BusinessException(
					"La description de la tondeuse ne respecte pas le bon format");
		}
	}
}
