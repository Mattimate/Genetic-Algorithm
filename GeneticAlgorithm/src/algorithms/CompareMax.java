package algorithms;

import function3.Chromosome;

/**
 *
 * @author Matt
 */
public class CompareMax implements IComparator {

    @Override
    public double compareIndv(Individual i1, Individual i2) {
        return i1.fitness > i2.fitness ? 1 : i2.fitness > i1.fitness ? -1 : 0;
    }

    @Override
    public double compareFunc3(Chromosome c1, Chromosome c2) {
        return c1.fitness > c2.fitness ? 1 : c2.fitness > c1.fitness ? -1 : 0;
    }
}
