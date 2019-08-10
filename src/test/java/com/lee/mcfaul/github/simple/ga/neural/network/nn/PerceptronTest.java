package com.lee.mcfaul.github.simple.ga.neural.network.nn;

import com.lee.mcfaul.github.simple.ga.neural.network.categories.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Category(UnitTest.class) public class PerceptronTest {

	@Test public void testBreeding() {
		double[] arrayA = new double[] { 1, 2 };
		double[] arrayB = new double[] { 3, 4 };
		double thresholdA = 5;
		double thesholdB = 10;
		double mutationRate = -1;
		double mutationMagnitude = 0;

		Perceptron perceptronA = new Perceptron(arrayA, thresholdA, mutationRate, mutationMagnitude);
		Perceptron perceptronB = new Perceptron(arrayB, thesholdB, mutationRate, mutationMagnitude);

		Perceptron child = perceptronA.breed(perceptronB);

		assertTrue(child.getWeights()[0] == arrayA[0] || child.getWeights()[0] == arrayB[0]);
		assertTrue(child.getWeights()[1] == arrayA[1] || child.getWeights()[1] == arrayB[1]);
		assertTrue(child.getThreshold() == thresholdA || child.getThreshold() == thesholdB);
		assertEquals(mutationRate, child.getMutationRate(), 0);
		assertEquals(mutationRate, child.getMutationRate(), 0);

	}

	@Test public void testMutation() {
		double[] arrayA = new double[] { 1, 2 };
		double[] arrayB = new double[] { 3, 4 };
		double thresholdA = 5;
		double thesholdB = 10;
		double mutationRate = 2;
		double mutationMagnitude = 0;

		Perceptron perceptronA = new Perceptron(arrayA, thresholdA, mutationRate, mutationMagnitude);
		Perceptron perceptronB = new Perceptron(arrayB, thesholdB, mutationRate, mutationMagnitude);

		Perceptron child = perceptronA.breed(perceptronB);

		assertTrue(child.getWeights()[0] != arrayA[0] || child.getWeights()[0] != arrayB[0]);
		assertTrue(child.getWeights()[1] != arrayA[1] || child.getWeights()[1] != arrayB[1]);
		assertTrue(child.getThreshold() != thresholdA || child.getThreshold() != thesholdB);
		assertEquals(mutationRate, child.getMutationRate(), 0);
		assertEquals(mutationRate, child.getMutationRate(), 0);

	}

}