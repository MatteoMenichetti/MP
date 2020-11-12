package mp.progetto.buildings.concrete;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

import org.junit.Test;

import mp.progetto.pattern.observer.ScreenObserver;
import mp.progetto.vehicle.concrete.TrainComposite;

public class StationCompositeTest {
	StationComposite station= new StationComposite(new LinkedList<>(), 1);
		RailComposite rail = new RailComposite(1);
		RailComposite rail1 = new RailComposite(2);
		TrainComposite train = new TrainComposite("Train", new LinkedList<>(), 2);
		ScreenObserver screen = new ScreenObserver(new LinkedList<>());

		@Test
		public void testAdd() {
			assertThat(station.add(train)).isFalse();
			assertThat(station.add(rail)).isTrue();
			assertThat(station.add(rail)).isFalse();
			assertThat(station.add(rail1)).isTrue();
			
			assertThat(station.getComponent().iterator()).toIterable().containsExactly(rail, rail1);

			assertThat(station.add(new GateComposite(1))).isFalse();
		}

		@Test
		public void testAttach() {
			assertThat(station.attach(screen)).isTrue();
			assertThat(station.attach(screen)).isTrue();
			assertThat(rail.attach(null)).isFalse();
		}

		@Test
		public void testDetach() {
			assertThat(station.detach(screen)).isFalse();

			station.attach(screen);

			assertThat(station.detach(screen)).isTrue();
			assertThat(station.detach(screen)).isFalse();
		}

		@Test
		public void testEquals() {
			StationComposite station1 = new StationComposite(new LinkedList<>(), 1);
			station.add(rail);

			assertThat(station.equals(station1)).isFalse();

			station1.add(rail);
			assertThat(station.equals(station1)).isTrue();
		}

		@Test
		public void testRemove() {
			assertThat(station.remove(rail)).isFalse();
			station.add(rail);
			assertThat(station.remove(rail)).isTrue();
			assertThat(station.getComponent().iterator()).toIterable().containsExactly();
		}
}
