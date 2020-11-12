package mp.progetto.pattern.adapter;

import mp.progetto.buildings.concrete.ControlTowerComposite;
import mp.progetto.pattern.composite.Vehicle;
import mp.progetto.pattern.visitor.Visitor;

public abstract class VehicleVisitorAdapter implements Visitor<Vehicle>  {

	@Override
	public void visit(ControlTowerComposite controltower) {}

}
