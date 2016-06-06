/*
 * File: HangmanLexicon.java
 * -------------------------
 *  Copyright @Majid Bavand
 * -------------------------------------------------- 
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */


import java.util.*;
//import acm.util.*;
import java.io.*;
//import acm.program.*;

import acm.util.ErrorException;


public class HangmanLexicon {

	// This is the HangmanLexicon constructor
	//public HangmanLexicon() throws Exception {
	public HangmanLexicon() {
		//BufferedReader lex;
		try {
			//lex = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			BufferedReader lex = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String word = lex.readLine();
				if (word == null) break;
				list.add(word);
			}
			//int nullLength;
			lex.close();			
		} catch (IOException e) {
			e.printStackTrace();
			throw new ErrorException(e);
			//println("File not found");
		}
	}


	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		//return 10;
		return list.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		//		switch (index) {
		//		case 0: return "BUOY";
		//		case 1: return "COMPUTER";
		//		case 2: return "CONNOISSEUR";
		//		case 3: return "DEHYDRATE";
		//		case 4: return "FUZZY";
		//		case 5: return "HUBBUB";
		//		case 6: return "KEYHOLE";
		//		case 7: return "QUAGMIRE";
		//		case 8: return "SLITHER";
		//		case 9: return "ZIRCON";
		//		default: throw new ErrorException("getWord: Illegal index");
		//		}
		return list.get(index);
	}

	/* Instance variables */
	private ArrayList<String> list = new ArrayList<String>(); 
}
