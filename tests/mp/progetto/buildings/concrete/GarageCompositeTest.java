package mp.progetto.buildings.concrete;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;

import mp.progetto.vehicle.concrete.CarLeaf;
import mp.progetto.vehicle.concrete.MotorBikeLeaf;

public class GarageCompositeTest {
//import static org.assertj.core.api.Assertions.*;
	GarageComposite garage = new GarageComposite(new ArrayList<>(), 1, 2);
	CarLeaf car = new CarLeaf("targa");
	MotorBikeLeaf bike = new MotorBikeLeaf("targa");

	@Test
	public void testAdd() {
		assertThat(garage.add(car)).isTrue();
		assertThat(garage.add(new CarLeaf("targa"))).isFalse();
		assertThat(garage.add(new CarLeaf("targa1"))).isTrue();
		assertThat(garage.add(bike)).isFalse();
	}

	@Test
	public void testRemove() {
		garage.add(car);
		garage.add(bike);
		assertThat(garage.remove(car)).isTrue();
		assertThat(garage.remove(car)).isFalse();
		assertThat(garage.remove(bike)).isTrue();
		assertThat(garage.remove(bike)).isFalse();
	}

	@Test
	public void testCapacity() {
		assertThat(garage.getCapacity()).isEqualTo(2);
	}

	@Test
	public void testSpace() {
		garage.add(car);
		assertThat(garage.getSpace()).isEqualTo(1);
		garage.add(bike);
		assertThat(garage.getSpace()).isEqualTo(0);
		garage.remove(bike);
		assertThat(garage.getSpace()).isEqualTo(1);
	}
}