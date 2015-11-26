package algorithms;

import java.util.Random;

public class Mutator {

    private int randomIndex;
    private final int geneQuantity;
    
    public Mutator(int geneQuantity){
        this.geneQuantity = geneQuantity;
    }
    
    public Individual[] run(Individual[] individualArr, double probability) {
        Random random = new Random();
        
        Individual[] anIndividualArr = NewObjectCreator.createNewIndividualArr(individualArr, geneQuantity);

        for (int i = 0; i < individualArr.length; i++) {
            if (random.nextDouble() < probability) {
                randomIndex = random.nextInt(geneQuantity);
                anIndividualArr[i].gene[randomIndex] = 1 - anIndividualArr[i].gene[randomIndex];
            }
        }
        return anIndividualArr;
    }
}
