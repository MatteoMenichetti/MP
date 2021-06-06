package mp.progetto.pattern.observer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

import org.junit.Test;

import mp.progetto.buildings.concrete.GateComposite;
import mp.progetto.pattern.composite.BuildingCompositeVehicle;
import mp.progetto.vehicle.concrete.AirPlaneComposite;

public class ScreenObserverTest {
	BuildingCompositeVehicle subject = new GateComposite(1);
	ScreenObserver screen = new ScreenObserver(new LinkedList<>());
	ScreenObserver screen1 = new ScreenObserver(new LinkedList<>());
	AirPlaneComposite airplane = new AirPlaneComposite("1", new LinkedList<>(), 1);
	AirPlaneComposite airplane1 = new AirPlaneComposite("2", new LinkedList<>(), 1);

	@Test
	public void testEquals() {
		assertThat(screen).isEqualTo(screen1);

		subject.attach(screen);
		assertThat(screen).isNotEqualTo(screen1);
		subject.attach(screen1);

		assertThat(screen).isEqualTo(screen1);

		subject.add(airplane);
		assertThat(screen).isEqualTo(screen1);

		subject.add(airplane1);
		assertThat(screen).isEqualTo(screen1);

		subject.remove(airplane);
		assertThat(screen).isEqualTo(screen1);

		subject.remove(airplane1);
		assertThat(screen).isEqualTo(screen1);
	}

	@Test
	public void testSetSubject() {
		subject.attach(screen);
		assertThat(screen.getSubject()).isEqualTo(subject);
	}

	@Test
	public void testUpdateAndGetVehicles() {
		subject.attach(screen);
		subject.add(airplane);
		screen.getVehicles();
		assertThat(screen.getVehicles()).toIterable().containsExactly(airplane);
	}

}
