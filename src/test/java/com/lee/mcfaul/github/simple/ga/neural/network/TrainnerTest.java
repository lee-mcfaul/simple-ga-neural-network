package com.lee.mcfaul.github.simple.ga.neural.network;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TrainnerTest {

	@Test public void dummyTestForBuild() {
		int[] layers = new int[] { 1, 2, 3 };
		int population = 5;
		Trainner trainner = new Trainner(layers, population);
		assertArrayEquals(layers, trainner.getLayers());
		assertEquals(population, trainner.getPopulationCount());
	}

}