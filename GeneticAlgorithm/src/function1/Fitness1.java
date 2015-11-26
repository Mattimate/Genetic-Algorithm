/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function1;

import algorithms.Individual;

/**
 *
 * @author m3-gordon
 */
public class Fitness1 {

    private int sum;

    void determineFitness(Individual[] population) {
        sum = 0;
        int decimalValue = 0;

        for (Individual individual : population) {
            for (int i = 0; i < individual.getGeneQuantity(); i++) {
                if (individual.getGene(i) != 0) {
                    decimalValue += Math.pow(2, (individual.getGeneQuantity() - i) - 1);
                }
            }
            individual.setFitness(Math.pow(decimalValue, 2));
            sum += Math.pow(decimalValue, 2);
            decimalValue = 0;
        }
    }

    int getFitnessSum() {
        return sum;
    }
}
