/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run(){
		while (frontIsClear()){
			repairNextArch();			
		}
		repairPillar();
	}

	private void repairNextArch(){
		repairPillar();
		move4Blocks();
	}

	private void repairPillar(){
		turnLeft();
		repairToWall();
		turnAround();
		moveToWall();
		turnLeft();
	}

	private void repairToWall(){
		while (frontIsClear()){
			if (noBeepersPresent()){
				putBeeper();
				move();
			} else {
				move();
			}
		}	
		if (noBeepersPresent()){
			putBeeper();
		}
	}

	private void moveToWall(){
		while(frontIsClear()){
			move();
		}
	}

	private void move4Blocks(){
		for (int i = 0; i < 4; i++){
			move();
		}
	}

}
