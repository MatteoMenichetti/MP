package mp.progetto.vehicle.concrete;

import java.util.LinkedList;

import mp.progetto.buildings.concrete.AirStripComposite;
import mp.progetto.buildings.concrete.GateComposite;
import mp.progetto.pattern.composite.AbstractComponent;
import mp.progetto.pattern.composite.VehicleComposite;
import mp.progetto.pattern.visitor.Visitor;

public class AirPlaneComposite extends VehicleComposite {

	public AirPlaneComposite(String targa, LinkedList<AbstractComponent> stops, int capacity) {
		super(targa, stops, capacity);
	}

	@Override
	public <T> void accept(Visitor<T> visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean add(AbstractComponent component) {
		return condition(component)
		? super.add(component)
		: false;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof AirPlaneComposite
		? super.equals((AirPlaneComposite) obj)
		: false;
	}

	private boolean condition(AbstractComponent component) {
		return component instanceof GateComposite || component instanceof AirStripComposite;
	}
}
