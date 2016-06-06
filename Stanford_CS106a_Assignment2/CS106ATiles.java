/*
 * File: CS106ATiles.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the CS106ATiles problem.
 */

import acm.graphics.*;
import acm.program.*;
//import java.awt.*;

public class CS106ATiles extends GraphicsProgram {

	/** Amount of space (in pixels) between tiles */
	private static final int TILE_SPACE = 20;
	private static final int TILE_WIDTH = 100;
	private static final int TILE_HEIGHT = 50;

	public void run() {
		double centreX = getWidth()/2;
		double centreY = getHeight()/2;
		double tagWidth = new GLabel("CSA106A").getWidth();
		double tagHeight = new GLabel("CSA106A").getAscent();
		//GLabel tag1 = new GLabel("CS106A");
		//double tagWidth = tag1.getWidth(); 
		//double tagHeight = tag1.getAscent();		

		for (int east = 0; east <= 1; east++){
			double rectX = centreX + (2*east-1)*TILE_SPACE/2 - (1-east)*TILE_WIDTH;
			double tagX = centreX + (2*east-1)*TILE_SPACE/2 +
					(2*east-1)*(TILE_WIDTH - tagWidth)/2 - (1-east)*tagWidth;			

			for (int north = 1; north >= 0; north--){

				double rectY = centreY - (2*north-1)*TILE_SPACE/2 - north*TILE_HEIGHT;
				GRect rect = new GRect(TILE_WIDTH,TILE_HEIGHT);
				add(rect,rectX,rectY);				

				GLabel tag = new GLabel("CS106A");
				double tagY = centreY - (2*north-1)*TILE_SPACE/2 - 
						(2*north-1)*(TILE_HEIGHT-tagHeight)/2 + (1-north)*tagHeight;
				add(tag,tagX,tagY);				
			}
		}
	}
}

