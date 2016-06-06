/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * We instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;


public class CollectNewspaperKarel extends SuperKarel {

	/* 
	 * Karel needs to move to the door, pickup the newspaper, and
	 * return to its initial position
	 */	 
	public void run() {
		moveToNewspaper();
		pickBeeper();
		moveToStart();
	}

	// Karel moves to its front door (position of newspaper)
	private void moveToNewspaper(){
		moveToDoor();
		move();		
	}

	// Karel moves from its initial position to face his door
	private void moveToDoor(){
		moveToWall();
		moveDownToDoor();				
	}

	// Karel moves until he reaches a wall
	private void moveToWall(){
		while(frontIsClear())
			move();
	}
	
	// Karel moves from the northeast corner of his house to the wall
	private void moveDownToDoor(){
		turnRight();
		while (leftIsBlocked()){
			move();
		}
		turnLeft();

	}

	/*
	 * With the initial direction facing eastward, Karel goes back
	 * from the position of newspaper to its orginial position at
	 * his house 
	 */
	private void moveToStart(){
		turnAround();
		move();
		turnRight();
		move();
		turnLeft();
		moveToWall();
		turnAround();
	}

}
