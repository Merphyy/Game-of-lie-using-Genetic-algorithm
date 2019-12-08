package edu.neu.coe.info6205.geneticAlgorithm;

import org.apache.log4j.Logger;



/**
 * This is our main class used to run the genetic algorithm.
 * 
 * This case is one of the simplest problems we can solve: the objective is to
 * end up with an individual whose chromosome is all ones.
 * 
 * The simplicity of this problem makes the GeneticAlgorithm class'
 * "calcFitness" method very simple. We'll just count the number of ones in the
 * chromosome and use that as the fitness score. Similarly, the
 * "isTerminationConditionMet" method in the GeneticAlgorithm class for this
 * example is very simple: if the fitness score (ie, number of ones) is the same
 * as the length of the chromosome (ie, we're all ones), we're done!
 * 
 * @author bkanber
 *
 */
public class AllOnesGA {
	final static Logger logger = Logger.getLogger(AllOnesGA.class);
	
	
	public static void main(String[] args) {
		System.out.println("Start!");
		Individual[] SolutionCandidate = new Individual[10];
		// Create GA object
		//GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount)
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.5, 0.95, 0);

		// Initialize population
		Population population = ga.initPopulation(200);
		Population previosPopulation = null;

		// Evaluate population
		//ga.evalPopulation(population);

		// Keep track of current generation
		int generation = 0;

		/**
		 * Start the evolution loop
		 * 
		 * Every genetic algorithm problem has different criteria for finishing.
		 * In this case, we know what a perfect solution looks like (we don't
		 * always!), so our isTerminationConditionMet method is very
		 * straightforward: if there's a member of the population whose
		 * chromosome is all ones, we're done!
		 */
		int bestCandidateIndex = 0;
		Population[] populationArray = new Population[2];
		while (generation<10) {

			// Apply mutation
			populationArray = ga.mutatePopulation(population);
			previosPopulation = populationArray[0];
			population = populationArray[1];
			//獲取最好的candidate的index
			bestCandidateIndex = population.getGrowthRateMaxIndex();
			
			
			SolutionCandidate[generation] = previosPopulation.getIndividual(bestCandidateIndex);
			
			
			System.out.println("Generation: " + generation+"    Growth Rate Max: "+ population.getIndividual(bestCandidateIndex).getGrowthRate());
			System.out.println("Generation: " + generation+"   Pre Fitness: "+  population.getIndividual(bestCandidateIndex).getPreFitness());
			System.out.println("Generation: " + generation+"    Fitness: "+  population.getIndividual(bestCandidateIndex).getFitness());
			
			//輸出到log
			logger.info("Soution Growth Rate: "+ population.getIndividual(bestCandidateIndex).getGrowthRate());
			String result = "";
			int[][] solutionPattern = previosPopulation.getIndividual(bestCandidateIndex).getChromosome();
			for(int i=0; i<200;i++) {
				for(int j=0; j<200; j++) {
					result = result + solutionPattern[i][j];
				}
			}
			logger.info(result);
			//輸出完畢

			// Increment the current generation
			generation++;
		}
		int index = 0;
		for(int i=1; i<SolutionCandidate.length;i++ ) {
			if(SolutionCandidate[i].getGrowthRate()>SolutionCandidate[index].getGrowthRate()) {
				index = i;
			}
		}
		String result = "";
		int[][] solutionPattern = SolutionCandidate[index].getChromosome();
		for(int i=0; i<200;i++) {
			for(int j=0; j<200; j++) {
				result = result + solutionPattern[i][j];
			}
		}
		Individual solution = SolutionCandidate[index];
		System.out.println("Soution Growth Rate: "+ solution.getGrowthRate());
		System.out.println("Generation: " + generation+"   Pre Fitness: "+  population.getIndividual(bestCandidateIndex).getPreFitness());
		System.out.println("Generation: " + generation+"    Fitness: "+  population.getIndividual(bestCandidateIndex).getFitness());
		logger.info("Soution Growth Rate: "+ solution.getGrowthRate());
		logger.info("Generation: " + generation+"   Pre Fitness: "+  population.getIndividual(bestCandidateIndex).getPreFitness());
		logger.info("Generation: " + generation+"    Fitness: "+  population.getIndividual(bestCandidateIndex).getFitness());
		logger.info(result);
	}
}
