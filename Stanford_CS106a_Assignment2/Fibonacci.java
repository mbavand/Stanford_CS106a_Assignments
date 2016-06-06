/*
 * File: Fibonacci.java
 * --------------------
 * This program lists the terms in the Fibonacci sequence up to
 * a constant MAX_TERM_VALUE, which is the largest Fibonacci term
 * the program will display.
 */


import acm.program.*;

public class Fibonacci extends ConsoleProgram {
	// Defines the largest term to be displayed
	private static final int MAX_TERM_VALUE = 10000;	

	public void run() {
		/* Bad programming:
		println("This program lists the Fibonacci sequence.");
		int termPrevious = 0;
		println(termPrevious);
		int term = 1;
		println(term);
		int termNext = termPrevious + term;
		while (termNext <= MAX_TERM_VALUE) {			
			println(termNext);
			termPrevious = term;
			term = termNext;
			termNext = termPrevious + term;
		}
		 */

		// Good programming:
		println("This program lists the Fibonacci sequence.");
		int t1 = 0;
		int t2 = 1;
		int t3;
		while (t1 <= MAX_TERM_VALUE) {
			println(t1);
			t3 = t1 + t2;
			t1 = t2;
			t2 = t3;
		}
	}

}


