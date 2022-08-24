package w7.dog2;

abstract public class Dog {

	protected String name;

	// 이 클래스는 이미 생성자를 갖고 있으므로
	// (파라미터 없는) 기본생성자가 컴파일러에 의해 자동으로 삽입되지 않는다.
	
	public Dog(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dog other = (Dog) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String speak() {
		return "Mung mung!";
	}

	abstract public int getAverageWeight();
	
	public String toString() {
		
		return String.format("Dog[name=%s]", name);
	}
}