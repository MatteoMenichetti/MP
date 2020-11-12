package mp.progetto.buildings.concrete;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

import mp.progetto.pattern.observer.ScreenObserver;

public class ControlTowerCompositeTest {
	ControlTowerComposite tower = new ControlTowerComposite(new ArrayList<>(), 1);
	ScreenObserver screen = new ScreenObserver(new LinkedList<>());
	
	@Test
	public void testAdd() {
		assertThat(tower.add(screen)).isTrue();
		assertThat(tower.getComponent().iterator()).toIterable().containsExactly(screen);
		assertThat(tower.add(screen)).isFalse();
		assertThat(tower.add(new GateComposite(1))).isFalse();
	}
	
	@Test
	public void testAttach() {
		assertThat(tower.attach(screen)).isTrue();
		assertThat(tower.attach(screen)).isFalse();
		assertThat(tower.add(new GateComposite(1))).isFalse();
		
		assertThat(tower.getComponent().iterator()).toIterable().contains(screen, screen);
	}
	
	@Test
	public void testDetach() {
		assertThat(tower.detach(screen)).isFalse();
		
		tower.attach(screen);
		
		assertThat(tower.detach(screen)).isTrue();
		assertThat(tower.getComponent().iterator()).toIterable().containsExactly();
		assertThat(tower.detach(screen)).isFalse();
	}
	
	@Test
	public void testEquals() {
		ControlTowerComposite tower1 = new ControlTowerComposite(new ArrayList<>(), 1);
		tower.add(screen);
		
		assertThat(tower.equals(tower1)).isFalse();
		
		tower1.add(screen);
		assertThat(tower.equals(tower1)).isTrue();
	}
	
	@Test
	public void testRemove() {
		tower.add(screen);
		assertThat(tower.remove(screen)).isTrue();
		assertThat(tower.getComponent().findFirst()).isEmpty();
	}
}
