package mp.progetto.pattern.visitor;

import java.util.Iterator;

import mp.progetto.buildings.concrete.AirPortComposite;
import mp.progetto.buildings.concrete.AirStripComposite;
import mp.progetto.buildings.concrete.ControlTowerComposite;
import mp.progetto.buildings.concrete.GarageComposite;
import mp.progetto.buildings.concrete.GateComposite;
import mp.progetto.buildings.concrete.RailComposite;
import mp.progetto.buildings.concrete.StationComposite;
import mp.progetto.pattern.composite.BuildingCompositeVehicle;
import mp.progetto.pattern.composite.Vehicle;
import mp.progetto.vehicle.concrete.AirPlaneComposite;
import mp.progetto.vehicle.concrete.BusComposite;
import mp.progetto.vehicle.concrete.CarLeaf;
import mp.progetto.vehicle.concrete.MotorBikeLeaf;
import mp.progetto.vehicle.concrete.TrainComposite;

public interface Visitor<T> {
	public void visit(AirPortComposite airport);

	public void visit(AirStripComposite airstrip);

	public void visit(ControlTowerComposite controltower);

	public void visit(GarageComposite garage);

	public void visit(GateComposite gate);

	public void visit(RailComposite rail);

	public void visit(StationComposite station);

	public void visit(AirPlaneComposite airplane);

	public void visit(BusComposite bus);

	public void visit(CarLeaf car);

	public void visit(MotorBikeLeaf bike);

	public void visit(TrainComposite train);

	public void visit(BuildingCompositeVehicle buildingCompositeVehicle);

	public void visit(Vehicle vehicle);

	public Iterator<T> getAccumulator();

}
