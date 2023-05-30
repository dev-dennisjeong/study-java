package hashMapTest;

public class Product {
	private int number;
	private String name;
	private int price;
	private int inventory;
	
	public Product() {;}

	public Product(int number, String name, int price, int inventory) {
		super();
		this.number = number;
		this.name = name;
		this.price = price;
		this.inventory = inventory;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "Product [number=" + number + ", name=" + name + ", price=" + price + ", inventory=" + inventory + "]";
	}
	
	
	
}
