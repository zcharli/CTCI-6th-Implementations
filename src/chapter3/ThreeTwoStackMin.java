package chapter3;

import java.util.Stack;

public class ThreeTwoStackMin<T  extends Comparable<T>> extends Stack<T> {

	private T min = null;
	
	public ThreeTwoStackMin() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public T pop() {
		return super.pop();
	}
	
	@Override
	public T push(T e) {
		if (min == null) {
			min = e;
		} else {
			min = min.compareTo(e) < 0 ? min : e;
		}
		super.push(e);
		return e;
	}

}
