/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL = 0;
	public void run() {
		int input, max, min;		
		println("This program finds the largest and smallest numbers.");
		input = readInt("?");

		if (input == SENTINEL){
			println("No value has been entered.");
		} else{
			max = input;
			min = input;
			while (true){
				input = readInt("?");
				if (input == SENTINEL) break;
				if (input > max) {
					max = input;
				} else if((input < min)) {
					min = input;
				}
			}
			println("smallest: " + min);
			println("largest: " + max);
		}
	}

}

