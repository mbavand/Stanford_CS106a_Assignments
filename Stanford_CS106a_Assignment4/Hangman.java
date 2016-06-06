/*
 * File: Hangman.java
 * ------------------
 * Copyright @Majid Bavand and Hossein Khonsari
 * --------------------------------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

//import acm.graphics.*;
import acm.program.*;
import acm.util.*;

//import java.awt.*;

public class Hangman extends ConsoleProgram {

	private static final int MAX_GUESSES = 8;

	/////////////////////////////////////////////////////////////////////////////
	public void init() {
		canvas = new HangmanCanvas();
		//canvas = new GCanvas();
		add(canvas);
		//canvas.add();
	}

	
	
	/////////////////////////////////////////////////////////////////////////////
	public void run() {
		/* You fill this in */
		chooseWord();
		playGame();    	
	}

	/////////////////////////////////////////////////////////////////////////////
	private void chooseWord(){
		int lexiconWordCount = lexicon.getWordCount();
		println("There are " + lexiconWordCount + " words in the lexicon.");
		int selectedWordIndex = rgen.nextInt(0,lexiconWordCount-1);
		word = lexicon.getWord(selectedWordIndex);		
	}


	/////////////////////////////////////////////////////////////////////////////
	private void playGame(){
		boolean correctWordMatchFlag = false;
		//println(word);
		int wordLength = word.length();
		initializationMessage(wordLength);
		canvas.reset();
		while (guessCounter>0){    	
			String yourGuess = getEntry();
			//println(yourGuess);
			if (!yourGuess.equals("")){
				boolean correctGuessFlag = isYourGuessCorrect(yourGuess);
				//println(correctGuessFlag);				
				//println(word.indexOf(yourGuess));
				if (correctGuessFlag){
					correctWordMatchFlag = printCorrectGuessMessage(yourGuess);
					canvas.displayWord(guessedWord);
				} else {
					guessCounter --;
					printWrongGuessMessage(yourGuess);
					canvas.noteIncorrectGuess(yourGuess.charAt(0));
				}				
				if (correctWordMatchFlag) break;
			}
		}
	}


	/////////////////////////////////////////////////////////////////////////////  
	private void initializationMessage(int wordLength){
		println("Welcome to Hangman!");
		print("The word now looks like this: ");
		for (int i=0; i<wordLength; i++){
			//print('-');
			guessedWord = guessedWord.concat("-");
		}
		println(guessedWord);
		//println();
		println("You have "+MAX_GUESSES+" guesses left");	
	}


	/////////////////////////////////////////////////////////////////////////////
	private String getEntry(){
		String yourGuess = readLine("Your guess: ");
		boolean illegalFlag = errorEntryCheck(yourGuess);
		if (illegalFlag){
			yourGuess = "";
		} else{
			yourGuess = yourGuess.toUpperCase();
		}		
		return yourGuess;
	}


	/////////////////////////////////////////////////////////////////////////////
	private boolean errorEntryCheck(String yourGuess){
		if (yourGuess.length() != 1){
			println("Illegal entry, please enter a letter (a-z or A-Z)!");
			return true;
		} else if (!Character.isLetter(yourGuess.charAt(0))){    	
			println("Illegal entry, please enter a letter (a-z or A-Z)!");
			return true;
		} else{
			return false;
		}
	}


	/////////////////////////////////////////////////////////////////////////////
	private boolean isYourGuessCorrect(String yourGuess){		
		if(word.indexOf(yourGuess) == -1) { // || yourGuess.equals("")
			return false;
		} 
		return true;
	}

	
	/////////////////////////////////////////////////////////////////////////////
	private boolean printCorrectGuessMessage(String yourGuess){
		updateGuessedWord(yourGuess);
		boolean correctWordMatchFlag = word.equals(guessedWord);
		println("That guess is correct.");
		if (!correctWordMatchFlag) {
			println("The word now looks like this: " + guessedWord + ".");
			if (guessCounter != 1){
				println("You have "+ guessCounter + " guesses left.");
			} else {
				println("You only have one guess left.");
			}
		} else {
			println("You guessed the word: "+guessedWord);
			println("You win!");			
		}
		return correctWordMatchFlag;		
	}

	
	/////////////////////////////////////////////////////////////////////////////
	private void updateGuessedWord(String yourGuess){
		int index = word.indexOf(yourGuess);
		String s1,s2;		
		while (index != -1){
			s1 = guessedWord.substring(0,index);
			s2 = guessedWord.substring(index+1);
			guessedWord = s1.concat(yourGuess).concat(s2);
			index = word.indexOf(yourGuess,index+1);
		}		
	}


	/////////////////////////////////////////////////////////////////////////////
	private void printWrongGuessMessage(String yourGuess){
		println("There are no " + yourGuess + "'s in the word.");		
		switch(guessCounter){
		case 0: 
			println("You are completely hung");
			println("The word was:" + word);
			println("You lose.");
			break;
		case 1: 
			println("The word now looks like this: " + guessedWord + ".");
			println("You have only one guess left.");
			break;
		default: 
			println("The word now looks like this: " + guessedWord + ".");
			println("You have "+ guessCounter + " guesses left.");
			break;
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	// Instance variables:
	private String word;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanLexicon lexicon = new HangmanLexicon();
	private int guessCounter = MAX_GUESSES;
	private String guessedWord = "";
	private HangmanCanvas canvas;
}



