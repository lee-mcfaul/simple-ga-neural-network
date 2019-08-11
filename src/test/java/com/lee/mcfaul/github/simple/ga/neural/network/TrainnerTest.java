package com.lee.mcfaul.github.simple.ga.neural.network;

import static org.junit.Assert.assertEquals;

import com.lee.mcfaul.github.simple.ga.neural.network.categories.FunctionalTest;
import com.lee.mcfaul.github.simple.ga.neural.network.nn.NeuralNetwork;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(FunctionalTest.class)
@Slf4j
public class TrainnerTest {

  /**
   * Will create a training set for XoR and train a small neural network.
   *
   * @see <a href="https://en.wikipedia.org/wiki/XOR_gate">XOR wiki</a>
   */
  @Test
  public void testTrainner() {

    List<List<double[]>> data = new ArrayList<>();

    data.add(getXoR(0, 0, 0));
    data.add(getXoR(1, 1, 0));
    data.add(getXoR(0, 1, 1));
    data.add(getXoR(1, 0, 1));

    Trainner trainner = new Trainner(new int[]{2, 2, 1}, 1000, 0.05, 0.001);

    NeuralNetwork nn = trainner.train(10, data, data);

    assertEquals(data.get(0).get(1)[0], nn.calculate(data.get(0).get(0))[0], 0.0001);
    assertEquals(data.get(1).get(1)[0], nn.calculate(data.get(1).get(0))[0], 0.0001);
    assertEquals(data.get(2).get(1)[0], nn.calculate(data.get(2).get(0))[0], 0.0001);
    assertEquals(data.get(3).get(1)[0], nn.calculate(data.get(3).get(0))[0], 0.0001);
  }

  private List<double[]> getXoR(double a, double b, double answer) {
    List<double[]> result = new ArrayList<>(2);
    result.add(new double[]{a, b});
    result.add(new double[]{answer});
    return result;
  }
}