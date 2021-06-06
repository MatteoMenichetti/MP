package mp.progetto.vehicle.concrete;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import mp.progetto.pattern.visitor.PersonVisitor;
import mp.progetto.pattern.visitor.VehicleVisitor;

public class MotorBikeLeafTest {

	MotorBikeLeaf bike = new MotorBikeLeaf("Targa");

	@Test
	public void testAccept() {
		VehicleVisitor vehicleVisitor = new VehicleVisitor();
		bike.accept(vehicleVisitor);
		assertThat(vehicleVisitor.getAccumulator()).toIterable().containsExactly(bike);

		PersonVisitor personVisitor = new PersonVisitor();
		bike.accept(personVisitor);
		assertThat(personVisitor.getAccumulator()).toIterable().containsExactly();
	}

	@Test
	public void testEquals() {
		assertThat(bike).isEqualTo(new MotorBikeLeaf("Targa"));
		assertThat(bike).isNotEqualTo(new MotorBikeLeaf("targa"));
	}
}
