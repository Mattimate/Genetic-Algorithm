package algorithms;

import function3.Chromosome;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matthew
 */
public class Elitism {

    private final CompareMin minimize = new CompareMin();
    private final CompareMax maximize = new CompareMax();
    private int highestFitness;
    private int lowestFitness;
    private Chromosome bestChromosome = new Chromosome();
    private Individual bestIndividual;

    public Elitism(int geneQuantity) {
        this.bestIndividual = new Individual(geneQuantity);
    }

    public Individual[] run(Individual[] individualArr, int generation, int function) {
        if (generation == 0) {
            bestIndividual.fitness = individualArr[0].fitness;
        }

        getHighLowFitness(individualArr);

        if (function == 1) {            //function 1
            if (bestIndividual.fitness < individualArr[highestFitness].fitness) {
                bestIndividual = individualArr[highestFitness];
            }
            individualArr[lowestFitness] = bestIndividual;
        } else if (function == 2) {     //function 2
            if (bestIndividual.fitness > individualArr[lowestFitness].fitness) {
                bestIndividual = individualArr[lowestFitness];
            }
            individualArr[highestFitness] = bestIndividual;   
        }
        return individualArr;
    }

    private void getHighLowFitness(Individual[] individualArr) {
        for (int i = 0; i < individualArr.length - 1; i++) {
            int temp = getBetterFitness(maximize, individualArr, i);
            if (individualArr[highestFitness].fitness < individualArr[temp].fitness) {
                highestFitness = temp;
            }

            temp = getBetterFitness(minimize, individualArr, i);
            if (individualArr[lowestFitness].fitness > individualArr[temp].fitness) {
                lowestFitness = temp;
            }
        }
    }

    private int getBetterFitness(IComparator cm, Individual[] individualArr, int i) {
        if (cm.compareIndv(individualArr[i], individualArr[i + 1]) == 1) {
            return i;
        } else {
            return i + 1;
        }
    }

    public Chromosome[] runFunc3(Chromosome[] chromosomeArr, int generation) {
        if (generation == 0) {
            bestChromosome.fitness = chromosomeArr[0].fitness;
        }

        getHighLowFitnessFunc3(chromosomeArr);

        if (bestChromosome.fitness > chromosomeArr[lowestFitness].fitness) {
            bestChromosome = chromosomeArr[lowestFitness];
        }
        chromosomeArr[highestFitness] = bestChromosome;
        System.out.println(bestChromosome.fitness);
        return chromosomeArr;
    }

    private void getHighLowFitnessFunc3(Chromosome[] chromosomeArr) {
        for (int i = 0; i < chromosomeArr.length - 1; i++) {
            int temp = doFunc3Selection(maximize, chromosomeArr, i);
            if (chromosomeArr[highestFitness].fitness < chromosomeArr[temp].fitness) {
                highestFitness = temp;
            }

            temp = doFunc3Selection(minimize, chromosomeArr, i);
            if (chromosomeArr[lowestFitness].fitness > chromosomeArr[temp].fitness) {
                lowestFitness = temp;
            }
        }
    }

    private int doFunc3Selection(IComparator cm, Chromosome[] chromosomeArr, int i) {
        if (cm.compareFunc3(chromosomeArr[i], chromosomeArr[i + 1]) == 1) {
            return i;
        } else {
            return i + 1;
        }
    }
}
