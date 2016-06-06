/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int n = readInt("Enter a positivie integer number: ");
		int counter = 0;
		while (n != 1) {
			counter += 1;
			if (n % 2 == 0){
				n /= 2;
				println (2*n + " is even, so I take half: " + n);
			} else {
				n = 3*n + 1;
				println((n-1)/3 + " is ood, so I make 3n + 1: " + n);
			}
		}
		println("The process took " + counter + " to reach 1.");
	}
}

