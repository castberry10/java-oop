package w7.dog2;

public class Yorki extends Dog {
	private static int averageWeight = 4;
	public Yorki(String name) {
		super(name);
	}

	public String speak() {
		return "yo yo!";
	}
	public int getAverageWeight() {
		return averageWeight;
	}
	public String toString() {
		
		return String.format("Yorki[name=%s]", name);
	}

}