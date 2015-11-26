/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function2;

import algorithms.Individual;
import java.util.Arrays;

/**
 *
 * @author m3-gordon
 */
public class Fitness2 {

    private double sum;
    private int subGeneQuantity;

    public Fitness2(int geneQuantity) {
        this.subGeneQuantity = geneQuantity/2;
    }

    void determineFitness(Individual[] population) {
        sum = 0;
        setFitnesses(population);
        for (Individual indv : population) {
            sum += indv.fitness;
        }
    }

    private void setFitnesses(Individual[] population) {
        int x, y;
        for (Individual individual : population) {
            x = binaryConverter(Arrays.copyOfRange(individual.getGenes(), 0, subGeneQuantity));
            y = binaryConverter(Arrays.copyOfRange(individual.getGenes(), subGeneQuantity, individual.getGeneQuantity()));
            individual.setFitness(getFitnessValue(x, y));
        }
    }

    private int binaryConverter(int[] array) {
        int value = 0;
        for (int i = 1; i < subGeneQuantity; i++) {
            if (array[i] != 0) {
                value += Math.pow(2, (array.length - i) - 1);
            }
        }

        return array[0] == 1 ? value * -1 : value;
    }

    private double getFitnessValue(int x, int y) {
        return 0.26 * (x * x + y * y) - 0.48 * x * y;
    }

    double getFitnessSum() {
        return sum;
    }

}
