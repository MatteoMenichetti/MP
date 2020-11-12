package mp.progetto.pattern.composite;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import mp.progetto.pattern.iterator.Iterator;
import mp.progetto.pattern.iterator.PersonIterator;
//import mp.progetto.pattern.iterator.Iterator;
import mp.progetto.person.Person;

public abstract class VehicleComposite extends Vehicle implements AbstractComposite {
	private LinkedList<AbstractComponent> stops;
	private Person[] people;
	private int index;

	public VehicleComposite(String targa, LinkedList<AbstractComponent> stops, int capacity) {
		super(targa);
		this.stops = stops;
		people = new Person[capacity];
	}

	@Override
	public boolean add(AbstractComponent component) {
		if (component instanceof BuildingComposite || component instanceof BuildingCompositeVehicle)
			return !stops.contains(component) ? stops.add(component) : false;
		return false;
	}

	public boolean addPerson(Person person) {
		if (index < people.length && !isPresent(person)) {
			people[index++] = person;
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj))
			if (obj instanceof VehicleComposite) {
				VehicleComposite vehicle = (VehicleComposite) obj;
				return index == vehicle.index && equalsPerson(vehicle) && equalsStop(vehicle);
			}
		return false;
	}

	private boolean equalsStop(VehicleComposite vehicle) {
		java.util.Iterator<AbstractComponent> iteratorVehicle = vehicle.stops.iterator();
		java.util.Iterator<AbstractComponent> iterator = stops.iterator();
		while (iterator.hasNext() && iteratorVehicle.hasNext())
			if (!iterator.next().equals(iteratorVehicle.next()))
				return false;
		if (iterator.hasNext() == iteratorVehicle.hasNext())
			return true;
		return false;
	}

	private boolean equalsPerson(VehicleComposite vehicle) {
		Iterator<Person> person = this.getPerson();
		Iterator<Person> personVehicle = vehicle.getPerson();
		while (person.hasNext() && personVehicle.hasNext())
			if (!person.next().equals(personVehicle.next()))
				return false;
		if (person.hasNext() == personVehicle.hasNext())
			return true;
		return false;
	}
	
	@Override
	public Stream<AbstractComponent> getComponent() {
		return stops.stream();
	}

	public Iterator<Person> getPerson() {
		return new PersonIterator() {
			int current = 0;

			public boolean hasNext() {
				return current < index ? true : false;
			}

			public Person next() {
				if (current >= index)
					throw new NoSuchElementException();
				return people[current++];
			}
		};
	}
	
	private boolean isPresent(Person person) {
		for (Person p : people) {
			if (p == null)
				break;
			if (person.equals(p))
				return true;
		}
		return false;
	}

	@Override
	public boolean remove(AbstractComponent component) {
		return stops.remove(component);
	}

	public void removePeople() {
		people = new Person[people.length];
		index = 0;
	}
}
