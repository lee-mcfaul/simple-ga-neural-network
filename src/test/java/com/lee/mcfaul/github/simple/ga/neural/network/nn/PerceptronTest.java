package com.lee.mcfaul.github.simple.ga.neural.network.nn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.lee.mcfaul.github.simple.ga.neural.network.categories.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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

  @Test
  public void testPerceptronCalculationFire() {

    double[] weights = new double[]{1, 2};
    double threshold = 2;
    double mutationRate = 0;
    double mutationMagnitude = 0;

    Perceptron perceptron = new Perceptron(weights, threshold, mutationRate, mutationMagnitude);

    double[] input = new double[]{1, 1};

    assertEquals(1.0, perceptron.calculate(input), 0);

  }

  @Test
  public void testPerceptronCalculationNoFire() {

    double[] weights = new double[]{1, 2};
    double threshold = 2;
    double mutationRate = 0;
    double mutationMagnitude = 0;

    Perceptron perceptron = new Perceptron(weights, threshold, mutationRate, mutationMagnitude);

    double[] input = new double[]{0, 1};

    assertEquals(0, perceptron.calculate(input), 0);

  }

  @Test
  public void testClone() {
    double[] weights = new double[]{1, 2};
    double threshold = 2;
    double mutationRate = 0;
    double mutationMagnitude = 0;

    Perceptron perceptron = new Perceptron(weights, threshold, mutationRate, mutationMagnitude);
    Perceptron clone = perceptron.clone();
    double previousWeight = perceptron.getWeights()[0];
    double newWeight = 9;
    perceptron.getWeights()[0] = newWeight;

    assertEquals(newWeight, perceptron.getWeights()[0], 0);
    assertEquals(clone.getWeights()[0], previousWeight, 0);
  }
}