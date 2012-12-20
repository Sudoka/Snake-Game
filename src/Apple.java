/*
 * Written by: Rachel Helmstetter
 * Purpose: Apple class and methods
 * Date Started: 12/13/12
 * Date Last Updated: 12/16/12
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;	// to place apples randomly


public class Apple {
	
	private Random r = new Random();
	
	private int x;
	private int y;
	private int panelWidth;
	private int panelHeight;
	private int blockWidth;
	private Color color;
	
	public Apple(int panelWidth, int panelHeight, int blockWidth) {
		this.blockWidth = blockWidth;
		this.panelHeight = panelHeight;
		this.panelWidth = panelWidth;
		x = (r.nextInt(panelWidth/blockWidth)) * blockWidth; 	//to ensure apple is within panel boundary and can line up with snake
		y = (r.nextInt(panelHeight/blockWidth)) * blockWidth;
		
		color = Color.GREEN;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	// move apple when snake has eaten it
	public void newLocation() {
		x = (r.nextInt(panelWidth/blockWidth)) * blockWidth; 
		y = (r.nextInt(panelHeight/blockWidth)) * blockWidth;
	}
	
	public void draw(Graphics page) {
		page.setColor(color);
		page.fillOval(x, y, blockWidth, blockWidth);
		
	}
}
