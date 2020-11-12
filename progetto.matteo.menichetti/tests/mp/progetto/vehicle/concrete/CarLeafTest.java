package mp.progetto.vehicle.concrete;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import mp.progetto.pattern.visitor.PersonVisitor;
import mp.progetto.pattern.visitor.VehicleVisitor;

public class CarLeafTest {
	CarLeaf car = new CarLeaf("Targa");

	@Test
	public void testAccept() {
		VehicleVisitor vehicleVisitor = new VehicleVisitor();
		car.accept(vehicleVisitor);
		assertThat(vehicleVisitor.getAccumulator()).toIterable().containsExactly(car);

		PersonVisitor personVisitor = new PersonVisitor();
		car.accept(personVisitor);
		assertThat(personVisitor.getAccumulator()).toIterable().containsExactly();
	}

	@Test
	public void testEquals() {
		assertThat(car).isEqualTo(new CarLeaf("Targa"));
		assertThat(car).isNotEqualTo(new CarLeaf("targa"));
	}
}
