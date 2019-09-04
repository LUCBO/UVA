package _386;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Solution for UVa problem 386 Perfect Cubes
 * 
 * @author Lucas Borg 2019-08-28
 */
public class Main {
	private int a, b, c, d;
	private HashMap<Integer, LinkedList<Integer[]>> list_cubes;

	public void start() {
		a = 2;
		b = 2;
		c = 2;
		d = 2;
		list_cubes = new HashMap<Integer, LinkedList<Integer[]>>();
		while (a <= 200) {
			while (b <= 200) {
				while (c <= 200) {
					while (d <= 200) {
						if (a * a * a < b * b * b + c * c * c + d * d * d)
							d = 200;
						else if (a * a * a == b * b * b + c * c * c + d * d * d) {
							if (!list_cubes.containsKey(a)) {
								LinkedList<Integer[]> list = new LinkedList<Integer[]>();
								Integer[] values = { b, c, d };
								list.add(values);
								list_cubes.put(a, list);
								System.out.println("Cube = " + a + ", Triple = (" + b + "," + c + "," + d + ")");
							} else {
								if (!isUsed()) {
									Integer[] values = { b, c, d };
									list_cubes.get(a).add(values);
									System.out.println("Cube = " + a + ", Triple = (" + b + "," + c + "," + d + ")");
								}
							}
							d = 201;
						}
						d++;
					}
					d = 2;
					c++;
				}
				c = 2;
				b++;
			}
			b = 2;
			a++;
		}
	}

	private boolean isUsed() {
		LinkedList<Integer[]> cube_nbrs = (LinkedList<Integer[]>) list_cubes.get(a).clone();
		Iterator<Integer[]> iterator = cube_nbrs.iterator();
		while (iterator.hasNext()) {
			Integer[] bcd = iterator.next();
			boolean[] used_char = new boolean[] { false, false, false };
			if (bcd[0] == b || bcd[0] == c || bcd[0] == d) {
				used_char[0] = true;
			}
			if (bcd[1] == b || bcd[1] == c || bcd[1] == d) {
				used_char[1] = true;
			}
			if (bcd[2] == b || bcd[2] == c || bcd[2] == d) {
				used_char[2] = true;
			}
			if (used_char[0] == true && used_char[1] == true && used_char[2] == true) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.start();
	}
}
