package _401;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Solution for UVa problem 401 Palindromes
 * 
 * @author Lucas Borg 2019-09-04
 */
public class Main {

	private static Scanner input;
	private HashMap<Character, Character> mirrorMap;

	public void start() {
		input = new Scanner(System.in);
		createMirrorMap();
		String text = input.nextLine();
		while (text.length() > 0) {
			examString(text);
			if(input.hasNextLine())
				text = input.nextLine();
			else
				break;
		}
	}

	/**
	 * Checks if the string is a palindrome and/or a mirrored string
	 * 
	 * @param text
	 *            The string
	 */
	private void examString(String text) {
		boolean palindrome = false, mirror = false;

		palindrome = isPalindrome(text);
		mirror = isMirror(text);

		if (palindrome && mirror)
			System.out.println(text + " -- is a mirrored palindrome.\n");
		else if (palindrome)
			System.out.println(text + " -- is a regular palindrome.\n");
		else if (mirror)
			System.out.println(text + " -- is a mirrored string.\n");
		else
			System.out.println(text + " -- is not a palindrome.\n");
	}

	/**
	 * Checks if a String is a mirrored string
	 * 
	 * @param text
	 *            The String to check
	 * @return true if the String is a mirror
	 */
	private boolean isMirror(String text) {
		for (int i = 0; i < text.length() / 2; i++) {
			if (mirrorMap.containsKey(text.charAt(i))) {
				if (mirrorMap.get(text.charAt(i)) != text.charAt(text.length() - 1 - i))
					return false;
			} else
				return false;
		}

		if (text.length() % 2 != 0) { // Check middle char if length is odd
			char c;
			if (text.length() == 1) {
				if (!mirrorMap.containsKey(text.charAt(0))) {
					return false;
				}
				c = text.charAt(0);
			} else if (!mirrorMap.containsKey(text.charAt(text.length() / 2)))
				return false;
			else
				c = text.charAt(text.length() / 2);
			if(c != mirrorMap.get(c))
				return false;
		}

		return true;
	}

	/**
	 * Checks if a String is a palindrome
	 * 
	 * @param text
	 *            The String to check
	 * @return true if the String is a palindrome
	 */
	private boolean isPalindrome(String text) {
		for (int i = 0; i < text.length() / 2; i++) {
			if (text.charAt(i) != text.charAt(text.length() - 1 - i))
				return false;
		}
		return true;
	}

	/**
	 * Map characters to their mirror
	 */
	private void createMirrorMap() {
		mirrorMap = new HashMap<Character, Character>();
		mirrorMap.put('A', 'A');
		mirrorMap.put('E', '3');
		mirrorMap.put('H', 'H');
		mirrorMap.put('I', 'I');
		mirrorMap.put('J', 'L');
		mirrorMap.put('L', 'J');
		mirrorMap.put('M', 'M');
		mirrorMap.put('O', 'O');
		mirrorMap.put('S', '2');
		mirrorMap.put('T', 'T');
		mirrorMap.put('U', 'U');
		mirrorMap.put('V', 'V');
		mirrorMap.put('W', 'W');
		mirrorMap.put('X', 'X');
		mirrorMap.put('Y', 'Y');
		mirrorMap.put('Z', '5');
		mirrorMap.put('1', '1');
		mirrorMap.put('2', 'S');
		mirrorMap.put('3', 'E');
		mirrorMap.put('5', 'Z');
		mirrorMap.put('8', '8');
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
}
