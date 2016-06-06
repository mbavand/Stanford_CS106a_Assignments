/*
 * File: HangmanCanvas.java
 * ------------------------
 *  Copyright @Majid Bavand and Hossein Khonsari
 * -------------------------------------------------- 
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	private int heightRef;
	private int widthRef;
	private GLabel guessedWordLabel;
	private GLabel incorrectGuessLabel;
	private String incorrectGuesses = "";
	private int incorrectNumber = 0;



	/** Resets the display so that only the scaffold appears */
	public void reset() {
		heightRef = getHeight()/20;
		widthRef = getWidth()/2;
		drawScaffold();	
	}

	private void drawScaffold() {
		add(new GLine(widthRef-BEAM_LENGTH, heightRef, widthRef-BEAM_LENGTH, 
				heightRef+SCAFFOLD_HEIGHT));
		add(new GLine(widthRef-BEAM_LENGTH, heightRef, widthRef, heightRef));
		add(new GLine(widthRef, heightRef, widthRef, heightRef+ROPE_LENGTH));		
	}


	/**
	 * Updates the word on the screen to correspond to the current
	 * state of the game.  The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		if (getElementAt(widthRef-BEAM_LENGTH, heightRef*18) != null) {
			remove(guessedWordLabel);			
		}
		guessedWordLabel = new GLabel(word);
		add(guessedWordLabel, widthRef-BEAM_LENGTH, heightRef*18);
	}


	/**
	 * Updates the display to correspond to an incorrect guess by the
	 * user.  Calling this method causes the next body part to appear
	 * on the scaffold and adds the letter to the list of incorrect
	 * guesses that appears at the bottom of the window.
	 */
	public void noteIncorrectGuess(char letter) {
		incorrectNumber++;
		nextBodyPart(incorrectNumber);
		updateIncorrectGuess(letter);
	}


	private void nextBodyPart(int state) {
		switch (state) {
		case 1:
			// Head:
			add(new GOval(2*HEAD_RADIUS, 2*HEAD_RADIUS), 
					widthRef-HEAD_RADIUS,heightRef + ROPE_LENGTH);
			break;
		case 2:
			// Body:
			add(new GLine(widthRef, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS, 
					widthRef, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH));
			break;
		case 3:
			// Left Arm:
			add(new GLine(widthRef, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					widthRef - UPPER_ARM_LENGTH, 
					heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD));
			add(new GLine(widthRef - UPPER_ARM_LENGTH, 
					heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					widthRef - UPPER_ARM_LENGTH, 
					heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH));
			break;
		case 4:
			// Right Arm:
			add(new GLine(widthRef, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					widthRef + UPPER_ARM_LENGTH, 
					heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD));
			add(new GLine(widthRef + UPPER_ARM_LENGTH, 
					heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					widthRef + UPPER_ARM_LENGTH, 
					heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH));
			break;
		case 5:
			// Left Leg: 
			add(new GLine(widthRef, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH, 
					widthRef - HIP_WIDTH, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH));
			add(new GLine(widthRef - HIP_WIDTH, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH, 
					widthRef - HIP_WIDTH, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH));
			break;
		case 6:
			// Right Leg: 
			add(new GLine(widthRef, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH, 
					widthRef + HIP_WIDTH, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH));
			add(new GLine(widthRef + HIP_WIDTH, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH, 
					widthRef + HIP_WIDTH, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH));
			break;
		case 7:
			// Left Foot:
			add(new GLine(widthRef - HIP_WIDTH, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, 
					widthRef - HIP_WIDTH - FOOT_LENGTH, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH));
			break;
		case 8:
			// Right Foot:
			add(new GLine(widthRef + HIP_WIDTH, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH, 
					widthRef + HIP_WIDTH + FOOT_LENGTH, heightRef + ROPE_LENGTH + 2*HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH));
			break;
		default:
			break;		
		}
	}

	private void updateIncorrectGuess(char letter) {
		if (getElementAt(widthRef-BEAM_LENGTH, heightRef*19) != null) {
			remove(incorrectGuessLabel);
		}
		incorrectGuesses += letter; 
		incorrectGuessLabel = new GLabel(incorrectGuesses);
		add(incorrectGuessLabel, widthRef-BEAM_LENGTH, heightRef*19);
	}


	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
