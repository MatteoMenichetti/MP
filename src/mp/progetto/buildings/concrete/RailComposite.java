package mp.progetto.buildings.concrete;

import mp.progetto.pattern.composite.AbstractComponent;
import mp.progetto.pattern.composite.BuildingCompositeVehicle;
import mp.progetto.pattern.visitor.Visitor;
import mp.progetto.vehicle.concrete.TrainComposite;

public class RailComposite extends BuildingCompositeVehicle {

	public RailComposite(int NUMBER) {
		super(NUMBER);
	}

	public boolean add(AbstractComponent train) {
		if (train instanceof TrainComposite)
			return super.add(train);
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (obj instanceof RailComposite)
			return true;
		return false;
	}

	@Override
	public boolean remove(AbstractComponent train) {
		if (train instanceof TrainComposite)
			return super.remove((TrainComposite) train);
		return false;
	}

	@Override
	public <T> void accept(Visitor<T> visitor) {
		visitor.visit(this);

	}
}
