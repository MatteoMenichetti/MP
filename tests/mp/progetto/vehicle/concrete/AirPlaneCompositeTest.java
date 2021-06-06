package mp.progetto.vehicle.concrete;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

import org.junit.Test;

import mp.progetto.buildings.concrete.AirStripComposite;
import mp.progetto.buildings.concrete.GateComposite;
import mp.progetto.buildings.concrete.StationComposite;
import mp.progetto.pattern.visitor.PersonVisitor;
import mp.progetto.pattern.visitor.VehicleVisitor;
import mp.progetto.person.Person;

public class AirPlaneCompositeTest {

	AirPlaneComposite airplane = new AirPlaneComposite("Targa", new LinkedList<>(), 2);
	AirPlaneComposite airplane1 = new AirPlaneComposite("Targa", new LinkedList<>(), 2);

	AirStripComposite airstrip = new AirStripComposite(1);
	GateComposite gate = new GateComposite(1);

	Person person = new Person("Nome", "Cognome");
	Person person1 = new Person("Nome1", "Cognome1");

	@Test
	public void testAccept() {
		VehicleVisitor vehicleVisitor = new VehicleVisitor();
		PersonVisitor personVisitor = new PersonVisitor();

		addPerson(airplane);

		airplane.accept(vehicleVisitor);
		assertThat(vehicleVisitor.getAccumulator()).toIterable().containsExactly(airplane);

		airplane.accept(personVisitor);
		assertThat(personVisitor.getAccumulator()).toIterable().containsExactly(person, person1);

	}

	@Test
	public void testAdd() {
		assertThat(airplane.add(airstrip)).isTrue();
		assertThat(airplane.add(new StationComposite(new LinkedList<>(), 1))).isFalse();
		assertThat(airplane.add(gate)).isTrue();
		assertThat(airplane.add(airstrip)).isFalse();
		assertThat(airplane.getComponent()).containsExactly(airstrip, gate);
	}

	@Test
	public void testAddPerson() {
		assertThat(airplane.addPerson(person)).isTrue();
		assertThat(airplane.addPerson(person)).isFalse();
		assertThat(airplane.addPerson(person1)).isTrue();

		PersonVisitor personVisitor = new PersonVisitor();
		airplane.accept(personVisitor);
		assertThat(personVisitor.getAccumulator()).toIterable().containsExactly(person, person1);
	}

	@Test
	public void testEquals() {
		addBuildings(airplane);
		addPerson(airplane);

		assertThat(airplane).isNotEqualTo(airplane1);
		assertThat(airplane).isNotEqualTo(gate);

		addBuildings(airplane1);
		addPerson(airplane1);
		assertThat(airplane).isEqualTo(airplane1);

	}

	@Test
	public void testGetComponent() {
		addBuildings(airplane);

		assertThat(airplane.getComponent().iterator()).toIterable().containsExactly(gate, airstrip);
	}

	@Test
	public void testGetPerson() {
		addPerson(airplane);
		addPerson(airplane1);

		assertThat(airplane.getPerson()).isEqualTo(airplane1.getPerson());
	}

	@Test
	public void testRemove() {
		addBuildings(airplane);

		assertThat(airplane.remove(airstrip)).isTrue();
		assertThat(airplane.remove(airstrip)).isFalse();
		assertThat(airplane.remove(gate)).isTrue();
		assertThat(airplane.getComponent().iterator()).toIterable().containsExactly();
	}

	@Test
	public void testRemovePeople() {
		addPerson(airplane);
		airplane.removePeople();

		assertThat(airplane.getPerson().hasNext()).isFalse();

	}

	private void addBuildings(AirPlaneComposite airplane) {
		airplane.add(gate);
		airplane.add(airstrip);
	}

	private void addPerson(AirPlaneComposite airplane) {
		airplane.addPerson(person);
		airplane.addPerson(person1);
	}
}
