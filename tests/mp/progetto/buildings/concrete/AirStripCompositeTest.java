package mp.progetto.buildings.concrete;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

import org.junit.Test;

import mp.progetto.pattern.observer.ScreenObserver;
import mp.progetto.vehicle.concrete.AirPlaneComposite;
import mp.progetto.vehicle.concrete.TrainComposite;

public class AirStripCompositeTest {
	AirStripComposite airStrip = new AirStripComposite(1);
	AirPlaneComposite airplane1 = new AirPlaneComposite("1", new LinkedList<>(), 1);
	AirPlaneComposite airplane2 = new AirPlaneComposite("2", new LinkedList<>(), 1);

	@Test
	public void testAdd() {
		assertThat(airStrip.add(airplane1)).isTrue();
		assertThat(airStrip.add(airplane1)).isFalse();
		assertThat(airStrip.add(airplane2)).isFalse();
		
		airStrip.add(airplane2);
		assertThat(airStrip.add(new TrainComposite("1", new LinkedList<>(), 1))).isFalse();
	}
	
	@Test
	public void testAttach() {
		ScreenObserver screen = new ScreenObserver(new LinkedList<>());
		assertThat(airStrip.attach(screen)).isTrue();
		assertThat(airStrip.attach(screen)).isTrue();
	}
	
	@Test
	public void testDetach() {
		ScreenObserver screen = new ScreenObserver(new LinkedList<>());
		ScreenObserver screen1 = new ScreenObserver(new LinkedList<>());
		
		airStrip.attach(screen);
		airStrip.add(airplane1);
		
		assertThat(airStrip.detach(screen1)).isFalse();
		assertThat(airStrip.detach(screen)).isTrue();
	}

	@Test
	public void testEquals() {
		airStrip.attach(new ScreenObserver(new LinkedList<>()));
		airStrip.add(airplane1);
		AirStripComposite airStrip1 = new AirStripComposite(1);

		assertThat(airStrip).isNotEqualTo(airStrip1);
		assertThat(airStrip1).isNotEqualTo(airStrip);

		airStrip1.attach(new ScreenObserver(new LinkedList<>()));
		airStrip1.add(airplane1);

		assertThat(airStrip).isEqualTo(airStrip1);
		assertThat(airStrip1).isEqualTo(airStrip);
		assertThat(airStrip).isEqualTo(airStrip);

		airStrip.remove(airplane1);
		assertThat(airStrip).isNotEqualTo(airStrip1);
	}

	@Test
	public void testAirStripGetComponent() {
		airStrip.add(airplane1);
		airStrip.add(airplane2);
		assertThat(airStrip.getComponent()).containsExactly(airplane1);
	}

	@Test
	public void testAirStripRemove() {
		airStrip.add(airplane1);

		assertThat(airStrip.remove(airplane1)).isTrue();
		assertThat(airStrip.getComponent().iterator()).toIterable().containsNull();
	}

}
