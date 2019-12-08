package edu.neu.coe.info6205.geneticAlgorithm;

public class Test {
	
	public static void main(String[] args) {
		Individual individual = new Individual(20);
		
		individual.show();
		
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.95, 2);
		
		double fitness = ga.calcFitness(individual);
		
		System.out.println(fitness);
		
		System.out.println("------");
		
		averageFitness();
	}
	
	public static void averageFitness() {
		
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.8, 2);

		// Initialize population
		Population population = ga.initPopulation(50);

		// Evaluate population
		ga.evalPopulation(population);
		
		
		double totalFitness = 0;
		for(Individual i : population.getIndividuals()) {
			//System.out.println(ga.calcFitness(i));
			totalFitness += ga.calcFitness(i);
			
		}
		
		double aveFitness = totalFitness/100;
		
		
		System.out.println("Average Fitness: " + aveFitness);
		
		
		
	}

}
