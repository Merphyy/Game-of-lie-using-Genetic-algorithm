package edu.neu.coe.info6205.ui;



public class CACrystal {
		
	
	
	//for printing on UI APP
	private CACell[][] flake;
	
	public CACrystal(int dimension){
		flake = new CACell[dimension][dimension];
		for (int i = 0; i< dimension ; i ++){
			for (int j = 0; j< dimension ; j ++){
				flake[i][j] = new CACell(false);
			}
		}
	}

	public CACell[][] getFlake() {
		return flake;
	}

	public void setFlake(CACell[][] flake) {
		this.flake = flake;
	}
	
	
	
	
	
    static int m = 31;
	
	public CACrystal() {
		flake = new CACell[m][m];
		for(int i=0;i<m;i++) 
			for(int j=0;j<m;j++) {
				flake[i][j] = new CACell();
			}
	}
	
	public CACrystal(CACrystal c) {
		flake = new CACell[m][m];
		for(int i=0;i<m;i++) 
			for(int j=0;j<m;j++) {
				this.flake[i][j] = new CACell(c.flake[i][j]);
			}
	}
	
	public void update(CACrystal c) {
		flake = new CACell[m][m];
		for(int i=0;i<m;i++) 
			for(int j=0;j<m;j++) {
				this.flake[i][j] = new CACell(c.flake[i][j]);
			}
	}
	
	public static void printcell(CACrystal c) {
		for(int i=0;i<m;i++) {
			for(int j=0;j<m;j++) {
				if(c.flake[i][j].state== true) System.out.print("*");
				if(c.flake[i][j].state== false) System.out.print(" ");
			}
			System.out.println("");
		}
	}
	
	
	
	

}
