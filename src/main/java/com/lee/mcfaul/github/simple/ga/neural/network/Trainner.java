package com.lee.mcfaul.github.simple.ga.neural.network;

import com.lee.mcfaul.github.simple.ga.neural.network.nn.NeuralNetwork;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Trainner {

  private static final int CORRECT_ELEMENT_VALUE = 10;
  private static final int WRONG_ELEMENT_VALUE = -1;
  private static final int PERFECT_ANSWER_VALUE = 10;

  private NeuralNetwork[] networks;

  /**
   * Signifies creation of a new population
   */
  public Trainner(int[] layers, int populationCount, double mutationRate,
      double mutationMagnitude) {

    networks = new NeuralNetwork[populationCount];

    for (int i = 0; i < networks.length; i++) {
      networks[i] = new NeuralNetwork(layers, mutationRate, mutationMagnitude);
    }
    log.info("Created {} individuals", populationCount);
  }

  /**
   * Will train the networks using the input data. will use the test data to obtain a final score.
   * This is simplistic in it's approach and it should be noted that it will not detect a higher
   * scoring network in generations previous to the last.
   *
   * @param generations how many generations to train
   * @param trainingSet the set of data to train. format is {{{input},{expected output}}}.
   * @param testSet the set of data used in final scoring
   * @return the highest scoring network in the final generation
   */
  public NeuralNetwork train(int generations, List<List<double[]>> trainingSet,
      List<List<double[]>> testSet) {
    log.info("Beginning training for {} generations. training set size: {} test set size: {}",
        generations, trainingSet.size(), testSet.size());
    NeuralNetwork highestScoringNetwork = null;

    double highestPossibleScore =
        trainingSet.size() * trainingSet.get(0).get(1).length * (double) (CORRECT_ELEMENT_VALUE
            + PERFECT_ANSWER_VALUE);

    for (int i = 0; i < generations; i++) {

      double[] networkScores = getScores(networks, trainingSet);

      int highestScoreingNetworkDx = getHighestDx(networkScores);

      highestScoringNetwork = networks[highestScoreingNetworkDx];

      for (int j = 0; j < networks.length; j++) {
        networks[j] = networks[j].breed(highestScoringNetwork);
      }

      log.info("generation: {}, highScore: {} out of {}", i,
          networkScores[highestScoreingNetworkDx], highestPossibleScore);
    }

    double[] finalTestScore = getScores(new NeuralNetwork[]{highestScoringNetwork}, testSet);

    double finalScore = finalTestScore[0];

    log.info("training finished. best individual = {}", finalScore);

    return highestScoringNetwork;
  }

  private int getHighestDx(double[] networkScores) {

    int highest = 0;
    double highScore = Double.MIN_VALUE;
    for (int i = 0; i < networkScores.length; i++) {
      if (networkScores[i] > highScore) {
        highest = i;
        highScore = networkScores[i];
      }
    }

    return highest;
  }

  /**
   * Runs each network through all training input/result sets and returns the resulting score. Score
   * is calculated by comparing obtained results with expected output. a matching element increases
   * score by 10 a non-matching element decreases score by 1. This should favor networks which get
   * most of their answers right. Perfect score get a bonus of (10 X result set length) to their
   * score
   */
  private double[] getScores(NeuralNetwork[] individuals, List<List<double[]>> trainingSet) {

    double[] scores = new double[individuals.length];

    for (int i = 0; i < individuals.length; i++) {

      double score = 0;

      for (int inputResultSet = 0; inputResultSet < trainingSet.size(); inputResultSet++) {

        double[] result = individuals[i].calculate(trainingSet.get(inputResultSet).get(0));
        boolean perfectScore = true;
        for (int resultDx = 0; resultDx < result.length; resultDx++) {

          if (result[resultDx] == trainingSet.get(inputResultSet).get(1)[resultDx]) {
            score += CORRECT_ELEMENT_VALUE;
          } else {
            score -= WRONG_ELEMENT_VALUE;
            perfectScore = false;
          }
          if (perfectScore) {
            // extra points for getting the answer perfect
            score += PERFECT_ANSWER_VALUE * result.length;
          }
        }
      }

      scores[i] = score;
    }

    return scores;
  }

}
