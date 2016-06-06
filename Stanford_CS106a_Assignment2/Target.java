/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		double centerX = getWidth()/2;
		double centerY = getHeight()/2;
		
		GOval outerCircle = new GOval(centerX-OUTER_RADIUS, 
				centerY-OUTER_RADIUS, 2*OUTER_RADIUS, 2*OUTER_RADIUS);
		outerCircle.setFillColor(Color.red);
		outerCircle.setFilled(true);
		add(outerCircle);
		
		GOval middleCircle = new GOval(centerX-MIDDLE_RADIUS, 
				centerY-MIDDLE_RADIUS, 2*MIDDLE_RADIUS, 2*MIDDLE_RADIUS);
		middleCircle.setFillColor(Color.WHITE);
		middleCircle.setFilled(true);
		add(middleCircle);
		
		GOval innerCircle = new GOval(centerX-INNER_RADIUS, 
				centerY-INNER_RADIUS, 2*INNER_RADIUS, 2*INNER_RADIUS);
		innerCircle.setFillColor(Color.red);
		innerCircle.setFilled(true);
		add(innerCircle);
	}
	private static final double OUTER_RADIUS = 72; 
	private static final double MIDDLE_RADIUS = 0.65 * 72;
	private static final double INNER_RADIUS = 0.3 * 72;
}

