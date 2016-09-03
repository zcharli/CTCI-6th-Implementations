package chapter3;

import java.rmi.NoSuchObjectException;

public class ThreeOneThreeInOne {

	private int[] _stack;
	private int[] _top;
	private int[] _capacity;
	private int _size;

	public ThreeOneThreeInOne(int size) {
		_size = size;
		int capacity = size * 3;
		_capacity = new int[] { capacity, capacity + 1, capacity + 2 };
		_stack = new int[capacity];
		_top = new int[] { -1, -1, -1 };
	}

	public void push(int stack, int element) {
		if (stack > 3 || stack < 0) {
			throw new IllegalArgumentException();
		}

		if (_top[stack] == _capacity[stack]) {
			throw new IllegalStateException("Stack " + stack + " is full.");
		}

		int index = 0;

		if (_top[stack] == -1) {
			index = stack - 1;
		} else {
			index = _top[stack] + 3;
		}

		_top[stack] = index;

		_stack[index] = element;
	}

	public int pop(int stack) throws IllegalStateException {
		if (stack > 3 || stack < 0) {
			throw new IllegalArgumentException();
		}

		if (_top[stack] == -1) {
			throw new IllegalStateException("This stack has nothing in it");
		}

		int top = _top[stack];
		int ret = _stack[top];
		_stack[top] = 0;
		
		top -= 3;

		if (top < 0) {
			top = -1;
		}
		
		_top[stack] = top;

		return ret;
	}

	public static void main(String[] args) {

	}

}
