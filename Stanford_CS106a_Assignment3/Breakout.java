/*
 * File: Breakout.java
 * -------------------
 * Copyright @Majid Bavand and Hossein Khonsari
 * --------------------------------------------
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels.  On some platforms 
	 * these may NOT actually be the dimensions of the graphics canvas. */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board.  On some platforms these may NOT actually
	 * be the dimensions of the graphics canvas. */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 60;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
			(WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;


	////////////////////////////
	// Constants defined by Majid and Hossein
	/**The y position of paddle  */
	private static final double Y_PADDLE = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
	private static final int DELAY = 10;
	private static final double V_Y = 4.0;
	private static final double V_X_MIN = 1.0;
	private static final double V_X_MAX = 4.0;
	private static final int SPEEDUP_COLLISION_NO = 7;
	private static final double SPEEDUP_VX = 1.25;
	private static final double SPEEDUP_VY = 1.05;
	private static final double Y_SCORE_LABEL = HEIGHT -PADDLE_Y_OFFSET + 2*BRICK_HEIGHT;


	/////////////////////////////////////////////////////////////////////////////
	/* Method: run() */
	/** Runs the Breakout program. */
	// It has two methods. One method for setting up the world config.
	// And another one for playing the game
	public void run() {
		setupGameBoard();		
		playGame();
	}


	////////////////////////////
	//
	private void setupGameBoard(){
		setupBricksConfig();
		setupPaddle();
		addMouseListeners();
		setupScoreLabel();
	}


	////////////////////////////
	// Sets up the config of the bricks in the world by breaking
	// the code to a for loop of 5 iterations in each of which it uses
	// different colors
	private void setupBricksConfig(){
		//double canvasWidth = getWidth();
		//double canvasHeight = getHeight();

		double x = (WIDTH - (BRICK_WIDTH * NBRICKS_PER_ROW + 
				BRICK_SEP * (NBRICKS_PER_ROW-1)))/2;
		//double x = (canvasWidth - (BRICK_WIDTH * NBRICKS_PER_ROW + 
		//		BRICK_SEP * (NBRICKS_PER_ROW-1)))/2;
		//double x = (canvasWidth - WIDTH) / 2 + BRICK_SEP/2;
		double y = BRICK_Y_OFFSET;


		for (int i = 0; i < NBRICK_ROWS / 2; i++){
			switch (i){
			case 0: {
				brick2RowsConstruction(x,y,Color.RED);
				break;
			}
			case 1: {
				brick2RowsConstruction(x,y,Color.ORANGE);
				break;
			}
			case 2: {
				brick2RowsConstruction(x,y,Color.YELLOW);
				break;
			}
			case 3: {
				brick2RowsConstruction(x,y,Color.GREEN);
				break;
			}
			case 4: {
				brick2RowsConstruction(x,y,Color.CYAN);
				break;
			}
			default: break;
			}
			y += 2 * (BRICK_HEIGHT + BRICK_SEP);
		}
	}


	////////////////////////////	
	// constructs two rows of bricks with the same color
	private void brick2RowsConstruction (double x, double y, Color color){		
		for (int j = 0; j <2; j++){
			y += j * (BRICK_HEIGHT + BRICK_SEP);
			brickRowConstruction(x,y,color);
		}		
	}


	////////////////////////////	
	// constructs one row of bricks with the same color
	private void brickRowConstruction(double x, double y, Color color){		
		for (int i = 0; i < NBRICKS_PER_ROW; i++){			
			GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
			//brick.setFillColor(color);
			brick.setColor(color);
			brick.setFilled(true);
			add(brick);
			//add(new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT));
			x += BRICK_WIDTH + BRICK_SEP;
		}
	}


	////////////////////////////	
	//
	private void setupPaddle(){
		double xPaddle = (WIDTH - PADDLE_WIDTH)/2;
		paddle = new GRect(xPaddle, Y_PADDLE, PADDLE_WIDTH, PADDLE_HEIGHT);
		add(paddle);
		paddle.setFillColor(Color.BLACK);
		paddle.setFilled(true);
	}


	////////////////////////////	
	//
	public void mouseMoved(MouseEvent e){
		double x = e.getX();
		if (x > WIDTH - PADDLE_WIDTH/2) {
			paddle.setLocation(WIDTH-PADDLE_WIDTH,Y_PADDLE);
			scoreLabel.setLocation(WIDTH-(PADDLE_WIDTH+scoreLabel.getWidth())/2,Y_SCORE_LABEL);
		} else if (x < PADDLE_WIDTH/2) {
			paddle.setLocation(0,Y_PADDLE);
			scoreLabel.setLocation((PADDLE_WIDTH-scoreLabel.getWidth())/2,Y_SCORE_LABEL);
		} else {
			paddle.setLocation(x-PADDLE_WIDTH/2,Y_PADDLE);
			scoreLabel.setLocation(x-scoreLabel.getWidth()/2,Y_SCORE_LABEL);
		}
	}

	
	private void setupScoreLabel(){		
		scoreLabel = new GLabel(""+playerScore);
		scoreLabel.setFont("Serif-20");
		scoreLabel.setColor(Color.BLACK);
		double x = (WIDTH - scoreLabel.getWidth()) / 2;
		add(scoreLabel,x,Y_SCORE_LABEL);		
	}

	////////////////////////////
	private void playGame() {
		setupBall();
		setupBallSpeed();
		startMessageGeneration();
		while (!startClick) {						
			//if (startClick) break; 
		}
		remove(startMessage);
		while (!gameOver){
			if (gameNearlyOver){
				gameNearlyOver = false;
				ball.setLocation(WIDTH/2-BALL_RADIUS,getHeight()/2-BALL_RADIUS);
				setupBallSpeed();
				startMessageGeneration();				
				pause(1000);
				//startClick = false;
				//while (true) {
				//	if (startClick) break; 
				//}
				remove(startMessage);
			}
			moveBall();
			bounceOffWall();
			collisionCheck();
			if (getElementCount() == 2){
				gameOver = true;
				winningMessage();
			}
			pause(DELAY);
		}
	}


	////////////////////////////
	private void setupBall() {
		ball = new GOval(2*BALL_RADIUS,2*BALL_RADIUS);
		ball.setFilled(true);
		add(ball,WIDTH/2-BALL_RADIUS,getHeight()/2-BALL_RADIUS);
	}


	////////////////////////////
	private void setupBallSpeed(){
		vy = V_Y;
		vx = rgen.nextDouble(V_X_MIN,V_X_MAX);
		if (rgen.nextBoolean()) vx = -vx;		
	}


	////////////////////////////
	public void mouseClicked(MouseEvent e){		
		startClick = true;
	}


	////////////////////////////
	private void startMessageGeneration(){
		startMessage.setLabel("Try # "+ (counter+1));
		startMessage.setFont("Serif-30");
		startMessage.setColor(Color.BLACK);
		double x = (WIDTH - startMessage.getWidth()) / 2;
		double y = (getHeight() + startMessage.getAscent()) / 3 * 2;
		add(startMessage, x, y);
	}


	////////////////////////////
	private void moveBall(){
		ball.move(vx,vy);
	}


	////////////////////////////
	private void bounceOffWall(){
		if (ball.getX() < 0){
			vx = -vx;
			double diff = -(ball.getX());
			ball.move(2 * diff, 0);
		} else if (ball.getX() > WIDTH-2*BALL_RADIUS) {
			vx = -vx;
			double diff = ball.getX() - (WIDTH - 2*BALL_RADIUS);
			ball.move(-2 * diff, 0);
		} else if (ball.getY() < 0) {
			vy = -vy;			
			double diff = -(ball.getY());
			ball.move(0, 2 * diff);
		} else if (ball.getY() > getHeight() - 2*BALL_RADIUS){
			vy = -vy;
			//vy = 0;
			//vx = 0;
			double diff = ball.getY() - (getHeight() - 2*BALL_RADIUS);
			ball.move(0, -2 * diff);
			counter ++;
			gameNearlyOver = true;
			startClick = false;
			if (counter >= NTURNS){
				gameOver = true;
				losingMessage();
			}
		}
	}


	////////////////////////////
	private void collisionCheck(){
		GObject collider = getCollidingObject();
		if (collider != null){
			bounceClip.play();
			if (collider == paddle){
				vy = -vy;
				double diff = ball.getY() - (Y_PADDLE - 2*BALL_RADIUS);
				ball.move(0, -2 * diff);
				speedUp();
			} else {
				vy = -vy;			
				remove(collider);
			}
		}
	}


	////////////////////////////
	private GObject getCollidingObject(){
		double  y = 0;
		GObject collObj;
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			collObj = getElementAt(ball.getX(), ball.getY());
			//x = ball.getX();
			y = ball.getY();
		} else if (getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY()) != null) {
			collObj = getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY());
			//x = ball.getX()+2*BALL_RADIUS;
			y = ball.getY();
		} else if (getElementAt(ball.getX() , ball.getY()+ 2*BALL_RADIUS) != null) {
			collObj = getElementAt(ball.getX() , ball.getY()+ 2*BALL_RADIUS);
			//x = ball.getX();
			y = ball.getY()+ 2*BALL_RADIUS;
		} else if (getElementAt(ball.getX() + 2*BALL_RADIUS ,
				ball.getY()+ 2*BALL_RADIUS) != null) {
			collObj = getElementAt(ball.getX()+ 2*BALL_RADIUS ,
					ball.getY()+ 2*BALL_RADIUS);
			//x = ball.getX()+ 2*BALL_RADIUS;
			y = ball.getY()+ 2*BALL_RADIUS;
		} else {
			collObj = null;
		}
		if (collObj != null){
			keepScore(y);
			scoreLabel.setLabel(""+playerScore);
		}
		return collObj;
	}


	////////////////////////////
	private void keepScore(double y){
		if (y >= BRICK_Y_OFFSET && y < BRICK_Y_OFFSET + 2*(BRICK_SEP+BRICK_HEIGHT)){
			playerScore +=10;
		} else if (y >= BRICK_Y_OFFSET + 2*(BRICK_SEP+BRICK_HEIGHT) &&
				y < BRICK_Y_OFFSET + 4*(BRICK_SEP+BRICK_HEIGHT)){
			playerScore +=8;
		} else if (y >= BRICK_Y_OFFSET + 4*(BRICK_SEP+BRICK_HEIGHT) && 
				y < BRICK_Y_OFFSET + 6*(BRICK_SEP+BRICK_HEIGHT)){
			playerScore +=4;
		} else if (y >= BRICK_Y_OFFSET + 6*(BRICK_SEP+BRICK_HEIGHT) && 
				y < BRICK_Y_OFFSET + 8*(BRICK_SEP+BRICK_HEIGHT)){
			playerScore +=2;
		} else if (y >= BRICK_Y_OFFSET + 8*(BRICK_SEP+BRICK_HEIGHT) && 
				y < BRICK_Y_OFFSET + 10*(BRICK_SEP+BRICK_HEIGHT)){
			playerScore +=1;
		}
	}


	////////////////////////////
	private void speedUp(){
		paddleHitCounter ++;
		if (paddleHitCounter % SPEEDUP_COLLISION_NO == 0){
			vx *= SPEEDUP_VX;
			vy *= SPEEDUP_VY;
		}
	}


	////////////////////////////
	private void winningMessage(){
		GLabel label = new GLabel("Congratulations, You Win!");
		label.setFont("Serif-30");
		label.setColor(Color.MAGENTA);
		double x = (WIDTH - label.getWidth()) / 2;
		double y = (getHeight() + label.getAscent()) / 2;
		add(label, x, y);					
	}


	////////////////////////////
	private void losingMessage(){
		GLabel label = new GLabel("Game Over, You Lose :(");
		label.setFont("Serif-30");
		label.setColor(Color.BLUE);
		double x = (WIDTH - label.getWidth()) / 2;
		double y = (getHeight() + label.getAscent()) / 2;
		add(label, x, y);					
	}


	/////////////////////////////////////////////////////////////////////////////	
	// Instance variables:
	private GRect paddle; 
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx, vy;
	private GOval ball;
	private Boolean gameOver = false;
	private Boolean gameNearlyOver = false;
	private int counter = 0;
	private AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	private boolean startClick = false; 
	private GLabel startMessage = new GLabel("");
	private int paddleHitCounter = 0;
	private int playerScore = 0;
	private GLabel scoreLabel;
}




