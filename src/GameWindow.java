/*
 * Written by: Rachel Helmstetter
 * Purpose: Creates a game of Snake
 * Date Started: 12/13/12
 */

import javax.swing.*;


public class GameWindow {

	
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame ("Snake: the Game");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setFocusable(true);	
		
		GamePanel panel = new GamePanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);	
		

	}

}
