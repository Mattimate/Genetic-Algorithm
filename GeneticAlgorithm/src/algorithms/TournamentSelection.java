package algorithms;

import function3.Chromosome;
import java.util.Random;

public class TournamentSelection {

    private final int size;
    private final int[] num1;
    private final int[] num2;
    private final Random random = new Random();
    private Individual[] newIndividualArr;
    private Chromosome[] newChromosomeArr;
    private final IComparator cm;

    public TournamentSelection(int size, IComparator cm) {
        this.size = size;
        num1 = new int[size];
        num2 = new int[size];

        this.cm = cm;
    }

    public Individual[] run(Individual[] individualArr) {
        newIndividualArr = new Individual[size];
        generateUniqueNumbers();
        for (int i = 0; i < size; i++) {
            newIndividualArr[i] = doIndvSelection(individualArr, i);
        }
        return newIndividualArr;
    }

    public Chromosome[] runFunc3(Chromosome[] chromosomeArr) {
        newChromosomeArr = new Chromosome[size];
        generateUniqueNumbers();
        for (int i = 0; i < size; i++) {
            newChromosomeArr[i] = doFunc3Selection(chromosomeArr, i);
        }
        return newChromosomeArr;
    }

    private void generateUniqueNumbers() {
        for (int i = 0; i < size; i++) {
            num1[i] = random.nextInt(size);
            num2[i] = random.nextInt(size);
        }
    }

    private Individual doIndvSelection(Individual[] individualArr, int i) {
        if (cm.compareIndv(individualArr[num1[i]], individualArr[num2[i]]) == 1) {
            return individualArr[num1[i]];
        } else {
            return individualArr[num2[i]];
        }
    }

    private Chromosome doFunc3Selection(Chromosome[] chromosomeArr, int i) {
        if (cm.compareFunc3(chromosomeArr[num1[i]], chromosomeArr[num2[i]]) == 1) {
            return chromosomeArr[num1[i]];
        } else {
            return chromosomeArr[num2[i]];
        }
    }
}
