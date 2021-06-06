package mp.progetto.buildings.concrete;

import mp.progetto.pattern.composite.AbstractComponent;
import mp.progetto.pattern.composite.BuildingCompositeVehicle;
import mp.progetto.pattern.visitor.Visitor;
import mp.progetto.vehicle.concrete.AirPlaneComposite;

public class GateComposite extends BuildingCompositeVehicle {

	public GateComposite(int NUMBER) {
		super(NUMBER);
	}

	@Override
	public boolean add(AbstractComponent airplane) {
		if (airplane instanceof AirPlaneComposite)
			return super.add((AirPlaneComposite) airplane);
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GateComposite)
			return super.equals(obj);
		return false;
	}

	@Override
	public boolean remove(AbstractComponent airplane) {
		if (airplane instanceof AirPlaneComposite)
			return super.remove((AirPlaneComposite) airplane);
		return false;
	}

	@Override
	public <T> void accept(Visitor<T> visitor) {
		visitor.visit(this);

	}

}
