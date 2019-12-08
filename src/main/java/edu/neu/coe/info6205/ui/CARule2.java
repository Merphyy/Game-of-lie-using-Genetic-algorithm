package edu.neu.coe.info6205.ui;

/*
 * The cell of rule 1 would melt when two of 6 neighbors are frozen,and would froze on the rest of the case
 */

public class CARule2 extends CARules { 

	@Override
	public CACrystal getNextFlake(CACrystal previousFlake) {
		CACrystal nextFlake = new CACrystal(previousFlake.getFlake()[0].length);
		
		int[] neighbors = {0, 1, -1};
		int rows = nextFlake.getFlake().length;
		int cols = nextFlake.getFlake().length;
		
		for(int i = 0 ; i < nextFlake.getFlake().length; i++ ){		
			
			for (int j = 0; j < nextFlake.getFlake()[0].length ; j++) {
				int liveNeighbors = 0;
				//get the neighbor of the center cell
//				boolean top = previousFlake.getFlake()[i-1][j].getState();
//				boolean topleft = previousFlake.getFlake()[i-1][j-1].getState();
//				boolean topRight = previousFlake.getFlake()[i-1][j+1].getState();
//				boolean bottom = previousFlake.getFlake()[i+1][j].getState();
//				boolean bottomLeft = previousFlake.getFlake()[i+1][j-1].getState();
//				boolean bottomRight = previousFlake.getFlake()[i+1][j+1].getState();
//				boolean left = previousFlake.getFlake()[i][j-1].getState();
//				boolean right = previousFlake.getFlake()[i][j+1].getState();
				
				
				
				
				
				//int value = r1(top, topleft, topRight, bottom, bottomLeft, bottomRight, left, right);
			
				
				
				
//				if((previousFlake.getFlake()[i][j].getState() == true) && (value<2)||(value>3)){
//					nextFlake.getFlake()[i][j].setState(false);
//					
//				}
//				if((previousFlake.getFlake()[i][j].getState() == false )&& (value == 3)){
//					nextFlake.getFlake()[i][j].setState(true);
//					
//				}
//				if((previousFlake.getFlake()[i][j].getState() == true )&& (value == 3||value == 2)){
//					nextFlake.getFlake()[i][j].setState(true);
//					
//				}
//				if((previousFlake.getFlake()[i][j].getState() == false) && (value<=3)&&(value>=1)){
//					nextFlake.getFlake()[i][j].setState(true);
//					
//				}
				 for (int ii = 0; ii < 3; ii++) {
	                    for (int jj = 0; jj < 3; jj++) {

	                        if (!(neighbors[ii] == 0 && neighbors[jj] == 0)) {
	                            int r = (i + neighbors[ii]);
	                            int c = (j + neighbors[jj]);

	                            // Check the validity of the neighboring cell.
	                            // and whether it was originally a live cell.
	                            // The evaluation is done against the copy, since that is never updated.
	                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (previousFlake.getFlake()[r][c].getState() == true)) {
	                                liveNeighbors += 1;
	                            }
	                        }
	                    }
	                }

	                // Rule 1 or Rule 3
	                if ((previousFlake.getFlake()[i][j].getState() == true) && (liveNeighbors < 2 || liveNeighbors > 3)) {
	                	nextFlake.getFlake()[i][j].setState(false);
	                }else if (previousFlake.getFlake()[i][j].getState() == false && liveNeighbors == 3) {
	                	nextFlake.getFlake()[i][j].setState(true);
	                }else{
	                	nextFlake.getFlake()[i][j].setState(previousFlake.getFlake()[i][j].getState());
	                	
	                }
	                
	                
				
				
				
				
				
				
			}
		}
		
		return nextFlake;
	}
	

}
