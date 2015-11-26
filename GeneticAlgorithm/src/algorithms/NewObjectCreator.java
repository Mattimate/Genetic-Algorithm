package algorithms;

import function3.Chromosome;

/**
 *
 * @author Matt
 */
public class NewObjectCreator {

    public static Chromosome[] createNewChromosomeArr(Chromosome[] chromosomeArr, int chromosomeSize, int geneQuantity) {
        Chromosome[] aChromosomeArr = new Chromosome[chromosomeSize];
        for (int i = 0; i < chromosomeSize; i++) {
            aChromosomeArr[i] = new Chromosome();
            aChromosomeArr[i].setIndividualArr(createNewIndividualArr(chromosomeArr[i].getIndividualArr(), geneQuantity));
        }
        return aChromosomeArr;
    }

    public static Individual[] createNewIndividualArr(Individual[] individualArr, int geneQuantity) {
        Individual[] anIndividualArr = new Individual[individualArr.length];
        for (int i = 0; i < anIndividualArr.length; i++) {
            anIndividualArr[i] = createNewIndividual(anIndividualArr[i], individualArr[i], geneQuantity);
        }
        return anIndividualArr;
    }

    public static Individual createNewIndividual(Individual anIndividual, Individual individual, int geneQuantity) {
        anIndividual = new Individual(geneQuantity);
        for (int j = 0; j < geneQuantity; j++) {
            anIndividual.gene[j] = individual.gene[j];
        }
        return anIndividual;
    }

}
