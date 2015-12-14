package com.mowitnow.supermower3000.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.junit.Test;

import com.mowitnow.supermower300.constant.OrientationEnum;
import com.mowitnow.supermower300.entities.Lawn;
import com.mowitnow.supermower300.entities.vehicles.Vehicle;
import com.mowitnow.supermower300.exception.BusinessException;
import com.mowitnow.supermower300.main.DescriptionReader;
import com.mowitnow.supermower300.main.VehicleRunner;

public class IntegrationTest {

	/**
	 * Ceci est un test qui teste
	 * @throws BusinessException
	 * @throws IOException 
	 */
	@Test
	public void CalTest() throws BusinessException, IOException {
		
		DescriptionReader builder = new DescriptionReader();
		Vehicle mower1 = builder.buildMower("1 2 N", "GAGAGAGAA");
		Vehicle mower2 = builder.buildMower("3 3 E", "AADAADADDA");
		Lawn l = builder.buildLawn("5 5");
		VehicleRunner m1 = new VehicleRunner(mower1, l);
		VehicleRunner m2 = new VehicleRunner(mower2, l);

		m1.run();
		assertThat(mower1.getX(), equalTo(1));
		assertThat(mower1.getY(), equalTo(3));
		assertThat(mower1.getOrientation(), equalTo(OrientationEnum.N));
		m2.run();
		assertThat(mower2.getX(), equalTo(5));
		assertThat(mower2.getY(), equalTo(1));
		assertThat(mower2.getOrientation(), equalTo(OrientationEnum.E));
		
	}
	
	/**
	 * Ceci est un test qui teste
	 * @throws BusinessException
	 * @throws IOException 
	 */
	@Test
	public void WrongTest() throws BusinessException, IOException {
		
		DescriptionReader builder = new DescriptionReader();
		Vehicle mower1 = builder.buildMower("1 2 N", "GAGAGXRG5AGAA");
		Lawn l = builder.buildLawn("5 5");
		VehicleRunner m1 = new VehicleRunner(mower1, l);

		m1.run();
	//	assertThat(mower1.getX(), equalTo(1));
	//	assertThat(mower1.getY(), equalTo(3));
	//	assertThat(mower1.getOrientation(), equalTo(OrientationEnum.N));
		
	}
	
	

}
