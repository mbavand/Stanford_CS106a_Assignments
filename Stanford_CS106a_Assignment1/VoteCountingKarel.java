/*
* File: VoteCountingKarel.java
* ----------------------------
* The VoteCountingKarel subclass cleans out the chad from
* a ballot as described in the section handout.
*/

import stanford.karel.*;

public class VoteCountingKarel extends SuperKarel{

	public void run(){
		while (frontIsClear()){
			move();
			if (noBeepersPresent()) {
				clearupRectangle();				
			} else {
				move();
			}
		}
	}
	
	private void clearupRectangle(){
		turnLeft();
		move();
		turnAround();
		cleanColumn();
		turnAround();
		move();
		turnRight();
		move();
	}
	
	private void cleanColumn(){
		while (frontIsClear()){
			while (beepersPresent()){
				pickBeeper();				
			}
			move();
		}
		while (beepersPresent()){
			pickBeeper();
		}
	}

}
