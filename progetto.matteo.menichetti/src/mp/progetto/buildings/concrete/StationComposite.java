package mp.progetto.buildings.concrete;

import java.util.Collection;

import mp.progetto.pattern.composite.AbstractComponent;
import mp.progetto.pattern.composite.BuildingComposite;
import mp.progetto.pattern.visitor.Visitor;

public class StationComposite extends BuildingComposite {

	public StationComposite(Collection<AbstractComponent> rails, int NUMBER) {
		super(rails, NUMBER);
	}

	@Override
	public <T> void accept(Visitor<T> visitor) {
		visitor.visit(this);

	}

	@Override
	public boolean add(AbstractComponent comp) {
		if (comp instanceof RailComposite)
			return super.add(comp);
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (obj instanceof StationComposite)
			return true;
		return false;
	}

	@Override
	public boolean remove(AbstractComponent component) {
		return super.remove(component, x -> x instanceof RailComposite);
	}

}
