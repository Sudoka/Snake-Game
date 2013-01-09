/*
 * Written by: Rachel Helmstetter
 * Purpose: Creates Game Panel and controls gameplay  
 * Date Started: 12/13/12
 * Date Last Updated: 12/16/12
 */

import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Direction of snake's movement
enum Direction {left, right, up, down}

public class GamePanel extends JPanel {

	// Both Width and Height must be even multiples of blockWidth
	// for snake and apple to line up correctly
	public final int panelWidth = 500;
	public final int panelHeight = 400;
	private final int blockWidth = 10;
	
	// Delay controls speed of snake
	private final int DELAY = 100;
	
	private JButton startButton;
	private int highestScore;
	private int currentScore;
	private JLabel currentScoreLabel;
	private JLabel highScoreLabel;
	private JToolBar toolBar;
	private Grid grid;
	private Timer timer;
	private Snake snake;
	private Apple apple;
	private boolean ingame;
	
	// places buttons and labels above game area
	public GamePanel() {
		
		setLayout(new BorderLayout());
		setBackground(Color.gray);
		
		
		
		currentScore = 6;
		highestScore = 0;
		startButton = new JButton("Start");
		startButton.addActionListener(new ButtonListener());
		
		highScoreLabel = new JLabel("Longest Snake: " + highestScore);
		currentScoreLabel = new JLabel("Current Length: " + currentScore);
		grid = new Grid();
		
		
		toolBar = new JToolBar("Controls");
		toolBar.setLayout(new FlowLayout());
		toolBar.setFloatable(false);
		toolBar.add(highScoreLabel);
        toolBar.add(startButton);
        toolBar.add(currentScoreLabel);
        
        add(toolBar, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
        
        ingame = false;     
        
		
	}
	
	// Grid controls the game
	private class Grid extends JPanel {
		
		
		public Grid() {
			
			KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	        manager.addKeyEventDispatcher(new MyDispatcher());
			
			setFocusable(true);			
			
			Color c = new Color(198,255,255);
			setBackground(c);
			setPreferredSize(new Dimension(panelWidth,panelHeight));
			
			timer = new Timer(DELAY, new TimerListener());

	        snake = new Snake(panelWidth, panelHeight, blockWidth);
	        
	        apple = new Apple(panelWidth, panelHeight, blockWidth);
			
		}
		
		// Restarts game after game over when button is clicked
		public void restart() {
			snake = new Snake(panelWidth, panelHeight, blockWidth);
			apple = new Apple(panelWidth, panelHeight, blockWidth);
			ingame = true;
		}
		
		// draws snake and apple if ingame
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			
			snake.draw(g);
			if(ingame) {
				apple.draw(g);
			}
		}
		
		
		private class TimerListener implements ActionListener {
			
			public void actionPerformed(ActionEvent event) {
				snake.move();
				
				//if snake had collistion, game over
				if(!snake.noCollision()) {
					ingame = false;
					timer.stop();
				}
				
				// move apple if snake ate it and update score if necessary
				if(snake.ateApple(apple)) {
					apple.newLocation();
					if(snake.getLength() > currentScore) {
						currentScore = snake.getLength();
						currentScoreLabel.setText("Longest Snake: " + currentScore);
					}
				}
				repaint();
			}
		}
			
		
	}
	
	// changes direction of snake when arrow key pressed
	 private class MyDispatcher implements KeyEventDispatcher {

		 public boolean dispatchKeyEvent(KeyEvent event) {
			 if (event.getID() == KeyEvent.KEY_PRESSED) {
					if (ingame) {
						switch (event.getKeyCode()) {
						case KeyEvent.VK_UP:
							snake.changeDirection(Direction.up);
							break;
						case KeyEvent.VK_DOWN:
							snake.changeDirection(Direction.down);
							break;
						case KeyEvent.VK_LEFT:
							snake.changeDirection(Direction.left);
							break;
						case KeyEvent.VK_RIGHT:
							snake.changeDirection(Direction.right);
							break;
						}
						repaint();
					}
			 } else if (event.getID() == KeyEvent.KEY_RELEASED) {
				 
			 } else if (event.getID() == KeyEvent.KEY_TYPED) {
				 
			 }
			 return false;
		 }
	 }
	
	
	// starts game and restarts when not already ingame
	private class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			timer.start();
			if(!ingame) 
				grid.restart();
			ingame = true;
			
		}
	}
	
	

}
