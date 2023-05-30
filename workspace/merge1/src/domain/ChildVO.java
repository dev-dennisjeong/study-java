package domain;

public class ChildVO {
	private String name;
	private int ranking;
	private int number;
	private char gender;
	
	public ChildVO() {;}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return gender + "\t" + name + "\t" + ranking + "\t" + number;
	}
	
	
	
}
