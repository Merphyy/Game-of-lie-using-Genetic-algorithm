package edu.neu.coe.info6205.geneticAlgorithm;

import org.apache.log4j.Logger;



/**
 * An "Individual" represents a single candidate solution. The core piece of
 * information about an individual is its "chromosome", which is an encoding of
 * a possible solution to the problem at hand. A chromosome can be a string, an
 * array, a list, etc -- in this class, the chromosome is an integer array. 
 * 
 * An individual position in the chromosome is called a gene, and these are the
 * atomic pieces of the solution that can be manipulated or mutated. When the
 * chromosome is a string, as in this case, each character or set of characters
 * can be a gene.
 * 
 * An individual also has a "fitness" score; this is a number that represents
 * how good a solution to the problem this individual is. The meaning of the
 * fitness score will vary based on the problem at hand.
 * 
 * @author bkanber
 *
 */
public class Individual {
	final static Logger logger = Logger.getLogger(Individual.class); 
	private int[][] chromosome;
	private double fitness = 0;
	private double preFitness = 0;
	private double growthRate = 0;
	
	
	public double getPreFitness() {
		return preFitness;
	}

	public void setPreFitness(double preFitness) {
		this.preFitness = preFitness;
	}



	

	/**
	 * Initializes individual with specific chromosome
	 * 
	 * @param chromosome
	 *            The chromosome to give individual
	 */
	public Individual(int[][] chromosome_raw) {
		// Create individual chromosome
		int[][] chromosome = new int[chromosome_raw.length][chromosome_raw.length];
		
		for(int i=0;i<chromosome.length;i++) {
			for(int j=0;j<chromosome.length;j++) {
				chromosome[i][j] = chromosome_raw[i][j];
			}
		}

		this.chromosome = chromosome;
	}

	/**
	 * Initializes random individual.
	 * 
	 * This constructor assumes that the chromosome is made entirely of 0s and
	 * 1s, which may not always be the case, so make sure to modify as
	 * necessary. This constructor also assumes that a "random" chromosome means
	 * simply picking random zeroes and ones, which also may not be the case
	 * (for instance, in a traveling salesman problem, this would be an invalid
	 * solution).
	 * 
	 * @param chromosomeLength
	 *            The length of the individuals chromosome
	 */
	public Individual(int chromosomeLength) {
		

		this.chromosome = new int[chromosomeLength][chromosomeLength];
		//隨機給值
		
//		for(int i=0; i<chromosomeLength; i++) {
//			for(int j=0; j<chromosomeLength; j++) {
//				if (0.5 > Math.random()) {
//					this.setGene(i, j, 1);
//				} else {
//					this.setGene(i, j, 0);
//				}
//			}
//		}
		
		
		//---隨機撒1000個
		
		for(int i=0; i<chromosomeLength; i++) {
			for(int j=0; j<chromosomeLength; j++) {
				setGene(i, j, 0);
			}
		}
		
		for(int count=0; count<800; count++) {
			int length = chromosomeLength;
			int i = (int) (40 * Math.random()+80);
			int j = (int) (40 * Math.random()+80);
			setGene(i, j, 1);
		}
		
		//---
		
		//進行game of life 1000次 獲得穩定狀態
		for(int count=0; count<1000; count++) {
			//System.out.println("generation: "+count);
			//show();
			gameOfLife(this.chromosome);
			
		}
		
		//計算Fitness
		calcFitness(this);
		
	}

	/**
	 * Gets individual's chromosome
	 * 
	 * @return The individual's chromosome
	 */
	public int[][] getChromosome() {
		return this.chromosome;
	}

	/**
	 * Gets individual's chromosome length
	 * 
	 * @return The individual's chromosome length
	 */
	public int getChromosomeLength() {
		return this.chromosome.length;
	}

	/**
	 * Set gene at offset
	 * 
	 * @param gene
	 * @param offset
	 * @return gene
	 */
	public void setGene(int i, int j, int gene) {
		this.chromosome[i][j] = gene;
	}

	/**
	 * Get gene at offset
	 * 
	 * @param offset
	 * @return gene
	 */
	public int getGene(int i, int j) {
		return this.chromosome[i][j];
	}

	/**
	 * Store individual's fitness
	 * 
	 * @param fitness
	 *            The individuals fitness
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	/**
	 * Gets individual's fitness
	 * 
	 * @return The individual's fitness
	 */
	public double getFitness() {
		return this.fitness;
	}
	
	
	
	//
	public double getGrowthRate() {
		return growthRate;
	}

	public void setGrowthRate(double growthRate) {
		this.growthRate = growthRate;
	}

	/**
	 * Display the chromosome as a string.
	 * 
	 * @return string representation of the chromosome
	 */
	public String toString() {
		String output = "";
		for (int gene = 0; gene < this.chromosome.length; gene++) {
			output += this.chromosome[gene];
		}
		return output;
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
	
	public void show() {
		
		for(int i=0; i<this.getChromosomeLength(); i++) {
			String result_log = "";
			for(int j=0; j<this.getChromosomeLength(); j++) {
				String result = chromosome[i][j] == 1 ? " * " : " . ";
				System.out.print(result);
				result_log = result_log + result;
			}
			System.out.println();
			logger.info(result_log);
		}

		System.out.println("\n\n"+"---------------------------------"+"\n\n");
	}
	
	
	
	public void gameOfLife(int[][] board) {

        // Neighbors array to find 8 neighboring cells for a given cell
        int[] neighbors = {0, 1, -1};

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
