package pack1;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class ArrayOperations {
	
	/** 
	 * Gets num Students randomly from s.
	 * @param s the Array.
	 * @param num the number of chosen ones.
	 * @return the new Array (num long).
	 */
	public static Student[] getDayRandom(Student[] s, int num) {
		Random rng = new Random();
		Student[] n = new Student[num];
		int r = 0;
		
		for (int i = 0; i < n.length; i++) {
			r = rng.nextInt(num - 1);
			
			n[i] = s[r];
			n = removeElement(n, r);
		}
		
		return n;
	}
	
	/** 
	 * Removes an Element from an Array.
	 * @param s the Array.
	 * @param i the Index.
	 * @return the new Array.
	 */
	public static Student[] removeElement(Student[] s, int i) {
		Student[] n = new Student[s.length - 1];
		for(int j = 0; j < n.length; j++) {
			if(j >= i) {
				n[j] = s[j+1];
			}else {
				n[j] = s[j];
			}
		}
		
		return n;
	}
	
	/**
	 * Updates the Index Numbers of an Array.
	 * @param s the Array.
	 * @return the new Array.
	 */
	public static Student[] updateIndex(Student[] s) {
		Student[] n = new Student[s.length];
		
		for (int i = 0; i < n.length; i++) {
			n[i] = new Student(s[i].getName(), s[i].getSurname(), i);
		}
		
		return n;
	}

	/**
	 * Creates a random Student Array with k Students.
	 * @param k the number of Students.
	 * @return the Array.
	 */
	public static Student[] makeArray(int k) {
		Student[] s = new Student[k];
		Random rng = new Random();
		
		String[] names = 	{"Florian", "Martin", "William", "John", "Lukas", "Thomas", "Franzi", "Berthold"};
		String[] surnames = {"Muster", "Thiem", "Novak", "Obama", "Trump", "Meister", "Mann", "Kellner"};
		
		for(int i = 0; i < s.length; i++) {
			s[i] = new Student(names[rng.nextInt(names.length)], surnames[rng.nextInt(names.length)], i);
		}
		
		return s;
	}

	/** 
	 * Gets the Splitting Character of a CSV-Formatted String. The Splitting Character must be non-Alphabetic.
	 * @param s the String.
	 * @return the Splitting Character. Returns 'e' (Error) if the String is not CSV-Formatted.
	 */
	public static char getSplittingChar(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(!isInAlphabet(s.charAt(i))) {
				return s.charAt(i);
			}
		}
		
		System.err.println("Error splitting Input String: " + s);
		return 'e';
	}
	
	/**
	 * Adds and Element to an Array.
	 * @param a the Array.
	 * @param b the Element.
	 * @return the new Array.
	 */
	public static String[] addToArray(String [] a , String b) {
		String[] n = new String[a.length];
		
		for(int i = 0; i < n.length; i++) {
			if(i < a.length) {
				n[i] = a[i];
			}else {
				n[i] = b;
			}
		}
		
		return n;
	}

	/**
	 * Converts a Student Array into a 2Dimensional Array.
	 * @param s the Array.
	 * @return the 2D Array.
	 */
	public static String[][] StudentArrayTo2D(Student[] s){
		String[][] n = new String[s.length][3];
		
		for(int row = 0; row < n.length; row++) {
			String[] s1 = {Integer.toString(s[row].getIndex()), s[row].getName(), s[row].getSurname()};
			n[row] = s1;
		}
		
		return n;
 	}

	/**
	 * Generates Data for the JTable from a Student Array.
	 * @param s the Array.
	 * @param columnCount the Number of Columns in the Table.
	 * @return
	 */
	public static String[][] StudentArrayToData(Student[] s, int columnCount, int num){
		String[][] n = new String[s.length][columnCount];

		int[][] r = selectRandomColumn(s.length, columnCount - 1, num);
		
		n[0][0] = s[0].getSurname();
		for(int col = 1; col < n[0].length; col++) {
			for(int row = 0; row < n.length; row++) {
				n[row][0] = s[row].getSurname();
				
				n[row][col] = r[col-1][row] == 1 ? "X" : "";
			}
		}
	
		//		print2DArray(r);
		//		print2DArray(n);
		
		return n;
 	}
	
	/**
	 * Returns a 2D-Array, wich has 1 Value of a column, and num Values in each row set to 1. Those are random
	 * @param rowCount Number of Rows.
	 * @param columnCount Number of Columns.
	 * @param num Number of Randomly generated Values that are 1. 
	 * @return the new Array.
	 */
	static int[][] selectRandomColumn(int rowCount, int columnCount, int num) {
		int[][] b = new int[columnCount][rowCount];
		
		for(int rows = 0; rows < b.length; rows++) {
			b[rows] = selectRandomInt(b[rows], num);

			for(int cols = 0; cols < b[0].length; cols++) {
				if(b[rows][cols] == 1) {
					for(int k = rows+1; k < b.length; k++) {
						b[k][cols] = 2;
					}
				}
			}
		}
		
		return b;
	}
	
	/**
	 * Sets a given Number of Elements in an Array to 1.
	 * @param ar the Array.
	 * @param num the Number.
	 * @return the new Array.
	 */
	static int[] selectRandomInt(int[] a, int num) {
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
	 * Sets all X-Values of the Student Array to False.
	 */
	public static void setXFalse() {
		for(int i = 0; i < FrmMain.allStudents.length; i++) {
			FrmMain.allStudents[i].setX(false);
		}
	}

	/**
	 * Copies an Array.
	 * @param o the Array.
	 * @return the new Array.
	 */
	public static Student[] copyArray(Student[] o) {
		Student[] n = new Student[o.length];
		for(int i = 0; i < n.length; i++) {
			n[i] = o[i];
		}
		
		return n;
	}
	
	/**
	 * Creates a Header for a JTable with the First Element being a String, and the other ones being Numbered Weeks.
	 * @param numWeeks the Number of Weeks.
	 * @param s the String.
	 * @return a String Array.
	 */
	public static String[] makeHeader(String s, int numWeeks) {
		String[] header = new String[numWeeks+1];
		
		header[0] = s;
		for(int i = 1; i < header.length; i++) {
			header[i] = "Week " + i;
		}
		
		return header;
	}
	
	/**
	 * Adds an Element to an Array.
	 * @param a the Array.
	 * @param s the Element.
	 * @return the new Array.
	 */
	public static Student[] addToArray(Student[] a, Student s) {
		Student[] n;
		
		if(a != null) {
			n = extendArray(a, 1);	
		}else {
			n = new Student[1];
		}
		
		n[n.length - 1] = s;	
		
		return n;
	}
	
	/**
	 * Extends an Array by a given Amount.
	 * @param a the Array.
	 * @param toExtend the Amount.
	 * @return the new Array.
	 */
	public static Student[] extendArray(Student[] a, int toExtend) {
		Student[] n = new Student[a.length + toExtend];
		
		for(int i = 0; i < n.length; i++) {
			if(i < a.length) {
				n[i] = a[i];
			}else {
				n[i] = new Student();
			}
		}
		
		return n;
	}
	
	/**
	 * Extends an Array by a given Amount.
	 * @param a the Array.
	 * @param toExtend the Amount.
	 * @return the new Array.
	 */
	public static boolean[] extendArray(boolean[] a, int toExtend) {
		boolean[] n = new boolean[a.length + toExtend];
		
		for(int i = 0; i < n.length; i++) {
			if(i < a.length) {
				n[i] = a[i];
			}else {
				n[i] = false;
			}
		}
		
		return n;
	}
	
	/** 
	 * Converts a CSV-Formatted String to a String Array.
	 * @param csv the String.
	 * @param splittingChar the Splitting Character.
	 * @return the Array.
	 */
	public static String[] csvToArray(String csv, char splittingChar) {
		if(csv != null) {
			return csv.split(Character.toString(splittingChar));
		}else {
			return null;
		}
	}
	
	/**
	 * Checks if a char is in the Alphabet (German-Austria);
	 * @param c the Character.
	 * @return true: is in Alphabet / false: is not in Alphabet.
	 */
	private static boolean isInAlphabet(char c) {
		char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ö', 'ä', 'ü', 'ß'};
		
		c = Character.toLowerCase(c);
		
		for(int i = 0; i < alphabet.length; i++) {
			if(c == alphabet[i]) {
				return true;
			}
		}
		
		return false;
	}
	
	/** 
	 * Prints an Array to the Console.
	 * @param s the Array.
	 */
	public static void printArray(Student[] s) {
		for (Student student : s) {
			System.out.println(student);
			//System.out.println(student.getName() + ";"+student.getSurname());
		}
	}
	
	/** 
	 * Prints an Array to the Console.
	 * @param bar the Array.
	 */
	public static void printArray(boolean[] bar) {
		for (boolean b : bar) {
			System.out.print(b +", ");
			//System.out.println(student.getName() + ";"+student.getSurname());
		}
	}
	
	/**
	 * Prints a 2D Array to the Console.
	 * @param b the Array.
	 */
	public static void print2DArray(boolean[][] b) {
		System.out.println("{");
		for (boolean[] bool : b) {
			System.out.print("{");
			printArray(bool);
			System.out.println("}");
		}
		System.out.println("}");
	}
	
	/**
	 * Prints a 2D Array to the Console.
	 * @param b the Array.
	 */
	public static void print2DArray(int[][] i) {
		System.out.println("{");
		for (int[] inte : i) {
			System.out.print("{");
			printArray(inte);
			System.out.println("}");
		}
		System.out.println("}");
	}
	
	/**
	 * Prints a 2D Array to the Console.
	 * @param s the Array.
	 */
	public static void print2DArray(String[][] s) {
		System.out.println("{");
		for (String[] strings : s) {
			System.out.print("	{");
			printArray(strings);
			System.out.println("}");
		}
		System.out.println("}");
	}
	
	/**
	 * Prints an Array to the Console.
	 * @param strings the String.
	 */
	public static void printArray(String[] strings) {
		for (String string : strings) {
			System.out.print(string + ",");
		}
	}
	
	/**
	 * Prints an Array to the Console.
	 * @param strings the String.
	 */
	public static void printArray(int[] i) {
		for (Integer inte : i) {
			System.out.print(inte + ",");
		}
	}
	
	/**
	 * Prints an Array to the Console.
	 * @param strings the String.
	 */
	public static void printArraySimple(Student[] stu) {
		for (Student s : stu) {
			System.out.print(s.getName() + ", " + s.getSurname());
			System.out.println();
		}
	}

	public static String generateCSVFrom2DArray(String[][] data, String[] header) {
		StringBuilder c = new StringBuilder();
		
		for(int k = 0; k < header.length; k++) {
			c.append(header[k] +";");
		}
		c.append("\n");
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				c.append(data[i][j] + ";");
			}
			c.append("\n");
		}
		
		return c.toString();
	}

	public static void writeData(String path) {
		PrintWriter out;
		try {
			out = 	new PrintWriter(
					new BufferedWriter(
					new OutputStreamWriter(
					new FileOutputStream(path)
					)));
			
			String csv = ArrayOperations.generateCSVFrom2DArray(FrmSchedule.data, FrmSchedule.header);
			
			out.write(csv);
			
			out.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}	
	}
}
