package Genetic_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.neu.coe.info6205.geneticAlgorithm.GeneticAlgorithm;
import edu.neu.coe.info6205.geneticAlgorithm.Individual;
import edu.neu.coe.info6205.geneticAlgorithm.MutateRunnable;
import edu.neu.coe.info6205.geneticAlgorithm.Population;



public class GeneticA_test {
	@Test
	public void testinitPopulation() {
		GeneticAlgorithm ga = new GeneticAlgorithm(5, 0.5, 0.95, 0);
		int a = ga.initPopulation(10).getPopulationSize();
		assertEquals(a, 5);
		int b = ga.initPopulation(10).getIndividuals()[0].getChromosomeLength();
		assertEquals(b, 10);
	}

	@Test
	public void testcalcGrowthRate() {
		GeneticAlgorithm ga = new GeneticAlgorithm(5, 0.5, 0.95, 0);
		Individual i2 = new Individual(10);
		Individual i1 = new Individual(10);
		int a = (int) ga.calcGrowthRate(i1, i2);
		int b = 0;
		assertEquals(a, b);
	}

	@Test
	public void testevalPopulation() {
		GeneticAlgorithm ga = new GeneticAlgorithm(5, 0.5, 0.95, 0);

		Population population = ga.initPopulation(10);
		double a = 0;
		for (Individual individual : population.getIndividuals()) {
			a = ga.calcFitness(individual);
		}
		int c = (int) a;
		ga.evalPopulation(population);
		int b = (int) population.getPopulationFitness();
		assertEquals(b, c);

	}
	
	@Test
	public void testMutation() {
		int[][] c = {{0,0},{0,0}};
		Individual individual = new Individual(c);

		MutateRunnable mr = new MutateRunnable();
		Individual nextIndividual = mr.mutateIndividual(individual);

		int[][] nextC = nextIndividual.getChromosome();
		assertEquals(0, nextC[0][0]);
		assertEquals(0, nextC[0][1]);
		assertEquals(0, nextC[1][0]);
		assertEquals(0, nextC[1][1]);
		
		
		
	}
	
}
