package _287;

import java.util.Scanner;

/**
 * Solution for UVa problem 287 Text Comparison
 * 
 * @author Lucas Borg 2019-
 */
public class Main {

	private static Scanner input;
	private String text1, text2;
	private int position;

	public void start() {
		input = new Scanner(System.in);
		position = 0;
		while (input.hasNext()) {
			text1 = input.nextLine();
			text1 = text1.toLowerCase();
//			text1 = text1.substring(7, text1.length());
			text2 = input.nextLine();
			text2 = text2.toLowerCase();
//			text2 = text2.substring(7, text2.length());
			compareTexts();
		}
	}

	private void compareTexts() {
		position = 0;
		for (int i = 0; i < text2.length(); i++) {
			if (text2.charAt(i) != text1.charAt(i)) {
				i += findDifference(i);
			}
			position++;
		}
	}

	private int findDifference(int index) {
		int size = 1;
		String word1Text1 = getWord(text1, index);
		String word2Text1 = getWord(text1, index + word1Text1.length());
		String word1Text2 = getWord(text2, index);
		String word2Text2 = getWord(text2, index + word1Text2.length());
		if (word2Text2.compareTo(word1Text1) == 0) { // Substring deleted
			System.out.println(
					"pos " + (index + (index - position)) + " deleted " + word1Text2.length() + " chars \"" + word1Text2 + "\"");
			text2 = text2.substring(0, index) + text2.substring(index + word1Text2.length(), text2.length());
			size = 0;
		} else if ((word1Text1.compareTo(word1Text2) == 0 && word2Text1.compareTo(word2Text2) != 0)
				|| (word2Text1.compareTo(word2Text2) == 0 && word1Text1.compareTo(word1Text2) != 0)) { // Substring
																										// changed
			size = word1Text2.length() - word1Text1.length();
			position += word1Text1.length();
			System.out.println("pos " + (index + (index - position)) + " changed " + word1Text2.length() + " chars from \""
					+ word1Text2 + "\" to " + "\""+ word1Text1 + "\"");
			text2 = text2.substring(0, index) + word1Text1 + text2.substring(index + word1Text2.length(), text2.length());
		} else if (word1Text1.compareTo(word2Text1) != 0 && word1Text1.compareTo(word2Text2) != 0) { // Substring
																										// inserted
			System.out.println("pos " + (index + (index - position)) + " inserted " + word1Text1.length() + " chars \""
					+ word1Text1 + "\"");
			text2 = text2.substring(0, index) + word1Text1 + text2.substring(index, text2.length());
			size = word1Text1.length();
			position += size;
		}
		return size;
	}

	private String getWord(String text, int index) {
		StringBuilder word = new StringBuilder();
		while (Character.isAlphabetic(text.charAt(index)) || text.charAt(index) == '\'') {
			word.append(text.charAt(index));
			index++;
		}
		try {
			if (text.charAt(index) == ' ')
				word.append(" ");
		} catch (Exception e) {
		}
		return word.toString();
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
}
