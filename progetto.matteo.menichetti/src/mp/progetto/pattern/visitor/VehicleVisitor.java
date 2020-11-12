package mp.progetto.pattern.visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import mp.progetto.buildings.concrete.AirPortComposite;
import mp.progetto.buildings.concrete.AirStripComposite;
import mp.progetto.buildings.concrete.GarageComposite;
import mp.progetto.buildings.concrete.GateComposite;
import mp.progetto.buildings.concrete.RailComposite;
import mp.progetto.buildings.concrete.StationComposite;
import mp.progetto.pattern.adapter.VehicleVisitorAdapter;
import mp.progetto.pattern.composite.AbstractComposite;
import mp.progetto.pattern.composite.BuildingCompositeVehicle;
import mp.progetto.pattern.composite.Vehicle;
import mp.progetto.vehicle.concrete.AirPlaneComposite;
import mp.progetto.vehicle.concrete.BusComposite;
import mp.progetto.vehicle.concrete.CarLeaf;
import mp.progetto.vehicle.concrete.MotorBikeLeaf;
import mp.progetto.vehicle.concrete.TrainComposite;

public class VehicleVisitor extends VehicleVisitorAdapter {
	private Collection<Vehicle> vehicles = new ArrayList<>();

	@Override
	public void visit(AirPortComposite airport) {
		visitBuildingComposite(airport);
	}

	@Override
	public void visit(AirStripComposite airstrip) {
		visitBuildingCompositeVehicle(airstrip);

	}

	@Override
	public void visit(GarageComposite garage) {
		visitBuildingComposite(garage);
	}

	@Override
	public void visit(GateComposite gate) {
		visitBuildingCompositeVehicle(gate);
	}

	@Override
	public void visit(RailComposite rail) {
		visitBuildingCompositeVehicle(rail);
	}

	@Override
	public void visit(StationComposite station) {
		visitBuildingComposite(station);
	}

	@Override
	public void visit(AirPlaneComposite airplane) {
		vehicles.add(airplane);
	}

	@Override
	public void visit(BusComposite bus) {
		vehicles.add(bus);
	}

	@Override
	public void visit(CarLeaf car) {
		vehicles.add(car);
	}

	@Override
	public void visit(MotorBikeLeaf bike) {
		vehicles.add(bike);
	}

	@Override
	public void visit(TrainComposite train) {
		vehicles.add(train);
	}

	@Override
	public void visit(BuildingCompositeVehicle building) {
		building.accept(this);
	}

	@Override
	public void visit(Vehicle vehicle) {
		vehicle.accept(this);
	}

	private void visitBuildingCompositeVehicle(BuildingCompositeVehicle building) {
		building.getComponent().filter(x -> x != null).findFirst().ifPresent(x -> x.accept(this));
	}

	private void visitBuildingComposite(AbstractComposite building) {
		building.getComponent().forEach(x -> x.accept(this));
	}

	@Override
	public Iterator<Vehicle> getAccumulator() {
		return vehicles.iterator();
	}

}
