package _100;

import java.util.Scanner;

public class Main {

	private static Scanner input;

	public void start() {
		input = new Scanner(System.in);
		int i, j, maxCycleLenght;
		
		while (input.hasNextInt()) {
			i = input.nextInt();
			j = input.nextInt();
			maxCycleLenght = calcMaxCycleLenght(i, j);
			System.out.println(i + " " + j + " " + maxCycleLenght);
		}
	}

	private int calcMaxCycleLenght(int i, int j) {
		int max = Integer.MIN_VALUE;
		int current = 0;
		int low, high;
		if(i<=j) {
			low = i;
			high = j;
		}
		else {
			low = j;
			high = i;
		}
		for(int x=low; x<=high; x++) {
			current = getCycleLenght(x);
			if(current>max)
				max = current;
		}
		return max;
	}

	private int getCycleLenght(int x) {
		int counter = 1;
		while(x != 1) {
			if(x%2 != 0) 
				x = 3*x + 1;
			else
				x = x/2;
			counter++;
		}
		return counter;
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
}
