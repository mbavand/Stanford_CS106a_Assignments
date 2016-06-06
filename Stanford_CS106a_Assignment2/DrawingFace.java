/* File: RobotFace.java 
 * --------------------
 * This program draws a robot face. 
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class DrawingFace extends GraphicsProgram {
	/* Parameters for the drawing */
	//private static final double APPLICATION_WIDTH   = 800;
	//private static final double APPLICATION_HEIGHT  = 600;
	private static final double HEAD_WIDTH  = 151;
	private static final double HEAD_HEIGHT  = 301;
	private static final double EYE_RADIUS = 15;
	private static final double MOUTH_WIDTH  = 100;
	private static final double MOUTH_HEIGHT  = 50;

	/* Bad Programming:
	public void run() {
		int centreX = getWidth()/2;
		int centreY = getHeight()/2;

		GRect head = new GRect(HEAD_WIDTH, HEAD_HEIGHT);
		head.setFillColor(Color.gray);
		head.setFilled(true);
		//head.setColor(Color.black);
		add(head, centreX-HEAD_WIDTH/2, centreY-HEAD_HEIGHT/2);

		GOval eyeLeft = new GOval(2*EYE_RADIUS, 2*EYE_RADIUS);
		eyeLeft.setFillColor(Color.yellow);
		eyeLeft.setFilled(true);
		eyeLeft.setColor(Color.yellow);
		eyeLeft.setLocation(centreX-HEAD_WIDTH/4-EYE_RADIUS,  centreY-HEAD_HEIGHT/4-EYE_RADIUS);

		GOval eyeRight = new GOval(2*EYE_RADIUS, 2*EYE_RADIUS);
		eyeRight.setFillColor(Color.yellow);
		eyeRight.setFilled(true);
		eyeRight.setColor(Color.yellow);

		add(eyeLeft);		
		add(eyeRight, centreX+HEAD_WIDTH/4-EYE_RADIUS,  centreY-HEAD_HEIGHT/4-EYE_RADIUS);

		GRect mouth = new GRect(MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setFillColor(Color.white);
		mouth.setFilled(true);
		mouth.setColor(Color.white);
		add(mouth, centreX-MOUTH_WIDTH/2, centreY+HEAD_HEIGHT/4-MOUTH_HEIGHT/2);
	}
	 */

	// Good Programming:
	public void run() {
		addFace(getWidth() / 2, getHeight() / 2);
	}
	
	// Adds the entire face centered at (cx, cy)
	private void addFace(double cx, double cy) {
		addHead(cx, cy);
		addEye(cx - HEAD_WIDTH / 4, cy - HEAD_HEIGHT / 4);
		addEye(cx + HEAD_WIDTH / 4, cy - HEAD_HEIGHT / 4);
		addMouth(cx, cy + HEAD_HEIGHT / 4);
	}
	
	// Adds the head centered at (cx, cy)
	private void addHead(double cx, double cy) {
		double x = cx - HEAD_WIDTH / 2;
		double y = cy - HEAD_HEIGHT / 2;
		GRect head = new GRect(x, y, HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(Color.GRAY);
		add(head);
	}
	
	// Adds an eye centered at (cx, cy) 
	private void addEye(double cx, double cy) {
		double x = cx - EYE_RADIUS;
		double y = cy - EYE_RADIUS;
		GOval eye = new GOval(x, y, 2 * EYE_RADIUS, 2 * EYE_RADIUS);
		eye.setFilled(true);
		eye.setColor(Color.YELLOW);
		add(eye);
	}
	
	// Adds a mouth centered at (cx, cy)
	private void addMouth(double cx, double cy) {
		double x = cx - MOUTH_WIDTH / 2;
		double y = cy - MOUTH_HEIGHT / 2;
		GRect mouth = new GRect(x, y, MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setFilled(true);
		mouth.setColor(Color.WHITE);
		add(mouth);
	}

}
