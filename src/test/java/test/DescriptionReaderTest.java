package test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import com.mowitnow.supermower300.entities.Lawn;
import com.mowitnow.supermower300.exception.BusinessException;
import com.mowitnow.supermower300.main.DescriptionReader;

public class DescriptionReaderTest {

	@Test
	public void lawnSucessTest() throws BusinessException {

		DescriptionReader lawnFact = new DescriptionReader();

		Lawn l = lawnFact.buildLawn("5 5");

		assertThat("Largeur", l.getHeight(), equalTo(5));
		assertThat("Hauteur", l.getHeight(), equalTo(5));

	}

	@Test(expected = BusinessException.class)
	public void lawnErrorTest() throws NumberFormatException, BusinessException {

		DescriptionReader lawnFact = new DescriptionReader();
		Lawn l = lawnFact.buildLawn("5 X");
	}
}
