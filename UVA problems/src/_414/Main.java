package _414;

import java.util.Scanner;

/**
 * Solution for UVa problem 414 Machined Surfaces
 * 
 * @author Lucas Borg 2019-11-28
 */
public class Main {

	private static Scanner input;

	public void start() {
		input = new Scanner(System.in);
		String line = input.nextLine();
		while (!line.equals("0")) {
			System.out.println(getSpace(Integer.parseInt(line)));
			line = input.nextLine();
		}
	}

	private int getSpace(int rows) {
		String[] surface = new String[rows];
		for (int i = 0; i < rows; i++) {
			surface[i] = input.nextLine();
		}
		int totalSpace = getTotalSpace(surface);
		int minDistance = getMinimumDistance(surface);
		return totalSpace - (minDistance * rows);
	}

	private int getTotalSpace(String[] surface) {
		int space = 0, x1, x2, index;

		for (int i = 0; i < surface.length; i++) {
			index = 0;
			while (index < 25 && surface[i].charAt(index) != ' ')
				index++;
			x1 = index-1;
			index = 24;
			while (index >= 0 && surface[i].charAt(index) != ' ')
				index--;
			x2 = index;
			if(x2 != -1)
				space += x2 - x1;
		}
		return space;
	}

	private int getMinimumDistance(String[] surface) {
		int minDistance = 25, currentDistance = 25, x1, x2, index;

		for (int i = 0; i < surface.length; i++) {
			index = 0;
			while (index < 25 && surface[i].charAt(index) != ' ')
				index++;
			x1 = index-1;
			index = 24;
			while (index >= 0 && surface[i].charAt(index) != ' ')
				index--;
			x2 = index;
			if(x2 != -1)
				currentDistance = x2 - x1;
			else
				currentDistance = 0;
			if(currentDistance < minDistance)
				minDistance = currentDistance;
			
		}
		return minDistance;
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
}