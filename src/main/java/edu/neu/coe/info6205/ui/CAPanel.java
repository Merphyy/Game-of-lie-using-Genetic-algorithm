package edu.neu.coe.info6205.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class CAPanel extends JPanel{
	
	private CACrystal flake = null;
	private String presentRule;


	public CAPanel(int dimension){
		flake = new CACrystal(dimension);
		System.out.println("panel initialised");
	}
	
	public CACrystal getFlake() {
		return flake;
	}

	public void setFlake(CACrystal flake) {
		this.flake = flake;
	}

	public String getPresentRule() {
		return presentRule;
	}

	public void setPresentRule(String presentRule) {
		this.presentRule = presentRule;
	}
	
	public void paint(Graphics g) {
		drawCanvas(g);
	}
	
	private int validColor(int colorVal) {
		if (colorVal > 255)
			colorVal = 255;
		if (colorVal < 0)
			colorVal = 0;
		return colorVal;
	}
	
	private Color getColorFor(int i,int j){
		
		   int redVal = validColor(i*5);
		   int greenVal = validColor(255-j*5);
		   int blueVal = validColor((j*5)-(i*2));
		   
		   return new Color(redVal, greenVal, blueVal);
	
	}
	
	private void drawCanvas(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		
		
		g2d.fillRect(0, 0, size.width, size.height);
		int height = flake.getFlake().length;
		
		/*
		 * ensure the snow prints at the center of the panel
		 */
		int leastOfTwoDime = size.width > size.height?size.height:size.width;
		int unitlength = leastOfTwoDime/height;
		int extralengthVertical = (size.height - unitlength*height)/2;
		int extralengthHorizontal = (size.width - unitlength*height)/2;

		for(int i=0; i < flake.getFlake().length; i++){
			for(int j=0; j < flake.getFlake()[i].length; j++){
				if(flake.getFlake()[i][j].getState() == false){
					
						g2d.setColor(Color.BLACK);
					
				}else{
					 g2d.setColor(getColorFor(i, j));
					 
				}
			
				     	g2d.fillRect((unitlength*j)+extralengthHorizontal, (unitlength*i)+extralengthVertical, unitlength, unitlength);
				
			}
		}
	}

	
}
