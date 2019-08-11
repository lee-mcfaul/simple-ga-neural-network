package com.lee.mcfaul.github.simple.ga.neural.network.nn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class NeuralNetworkTest {

  NeuralNetwork nn;

  @Before
  public void setup() {
    Perceptron p = new Perceptron(new double[]{1, 2}, 2, 0, 0);

    Perceptron[] firstLayer = new Perceptron[]{p, p.clone()};
    Perceptron[] secondLayer = new Perceptron[]{p.clone()};

    List<Perceptron[]> layers = new ArrayList<>();
    layers.add(firstLayer);
    layers.add(secondLayer);

    nn = new NeuralNetwork(layers);
  }

  @Test
  public void testNetworkCalculateFire() {

    assertEquals(0, nn.calculate(new double[]{0, 0})[0], 0);
  }

  @Test
  public void testNetworkCalculateNoFire() {

    assertEquals(1, nn.calculate(new double[]{1, 1})[0], 0);
  }

  @Test
  public void testBreeding() {
    Perceptron a = new Perceptron(new double[]{1, 1}, 1, 0, 0);
    Perceptron b = new Perceptron(new double[]{2, 2}, 2, 0, 0);

    List<Perceptron[]> layersA = new ArrayList<>();
    List<Perceptron[]> layersB = new ArrayList<>();

    layersA.add(new Perceptron[]{a, a.clone()});
    layersA.add(new Perceptron[]{a, a.clone()});

    layersB.add(new Perceptron[]{b, b.clone()});
    layersB.add(new Perceptron[]{b, b.clone()});

    NeuralNetwork nnA = new NeuralNetwork(layersA);
    NeuralNetwork nnB = new NeuralNetwork(layersB);

    NeuralNetwork nnChild = nnA.breed(nnB);

    for (int i = 0; i < nnChild.getLayers().size(); i++) {
      Perceptron[] layer = nnChild.getLayers().get(i);
      for (int j = 0; j < layer.length; j++) {
        Perceptron p = layer[j];
        for (int k = 0; k < p.getWeights().length; k++) {
          assertTrue(equalsOneOf(p.getWeights()[k], 1.0, 2.0));
        }
        assertTrue(equalsOneOf(p.getThreshold(), 1.0, 2.0));
      }
    }
  }

  @Test
  public void testGenerate() {
    NeuralNetwork nn = new NeuralNetwork(new int[]{1, 2, 3}, 0, 0);
    for (int i = 0; i < nn.getLayers().size(); i++) {
      assertEquals(i + 1, nn.getLayers().get(i).length);
    }
  }

  @Test
  public void testMutation() {
    int[] layerlengths = new int[]{1024, 1024, 1024};
    NeuralNetwork nnA = new NeuralNetwork(layerlengths, 0.1, 1);
    NeuralNetwork nnB = nnA.clone();

    NeuralNetwork child = nnA.breed(nnB);

    boolean different = false;

    for (int i = 0; i < child.getLayers().size(); i++) {
      Perceptron[] childLayer = child.getLayers().get(i);
      Perceptron[] parentLayer = nnA.getLayers().get(i);
      for (int j = 0; j < childLayer.length; j++) {
        if (childLayer[j] != parentLayer[j]) {
          different = true;
          break;
        }
      }
    }

    assertTrue(different);
  }

  /**
   * The first object will be the object to compare. the rest are the objects to compare the first
   * to. if at least one matches, we return true.
   */
  private boolean equalsOneOf(Object... objs) {
    Preconditions.checkArgument(objs.length > 1);

    Object x = objs[0];

    for (int i = 1; i < objs.length; i++) {
      if (x.equals(objs[i])) {
        return true;
      }
    }
    return false;
  }
}
