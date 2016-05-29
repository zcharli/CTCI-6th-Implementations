package chapter3;

import java.util.LinkedList;

public class ThreeSixAnimalShelter {

	private LinkedList<Animal> mShelter;
	private LinkedList<Dog> mDogQ;
	private LinkedList<Cat> mCatQ;

	public ThreeSixAnimalShelter() {
		this.mShelter = new LinkedList<>();
		this.mDogQ = new LinkedList<>();
		this.mCatQ = new LinkedList<>();
	}

	public void enqueue(Animal a) throws Exception {

		if (a instanceof Cat) {
			this.mCatQ.add((Cat) a);
		} else if (a instanceof Dog) {
			this.mDogQ.add((Dog) a);
		} else {
			throw new Exception("Shelter cannot accept this animal type.");
		}

		this.mShelter.add(a);
	}

	public Animal dequeueAny() throws Exception {

		if (this.mShelter.size() == 0) {
			throw new Exception("No Animals in shelter");
		}

		Animal a = this.mShelter.poll();

		if (a instanceof Cat) {
			Cat c = (Cat) a;
			this.mCatQ.remove(c);
		} else if (a instanceof Dog) {
			Dog d = (Dog) a;
			this.mDogQ.remove(d);
		}
		return a;
	}

	public Dog dequeueDog() throws Exception {

		if (this.mDogQ.size() == 0) {
			throw new Exception("No dogs in shelter");
		}

		Dog d = this.mDogQ.poll();
		this.mShelter.remove((Animal) d);
		return d;
	}

	public Cat dequeueCat() throws Exception {

		if (this.mCatQ.size() == 0) {
			throw new Exception("No cats in Shelter");
		}

		Cat c = this.mCatQ.poll();
		this.mShelter.remove((Animal) c);
		return c;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Animal a : this.mShelter) {
			if (a instanceof Dog) {
				sb.append(((Dog) a).toString());
			} else if (a instanceof Cat) {
				sb.append(((Cat) a).toString());
			}
			sb.append(", ");
		}

		return sb.toString();
	}

	public static class Cat extends Animal {
		private String name;

		public Cat(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Cat " + name;
		}
	}

	public static class Dog extends Animal {
		private String name;

		public Dog(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Dog " + name;
		}
	}

	public static class Animal {
		@Override
		public String toString() {
			return "Animal";
		}
	}

	public static void main(String[] args) {
		Cat c1 = new Cat("1");
		Cat c2 = new Cat("2");
		Cat c3 = new Cat("3");
		Cat c4 = new Cat("4");
		Cat c5 = new Cat("5");
		Dog d1 = new Dog("1");
		Dog d2 = new Dog("2");
		Dog d3 = new Dog("3");
		Dog d4 = new Dog("4");
		Dog d5 = new Dog("5");
		ThreeSixAnimalShelter s = new ThreeSixAnimalShelter();
		try {
			s.enqueue(c1);
			s.enqueue(d1);
			s.enqueue(c2);
			s.enqueue(d4);
			s.enqueue(c4);
			s.enqueue(c5);
			s.enqueue(d5);
			s.enqueue(d2);
			s.enqueue(c3);
			s.enqueue(d3);
			System.out.println(s.toString());
			s.dequeueAny();
			System.out.println(s.toString());
			s.dequeueCat();
			System.out.println(s.toString());
			s.dequeueDog();
			System.out.println(s.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
