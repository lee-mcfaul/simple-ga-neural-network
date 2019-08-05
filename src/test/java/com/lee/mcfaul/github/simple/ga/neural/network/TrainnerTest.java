package com.lee.mcfaul.github.simple.ga.neural.network;

import com.lee.mcfaul.github.simple.ga.neural.network.categories.FunctionalTest;
import com.lee.mcfaul.github.simple.ga.neural.network.categories.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TrainnerTest {

	@Category(UnitTest.class) @Test public void dummyTestForBuild() {
		int[] layers = new int[] { 1, 2, 3 };
		int population = 5;
		Trainner trainner = new Trainner(layers, population);
		assertArrayEquals(layers, trainner.getLayers());
		assertEquals(population, trainner.getPopulationCount());
	}

	@Category(FunctionalTest.class) @Test public void dummyFunctionalTest() {
		int x = 0;
		for (int i = 0; i < 10; i++) {
			x++;
		}
		assertEquals(10, x);
	}

}