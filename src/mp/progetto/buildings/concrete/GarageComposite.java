package mp.progetto.buildings.concrete;

import java.util.Collection;

import mp.progetto.pattern.composite.AbstractComponent;
import mp.progetto.pattern.composite.BuildingComposite;
import mp.progetto.pattern.composite.Vehicle;
import mp.progetto.pattern.visitor.Visitor;

public class GarageComposite extends BuildingComposite {
	private int capacity;
	private int space;

	public GarageComposite(Collection<AbstractComponent> components, int NUMBER, int capacity) {
		super(components, NUMBER);
		this.capacity = capacity;
		this.space = capacity;
	}

	@Override
	public boolean add(AbstractComponent comp) {
		if (space > 0 && comp instanceof Vehicle && super.add((Vehicle) comp)) {
			space--;
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GarageComposite) {
			GarageComposite garage = (GarageComposite) obj;
			return super.equals(obj) && garage.capacity == capacity && garage.space == space;
		}
		return false;
	}

	@Override
	public boolean remove(AbstractComponent comp) {
		if (getComponent().anyMatch(x -> x.equals(comp))) {
			space++;
			return super.remove(comp, x -> x instanceof Vehicle && x.equals(comp));
		}

		return false;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getSpace() {
		return space;
	}

	@Override
	public <T> void accept(Visitor<T> visitor) {
		visitor.visit(this);

	}
}
