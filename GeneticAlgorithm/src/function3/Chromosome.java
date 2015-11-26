/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function3;

import algorithms.Individual;

/**
 *
 * @author m3-gordon
 */
public class Chromosome {
// The chromosome is similar to the individual but instead of one individual for each chromosome 
// it is now an array of individuals for each chromomsome. This will only be used by function 3

    private Individual[] individualArr;
    public double fitness;

    public void setIndividualArr(Individual[] individualArr) {
        this.individualArr = individualArr;
    }

    public void setAnIndividual(Individual individual, int index) {
        this.individualArr[index] = individual;
    }

    public Individual[] getIndividualArr() {
        return individualArr;
    }

    void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
