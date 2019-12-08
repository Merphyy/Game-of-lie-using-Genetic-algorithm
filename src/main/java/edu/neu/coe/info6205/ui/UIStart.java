package edu.neu.coe.info6205.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//import ca.CAApp;

public class UIStart extends CAApp implements Observer{
	private CAPanel centerPanel = null;
	private CACrystalSet CrystalObj = null;
	private Logger log = Logger.getLogger(UIStart.class.getName());
	
	
	private HashMap<String, CARules> rules = new HashMap<String, CARules>(); 
	static CANorthPanel northPanel = null;
	
	//Constructor
	public UIStart() {
		
		super();
		
		log.info("Frame initinalized");
	    System.out.println("Frame initinalized");
		frame.setSize(800, 800);
		frame.setTitle("Game Of Life Project");
		centerPanel = new CAPanel(1);
		frame.add(centerPanel,BorderLayout.CENTER);
		
		CrystalObj = new CACrystalSet();
		CrystalObj.addObserver(this);
		RuleInit();
		setBtnlistener();
		frame.setVisible(true);
	}
	//***
	
	// APP starts
	public static void main(String[] args) {
		System.out.println("Game of life using Genetuc algorithm starts");
		UIStart obj = new UIStart();
	}
	
	
	    // put rules in hashmap for later use.
		private void RuleInit(){
			rules.put("Rule 1", new CARule1());
			rules.put("Rule 2", new CARule2());
			rules.put("Rule 3", new CARule3());
		
		}
		
		
		private void printFlake(CACrystal flake){
			centerPanel.setFlake(flake);
			System.out.println("action occured");
			frame.repaint();
			//centerPanel.repaint();
		}
		
		
		private void setBtnlistener(){
			northPanel.getStartBtn().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand() == "Start") {
						System.out.println("Start startAction");
						startAction();
						System.out.println("finish startAction");
						log.info("Start Event " + e);
					    System.out.println("Start Frozen");
					}
					else if(e.getActionCommand() == "Stop")
						stopAction();
					    log.info("Stop Event " + e);
				        System.out.println("Stop Frozen");
				}
			});
			northPanel.getPauseBtn().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand() == "Pause") {
						pauseAction();
					    log.info("Pause Event " + e);
				        System.out.println("Pause Frozen");
					}
					else if(e.getActionCommand() == "Continue")
						continueAction();
					    log.info("Pause Event " + e);
			            System.out.println("Continue to froze");
				}
			});
			northPanel.getResetBtn().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					resetActions();	
					log.info("Reset Event " + e);
		            System.out.println("Reset the event");
				}
			});

		

		}
	
		private void startAction() {
			String rule = (String)northPanel.getRuleBox().getSelectedItem();
			System.out.println("Rule: "+rule);
			//String stepCount = northPanel.getCellDime().getText();
			int stepCountInteger = 0;
			try{
				stepCountInteger = 1000;
				if(stepCountInteger >= 2){
						//Start Action
						CrystalObj.startAction(stepCountInteger, rules.get(rule),rule);
						centerPanel.setPresentRule(rule);
						northPanel.getStartBtn().setActionCommand("Stop");
						northPanel.getStartBtn().setText("Stop");
						northPanel.getPauseBtn().setEnabled(true);
						northPanel.getPauseBtn().setActionCommand("Pause");
						northPanel.getPauseBtn().setText("Pause");
						northPanel.getPauseBtn().setEnabled(true);
					
				}else{
					//dialog
					JOptionPane.showMessageDialog(centerPanel, "Crystal stages should between 2 and 280");
				}
			}catch (Exception e) {
				//dialog
				JOptionPane.showMessageDialog(centerPanel, "Invalid input, stages should be Integer");
			}
			
			
		}
		
		private void stopAction() {
			CrystalObj.stopAction();
			northPanel.getStartBtn().setActionCommand("Start");
			northPanel.getStartBtn().setText("Start");
			northPanel.getPauseBtn().setEnabled(false);
		
		}
		private void pauseAction() {
			CrystalObj.pauseAction();
			northPanel.getPauseBtn().setActionCommand("Continue");
			northPanel.getPauseBtn().setText("Continue");
			
		}
		private void continueAction() {
			CrystalObj.continueAction();
			northPanel.getPauseBtn().setActionCommand("Pause");
			northPanel.getPauseBtn().setText("Pause");
			
		}
		
		private void resetActions() {
			CrystalObj.resetAction();
			
			
			
			northPanel.getStartBtn().setActionCommand("Start");
			northPanel.getStartBtn().setText("Start");
			northPanel.getPauseBtn().setActionCommand("Pause");
			northPanel.getPauseBtn().setText("Pause");
			northPanel.getPauseBtn().setEnabled(false);
			
		}
		private void generationCompleted() {
			northPanel.getStartBtn().setActionCommand("Start");
			northPanel.getStartBtn().setText("Start");
			northPanel.getPauseBtn().setActionCommand("Pause");
			northPanel.getPauseBtn().setText("Completed");
			northPanel.getPauseBtn().setEnabled(false);
			
		
		}
	
	//Abstract method definition***
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
	
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	
	public JPanel getNorthPanel() {
		northPanel = new CANorthPanel();
		return northPanel.getPanel();
	}

	@Override
	public void update(Observable o, Object Crystal) {
		if(o.getClass().getName() == "edu.neu.coe.info6205.ui.CACrystalSet"){
			if(Crystal != null){
				CACrystal flake =  (CACrystal) Crystal;
				printFlake(flake);
			}else{
				generationCompleted();
			}
		}		
	}


}
