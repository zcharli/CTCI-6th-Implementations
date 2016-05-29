package chapter3;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Arrays;

public class ThreeThreeStackOfPlates {

	private int _capacity;
	private ArrayList<int[]> _stacks;
	private int[] _head;
	private ArrayList<Integer> _stackSize;
	
	public ThreeThreeStackOfPlates(int capacity) {
		_capacity = capacity;
		_stacks = new ArrayList<>();
		_head = new int[_capacity];
		_stackSize = new ArrayList<>();
		_stackSize.add(0);
		_stacks.add(_head);
	}
	
	public int pop() throws NoSuchObjectException {
		if(_stacks.size() == 1 && _stackSize.get(0) == 0) {
			throw new NoSuchObjectException("Element not found");
		}
		int lastStack = _stackSize.get(_stackSize.size() - 1) - 1;
		int ret = _stacks.get(_stackSize.size() - 1)[lastStack];
		_stackSize.set(_stackSize.size() - 1, _stackSize.get(_stackSize.size() - 1) - 1);
		int[] stack = _stacks.get(_stacks.size() - 1);
		stack[lastStack] = 0;
		return ret;
	}
	
	public int popAt(int i) throws NoSuchObjectException {
		if (i < 0 || i >= _stacks.size()) {
			throw new IllegalArgumentException();
		}
		if(_stackSize.get(i) == 0) {
			throw new NoSuchObjectException("Element not found");
		}
		int ret = _stacks.get(i)[_stackSize.get(i) - 1];
		int[] s = _stacks.get(i);
		s[s.length - 1] = 0;
		_stackSize.set(i, _stackSize.get(i) - 1);
		return ret;
	}
	
	public void push(int e) {
		if(_stackSize.get(_stackSize.size() - 1) == _capacity) {
			_stackSize.add(0);
			_stacks.add(new int[_capacity]);
		}
		int[] stack = _stacks.get(_stacks.size() - 1);
		stack[_stackSize.get(_stackSize.size() - 1)] = e;
		_stackSize.set(_stackSize.size() - 1, _stackSize.get(_stackSize.size() - 1) + 1);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int[] a: _stacks) {
			sb.append(Arrays.toString(a));
			sb.append(",");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		ThreeThreeStackOfPlates s = new ThreeThreeStackOfPlates(2);
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);
		s.push(7);
		s.push(8);
		System.out.println(s.toString());
		try {
			int i = s.popAt(2);
			System.out.println(i);
			System.out.println(s.toString());
			int k = s.pop();
			System.out.println(k);
			System.out.println(s.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
