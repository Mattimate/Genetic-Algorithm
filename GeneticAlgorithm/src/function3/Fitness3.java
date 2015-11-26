/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function3;

import algorithms.Individual;
import java.util.Arrays;

/**
 *
 * @author m3-gordon
 */
public class Fitness3 {

    private double sigmaSum;
    private double fitnessSum;
    private final int geneQuantity;
    private final int posNegDeterminator = 0;
    private final int plus1Determinator = 1;
    private int n;

    public Fitness3(int geneQuantity) {
        this.geneQuantity = geneQuantity;
    }

    void determineFitness(Chromosome[] chromosomeArr) {
        setFitnesses(chromosomeArr);
    }

    private void setFitnesses(Chromosome[] chromosomeArr) {
        int bineryNum;
        double moddedNum;

        fitnessSum = 0;
        for (int i = 0; i < chromosomeArr.length; i++) {
            sigmaSum = 0;
            for (int j = 0; j < chromosomeArr[i].getIndividualArr().length; j++) {
                bineryNum = binaryConverter(Arrays.copyOfRange(chromosomeArr[i].getIndividualArr()[j].getGenes(), 2, geneQuantity));
                moddedNum = binaryMod(Arrays.copyOfRange(chromosomeArr[i].getIndividualArr()[j].getGenes(), 0, 2), bineryNum);
                chromosomeArr[i].getIndividualArr()[j].setFitness(getFitnessValue(moddedNum / 100));
                sigmaSum += chromosomeArr[i].getIndividualArr()[j].fitness;
            }
            chromosomeArr[i].setFitness(sigmaSum + (10 * n));
            fitnessSum += chromosomeArr[i].fitness;
        }
    }

    private int binaryConverter(int[] array) {
        int value = 0;
        for (int i = 0; i < geneQuantity - 2; i++) {
            if (array[i] != 0) {
                value += Math.pow(2, (array.length - i) - 1);
            }
        }
        return value;
    }

    private int binaryMod(int[] array, int binaryNum) {
        return array[posNegDeterminator] == 1 ? (binaryNum + array[plus1Determinator]) * -1 : binaryNum + array[plus1Determinator];
    }

    double getFitnessValue(double x) {
        return x * x - 10 * Math.cos(2 * Math.PI * x);

    }

    double getFitnessSum() {
        return fitnessSum;
    }

    void setN(int n) {
        this.n = n;
    }

}
