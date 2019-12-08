package edu.neu.coe.info6205.ui;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CANorthPanel{
	
	private JButton startBtn = null;
	private JButton resetBtn = null;
	private JButton pauseBtn = null;
	private JTextField cellDime = null;
	private JComboBox<String> ruleBox = null;
 
	private JPanel panel = null;
	private JPanel Controling_Panel = null;
	
	private JLabel ruletitle = null;
    private JLabel flakeSteps = null;

	public CANorthPanel() {

		panel = new JPanel(new GridLayout(1, 1));
		Controling_Panel = new JPanel(new GridLayout(1, 6));
		
				
		startBtn = new JButton("Start");
		pauseBtn = new JButton("Pause");
		resetBtn = new JButton("Reset");
		
		ruletitle = new JLabel("Choosing rules");
		flakeSteps = new JLabel("Total Steps =1000");
		//cellDime = new JTextField();
		ruleBox = new JComboBox<String>();
		//cellDime.setColumns(3);
		pauseBtn.setEnabled(false);
		flakeSteps.setHorizontalAlignment(SwingConstants.CENTER);
		
		ruleBox.addItem("Rule 1");
		ruleBox.addItem("Rule 2");
		//ruleBox.addItem("Rule 3");

			
		Controling_Panel.add(resetBtn,0,0);
		Controling_Panel.add(pauseBtn,1,0);
		Controling_Panel.add(startBtn,2,0);
		//Controling_Panel.add(cellDime,3,0);
		Controling_Panel.add(flakeSteps,4,0);
		Controling_Panel.add(ruleBox,5,0);
		Controling_Panel.add(ruletitle,6,0);
		Controling_Panel.setLayout(new FlowLayout());
		panel.add(Controling_Panel);
	
	}

	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JButton getStartBtn() {
		return startBtn;
	}

	public void setStartBtn(JButton startBtn) {
		this.startBtn = startBtn;
	}

	public JButton getPauseBtn() {
		return pauseBtn;
	}

	public void setPauseBtn(JButton pauseBtn) {
		this.pauseBtn = pauseBtn;
	}

	public JTextField getCellDime() {
		return cellDime;
	}

	public void setCellDime(JTextField cellDime) {
		this.cellDime = cellDime;
	}

	public JComboBox<String> getRuleBox() {
		return ruleBox;
	}

	public void setRuleBox(JComboBox<String> ruleBox) {
		this.ruleBox = ruleBox;
	}
	
	public JButton getResetBtn() {
		return resetBtn;
	}

	public void setResetBtn(JButton resetBtn) {
		this.resetBtn = resetBtn;
	}
	

	
}
