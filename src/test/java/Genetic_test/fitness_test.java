package Genetic_test;




import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import edu.neu.coe.info6205.geneticAlgorithm.GeneticAlgorithm;
import edu.neu.coe.info6205.geneticAlgorithm.Individual;
import edu.neu.coe.info6205.geneticAlgorithm.Population;




public class fitness_test {
	@Test
	public void testcalfitness() {
		GeneticAlgorithm ga = null;
		Individual individual = new Individual(4);
		int a = (int)individual.getFitness();
		int b = (int)ga.calcFitness(individual);
		assertEquals(b,a);
	}
	
	@Test
	public void testGetfittest() {
		Population population = new Population(5, 10); 
		Arrays.sort(population.population, (Individual i1, Individual i2) -> (int)(i1.getFitness() - i1.getFitness()) );
		assertEquals(population.getFittest(0), population.population[0]);
		
	}
}
