package arrayListTask;

public class Love {
	private String phoneNumber;
	private String name;
	private int age;
	
	 public Love() {;}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Love [phoneNumber=" + phoneNumber + ", name=" + name + ", age=" + age + "]";
	}
	 
	 
	
}
