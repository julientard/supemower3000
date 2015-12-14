package com.mowitnow.supermower3000.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.junit.Test;

import com.mowitnow.supermower300.constant.OrientationEnum;
import com.mowitnow.supermower300.entities.Lawn;
import com.mowitnow.supermower300.entities.vehicles.Mower;
import com.mowitnow.supermower300.entities.vehicles.Vehicle;
import com.mowitnow.supermower300.exception.BusinessException;
import com.mowitnow.supermower300.main.DescriptionReader;
import com.mowitnow.supermower300.main.VehicleRunner;

public class VehicleRunnerTest {

	/**
	 * Test a vehicleRunner, with positions check, on the right side
	 * @throws BusinessException if regex not respected
	 */
	@Test
	public void aroundRight() throws BusinessException {

		DescriptionReader builder = new DescriptionReader();
		Vehicle mower = builder.buildMower("5 5 N", "");
		DescriptionReader lawnFact = new DescriptionReader();
		Lawn l = lawnFact.buildLawn("6 6");
		VehicleRunner m = new VehicleRunner(mower, l);

		OrientationEnum out;

		// Avancé vers nord
		m.move(Mower.getActions().get('A'));
		assertThat(mower.getOrientation(), equalTo(OrientationEnum.N));
		assertThat(mower.getX(), equalTo(5));
		assertThat(mower.getY(), equalTo(6));

		// Tourner à droite
		m.move(Mower.getActions().get('D'));
		assertThat(mower.getOrientation(), equalTo(OrientationEnum.E));
		assertThat(mower.getX(), equalTo(5));
		assertThat(mower.getY(), equalTo(6));

		// Avancer
		m.move(Mower.getActions().get('A'));
		assertThat(mower.getOrientation(), equalTo(OrientationEnum.E));
		assertThat(mower.getX(), equalTo(6));
		assertThat(mower.getY(), equalTo(6));

		// Tourner à droite
		m.move(Mower.getActions().get('D'));
		assertThat(mower.getOrientation(), equalTo(OrientationEnum.S));
		assertThat(mower.getX(), equalTo(6));
		assertThat(mower.getY(), equalTo(6));

		// Avancer
		m.move(Mower.getActions().get('A'));
		assertThat(mower.getOrientation(), equalTo(OrientationEnum.S));
		assertThat(mower.getX(), equalTo(6));
		assertThat(mower.getY(), equalTo(5));

		// Tourner à droite
		m.move(Mower.getActions().get('D'));
		assertThat(mower.getOrientation(), equalTo(OrientationEnum.O));
		assertThat(mower.getX(), equalTo(6));
		assertThat(mower.getY(), equalTo(5));

		// Avancer
		m.move(Mower.getActions().get('A'));
		assertThat(mower.getOrientation(), equalTo(OrientationEnum.O));
		assertThat(mower.getX(), equalTo(5));
		assertThat(mower.getY(), equalTo(5));

	}

	/**
	 * Test a vehicleRunner, with positions check, on the left side
	 * @throws BusinessException if regex not respected
	 */
	@Test
	public void aroundLeft() throws BusinessException {

		DescriptionReader builder = new DescriptionReader();
		Vehicle mower = builder.buildMower("5 5 N", "");
		DescriptionReader lawnFact = new DescriptionReader();
		Lawn l = lawnFact.buildLawn("5 5");
		VehicleRunner m = new VehicleRunner(mower, l);

		OrientationEnum out;

		// Tourner à gauche
		m.move(Mower.getActions().get('G'));
		assertThat(mower.getOrientation(), equalTo(OrientationEnum.O));

		// Tourner à gauche
		m.move(Mower.getActions().get('G'));
		assertThat(mower.getOrientation(), equalTo(OrientationEnum.S));

		// Tourner à gauche
		m.move(Mower.getActions().get('G'));
		assertThat(mower.getOrientation(), equalTo(OrientationEnum.E));

		// Tourner à gauche
		m.move(Mower.getActions().get('G'));
		assertThat(mower.getOrientation(), equalTo(OrientationEnum.N));

		// Tourner à gauche
		m.move(Mower.getActions().get('G'));
		assertThat(mower.getOrientation(), equalTo(OrientationEnum.O));
	}

	/**	
	 * Test behaviour when vehicle reach limit
	 * @throws BusinessException
	 */
	@Test
	public void reachLimitLawnTest() throws BusinessException {

		DescriptionReader builder = new DescriptionReader();
		Vehicle mower1 = builder.buildMower("1 2 N", "AAAAAAAADAAAAAAAAAA");
		Lawn l = builder.buildLawn("5 8");
		VehicleRunner m1 = new VehicleRunner(mower1, l);

		m1.run();
		assertThat(mower1.getX(), equalTo(5));
		assertThat(mower1.getY(), equalTo(8));
		assertThat(mower1.getOrientation(), equalTo(OrientationEnum.E));

	}

}
