/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function2;

import algorithms.CompareMin;
import algorithms.Crossover;
import algorithms.Elitism;
import algorithms.Individual;
import algorithms.Mutator;
import algorithms.TournamentSelection;
import algorithms.XLFileWriter;

/**
 *
 * @author m3-gordon
 */
public class Func2 {

    private final int populationSize = 10;
    private final int geneQuantity = 10;
    private final int generationLimit = 20;

    private final double crossoverProbability = 0.1;
    private final double mutationProbability = 0.08;

    private Individual[] population;

    private final CompareMin cmin = new CompareMin();
    private final TournamentSelection tournament = new TournamentSelection(populationSize, cmin);
    private final Crossover crossover = new Crossover(populationSize, 0, geneQuantity);
    private final Mutator mutator = new Mutator(geneQuantity);
    private final Elitism elite = new Elitism(geneQuantity);

    private final Fitness2 fitness = new Fitness2(geneQuantity);
    private final double[] bestArray = new double[generationLimit];
    private final double[] meanArray = new double[generationLimit];

    private XLFileWriter xlWriter = new XLFileWriter();

    public Func2() {
        populate();
        fitness.determineFitness(population);

        for (int i = 0; i < generationLimit; i++) {
            System.out.println("Generation: " + (i + 1));

            population = tournament.run(population);
            population = crossover.run(population, crossoverProbability);
            population = mutator.run(population, mutationProbability);

            fitness.determineFitness(population);
            population = elite.run(population, i, 2);
            fitness.determineFitness(population);

            displayPopulation();
            displayStats();
            getBestAndMean(i);
        }

        xlWriter.writeToFile("Function2.xls", generationLimit, meanArray, bestArray);

    }

    private void populate() {
        population = new Individual[populationSize];
        for (int i = 0; i < populationSize; i++) {
            population[i] = new Individual(geneQuantity);
            population[i].randomise();
        }
    }

    private void displayPopulation() {
        System.out.println("Element\tBinary string\tFitness");
        System.out.println("=======\t=============\t=======");
        for (int i = 0; i < populationSize; i++) {
            System.out.print(i + "\t");
            System.out.print(population[i].printSubGenes() + "\t");
            System.out.println(population[i].fitness);
        }
        System.out.println("");
    }

    private void displayStats() {
        System.out.println("");
        System.out.println("\nTotal:\t" + fitness.getFitnessSum() + "\nAverage: "
                + fitness.getFitnessSum() / populationSize);
    }

    private void getBestAndMean(int i) {
        meanArray[i] = fitness.getFitnessSum() / populationSize;
        double bestIndvFitness = population[0].fitness;
        for (int j = 0; j < populationSize; j++) {
            double currentIndvFitness = population[j].fitness;
            bestIndvFitness = bestIndvFitness > currentIndvFitness ? population[j].fitness : bestIndvFitness;
        }
        bestArray[i] = bestIndvFitness;
    }

    public static void main(String[] args) {
        new Func2();
    }
}
