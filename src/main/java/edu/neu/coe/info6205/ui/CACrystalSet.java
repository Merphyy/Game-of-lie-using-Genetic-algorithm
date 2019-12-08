package edu.neu.coe.info6205.ui;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;


public class CACrystalSet extends Observable{

	private ArrayList<CACrystal> FlakeCollection = new ArrayList<CACrystal>();

	private static boolean pause = false;

	private int j;
	
	private boolean inProccess = false;
	private Timer myTimer = null;	

	public CACrystalSet() {
		
	}
	

	
	public ArrayList<CACrystal> getCacrystalList() {
		return FlakeCollection;
	}
	
	//public action method, including start, stop, pause, reset 
	
	public void stopAction() {
		if(myTimer != null){
			myTimer.cancel();
			myTimer = null;
		}
	}
	
	public void pauseAction() {
		pause = true;
	}
	public void continueAction() {
		pause = false;
	}
	//ruleClass
	public void startAction(int stepCount, CARules ruleClass, String rule) {
      
		//for getting 
		j = 0;
		
		
		pause = false;
		if(myTimer != null){
			myTimer.cancel();
			myTimer = null;
		}
		FlakeCollection.clear();
		
		/*  the initial 2D arraylist of CACrystal should be 2 times of 
		 * the input steps to keep the snow flake print from the center
		 * and take the entire space of the panel. 
		 */
		FlakeCollection.add(ruleClass.initializeFlake(rule));  
		
		setChanged();
		notifyObservers(FlakeCollection.get(0));
		
		
		myTimer = new Timer();
		myTimer.scheduleAtFixedRate(
			new TimerTask() {
				
				@Override
				public void run() {
					if(inProccess == false){
						if(!pause){    //check if the generation is paused
							
							j++;  // count the step

							    //start processing
								inProccess = true;//prevents any calls from timer while painting the snow flake
								FlakeCollection.add(ruleClass.getNextFlake(FlakeCollection.get(j-1))); //collector
								
								setChanged();
								notifyObservers(FlakeCollection.get(j));
								
								
								inProccess = false;
							
							//kill the Timer when finished
							if(j == stepCount){
								
								if(myTimer != null){
									myTimer.cancel();
									myTimer = null;
									setChanged();
									notifyObservers();  //inform the completion of the printing snowflake process
								}
								
							}
							
						}
					}

				}
			},100, 100);// pause 0.05 second to start and each action's interval is 0.05 second.

	}
	

	/*
	 * kill the Timer and reset the frame;
	 */
	
	
	public void resetAction() {
		if(myTimer != null){
			myTimer.cancel();
			myTimer = null;
		}
		CACrystal blankFlake = new CACrystal(1);  // reinitialize the panel with a [1][1] array , which would be shown black on the panel.
		setChanged();
		notifyObservers(blankFlake);
	}
	


}
