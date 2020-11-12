package mp.progetto.vehicle.concrete;

import mp.progetto.pattern.composite.VehicleLeaf;
import mp.progetto.pattern.visitor.Visitor;

public class MotorBikeLeaf extends VehicleLeaf {

	public MotorBikeLeaf(String targa) {
		super(targa);
	}

	@Override
	public <T> void accept(Visitor<T> visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof MotorBikeLeaf ? super.equals((MotorBikeLeaf) obj) : false;
	}
}
