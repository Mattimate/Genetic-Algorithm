/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import function3.Chromosome;

/**
 *
 * @author Matt
 */
public interface IComparator {
    double compareIndv(Individual i1, Individual i2);
    double compareFunc3(Chromosome i1, Chromosome i2);
}
