package mp.progetto.buildings.concrete;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

import org.junit.Test;

import mp.progetto.pattern.observer.ScreenObserver;
import mp.progetto.vehicle.concrete.AirPlaneComposite;
import mp.progetto.vehicle.concrete.CarLeaf;
import mp.progetto.vehicle.concrete.TrainComposite;

public class AirPortCompositeTest {
	AirPortComposite airport= new AirPortComposite(new LinkedList<>(), 1);
	StationComposite station= new StationComposite(new LinkedList<>(), 1);
	AirStripComposite airstrip = new AirStripComposite(1);
	GateComposite gate = new GateComposite(2);
	AirPlaneComposite airplane = new AirPlaneComposite("Airplane", new LinkedList<>(), 2);
	AirPlaneComposite airplane1 = new AirPlaneComposite("Airplane", new LinkedList<>(), 2);
	TrainComposite train = new TrainComposite("Train", new LinkedList<>(), 2);
	RailComposite rail = new RailComposite(1);
	ScreenObserver screen = new ScreenObserver(new LinkedList<>());

	@Test
	public void testAdd() {
		assertThat(airport.add(airplane)).isFalse();
		assertThat(airport.add(gate)).isTrue();
		assertThat(airport.add(gate)).isFalse();
		assertThat(airport.add(airstrip)).isTrue();
		
		assertThat(airport.getComponent().iterator()).toIterable().containsExactly(gate, airstrip);

		assertThat(airport.add(new CarLeaf("Targa"))).isFalse();
	}

	@Test
	public void testAttach() {
		assertThat(airport.attach(screen)).isTrue();
		assertThat(airport.attach(screen)).isTrue();
		assertThat(airport.attach(null)).isFalse();
	}

	@Test
	public void testDetach() {
		assertThat(airport.detach(screen)).isFalse();

		airport.attach(screen);

		assertThat(airport.detach(screen)).isTrue();
		assertThat(airport.detach(screen)).isFalse();
	}

	@Test
	public void testEquals() {
		AirPortComposite airport1 = new AirPortComposite(new LinkedList<>(), 1);
		
		airport.add(gate);
		airport.add(airstrip);
		airport.add(station);
		station.add(rail);
		
		rail.add(train);
		gate.add(airplane);
		gate.add(airplane1);

		assertThat(airport.equals(airport1)).isFalse();

		airport1.add(gate);
		airport1.add(airstrip);
		airport1.add(station);
		assertThat(airport.equals(airport1)).isTrue();
	}

	@Test
	public void testRemove() {
		assertThat(airport.remove(gate)).isFalse();
		airport.add(gate);
		assertThat(airport.remove(gate)).isTrue();
		assertThat(airport.getComponent().iterator()).toIterable().containsExactly();
	}
}
