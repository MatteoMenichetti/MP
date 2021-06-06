package mp.progetto.buildings.concrete;

import java.util.Collection;

import mp.progetto.pattern.composite.AbstractComponent;
import mp.progetto.pattern.composite.BuildingComposite;
import mp.progetto.pattern.observer.Observer;
import mp.progetto.pattern.visitor.Visitor;

public class ControlTowerComposite extends BuildingComposite {

	public ControlTowerComposite(Collection<AbstractComponent> screens, int NUMBER) {
		super(screens, NUMBER);
	}

	@Override
	public <T> void accept(Visitor<T> visitor) {
		visitor.visit(this);

	}
	
	@Override
	public boolean attach(Observer observer) {
		return this.add((AbstractComponent) observer);
	}

	@Override
	public boolean add(AbstractComponent component) {
		return component instanceof Observer
				? super.attach((Observer) component) && super.add(component)
				: false;
	}
	
	@Override
	public boolean detach(Observer observer) {
		return this.remove((AbstractComponent) observer);
	}

	@Override
	public boolean equals(Object obj) {
		if(!super.equals(obj))
			return false;
		if (obj instanceof ControlTowerComposite)
			return true;
		return false;
	}

	@Override
	public boolean remove(AbstractComponent component) {
		return component instanceof Observer
				? super.detach((Observer) component) && super.remove(component, x -> x instanceof Observer)
				: false;
	}
}
