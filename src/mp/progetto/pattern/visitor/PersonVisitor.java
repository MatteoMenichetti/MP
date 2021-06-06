package mp.progetto.pattern.visitor;

import java.util.ArrayList;
import java.util.Collection;

import mp.progetto.buildings.concrete.AirPortComposite;
import mp.progetto.buildings.concrete.AirStripComposite;
import mp.progetto.buildings.concrete.GateComposite;
import mp.progetto.buildings.concrete.RailComposite;
import mp.progetto.buildings.concrete.StationComposite;
import mp.progetto.pattern.adapter.PersonVisitorAdapter;
import mp.progetto.pattern.composite.AbstractComposite;
import mp.progetto.pattern.composite.BuildingCompositeVehicle;
import mp.progetto.pattern.composite.Vehicle;
import mp.progetto.pattern.composite.VehicleComposite;
import mp.progetto.pattern.iterator.Iterator;
import mp.progetto.person.Person;
import mp.progetto.vehicle.concrete.AirPlaneComposite;
import mp.progetto.vehicle.concrete.BusComposite;
import mp.progetto.vehicle.concrete.TrainComposite;

public class PersonVisitor extends PersonVisitorAdapter implements Visitor<Person> {
	private Collection<Person> people = new ArrayList<>();

	@Override
	public void visit(AirPortComposite airport) {
		visitBuildingComposite(airport);
	}

	@Override
	public void visit(AirStripComposite airstrip) {
		visitBuildingCompositeVehicle(airstrip);
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
		addPeople(airplane);
	}

	@Override
	public void visit(BusComposite bus) {
		addPeople(bus);
	}

	@Override
	public void visit(TrainComposite train) {
		addPeople(train);
	}

	private void addPeople(VehicleComposite vehicle) {
		Iterator<Person> people = vehicle.getPerson();
		while (people.hasNext()) {
			Person person = people.next();
			if (person != null)
				this.people.add(person);
		}
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
	public java.util.Iterator<Person> getAccumulator() {
		return people.iterator();
	}

}
