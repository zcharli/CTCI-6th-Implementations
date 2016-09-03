package chapter3;

import java.util.Arrays;

public class ThreeFiveSortStack {

	private int mSize = 0;
	private int[] mStack;
	private int mCapacity;
	
	public ThreeFiveSortStack(int cap) {
		this.mCapacity = cap;
		this.mSize = 0;
		this.mStack = new int[cap];
	}

	public void push(int e) throws IllegalStateException {
		if(this.mSize == this.mCapacity) {
			throw new IllegalStateException("The stack is already full");
		}
		if(this.mSize == 0) {
			this.mStack[0] = e;
			this.mSize += 1;
			return;
		}
		int idxToSlideOver = 0;
		boolean switchHead = true;
		for (int i = this.mSize - 1; i >= 0; i--) {
			if(this.mStack[i] > e) {
				idxToSlideOver = i;
				switchHead = false;
				break;
			}
		}
		
		for (int i = mSize; i > idxToSlideOver; i--) {
			this.mStack[i] = this.mStack[i - 1];
		}
		if(idxToSlideOver == 0 && switchHead) {
			this.mStack[idxToSlideOver] = e;
		} else {
			this.mStack[idxToSlideOver + 1] = e;
		}
		
		this.mSize += 1;
	}
	
	public int peek() throws IllegalStateException {
		if(this.mSize == 0) {
			throw new IllegalStateException("No elements in the stack");
		}
		return this.mStack[this.mSize - 1];
	}
	
	public int pop() throws IllegalStateException {
		if(this.mSize == 0) {
			throw new IllegalStateException("No elements in the stack");
		}
		int ret = this.mStack[--this.mSize];
		return ret;
	}
	
	public boolean isEmpty() {
		return this.mSize == 0;
	}
	
	@Override 
	public String toString() {
		return Arrays.toString(this.mStack);
	}
	
	public static void main(String[] args) {
		ThreeFiveSortStack s = new ThreeFiveSortStack(10);
		
		try {
			s.push(5);
			s.push(6);
			s.push(9);
			s.push(2);
			s.push(4);
			s.push(7);
			s.push(1);
			s.push(8);
			s.push(10);
			s.push(3);
			System.out.println(s.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
