package com.mowitnow.supermower3000.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.mowitnow.supermower300.constant.OrientationEnum;
import com.mowitnow.supermower300.entities.Lawn;
import com.mowitnow.supermower300.entities.vehicles.Vehicle;
import com.mowitnow.supermower300.exception.BusinessException;
import com.mowitnow.supermower300.main.DescriptionReader;

import org.junit.Test;

public class DescriptionReaderTest {

    /**
     * Test a lawn instanciation
     * 
     * @throws BusinessException if regex not respected
     */
    @Test
    public void lawnSucessTest() throws BusinessException {

        DescriptionReader reader = new DescriptionReader();

        Lawn l = reader.buildLawn("5 5");

        assertThat("Largeur", l.getHeight(), equalTo(5));
        assertThat("Hauteur", l.getHeight(), equalTo(5));

    }

    /**
     * .Test a failed lawn instanciation
     * 
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void lawnErrorRegexTest() throws BusinessException {

        DescriptionReader lawnFact = new DescriptionReader();
        Lawn l = lawnFact.buildLawn("5 X");
    }


    /**
     * Test a mower instanciation
     * 
     * @throws BusinessException
     */
    @Test
    public void mowerSucessTest() throws BusinessException {

        DescriptionReader builder = new DescriptionReader();
        Vehicle mower = builder.buildMower("1 2 N", "GAGAGXRG5AGAA");

        assertThat("x", mower.getX(), equalTo(1));
        assertThat("y", mower.getY(), equalTo(2));
        assertThat("orientation", mower.getOrientation(), equalTo(OrientationEnum.N));

    }

    /**
     * .Test a failed mower instanciation
     * 
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void mowerFailedTest() throws BusinessException {

        DescriptionReader builder = new DescriptionReader();
        Vehicle mower = builder.buildMower("1 2 X", "GAGAGXRG5AGAA");


    }
}
