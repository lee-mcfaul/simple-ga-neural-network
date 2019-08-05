package com.lee.mcfaul.github.simple.ga.neural.network;

import com.lee.mcfaul.github.simple.ga.neural.network.categories.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@Category(UnitTest.class) public class DummyUnitTest {

	@Test public void dummyTestForBuild() {
		int[] layers = new int[] { 1, 2, 3 };
		int population = 5;
		Trainner trainner = new Trainner(layers, population);
		assertArrayEquals(layers, trainner.getLayers());
	}

	@Test public void dummyTest2() {

		int[] layers = new int[] { 1, 2, 3 };
		int population = 5;
		Trainner trainner = new Trainner(layers, population);
		assertEquals(population, trainner.getPopulationCount());

	}

}