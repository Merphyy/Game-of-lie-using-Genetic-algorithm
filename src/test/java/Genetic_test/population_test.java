package Genetic_test;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import edu.neu.coe.info6205.geneticAlgorithm.Individual;
import edu.neu.coe.info6205.geneticAlgorithm.Population;

public class population_test {
	
	@Test
	public void testpopulation() {
		Individual individual = new Individual(100);
		assertEquals(100,individual.getChromosomeLength());
	}
	
	@Test
	public void testGrowthRatemax() {
		Population population = new Population(5, 10);
		Arrays.sort(population.population,(Individual i1, Individual i2) -> (int)(i1.getGrowthRate() - i2.getGrowthRate()));;
		assertEquals(population.getGrowthRateMax(0),population.population[0]);
	}
	
	
	

	
}
