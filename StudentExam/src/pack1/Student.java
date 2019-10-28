package pack1;

public class Student {
	String name, surname;
	int index;
	boolean isX = false;
	
	public Student() {
		name = "John";
		surname = "Doe";
		index = 0;
	}

	public Student(String name, String surname, int index) {
		super();
		this.name = name;
		this.surname = surname;
		this.index = index;
	}

	@Override
	public String toString() {
		return "Student [getName()=" + getName() + ", getSurname()=" + getSurname() + ", getIndex()=" + getIndex()
				+ ", isX()=" + isX() + "]";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the isX
	 */
	public boolean isX() {
		return isX;
	}

	/**
	 * @param isX the isX to set
	 */
	public void setX(boolean isX) {
		this.isX = isX;
	}
	
	
}
