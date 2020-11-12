package mp.progetto.pattern.composite;

import java.util.stream.Stream;

import mp.progetto.pattern.observer.Subject;

public abstract class BuildingCompositeVehicle extends Subject implements AbstractComposite {
	private VehicleComposite vehicle;

	public BuildingCompositeVehicle(int NUMBER) {
		super(NUMBER);
	}

	@Override
	public boolean add(AbstractComponent vehicle) {
		if (this.vehicle == null && vehicle instanceof VehicleComposite) {
			this.vehicle = (VehicleComposite) vehicle;
			notifyObserver();
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (obj instanceof BuildingCompositeVehicle) {
			BuildingCompositeVehicle composite = ((BuildingCompositeVehicle) obj);
			return cond(composite);
		}
		return false;

	}

	private boolean cond(BuildingCompositeVehicle composite) {
		if (vehicle != null)
			return vehicle.equals(composite.vehicle)
					&& super.equals(composite);
		return composite.vehicle == null 
				? true
				: false;
	}

	@Override
	public Stream<AbstractComponent> getComponent() {
		return Stream.of(vehicle);
	}

	@Override
	public void notifyObserver() {
		super.notifyObserver();
	}
	
	public boolean remove(Vehicle vehicle) {
		if (cond(vehicle)) {
			this.vehicle = null;
			notifyObserver();
			return true;
		}
		return false;
	}

	private boolean cond(Vehicle vehicle) {
		return this.vehicle != null &&
				this.vehicle instanceof VehicleComposite &&
				this.vehicle.equals(vehicle);
	}


}
