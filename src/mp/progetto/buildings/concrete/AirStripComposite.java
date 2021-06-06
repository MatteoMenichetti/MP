package mp.progetto.buildings.concrete;

import mp.progetto.pattern.composite.AbstractComponent;
import mp.progetto.pattern.composite.BuildingCompositeVehicle;
import mp.progetto.pattern.visitor.Visitor;
import mp.progetto.vehicle.concrete.AirPlaneComposite;

public class AirStripComposite extends BuildingCompositeVehicle {

	public AirStripComposite(int NUMBER) {
		super(NUMBER);
	}
	@Override
	public <T> void accept(Visitor<T> visitor) {
		visitor.visit(this);

	}

	@Override
	public boolean add(AbstractComponent airplane) {
		if (airplane instanceof AirPlaneComposite)
			return super.add((AirPlaneComposite) airplane);
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (obj instanceof AirStripComposite)
			return true;
		return false;
	}

	@Override
	public boolean remove(AbstractComponent airplane) {
		if (airplane instanceof AirPlaneComposite)
			return super.remove((AirPlaneComposite) airplane);
		return false;
	}
}
