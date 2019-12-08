package edu.neu.coe.info6205.geneticAlgorithm;

public class InitRunnable implements Runnable {
	
	private int chromosomeLength;
	private Population pop;
	private int individualCount;
	
	

	public InitRunnable(int chromosomeLength, Population pop, int individualCount) {
		super();
		this.chromosomeLength = chromosomeLength;
		this.pop = pop;
		this.individualCount = individualCount;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Create an individual, initializing its chromosome to the given
		// length
		Individual individual = new Individual(chromosomeLength);
		// Add individual to population
		pop.population[individualCount] = individual;

	}

}
