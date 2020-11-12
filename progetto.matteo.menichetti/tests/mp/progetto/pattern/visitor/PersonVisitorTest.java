package mp.progetto.pattern.visitor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import mp.progetto.buildings.concrete.AirPortComposite;
import mp.progetto.buildings.concrete.AirStripComposite;
import mp.progetto.buildings.concrete.ControlTowerComposite;
import mp.progetto.buildings.concrete.GarageComposite;
import mp.progetto.buildings.concrete.GateComposite;
import mp.progetto.buildings.concrete.RailComposite;
import mp.progetto.buildings.concrete.StationComposite;
import mp.progetto.pattern.composite.AbstractComposite;
import mp.progetto.pattern.composite.BuildingComposite;
import mp.progetto.pattern.composite.BuildingCompositeVehicle;
import mp.progetto.pattern.composite.VehicleComposite;
import mp.progetto.pattern.iterator.Iterator;
import mp.progetto.pattern.observer.ScreenObserver;
import mp.progetto.person.Person;
import mp.progetto.vehicle.concrete.AirPlaneComposite;
import mp.progetto.vehicle.concrete.BusComposite;
import mp.progetto.vehicle.concrete.CarLeaf;
import mp.progetto.vehicle.concrete.MotorBikeLeaf;
import mp.progetto.vehicle.concrete.TrainComposite;

public class PersonVisitorTest {

	PersonVisitor visitor;
	AirPortComposite airport = new AirPortComposite(new ArrayList<>(), 1);
	AirStripComposite airStrip = new AirStripComposite(1);
	AirStripComposite airStrip1 = new AirStripComposite(2);
	ControlTowerComposite tower = new ControlTowerComposite(new ArrayList<>(), 1);
	GarageComposite garage = new GarageComposite(new ArrayList<>(), 1, 10);
	GateComposite gate = new GateComposite(1);
	GateComposite gate1 = new GateComposite(2);
	RailComposite rail = new RailComposite(1);
	RailComposite rail1 = new RailComposite(2);
	StationComposite station = new StationComposite(new ArrayList<>(), 1);

	@Before
	public void initBuildings() {
		airport.add(airStrip);
		airport.add(airStrip1);
		airport.add(gate);
		airport.add(gate1);
		airport.add(garage);
		airport.add(station);
		station.add(rail);
		station.add(rail1);
	}

	@Before
	public void initVehicles() {
		AirPlaneComposite airplane = new AirPlaneComposite("targa", new LinkedList<>(), 100);
		airStrip.add(airplane);

		airplane.addPerson(new Person("Nome", "Cognome"));
		airplane.addPerson(new Person("Nome1", "Cognome1"));

		airplane = new AirPlaneComposite("targa1", new LinkedList<>(), 100);
		gate.add(airplane);
		airplane.addPerson(new Person("Nome", "Cognome"));
		airplane.addPerson(new Person("Nome1", "Cognome1"));

		garage.add(new CarLeaf("targa2"));
		garage.add(new MotorBikeLeaf("targa3"));
		rail.add(new TrainComposite("targa4", new LinkedList<>(), 100));

		tower.add(new ScreenObserver(new LinkedList<>()));
	}

	@Test
	public void testAirPortComposite() {
		testBuilding(airport);
	}

	@Test
	public void testAirStripComposite() {
		testBuilding(airStrip);
	}

	@Test
	public void testControlTowerComposite() {
		methodAdaptee(tower);
	}

	@Test
	public void testGarageComposite() {
		methodAdaptee(garage);
	}

	private void methodAdaptee(AbstractComposite building) {
		visitor = new PersonVisitor();
		building.accept(visitor);
		assertThat(visitor.getAccumulator()).toIterable().containsExactly();
	}

	@Test
	public void testGateComposite() {
		testBuilding(gate);
	}

	@Test
	public void testRailComposite() {
		testBuilding(rail);
	}

	@Test
	public void testStationComposite() {
		testBuilding(station);
	}

	@Test
	public void testAirPlaneComposite() {
		AirPlaneComposite airplane = new AirPlaneComposite("Targa", new LinkedList<>(), 1);
		testVehicle(airplane);
	}
	@Test
	public void testBusComposite() {
		BusComposite bus = new BusComposite("Targa", new LinkedList<>(), 1);
		testVehicle(bus);
	}
	@Test
	public void testTrainComposite() {
		TrainComposite train = new TrainComposite("Targa", new LinkedList<>(), 1);
		testVehicle(train);
	}
	
	private void testVehicle(VehicleComposite vehicle) {
		visitor = new PersonVisitor();
		Person person = new Person("Nome", "Cognome");

		vehicle.accept(visitor);
		assertThat(visitor.getAccumulator()).toIterable().containsExactly();

		vehicle.addPerson(person);
		vehicle.accept(visitor);
		assertThat(visitor.getAccumulator()).toIterable().containsExactly(person);
	}

	private void testBuilding(BuildingCompositeVehicle building) {
		Optional<VehicleComposite> op = building.getComponent().filter(x -> x != null).findFirst()
				.map(x -> ((VehicleComposite) x));

		visitor = new PersonVisitor();
		building.accept(visitor);
		assertThat(visitor.getAccumulator()).toIterable().allMatch(x -> {
			Iterator<Person> people = op.get().getPerson();
			while (people.hasNext())
				if (x.equals(people.next()))
					return true;
			return true;
		});
	}

	private void testBuilding(AbstractComposite building) {
		building.getComponent().filter(x -> x instanceof BuildingCompositeVehicle)
				.forEach(x -> testBuilding((BuildingCompositeVehicle) x));
		building.getComponent().filter(x -> x instanceof BuildingComposite)
				.forEach(x -> testBuilding((AbstractComposite) x));
	}
}
