package pack1;

import java.util.Random;

public class MethodTest {

	public static void main(String[] args) {
		int[] c1 = new int[20];
		int num = 6;
		int head = 4;
		
		selectRandomColumn(c1.length, head, num);
	}

	static int[][] selectRandomColumn(int a, int c, int num) {
		int[][] b = new int[c][a];
		
		for(int j = 0; j < b.length; j++) {
		b[j] = selectRandom(b[j], 6);
		
			for(int i = 0; i < b[0].length; i++) {
				if(b[j][i] == 1) {
					for(int k = j+1; k < b.length; k++) {
						b[k][i] = 2;
					}
				}
			}
		}
		print2DArray(b);
		
		return null;
	}
	
	/**
	 * Funktioniert.
	 * @param ar
	 * @param num
	 * @return
	 */
	static int[] selectRandom(int[] a, int num) {
		Random rng = new Random();
		int out = 1000, j = 0, i = 0;

		while(i < num) {
			int r = rng.nextInt(a.length);
				
			if(a[r] == 0) {
				a[r] = 1;

				i++;
			}
			
			j++;
			if(j >= out) {
				System.out.println("Max Students reached");
				break;
			}
		}
		
		return a;
	}

	/**
	 * Sets n Numbers of Booleans in an Array to true. Sets everything to false before. --> Works
	 * @param length the Length of the Boolean Array.
	 * @param num the number of True Outputs.
	 * @return
	 */
	public static boolean[] selectRandom(int length, int num) {
		boolean[] ba = new boolean[length];
		
		Random rng = new Random();
		int out = 1000, j = 0, i = 0;

		while(i < num) {
			int r = rng.nextInt(ba.length);
	
			boolean temp = ba[r];
			
			if(!temp) {
				ba[r] = true;

				i++;
				//n = addToArray(n, s[r]);
			}
			
			j++;
			if(j >= out) {
				break;
			}
		}
		
		return ba;
	}
	
	public static void printArray(int[] i) {
		for (Integer inte : i) {
			System.out.print(inte + ",");
		}
	}
	
	public static void print2DArray(int[][] i) {
		System.out.println("{");
		for (int[] inte : i) {
			System.out.print("{");
			printArray(inte);
			System.out.println("}");
		}
		System.out.println("}");
	}
}
