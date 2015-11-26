package algorithms;

import function3.Chromosome;
import java.util.Random;

public class Crossover {

    private final int populationSize;
    private final int geneQuantity;
    private int index;
    private final int[] crossoverPnt;
    private Individual[] individualArr;
    private final Random random = new Random();
    private Chromosome[] chromosomeArr;
    private final int chromosomeSize;

    public Crossover(int populationSize, int chromosomeSize, int geneQuantity) {
        this.populationSize = populationSize;
        this.chromosomeSize = chromosomeSize;
        this.geneQuantity = geneQuantity;
        crossoverPnt = new int[populationSize - 1];
    }

    public Individual[] run(Individual[] individualArr, double probability) {
        this.individualArr = NewObjectCreator.createNewIndividualArr(individualArr, geneQuantity);

        createCrossoverPoints(populationSize);
        for (int i = 0; i < populationSize - 1; i++) {
            if (random.nextDouble() < probability) {
                index = i;
                doCrossover(index, crossoverPnt[i]);
            }
        }
        return this.individualArr;
    }

    public Chromosome[] runFunc3(Chromosome[] chromosome, int chromosomeSize, double probability) {
        this.chromosomeArr = NewObjectCreator.createNewChromosomeArr(chromosome, chromosomeSize, geneQuantity);

        createCrossoverPoints(populationSize);
        for (int i = 0; i < populationSize - 1; i++) {
            if (random.nextDouble() < probability) {
                index = i;
                doFunc3Crossover(index, crossoverPnt[i]);
            }
        }
        return chromosome;
    }

    private void createCrossoverPoints(int size) {
        for (int i = 0; i < size / 2; i++) {
            crossoverPnt[i] = random.nextInt(geneQuantity - 1) + 1;
        }
    }

    private void doCrossover(int index, int crossoverPoint) {
        for (int j = crossoverPoint; j < geneQuantity; j++) {
            individualArr = recombineIndividual(index, j);
        }
    }

    private Individual[] recombineIndividual(int i, int j) {
        int tempValue = individualArr[i].gene[j];
        individualArr[i].setGene(individualArr[i + 1].gene[j], j);
        individualArr[i + 1].setGene(tempValue, j);
        return individualArr;
    }

    private void doFunc3Crossover(int index, int crossoverPoint) {
        for (int j = crossoverPoint; j < chromosomeSize; j++) {
            chromosomeArr = recombineFunc3Chromosome(index, j);
        }
    }

    private Chromosome[] recombineFunc3Chromosome(int i, int j) {
        Individual tempValue = chromosomeArr[i].getIndividualArr()[j];
        chromosomeArr[i].setAnIndividual(chromosomeArr[i + 1].getIndividualArr()[j], j);
        chromosomeArr[i + 1].setAnIndividual(tempValue, j);
        return chromosomeArr;
    }
}
