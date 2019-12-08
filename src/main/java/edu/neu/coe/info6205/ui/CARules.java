package edu.neu.coe.info6205.ui;

import java.security.interfaces.RSAKey;
import java.util.Random;

import edu.neu.coe.info6205.geneticAlgorithm.Driver;
import edu.neu.coe.info6205.geneticAlgorithm.ReadSolution;




public abstract class CARules {

	
	//Set the abstract method for different rules to extend
	abstract public CACrystal getNextFlake(CACrystal previousFlake);
	
	
	//Initialize the flake: set the center spot of the flake to be true
	public CACrystal initializeFlake(String rule) {
		System.out.println(rule);
		Random random = new Random();
		int total_pixel = 200;
		CACrystal flake = new CACrystal(total_pixel);
//		flake.getFlake()[total_pixel/2][total_pixel/2].setState(true);
//		flake.getFlake()[total_pixel/2][total_pixel/2+1].setState(true);
//		flake.getFlake()[total_pixel/2][total_pixel/2+2].setState(true);
//		flake.getFlake()[total_pixel/2+1][total_pixel/2+2].setState(true);
//		flake.getFlake()[total_pixel/2+2][total_pixel/2+1].setState(true);
		
		
//		flake.getFlake()[total_pixel/2-7][total_pixel/2-8].setState(true);
//		flake.getFlake()[total_pixel/2+2][total_pixel/2+3].setState(true);
//		flake.getFlake()[total_pixel/2+4][total_pixel/2+4].setState(true);
//		flake.getFlake()[total_pixel/2-2][total_pixel/2].setState(true);
//		flake.getFlake()[total_pixel/2][total_pixel/2].setState(true);
//		flake.getFlake()[total_pixel/2][total_pixel/2].setState(true);
		int[][] board = null;
		if(rule.equals("Rule 1")) {
			ReadSolution rs = new ReadSolution();
			board = rs.read();
		}else if(rule.equals("Rule 2")){
			
			board = Driver.solutionMethod();
		}
		
		for(int i=0;i<total_pixel;i++) {
			for(int j=0;j<total_pixel;j++) {
//				if(random.nextInt(10)==1) {
//					flake.getFlake()[i][j].setState(true);
//				}
				flake.getFlake()[i][j].setState(board[i][j]==1 ? true : false);
			}
		}
		
		

		return flake;
	}
	
	public static int r1(boolean top,boolean topL,boolean topR,boolean bottom,boolean bottomL,boolean bottomR,boolean left,boolean right) {
		   
			int cnt = 0;
			
			if(topL == true){
				cnt++;
			}
			if(bottomL == true){
				cnt++;
			}
			if(topR == true){
				cnt++;
			}
			if(bottomR == true){
				cnt++;
			}
		
			if(top == true){
				cnt++;
			}
			if(bottom == true){
				cnt++;
			}
			if(left == true){
				cnt++;
			}
			if(right == true){
				cnt++;
			}
			
		
		return cnt;
	}
	public static int r2(boolean top,boolean topL,boolean topR,boolean bottom,boolean bottomL,boolean bottomR,boolean left,boolean right) {
		   
		int cnt = 0;
		
		if(topL == true){
			cnt++;
		}
		if(bottomL == true){
			cnt++;
		}
		if(topR == true){
			cnt++;
		}
		if(bottomR == true){
			cnt++;
		}
	
		if(top == true){
			cnt++;
		}
		if(bottom == true){
			cnt++;
		}
		if(left == true){
			cnt++;
		}
		if(right == true){
			cnt++;
		}
		
	
	return cnt;
}
	
	public static boolean rm(boolean top,boolean topL,boolean topR,boolean bottom,boolean bottomL,boolean bottomR,boolean left,boolean right,int position) {
		
		boolean value = false;
	    int cnt = 0;
		/*
		 * To judge if it is odd row or even row
		 */
	   
	if(position == 0){
		if(topR == true){
			cnt++;
		}
		if(bottomR == true){
			cnt++;
		}
		
	}else{
		if(topL == true){
			cnt++;
		}
		if(bottomL == true){
			cnt++;
		}
	}
	if(top == true){
		cnt++;
	}
	if(bottom == true){
		cnt++;
	}
	if(left == true){
		cnt++;
	}
	if(right == true){
		cnt++;
	}
	if(cnt == 1||cnt ==3||cnt == 4||cnt ==5){
		value = true; 	
	}

    return value;

}
	
	public static boolean r3(boolean top,boolean topL,boolean topR,boolean bottom,boolean bottomL,boolean bottomR,boolean left,boolean right,int position) {
		
    boolean value = false;
	int cnt = 0;
	/*
	 * To judge if it is odd row or even row
	 */
	
	if(position == 0){
		
		if(bottomR == true){
			cnt++;
		}
		if(topR == true){
			cnt++;
		}
		
	}else{
		if(topL == true){
			cnt++;
		}
		if(bottomL == true){
			cnt++;
		}
	}
	if(top == true){
		cnt++;
	}
	if(bottom == true){
		cnt++;
	}
	if(left == true){
		cnt++;
	}
	if(right == true){
		cnt++;
	}
	if(cnt == 1){
		value = true; 	
	}

return value;
}
	
}
