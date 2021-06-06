package mp.progetto.pattern.composite;

public abstract class VehicleLeaf extends Vehicle{
	public VehicleLeaf(String targa) {
		super(targa);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof VehicleLeaf ? super.equals(obj) : false;
	}

}
