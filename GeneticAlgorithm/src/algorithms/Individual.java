package algorithms;

import java.util.Random;

public class Individual {

    int[] gene;
    public double fitness;
    Random random = new Random();
    private final int geneQuantity;

    public Individual(int geneQauntity) {
        this.geneQuantity = geneQauntity;
        gene = new int[geneQauntity];
    }

    public void randomise() {
        for (int i = 0; i < geneQuantity; i++) {
            gene[i] = random.nextInt(2);
        }
    }

    public String printGenes() {
        String genes = "";
        for (int i = 0; i < gene.length; i++) {
            genes = genes + gene[i];
        }
        return genes;
    }

    public String printSubGenes() {
        String genes = "";
        for (int i = 0; i < gene.length; i++) {
            if (i == 0 || i == geneQuantity / 2) {
                if (gene[i] == 0) {
                    genes = genes + " +";
                } else {
                    genes = genes + " -";
                }
            } else {
                genes = genes + gene[i];
            }
        }
        return genes;
    }

    public String printModGenes() {
        String genes = "";
        for (int i = 0; i < gene.length; i++) {
            if (i == 0) {
                if (gene[0] == 0) {
                    genes += "+";
                } else if (gene[0] == 1) {
                    genes += "-";
                }
            } else if (i == 1) {
                genes += " +" + gene[1] + " ";
            } else {
                genes = genes + gene[i];
            }
        }
        return genes;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getGeneQuantity() {
        return geneQuantity;
    }

    public int getGene(int index) {
        return gene[index];
    }

    public void setGene(int geneValue, int index) {
        this.gene[index] = geneValue;
    }

    public int[] getGenes() {
        return gene;
    }

    void setGenes(int[] gene) {
        this.gene = gene;
    }
}
