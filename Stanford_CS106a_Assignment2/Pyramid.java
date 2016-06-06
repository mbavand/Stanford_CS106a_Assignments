/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
//import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	public void run() {
		int canvasWidth = getWidth();
		int canvasHeight = getHeight();
		int x, y;
		for (int i = BRICKS_IN_BASE; i > 0; i--){
			x = (canvasWidth - BRICK_WIDTH * i)/2;
			y = canvasHeight - BRICK_HEIGHT * (BRICKS_IN_BASE - i + 1);
			for (int j = 0; j < i; j++){				
				GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);				
				x = x + BRICK_WIDTH;
			}
		}
	}
}



