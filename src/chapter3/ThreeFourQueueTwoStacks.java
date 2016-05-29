package chapter3;

import java.util.Arrays;

public class ThreeFourQueueTwoStacks {

	private int mSize;
	private int[] mStack;
	
	private int mCapacity;
	
	public ThreeFourQueueTwoStacks(int capacity) {
		mSize = 0;
		mCapacity = capacity; 
		mStack = new int[capacity];
	}
	
	public void enqueue(int e) throws Exception {
		if(mSize == mCapacity) {
			throw new Exception("Stack is full");
		}
		mStack[mSize++] = e;
	}
	
	public int dequeue() throws Exception {
		if(mSize == 0) {
			throw new Exception("Nothing was in the queue");
		}
		int[] tempStack = new int[mSize];
		for (int i = 0; i < tempStack.length; i++) {
			tempStack[i] = mStack[mSize - i - 1];
		}
		
		int ret = tempStack[mSize - 1]; // Pop top
		mSize -= 1;
		for (int i = 0; i < tempStack.length - 1; i++) {
			mStack[i] = tempStack[mSize - i - 1];
		}
		mStack[mSize] = 0; // Reduce obsolete pointers
		return ret;
	}
	
	@Override 
	public String toString() {
		return Arrays.toString(mStack);
	}
	
	public static void main(String[] args) {
		ThreeFourQueueTwoStacks s = new ThreeFourQueueTwoStacks(10);
		try {
			s.enqueue(1);
			s.enqueue(2);
			s.enqueue(3);
			s.enqueue(4);
			s.enqueue(5);
			s.enqueue(6);
			s.enqueue(7);
			s.enqueue(8);
			System.out.println(s.toString());
			int k = s.dequeue();
			System.out.println(k);
			System.out.println(s.toString());
			
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
	}

}
