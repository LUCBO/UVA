package _454;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Solution for UVa problem 454 Anagrams
 * 
 * @author Lucas Borg 2019-
 */
public class Main {

	private static Scanner input;

	public void start() {
		input = new Scanner(System.in);
		int testCases = Integer.parseInt(input.nextLine());

		while (testCases >= 0) {
			printAnagrams();
			testCases--;
			if (testCases != 0)
				System.out.println();
		}
	}

	private void printAnagrams() {
		String line = input.nextLine();
		LinkedList<String> list = new LinkedList<String>();

		while (line.length() != 0) {
			if (!list.contains(line))
				list.addLast(line);
			if (input.hasNextLine())
				line = input.nextLine();
			else
				line = "";
		}

		Collections.sort(list);
		
//		for (int i = 0; i < list.size()-1; ++i) {
//			for (int j = i + 1; j < list.size(); ++j) {
//				if (list.get(i).compareTo(list.get(j)) > 0) {
//					String temp = list.get(i);
//					list.set(i, list.get(j));
//					list.set(j, temp);
//				}
//			}
//		}

		while (list.size() > 1)
			getAnograms(list);

	}

	private void getAnograms(LinkedList<String> list) {
		String line = list.removeFirst();
		String baseLine = line.toUpperCase();
		baseLine = baseLine.replaceAll("\\s", "");
		for (int i = 0; i < list.size(); i++) {
			String otherLine = list.get(i);
			String otherBaseLine = otherLine.toUpperCase();
			otherBaseLine = otherBaseLine.replaceAll("\\s", "");
			if (lineMatch(baseLine, otherBaseLine))
				System.out.println(line + " = " + otherLine);
		}
	}

	private boolean lineMatch(String line, String otherLine) {
		if (line.length() == otherLine.length()) {
			boolean[] used = new boolean[line.length()];

			for (int i = 0; i < used.length; i++)
				used[i] = false;

			for (int i = 0; i < line.length(); i++) {
				for (int j = 0; j < otherLine.length(); j++) {
					if (used[j] == false && line.charAt(i) == otherLine.charAt(j)) {
						used[j] = true;
					}
				}
			}

			for (int i = 0; i < used.length; i++) {
				if (used[i] == false)
					return false;
			}

			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
}
