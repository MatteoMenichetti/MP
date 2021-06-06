package mp.progetto.pattern.observer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Stream;

import mp.progetto.pattern.adapter.AbstractComponentAdapter;
import mp.progetto.pattern.composite.AbstractComponent;

public class ScreenObserver extends AbstractComponentAdapter implements Observer {
	private Subject subject;
	private LinkedList<AbstractComponent> vehicles;

	public ScreenObserver(LinkedList<AbstractComponent> vehicles) {
		this.vehicles = vehicles;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj instanceof ScreenObserver) {
			ScreenObserver screen = (ScreenObserver) obj;
			if (!vehicles.isEmpty() && !screen.vehicles.isEmpty())
				return vehicles.size() == screen.vehicles.size()
				? vehicles.containsAll(screen.vehicles)
				: false;
			return vehicles.isEmpty() && screen.vehicles.isEmpty()
				? conditionReturn(screen.subject)
				: false;
		}
		return false;
	}
	
	private boolean conditionReturn(Subject subject) {
		return subject != null && this.subject != null
				? subject.getNUMBER() == this.subject.getNUMBER()
				: subject == null && this.subject == null;
	}


	public Subject getSubject() {
		return subject;
	}

	public Iterator<AbstractComponent> getVehicles() {
		return vehicles.iterator();
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public void update() {
		Stream<AbstractComponent> vehicles = subject.getComponent();
		Optional<AbstractComponent> add = vehicles
										.filter(x -> x != null && !this.vehicles.contains(x))
										.findFirst();
		Optional<AbstractComponent> remove = this.vehicles.stream()
												.filter(x -> !this.vehicles.contains(x))
												.findFirst();
		add.ifPresent(x -> this.vehicles.addFirst(x));
		remove.ifPresent(x -> this.vehicles.remove(x));
	}

}
