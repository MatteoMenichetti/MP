package mp.progetto.pattern.observer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Stream;

import mp.progetto.pattern.composite.AbstractComponent;

public abstract class Subject {

	private Collection<Observer> observers = new LinkedList<>();
	private final int NUMBER;

	public Subject(int NUMBER) {
		this.NUMBER = NUMBER;
	}

	public boolean attach(Observer observer) {
		if(observer == null)
			return false;
		observer.setSubject(this);
		return observers.add(observer);
	}

	public boolean detach(Observer observer) {
		return observers.remove(observer);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj instanceof Subject) {
			Subject subject = ((Subject) obj);
			if (condition(subject.observers))
				return NUMBER == subject.NUMBER
				? this.observers.containsAll(subject.observers)
				: false;
			return NUMBER == subject.NUMBER && this.observers.isEmpty() && observers.isEmpty();
		}
		return false;
	}

	private boolean condition(Collection<Observer> observers) {
		return !this.observers.isEmpty() &&
				!observers.isEmpty() &&
				this.observers.size() == observers.size();
	}
	
	public abstract Stream<AbstractComponent> getComponent();

	
	public int getNUMBER() {
		return NUMBER;
	}
	
	protected void notifyObserver() {
		if (observers.size() > 0)
			observers.forEach(Observer::update);
	}

}
