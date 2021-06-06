package mp.progetto.vehicle.concrete;

import java.util.LinkedList;

import mp.progetto.pattern.composite.AbstractComponent;
import mp.progetto.pattern.composite.VehicleComposite;
import mp.progetto.pattern.visitor.Visitor;

public class BusComposite extends VehicleComposite {

	public BusComposite(String targa, LinkedList<AbstractComponent> stops, int capacity) {
		super(targa, stops, capacity);
	}

	@Override
	public <T> void accept(Visitor<T> visitor) {
		visitor.visit(this);

	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof BusComposite
		? super.equals((BusComposite) obj)
		: false;
	}

}
