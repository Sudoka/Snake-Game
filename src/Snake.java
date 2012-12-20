/*
 * Written by: Rachel Helmstetter
 * Purpose: Snake class and methods
 * Date Started: 12/13/12
 * Date Last Updated: 12/16/12
 */

import java.awt.*;
import java.util.*;


public class Snake {

	
	private final int startLength = 6;
	private int panelHeight;
	private int panelWidth;
	private int blockWidth;
	private int currentLength;
	private Color color = Color.blue;	//color of snake
	private ArrayList<Block> blocks;	//body of snake is made of blocks
	private int firstX;
	private int firstY;
	private Direction currentDirection;
	
	
	public Snake(int panelWidth, int panelHeight, int blockWidth) {
		currentLength = startLength;
		this.panelHeight = panelHeight;
		this.panelWidth = panelWidth;
		this.blockWidth = blockWidth;
		firstX = panelWidth/2;
		firstY = panelHeight/2;	
		blocks = new ArrayList<Block>(currentLength);
		for (int i = 0; i < startLength; i++) {
			blocks.add(new Block(firstX+i*blockWidth, firstY, color));
		}
		currentDirection = Direction.left;		// snake starts moving in the left direction
	}
	
	public void draw(Graphics page) {
		for(int i = 0; i < blocks.size(); i++) {
			blocks.get(i).draw(page);
		}
	}
	
	// moves the snake in a way that each block follows the block before it and 
	// only the head moves in the most recent direction
	public void move() {
		for(int i = blocks.size()-1; i > 0; i--) {
			blocks.get(i).x = blocks.get(i-1).x;
			blocks.get(i).y = blocks.get(i-1).y;
		}
		if (currentDirection == Direction.left) {
			blocks.get(0).x -= blockWidth;
		}
		else if(currentDirection == Direction.right) {
			blocks.get(0).x += blockWidth;
		}
		else if(currentDirection == Direction.up){
			blocks.get(0).y -= blockWidth;
		}
		else {
			blocks.get(0).y += blockWidth;
		}
	}
	
	// changes the direction of the snake if different than current
	// Also: prevents change of direction is opposite of current so snake
	// 		 does not move over itself, causing game over
	public void changeDirection(Direction newDirection) {
		if (currentDirection != newDirection && !Opposite(newDirection))
			currentDirection = newDirection;
	}
	
	// checks if newDirection is opposite of current
	private boolean Opposite(Direction newDirection) {
		boolean result = false;
		if(currentDirection == Direction.up && newDirection == Direction.down)
			result = true;
		else if(currentDirection == Direction.down && newDirection == Direction.up)
			result = true;
		else if(currentDirection == Direction.left && newDirection == Direction.right)
			result = true;
		else if(currentDirection == Direction.right && newDirection == Direction.left)
			result = true;
		return result;
	}
	
	// checks that 
	public boolean noCollision() {
		boolean result = true;

		for (int i = blocks.size()-1; i > 0; i--) {

			if ((blocks.get(0).x == blocks.get(i).x) && (blocks.get(0).y == blocks.get(i).y)) {
				result = false;
			}
		}

		if (blocks.get(0).y > panelHeight) {
			result = false;
		}

		if (blocks.get(0).y < 0) {
			result = false;
		}

		if (blocks.get(0).x > panelWidth) {
			result = false;
		}

		if (blocks.get(0).x < 0) {
			result = false;
		}

		return result;
	}
	
	public boolean ateApple(Apple apple) {
		boolean result = false;
		if(blocks.get(0).x == apple.getX() && blocks.get(0).y == apple.getY()) {
			result = true;
			blocks.add(0, new Block(apple.getX(), apple.getY(), color));			
		}
		return result;
	}
	
	public int getLength() {
		return blocks.size();
	}
	
 	private class Block {
		private Color color;
		private int x;
		private int y;
		private int width = blockWidth;
		
		public Block(int x, int y, Color color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
		
		public void draw(Graphics page) {
			page.setColor(color);
			page.fillRect(x, y, width, width);
			
		}
		
		
	}
}
