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
import mp.progetto.pattern.composite.AbstractComponent;
import mp.progetto.pattern.composite.AbstractComposite;
import mp.progetto.pattern.composite.BuildingComposite;
import mp.progetto.pattern.composite.BuildingCompositeVehicle;
import mp.progetto.pattern.composite.Vehicle;
import mp.progetto.vehicle.concrete.AirPlaneComposite;
import mp.progetto.vehicle.concrete.BusComposite;
import mp.progetto.vehicle.concrete.CarLeaf;
import mp.progetto.vehicle.concrete.MotorBikeLeaf;
import mp.progetto.vehicle.concrete.TrainComposite;

public class VehicleVisitorTest {

	VehicleVisitor visitor;
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
		airStrip.add(new AirPlaneComposite("targa", new LinkedList<>(), 100));
		gate.add(new AirPlaneComposite("targa1", new LinkedList<>(), 100));
		garage.add(new CarLeaf("targa2"));
		garage.add(new MotorBikeLeaf("targa3"));
		rail.add(new TrainComposite("targa4", new LinkedList<>(), 100));
	}

	@Test
	public void testVisitAirportComposite() {
		testBuilding(airport);
	}

	@Test
	public void testVisitAirStripComposite() {
		testBuilding(airStrip);
		testBuilding(airStrip1);
	}

	@Test
	public void testVisitControlTowerComposite() {
		ControlTowerComposite tower = new ControlTowerComposite(new LinkedList<>(), 1);
		visitor = new VehicleVisitor();
		tower.accept(visitor);
		assertThat(visitor.getAccumulator()).toIterable().containsExactly();
	}

	@Test
	public void testVisitGarageComposite() {
		testBuilding(garage);
	}

	@Test
	public void testVisitGateComposite() {
		testBuilding(gate);
		testBuilding(gate1);
	}

	@Test
	public void testVisitRailComposite() {
		testBuilding(rail);
		testBuilding(rail1);
	}

	@Test
	public void testVisitStationComposite() {
		testBuilding(station);
	}

	@Test
	public void testVisitAirPlaneComposite() {
		testVehicle(new AirPlaneComposite("targa", new LinkedList<>(), 1));
	}

	@Test
	public void testVisitBusComposite() {
		testVehicle(new BusComposite("targa", new LinkedList<>(), 1));
	}

	@Test
	public void testVisitCarLeaf() {
		testVehicle(new CarLeaf("targa"));
	}

	@Test
	public void testVisitMotorBikeLeaf() {
		testVehicle(new MotorBikeLeaf("targa"));
	}

	@Test
	public void testVisitTrainComposite() {
		testVehicle(new TrainComposite("targa", new LinkedList<>(), 1));
	}

	private void testVehicle(Vehicle vehicle) {
		visitor = new VehicleVisitor();
		vehicle.accept(visitor);
		assertThat(visitor.getAccumulator()).toIterable().containsExactly(vehicle);
	}
	
	private void testBuilding(AbstractComposite building) {
		building.getComponent().filter(x -> x instanceof BuildingComposite)
				.forEach(x -> testBuilding((AbstractComposite) x));
		building.getComponent().filter(x -> x instanceof BuildingCompositeVehicle)
				.forEach(x -> testBuilding((BuildingCompositeVehicle) x));
	}

	private void testBuilding(BuildingCompositeVehicle building) {
		Optional<AbstractComponent> optional = building.getComponent().filter(x -> x != null).findFirst();
		visitor = new VehicleVisitor();
		building.accept(visitor);
		if (optional.isPresent()) {
			assertThat(visitor.getAccumulator()).toIterable().containsExactly((Vehicle) optional.get());
		} else {
			assertThat(visitor.getAccumulator()).toIterable().containsExactly();
		}
	}
}
