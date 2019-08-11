package com.lee.mcfaul.github.simple.ga.neural.network.nn;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NeuralNetwork {

  List<Perceptron[]> layers;

  public NeuralNetwork(int[] layerSizes, double mutationRate, double mutationMagnitude) {

    Preconditions.checkArgument(layerSizes.length > 0);

    layers = new ArrayList<>(layerSizes.length);

    int previousLayerSize = layerSizes[0];
    for (int i = 0; i < layerSizes.length; i++) {

      Perceptron[] layer = new Perceptron[layerSizes[i]];
      for (int j = 0; j < layer.length; j++) {
        layer[j] = new Perceptron(previousLayerSize, mutationRate, mutationMagnitude);
      }
      previousLayerSize = layer.length;
      layers.add(layer);
    }
  }

  /**
   * Calculates the result of an input when run through this nerual network
   *
   * @param input normalized array of doubles. must be same length as first layer (input layer)
   * @return array of doubles the size of teh output layer with fire (1) and no fire (0)
   */
  public double[] calculate(double[] input) {
    Preconditions.checkArgument(input.length == layers.get(0).length);

    double[] layerInput = input;

    for (int i = 0; i < layers.size(); i++) {

      int layerLength = layers.get(i).length;

      double[] result = new double[layerLength];

      for (int perceptronInLayer = 0; perceptronInLayer < layerLength; perceptronInLayer++) {
        result[perceptronInLayer] = layers.get(i)[perceptronInLayer].calculate(layerInput);
      }
      layerInput = result;
    }

    return layerInput;
  }

  @Override
  public NeuralNetwork clone() {

    List<Perceptron[]> cloneLayers = new ArrayList<>();

    for (int i = 0; i < layers.size(); i++) {

      Perceptron[] layer = layers.get(i);
      Perceptron[] cloneLayer = new Perceptron[layer.length];

      for (int j = 0; j < layer.length; j++) {
        cloneLayer[j] = layer[j].clone();
      }
      cloneLayers.add(cloneLayer);
    }

    return new NeuralNetwork(cloneLayers);
  }

  public NeuralNetwork breed(NeuralNetwork parentB) {

    List<Perceptron[]> childLayers = new ArrayList<>(layers.size());

    for (int i = 0; i < layers.size(); i++) {

      Perceptron[] parentALayer = layers.get(i);
      Perceptron[] parentBLayer = parentB.layers.get(i);
      Perceptron[] childLayer = new Perceptron[parentALayer.length];

      for (int j = 0; j < parentALayer.length; j++) {
        childLayer[j] = parentALayer[j].breed(parentBLayer[j]);
      }
      childLayers.add(childLayer);
    }

    return new NeuralNetwork(childLayers);
  }
}
