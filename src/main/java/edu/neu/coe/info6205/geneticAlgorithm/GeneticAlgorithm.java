package edu.neu.coe.info6205.geneticAlgorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The GeneticAlgorithm class is our main abstraction for managing the
 * operations of the genetic algorithm. This class is meant to be
 * problem-specific, meaning that (for instance) the "calcFitness" method may
 * need to change from problem to problem.
 * 
 * This class concerns itself mostly with population-level operations, but also
 * problem-specific operations such as calculating fitness, testing for
 * termination criteria, and managing mutation and crossover operations (which
 * generally need to be problem-specific as well).
 * 
 * Generally, GeneticAlgorithm might be better suited as an abstract class or an
 * interface, rather than a concrete class as below. A GeneticAlgorithm
 * interface would require implementation of methods such as
 * "isTerminationConditionMet", "calcFitness", "mutatePopulation", etc, and a
 * concrete class would be defined to solve a particular problem domain. For
 * instance, the concrete class "TravelingSalesmanGeneticAlgorithm" would
 * implement the "GeneticAlgorithm" interface. This is not the approach we've
 * chosen, however, so that we can keep each chapter's examples as simple and
 * concrete as possible.
 * 
 * @author bkanber
 *
 */
public class GeneticAlgorithm {
	
	
	
	private int populationSize;

	/**
	 * Mutation rate is the fractional probability than an individual gene will
	 * mutate randomly in a given generation. The range is 0.0-1.0, but is generally
	 * small (on the order of 0.1 or less).
	 */
	private double mutationRate;

	/**
	 * Crossover rate is the fractional probability that two individuals will "mate"
	 * with each other, sharing genetic information, and creating offspring with
	 * traits of each of the parents. Like mutation rate the rance is 0.0-1.0 but
	 * small.
	 */
	private double crossoverRate;

	/**
	 * Elitism is the concept that the strongest members of the population should be
	 * preserved from generation to generation. If an individual is one of the
	 * elite, it will not be mutated or crossover.
	 */
	private int elitismCount;
	
	

	public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount) {
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
		this.elitismCount = elitismCount;
		
	}
	
	




	/**
	 * Initialize population
	 * 
	 * @param chromosomeLength The length of the individuals chromosome
	 * @return population The initial population generated
	 */
	public Population initPopulation(int chromosomeLength) {
		// Initialize population
		Population population = new Population(this.populationSize, chromosomeLength);
		
		return population;
	}

	/**
	 * Calculate fitness for an individual.
	 * 
	 * In this case, the fitness score is very simple: it's the number of ones in
	 * the chromosome. Don't forget that this method, and this whole
	 * GeneticAlgorithm class, is meant to solve the problem in the "AllOnesGA"
	 * class and example. For different problems, you'll need to create a different
	 * version of this method to appropriately calculate the fitness of an
	 * individual.
	 * 
	 * @param individual the individual to evaluate
	 * @return double The fitness value for individual
	 */
	public double calcFitness(Individual individual) {

		// Track number of correct genes
		int correctGenes = 0;

		// Loop over individual's genes 遍歷individual(chromosome)數組，並記錄等於1的數量
		for (int i = 0; i < individual.getChromosomeLength(); i++) {
			// Add one fitness point for each "1" found
			for (int j = 0; j < individual.getChromosomeLength(); j++) {
				if (individual.getGene(i, j) == 1) {
					correctGenes += 1;
					
				}
			}

		}

		// Calculate fitness
		double fitness = (double) correctGenes;

		// Store fitness
		individual.setFitness(fitness);

		return fitness;
	}
	
	//Calculate Growth Rate
		public double calcGrowthRate(Individual i1, Individual i2) {
			double f1 = i1.getFitness();
			double f2 = i2.getFitness();
			
			return (f2-f1)/f1;
		}

	/**
	 * Evaluate the whole population
	 * 
	 * Essentially, loop over the individuals in the population, calculate the
	 * fitness for each, and then calculate the entire population's fitness. The
	 * population's fitness may or may not be important, but what is important here
	 * is making sure that each individual gets evaluated.
	 * 
	 * @param population the population to evaluate
	 */
	public void evalPopulation(Population population) {
		double populationFitness = 0;

		// Loop over population evaluating individuals and suming population
		// fitness
		for (Individual individual : population.getIndividuals()) {
			populationFitness += calcFitness(individual);
		}

		population.setPopulationFitness(populationFitness);
	}

	/**
	 * Check if population has met termination condition
	 * 
	 * For this simple problem, we know what a perfect solution looks like, so we
	 * can simply stop evolving once we've reached a fitness of one.
	 * 
	 * @param population
	 * @return boolean True if termination condition met, otherwise, false
	 */
	public boolean isTerminationConditionMet(Population population) {
		for (Individual individual : population.getIndividuals()) {
			// fitness > 多少就停止
			if (individual.getGrowthRate() >= 10000) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Select parent for crossover
	 * 
	 * @param population The population to select parent from
	 * @return The individual selected as a parent
	 */
	public Individual selectParent(Population population) {
		// Get individuals
		Individual individuals[] = population.getIndividuals();

		// Spin roulette wheel
		double populationFitness = population.getPopulationFitness();
		double rouletteWheelPosition = Math.random() * populationFitness;

		// Find parent
		double spinWheel = 0;
		for (Individual individual : individuals) {
			spinWheel += individual.getFitness();
			if (spinWheel >= rouletteWheelPosition) {
				return individual;
			}
		}
		return individuals[population.size() - 1];
	}

	/**
	 * Apply crossover to population
	 * 
	 * Crossover, more colloquially considered "mating", takes the population and
	 * blends individuals to create new offspring. It is hoped that when two
	 * individuals crossover that their offspring will have the strongest qualities
	 * of each of the parents. Of course, it's possible that an offspring will end
	 * up with the weakest qualities of each parent.
	 * 
	 * This method considers both the GeneticAlgorithm instance's crossoverRate and
	 * the elitismCount.
	 * 
	 * The type of crossover we perform depends on the problem domain. We don't want
	 * to create invalid solutions with crossover, so this method will need to be
	 * changed for different types of problems.
	 * 
	 * This particular crossover method selects random genes from each parent.
	 * 
	 * @param population The population to apply crossover to
	 * @return The new population
	 */
	public Population crossoverPopulation(Population population) {
		// Create new population
		Population newPopulation = new Population(population.size());

		// Loop over current population by fitness
		for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
			Individual parent1 = population.getFittest(populationIndex);

			// Apply crossover to this individual?
			if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
				// Initialize offspring
				Individual offspring = new Individual(parent1.getChromosomeLength());

				// Find second parent
				Individual parent2 = selectParent(population);

				// Loop over genome
				for (int i = 0; i < parent1.getChromosomeLength(); i++) {
					for (int j = 0; j < parent1.getChromosomeLength(); j++) {
						// Use half of parent1's genes and half of parent2's genes
						if (0.5 > Math.random()) {
							offspring.setGene(i, j, parent1.getGene(i, j));
						} else {
							offspring.setGene(i, j, parent2.getGene(i, j));
						}
					}
				}
				// 存入新solution前 先跑500次game of life
				stableGameOflife(offspring);
				// Add offspring to new population
				newPopulation.setIndividual(populationIndex, offspring);
			} else {
				// Add individual to new population without applying crossover
				// stableGameOflife(parent1);
				newPopulation.setIndividual(populationIndex, parent1);
			}
		}

		return newPopulation;
	}

	/**
	 * Apply mutation to population
	 * 
	 * Mutation affects individuals rather than the population. We look at each
	 * individual in the population, and if they're lucky enough (or unlucky, as it
	 * were), apply some randomness to their chromosome. Like crossover, the type of
	 * mutation applied depends on the specific problem we're solving. In this case,
	 * we simply randomly flip 0s to 1s and vice versa.
	 * 
	 * This method will consider the GeneticAlgorithm instance's mutationRate and
	 * elitismCount
	 * 
	 * @param population The population to apply mutation to
	 * @return The mutated population
	 */
	public Population[] mutatePopulation(Population population) {
		
		ExecutorService threadPool = Executors.newFixedThreadPool(20);
		// Initialize new population
		//用來存game of life之後的
		Population newPopulation = new Population(this.populationSize);
		//用來存game of life之前的
		Population previousPopulation = new Population(this.populationSize);

		// Loop over current population by fitness
		for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
			
			Runnable runnable = new MutateRunnable(populationIndex, population, newPopulation,previousPopulation);
			threadPool.execute(runnable);
		}
		threadPool.shutdown();
		
		try
		{
			// awaitTermination返回false即超时会继续循环，返回true即线程池中的线程执行完成主线程跳出循环往下执行，每隔10秒循环一次
			while (!threadPool.awaitTermination(10, TimeUnit.SECONDS));
			System.out.println("Wait For All Thread Finish!");
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("All Thread Finish");

		// Return mutated population
		Population[] populationArray = new Population[2];
		populationArray[0] = previousPopulation;
		populationArray[1] = newPopulation;
		
		return populationArray;
	}

	private Individual mutateIndividual(Individual individual) {
		// TODO Auto-generated method stub
		Individual nextIndividual = new Individual(individual.getChromosome());

		int[] neighbors = { 0, 1, -1 };

		int rows = individual.getChromosomeLength();
		int cols = individual.getChromosomeLength();

		// Loop over individual's genes
		for (int i = 0; i < individual.getChromosomeLength(); i++) {
			for (int j = 0; j < individual.getChromosomeLength(); j++) {
				// Skip mutation if this is an elite individual

				// -------check liveNeighbors
				int liveNeighbors = 0;
				for (int a = 0; a < 3; a++) {
					for (int b = 0; b < 3; b++) {

						if (!(neighbors[a] == 0 && neighbors[b] == 0)) {
							int r = (i + neighbors[a]);
							int c = (j + neighbors[b]);

							// Check the validity of the neighboring cell.
							// and whether it was originally a live cell.
							// The evaluation is done against the copy, since that is never updated.
							if ((r < rows && r >= 0) && (c < cols && c >= 0) && (individual.getGene(r, c) == 1)) {
								liveNeighbors += 1;
							}
						}
					}
				}
				// -------check liveNeighbors end

				// do mutation algorithm
				if ((int) (Math.random() * 12) % 4 == 0) {

					if ((individual.getGene(i, j) == 1)
							&& (liveNeighbors == 1 || liveNeighbors == 3 || liveNeighbors == 5)) {
						individual.setGene(i, j, 0);
					}
					if ((individual.getGene(i, j) == 0)
							&& (liveNeighbors == 2 || liveNeighbors == 4 || liveNeighbors == 6)) {
						nextIndividual.setGene(i, j, 1);
					}
					if ((individual.getGene(i, j) == 1) && (liveNeighbors == 2 || liveNeighbors == 4)) {
						nextIndividual.setGene(i, j, 1);
					}

				} else if ((int) (Math.random() * 12) % 4 == 1) {
					if ((individual.getGene(i, j) == 1)
							&& (liveNeighbors == 2 || liveNeighbors == 4 || liveNeighbors == 6)) {
						nextIndividual.setGene(i, j, 0);
					}
					if ((individual.getGene(i, j) == 0)
							&& (liveNeighbors == 1 || liveNeighbors == 3 || liveNeighbors == 5)) {
						nextIndividual.setGene(i, j, 1);
					}
					if ((individual.getGene(i, j) == 1)
							&& (liveNeighbors == 2 || liveNeighbors == 4 || liveNeighbors == 3)) {
						nextIndividual.setGene(i, j, 1);
					}

				} else if ((int) (Math.random() * 12) % 4 == 2) {

					if ((individual.getGene(i, j) == 1) && (liveNeighbors == 2 || liveNeighbors == 4)) {
						nextIndividual.setGene(i, j, 0);
					}
					if ((individual.getGene(i, j) == 0) && (liveNeighbors == 1 || liveNeighbors == 3)) {
						nextIndividual.setGene(i, j, 1);
					}
					if ((individual.getGene(i, j) == 1)
							&& (liveNeighbors == 2 || liveNeighbors == 4 || liveNeighbors == 3 || liveNeighbors == 5)) {
						nextIndividual.setGene(i, j, 1);
					}

				} else if ((int) (Math.random() * 12) % 4 == 3) {

					if ((individual.getGene(i, j) == 1) && (liveNeighbors == 3 || liveNeighbors == 5)) {
						nextIndividual.setGene(i, j, 0);
					}
					if ((individual.getGene(i, j) == 0)
							&& (liveNeighbors == 0 || liveNeighbors == 4 || liveNeighbors == 6)) {
						nextIndividual.setGene(i, j, 1);
					}
					if ((individual.getGene(i, j) == 1)
							&& (liveNeighbors == 2 || liveNeighbors == 3 || liveNeighbors == 5)) {
						nextIndividual.setGene(i, j, 1);
					}

				}
			}
			// mutation algorithm end
		} // end of double loop (end iterate one table)
		return nextIndividual;
	}

	public void stableGameOflife(Individual indivisua) {
		// 跑100次後的結果
		for (int count = 0; count < 1000; count++) {
			// System.out.println("generation: "+count);
			// show();
			gameOfLife(indivisua.getChromosome());

		}
	}

	public void gameOfLife(int[][] board) {

		// Neighbors array to find 8 neighboring cells for a given cell
		int[] neighbors = { 0, 1, -1 };

		int rows = board.length;
		int cols = board[0].length;

		// Create a copy of the original board
		int[][] copyBoard = new int[rows][cols];

		// Create a copy of the original board
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				copyBoard[row][col] = board[row][col];
			}
		}

		// Iterate through board cell by cell.
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {

				// For each cell count the number of live neighbors.
				int liveNeighbors = 0;

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {

						if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
							int r = (row + neighbors[i]);
							int c = (col + neighbors[j]);

							// Check the validity of the neighboring cell.
							// and whether it was originally a live cell.
							// The evaluation is done against the copy, since that is never updated.
							if ((r < rows && r >= 0) && (c < cols && c >= 0) && (copyBoard[r][c] == 1)) {
								liveNeighbors += 1;
							}
						}
					}
				}

				// Rule 1 or Rule 3
				if ((copyBoard[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
					board[row][col] = 0;
				}
				// Rule 4
				if (copyBoard[row][col] == 0 && liveNeighbors == 3) {
					board[row][col] = 1;
				}
			}
		}

	}

}
