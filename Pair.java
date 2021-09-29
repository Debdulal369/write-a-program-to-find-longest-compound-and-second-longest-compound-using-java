// used to store a pair of word and 
public class Pair<A> {
	
	private A first;
	private A second;
	
	public Pair(A first, A second) {
		this.first = first;
		this.second = second;
	}
	// return first element
	public A first() {
		return first;
	}
	// return second element
	public A second() {
		return second;
	}
}