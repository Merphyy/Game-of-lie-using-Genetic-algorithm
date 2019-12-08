package edu.neu.coe.info6205.ui;



public class CACell {
	public boolean state = false;
	public boolean ischange = false;
	
	
	

	public CACell() {
		this.state = false;
	
	}
	
	
	public CACell(boolean state) {
		
		this.state = state;
		
	}
	

//
public CACell(CACell c) {
	this.state = c.state;
	//this.ischange = c.ischange;
}


	


	
	
	
	public void setState(boolean state) {
		
		this.state = state;
	}
	public boolean getState() {
		return state;
	}
	
	
}
