/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function3;

import algorithms.CompareMin;
import algorithms.Crossover;
import algorithms.Elitism;
import algorithms.Individual;
import algorithms.Mutator;
import algorithms.NewObjectCreator;
import algorithms.TournamentSelection;
import algorithms.XLFileWriter;

/**
 *
 * @author m3-gordon
 */
public class Func3 {

    private final int populationSize = 10;
    private final int chromosomeSize = 20; // Chromosome size is the number of individuals in a chromsome which is equal to the value of n
    private final int geneQuantity = 11;
    private final int generationLimit = 2000;

    private final double crossoverProbability = 0.8;
    private final double mutationProbability = 0.08;

    private Chromosome[] chromosomeArr;
    private Individual[] individualArr;
    private final double[] bestArray = new double[generationLimit];
    private final double[] meanArray = new double[generationLimit];

    private final CompareMin minimize = new CompareMin();
    private final TournamentSelection tournament = new TournamentSelection(populationSize, minimize);
    private final Crossover crossover = new Crossover(populationSize, chromosomeSize, geneQuantity);
    private final Mutator mutator = new Mutator(geneQuantity);
    private final Elitism elite = new Elitism(geneQuantity);
    private XLFileWriter xlWriter = new XLFileWriter();

    private final Fitness3 fitness = new Fitness3(geneQuantity);

    public Func3() {
        populate();
        fitness.setN(chromosomeSize);
        runAlgorithm();
        xlWriter.writeToFile("Function3.xls", generationLimit, meanArray, bestArray);
    }

    private void runAlgorithm() {
        fitness.determineFitness(chromosomeArr);
        
        for (int i = 0; i < generationLimit; i++) {            
            System.out.println("Generation: " + (i+1));
            
            chromosomeArr = tournament.runFunc3(chromosomeArr);
            chromosomeArr = crossover.runFunc3(chromosomeArr, populationSize, crossoverProbability);
            doMatation();

            fitness.determineFitness(chromosomeArr);
            chromosomeArr = elite.runFunc3(chromosomeArr, i);
            fitness.determineFitness(chromosomeArr);

            displayStats();
            getBestAndMean(i);
        }

    }

    private void populate() {
        chromosomeArr = new Chromosome[populationSize];
        for (int i = 0; i < populationSize; i++) {
            individualArr = new Individual[chromosomeSize];
            for (int j = 0; j < chromosomeSize; j++) {
                individualArr[j] = new Individual(geneQuantity);
                individualArr[j].randomise();
            }
            chromosomeArr[i] = new Chromosome();
            chromosomeArr[i].setIndividualArr(individualArr);
        }
    }

    private void doMatation() {
        Chromosome[] aChromosomeArr = null;
        aChromosomeArr = NewObjectCreator.createNewChromosomeArr(chromosomeArr, populationSize, geneQuantity);
        for (int j = 0; j < populationSize; j++) {
            aChromosomeArr[j].setIndividualArr(mutator.run(chromosomeArr[j].getIndividualArr(), mutationProbability));
        }
        chromosomeArr = aChromosomeArr;

    }

    private void displayPopulation() {
        int a = 1;
        System.out.println("Element\tBinary string\tSigma Values");
        System.out.println("=======\t=============\t=======");
        for (Chromosome aChromosome : chromosomeArr) {
            System.out.println("Run: " + a);
            a++;
            for (int i = 0; i < chromosomeSize; i++) {
                System.out.print(i + "\t");
                System.out.print(aChromosome.getIndividualArr()[i].printModGenes() + "\t");
                System.out.println(aChromosome.getIndividualArr()[i].fitness);
            }
            System.out.println("Chomosome total: " + aChromosome.fitness);
            System.out.println("");
        }
    }

    private void getBestAndMean(int i) {
        meanArray[i] = (double) fitness.getFitnessSum() / populationSize;
        double bestIndvFitness = chromosomeArr[0].fitness;
        for (int j = 0; j < populationSize; j++) {
            double currentIndvFitness = chromosomeArr[j].fitness;
            bestIndvFitness = bestIndvFitness > currentIndvFitness ? chromosomeArr[j].fitness : bestIndvFitness;
        }
        bestArray[i] = bestIndvFitness;
    }

    private void displayStats() {
        System.out.println("Total:\t" + fitness.getFitnessSum());
        System.out.println("Average: " + (double) fitness.getFitnessSum() / populationSize);
        System.out.println("\n");
    }

    public static void main(String[] args) {
        new Func3();
    }
}
