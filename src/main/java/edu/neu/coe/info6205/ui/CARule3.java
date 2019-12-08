package edu.neu.coe.info6205.ui;


/*
 * The cell of rule 3 would not melt and would grow only under the condition when one of the neighbors is frozen
 */

public class CARule3 extends CARules{//extended class for rule Hex-3

	@Override
	public CACrystal getNextFlake(CACrystal previousFlake) {
		CACrystal nextFlake = new CACrystal(previousFlake.getFlake()[0].length);
		
		for(int i = 1 ; i < nextFlake.getFlake().length -1 ;i++ ){
			int position = i%2;    // switch the role for one unit
			for (int j = 1; j < nextFlake.getFlake()[i].length -1; j++) {
				
				//get the 8 neighbors of a cell
				boolean top = previousFlake.getFlake()[i-1][j].getState();
				boolean topLeft = previousFlake.getFlake()[i-1][j-1].getState();
				boolean topRight = previousFlake.getFlake()[i-1][j+1].getState();
				boolean bottom = previousFlake.getFlake()[i+1][j].getState();
				boolean bottomLeft = previousFlake.getFlake()[i+1][j-1].getState();
				boolean bottomRight = previousFlake.getFlake()[i+1][j+1].getState();
				boolean left = previousFlake.getFlake()[i][j-1].getState();
				boolean right = previousFlake.getFlake()[i][j+1].getState();
				
				 //tells if its even column or odd
				boolean returnedValue = r3(top, topLeft, topRight, bottom, bottomLeft, bottomRight, left, right, position);
				
				if(returnedValue == true){
					
				
					if(i == 1 || j == 1){
						System.out.println("breaking");
						return previousFlake;
					}
				}
				if(previousFlake.getFlake()[i][j].getState() == true){
					nextFlake.getFlake()[i][j].setState(true);
					
				}
				else{
					nextFlake.getFlake()[i][j].setState(returnedValue);
				
				}
			}
		}
		
		return nextFlake;
	}
	

}
