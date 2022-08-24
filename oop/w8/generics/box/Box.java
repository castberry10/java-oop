package w8.generics.box;

public class Box<T> {
	
	T contents;
	
	public Box(T initialContents) {
		this.contents = initialContents;
	}
	
	public T getContents() {
		return contents;
	}

	public void setContents(T newcontents) {
		this.contents = newcontents;
	}

	@Override
	public String toString() {
		return contents.toString();
	}
	

	

	

}
