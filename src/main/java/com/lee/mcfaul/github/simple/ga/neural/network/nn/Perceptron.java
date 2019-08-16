package com.lee.mcfaul.github.simple.ga.neural.network.nn;

import com.google.common.base.Preconditions;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A perceptron is a basic unit of a nerual network. It is meant to emulate the behavior of neuron
 * cells. A given number of input will be submitted to a perceptron and it will weight each input
 * based on the weight pre-assigned to it and add the resulting numbers together. if the final tally
 * exceeds the threshold value then the perceptron will "fire" and output a value of 1, otherwise it
 * will output 0. For simplicity all input should be normalized to be between 0 and 1 before being
 * submitted at the input level.
 * <p>
 * note that the lombok library is used here to simplify the code.
 */
@Getter
@AllArgsConstructor
public class Perceptron {

  private static final String BAD_INPUT_LENGTH_MSG = "Input length %d does not match expected layer length %d.";
  private static final Random random = new Random();
  /**
   * Represents the wights given to this perceptron's input. There is one wight per input value so
   * the length of the array will be equal to the number of perceptrons in the previous layer or the
   * size of the ingest array if this perceptron is part of the input layer.
   */
  private double[] weights;

  /**
   * This threshold value must be exceeded by the result of an input calculation in order for the
   * perceptron to "fire"
   */
  private double threshold;

  private double mutationRate;

  private double mutationMagnitude;

  /**
   * This constructor will initialize the perceptron with a random threshold and weights.
   *
   * @param previousLayerSize the size of the input layer for this perceptron (previous layer or
   * input)
   * @param mutationRate the rate at which mutations occur
   * @param mutationMagnitude the magnitude with which a mutation occurs
   */
  public Perceptron(int previousLayerSize, double mutationRate, double mutationMagnitude) {
    this.mutationRate = mutationRate;
    this.mutationMagnitude = mutationMagnitude;

    weights = new double[previousLayerSize];

    for (int i = 0; i < weights.length; i++) {
      weights[i] = getRandomValue(random);
    }
    threshold = getRandomValue(random);
  }

  /**
   * Use to make deep copy
   *
   * @param perceptron to be coppied
   */
  public Perceptron(Perceptron perceptron) {

    weights = new double[perceptron.getWeights().length];
    System.arraycopy(perceptron.getWeights(), 0, weights, 0, weights.length);

    threshold = perceptron.getThreshold();
    mutationRate = perceptron.getMutationRate();
    mutationMagnitude = perceptron.mutationMagnitude;
  }

  /**
   * Creates a random double between -1 and 1
   *
   * @param random the instance of the object to be used to generate random doubles.
   * @return between -1 and 1
   */
  private static double getRandomValue(Random random) {
    return random.nextDouble() * ((random.nextDouble() > 0.5) ? 1 : -1);
  }

  /**
   * Return a new perceptron with randomly selected "genes" (weight and threshold values) from both
   * this perceptron and the given perceptron
   *
   * @param secondParent the parent with which to breed the current perceptron
   * @return a new child perceptron with traits from this perceptron and teh parent
   */
  public Perceptron breed(Perceptron secondParent) {

    double[] childWeights = new double[weights.length];
    for (int i = 0; i < childWeights.length; i++) {
      childWeights[i] = selectGene(weights[i], secondParent.weights[i], random);
    }
    double childThreshold = selectGene(threshold, secondParent.threshold, random);

    return new Perceptron(childWeights, childThreshold, mutationRate, mutationMagnitude);
  }

  /**
   * Helper method to randoml;y select between two genes and mutate if necessary
   *
   * @param geneA gene from parent A
   * @param geneB gene from parent B
   * @param random random provider
   * @return the selected gene with mutation if applicable
   */
  private double selectGene(double geneA, double geneB, Random random) {

    double childGene = (random.nextDouble() > 0.5) ? geneA : geneB;

    if (random.nextDouble() < mutationRate) {
      //we have mutation (may increase or decrease value of gene
      double direction = (random.nextDouble() > 0.5) ? 1 : -1;
      childGene += mutationMagnitude * direction;
    }

    return childGene;
  }

  /**
   * Calculate the sum of weighted inputs and return whether the sum exceeds the threshold as a
   * "fire" (1) or a no "fire" (0)
   *
   * @param input input values to be weighed
   * @return 1 for "fire" 0 for no "fire"
   */
  public double calculate(double[] input) {
    Preconditions.checkArgument(input.length == weights.length,
        String.format(BAD_INPUT_LENGTH_MSG, input.length, weights.length));

    double sum = 0;
    for (int i = 0; i < input.length; i++) {
      sum += input[i] * weights[i];
    }
    return (sum > threshold) ? 1 : 0;
  }
}
