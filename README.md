# Game-of-lie-using-Genetic-algorithm
This is the final project of the course Info 6205 which implemented the Conway Game Of Life using genetic algorithm.
Conway game of life:
Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
1.Any live cell with fewer than two live neighbors dies, as if caused by under-population.
2.Any live cell with two or three live neighbors lives on to the next generation.
3.Any live cell with more than three live neighbors dies, as if by over-population..
4.Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

In the project, our team implemented the UI which you can see the progress vividly by running the code in the src/main/java/edu/neu/coe/info6205/ui/UIStart.java.
And there is also console display without using the UI by running the code in the src/main/java/edu/neu/coe/info6205/geneticAlgorithm/AllOnesGA.java.
In this program, our team also implemented the multithread to accelerate the progress of the outcome.
As for the testing part, we use unit test to do the testing where you can find all the code in src/test/java/Genetic_test.
