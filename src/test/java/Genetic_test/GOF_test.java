package Genetic_test;



import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import edu.neu.coe.info6205.geneticAlgorithm.Individual;



public class GOF_test {
	@Test
	public void testgameoflife() {
		Individual individual = new Individual(100);
		int[][] a = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
		int[][] b = {{0,0,0},{1,0,1},{0,1,1},{0,1,0}};
		individual.gameOfLife(a);
		assertTrue(Arrays.deepEquals(a, b));
	}
}
