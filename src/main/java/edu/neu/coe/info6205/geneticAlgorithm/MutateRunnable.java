package edu.neu.coe.info6205.geneticAlgorithm;

public class MutateRunnable implements Runnable{
	
	private int populationIndex;
	private Population population;
	private Population newPopulation;
	private Population previousPopulation;

	
	public MutateRunnable() {
		// TODO Auto-generated constructor stub
	}

	public MutateRunnable(int populationIndex, Population population, Population newPopulation,Population previousPopulation) {
		
		this.populationIndex = populationIndex;
		this.population = population;
		this.newPopulation = newPopulation;
		this.previousPopulation = previousPopulation;
		
		
	}


	@Override
	public void run() {
		
		Individual individual = population.getIndividual(populationIndex);
		
		//individual.setPreFitness(individual.getFitness());
		//進行mutate
		
		Individual nextIndividual = mutateIndividual(individual);
		
		//做完mutation後進行計算fitness
		double preFitness = calcFitness(nextIndividual);
		//把mutatin前的fitness傳給preFitness
		nextIndividual.setPreFitness(nextIndividual.getFitness());
		
		//----------
		//保存 Game of life 之前的資料
		
		
		
		
		
		
		Individual preIndividual = new Individual(nextIndividual.getChromosome());
		preIndividual.setPreFitness(preFitness);
		previousPopulation.setIndividual(populationIndex, preIndividual);

		
		//------我是 Game Of life
		// 存入新solution前 先跑1000次game of life
		stableGameOflife(nextIndividual);
		//-----game of life 1000次結束
		
		
		//計算新的fitness 並設定好fitness
		double fitness = calcFitness(nextIndividual);
		nextIndividual.setFitness(fitness);
		//計算growthRate 並存入individaul
		double growthRate = calcGrowthRate(nextIndividual.getPreFitness(), nextIndividual.getFitness());
		nextIndividual.setGrowthRate(growthRate);
		// Add individual to population
		//||||||||
		newPopulation.setIndividual(populationIndex, nextIndividual);
		
		previousPopulation.getIndividual(populationIndex).setGrowthRate(growthRate);
		
	}
	
	public double calcGrowthRate(double f1, double f2) {
	
		return (f2-f1)/f1;
	}
	
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

	
	
	public Individual mutateIndividual(Individual individual) {
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
							&& (liveNeighbors == 2 || liveNeighbors == 4 || liveNeighbors == 6||liveNeighbors == 7)) {
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
//					if ((individual.getGene(i, j) == 0)
//							&& (liveNeighbors == 1 || liveNeighbors == 3 || liveNeighbors == 5)) {
//						nextIndividual.setGene(i, j, 1);
//					}
					if ((individual.getGene(i, j) == 1)
							&& (liveNeighbors == 1 || liveNeighbors == 3 || liveNeighbors == 5)) {
						nextIndividual.setGene(i, j, 1);
					}

				} else if ((int) (Math.random() * 12) % 4 == 2) {

					if ((individual.getGene(i, j) == 1) && (liveNeighbors == 2 || liveNeighbors == 4)) {
						nextIndividual.setGene(i, j, 0);
					}
//					if ((individual.getGene(i, j) == 0) && (liveNeighbors == 1 || liveNeighbors == 3)) {
//						nextIndividual.setGene(i, j, 1);
//					}
					if ((individual.getGene(i, j) == 1)
							&& (liveNeighbors == 2 || liveNeighbors == 4 || liveNeighbors == 3 || liveNeighbors == 5)) {
						nextIndividual.setGene(i, j, 1);
					}

				} else if ((int) (Math.random() * 12) % 4 == 3) {

					if ((individual.getGene(i, j) == 1) && (liveNeighbors == 3 || liveNeighbors == 5)) {
						nextIndividual.setGene(i, j, 0);
					}
					if ((individual.getGene(i, j) == 0) && (liveNeighbors == 4 || liveNeighbors == 6)) {
						
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
