/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	/* 
	 * Consider bundles of two rows from bottom. Fill the board 
	 * checkered-like with beepers one bundle at a time. Fill each 
	 * bundle one row at a time:
	 */
	public void run(){
		/*
		 * First bundel of two rows are exceptions and are considered as
		 * parts of initialization
		 */
		
		// Fill the first row:
		putBeeper();
		fillRowEveryOtherBlock();
		/* 
		 * After filling the first row, check if the board is not blocked 		
		 * from above:
		 */
		if (leftIsClear()){
			// To create checkered pattern for the next row:
			if (beepersPresent()){
				moveDouble();
			} else{
				moveSingle();
			}
			fillRowEveryOtherBlock();
		} 

		// Main body of algorithm (starts from the third row of the checkered board)
		while (rightIsClear()){ // checks if the row is not blocked from above
			rightUTurn();
			putBeeper();
			fillRowEveryOtherBlock();
			if (leftIsClear()){ // checks if the row is not blocked from above
				// To create checkered pattern for the next row:
				if (beepersPresent()){
					moveDouble();
				} else{
					moveSingle();
				}						
				fillRowEveryOtherBlock();
			} else{
				// so it can get out of while loop when the above row is blocked
				turnAround();
			}
		}
	}

	/*
	 * Karel starts from its current position and without putting any beeper
	 * at its current position moves two blocks in the direction that he is
	 * facing and put beeper down and continues to put beeper down every
	 * other blocks until reaches a wall
	 */
	private void fillRowEveryOtherBlock(){
		while (frontIsClear()){			
			move();
			if (frontIsClear()){
				move();
				putBeeper();
			}
		}
	}

	/*
	 * When Karel reaches a wall at the right side of the world and there
	 * is no wall above, he either has to go the next row and put a beeper
	 * down immediately or after one block. If he needs put beeper down
	 * after one block: 
	 */
	private void moveDouble(){
		leftUTurn();
		if (frontIsClear()){
			move();
			putBeeper();
		}
	}

	/* 
	 * Karel makes a left U-turn to the above row when he reaches a 
	 * wall at east
	 */
	private void leftUTurn(){
		turnLeft();
		move();
		turnLeft();
	}

	/*
	 * When Karel reaches a wall at the right side of the world and there
	 * is no wall above, he either has to go the next row and put a beeper
	 * down immediately or after one block. If he needs put beeper down
	 * immediately: 
	 */
	private void moveSingle(){
		leftUTurn();
		putBeeper();
	}

	/* 
	 * Karel makes a right U-turn to the above row when he reaches a 
	 * wall at west:
	 */
	private void rightUTurn(){
		turnRight();
		move();
		turnRight();

	}


}
