package mp.progetto.buildings.concrete;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

import org.junit.Test;

import mp.progetto.pattern.observer.ScreenObserver;
import mp.progetto.vehicle.concrete.TrainComposite;

public class RailCompositeTest {
	RailComposite rail = new RailComposite(1);
	TrainComposite train = new TrainComposite("Train", new LinkedList<>(), 2);
	ScreenObserver screen = new ScreenObserver(new LinkedList<>());

	@Test
	public void testAdd() {
		assertThat(rail.add(train)).isTrue();
		assertThat(rail.getComponent().iterator()).toIterable().containsExactly(train);
		assertThat(rail.add(train)).isFalse();
		assertThat(rail.add(new GateComposite(1))).isFalse();
	}

	@Test
	public void testAttach() {
		assertThat(rail.attach(screen)).isTrue();
		assertThat(rail.attach(screen)).isTrue();
		assertThat(rail.attach(null)).isFalse();
	}

	@Test
	public void testDetach() {
		assertThat(rail.detach(screen)).isFalse();

		rail.attach(screen);

		assertThat(rail.detach(screen)).isTrue();
		assertThat(rail.detach(screen)).isFalse();
	}

	@Test
	public void testEquals() {
		RailComposite rail1 = new RailComposite(1);
		rail.add(train);

		assertThat(rail.equals(rail1)).isFalse();

		rail1.add(train);
		assertThat(rail.equals(rail1)).isTrue();
	}

	@Test
	public void testRemove() {
		rail.add(train);
		assertThat(rail.remove(train)).isTrue();
		assertThat(rail.getComponent().iterator()).toIterable().containsNull();
	}
}
