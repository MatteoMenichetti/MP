package mp.progetto.vehicle.concrete;

import java.util.LinkedList;

import mp.progetto.buildings.concrete.RailComposite;
import mp.progetto.pattern.composite.AbstractComponent;
import mp.progetto.pattern.composite.VehicleComposite;
import mp.progetto.pattern.visitor.Visitor;

public class TrainComposite extends VehicleComposite {

	public TrainComposite(String targa, LinkedList<AbstractComponent> stops, int capacity) {
		super(targa, stops, capacity);
	}

	@Override
	public <T> void accept(Visitor<T> visitor) {
		visitor.visit(this);

	}

	@Override
	public boolean add(AbstractComponent component) {
		return component instanceof RailComposite
		? super.add(component)
		: false;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof TrainComposite ? super.equals((TrainComposite) obj) : false;
	}

}