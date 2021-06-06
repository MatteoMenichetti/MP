package mp.progetto.pattern.composite;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

import mp.progetto.pattern.observer.Subject;

public abstract class BuildingComposite extends Subject implements AbstractComposite {
	private Collection<AbstractComponent> components;

	public BuildingComposite(Collection<AbstractComponent> components, int NUMBER) {
		super(NUMBER);
		this.components = components;
	}

	@Override
	public boolean add(AbstractComponent comp) {
		return !components.contains(comp)
			    ? components.add(comp)
				: false;
	}

	protected boolean remove(AbstractComponent comp, Predicate<AbstractComponent> pred) {
		return components.removeIf(pred);
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (obj instanceof BuildingComposite) {
			BuildingComposite composite = ((BuildingComposite) obj);
			return composite.components.containsAll(components);
		}
		return false;
	}

	@Override
	public Stream<AbstractComponent> getComponent() {
		return components.stream();
	}

	@Override
	public void notifyObserver() {
		super.notifyObserver();
	}
}
