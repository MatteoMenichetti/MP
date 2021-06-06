package mp.progetto.vehicle.concrete;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

import org.junit.Test;

import mp.progetto.buildings.concrete.AirStripComposite;
import mp.progetto.buildings.concrete.GateComposite;
import mp.progetto.pattern.visitor.PersonVisitor;
import mp.progetto.pattern.visitor.VehicleVisitor;
import mp.progetto.person.Person;

public class BusCompositeTest {
	BusComposite bus = new BusComposite("Targa", new LinkedList<>(), 2);
	BusComposite bus1 = new BusComposite("Targa", new LinkedList<>(), 2);

	AirStripComposite airstrip = new AirStripComposite(1);
	GateComposite gate = new GateComposite(1);

	Person person = new Person("Nome", "Cognome");
	Person person1 = new Person("Nome1", "Cognome1");

	@Test
	public void testAccept() {
		VehicleVisitor vehicleVisitor = new VehicleVisitor();
		PersonVisitor personVisitor = new PersonVisitor();

		addPerson(bus);

		bus.accept(vehicleVisitor);
		assertThat(vehicleVisitor.getAccumulator()).toIterable().containsExactly(bus);

		bus.accept(personVisitor);
		assertThat(personVisitor.getAccumulator()).toIterable().containsExactly(person, person1);

	}

	@Test
	public void testAdd() {
		assertThat(bus.add(airstrip)).isTrue();
		assertThat(bus.add(gate)).isTrue();
		assertThat(bus.add(airstrip)).isFalse();

		assertThat(bus.getComponent()).containsExactly(airstrip, gate);
	}

	@Test
	public void testAddPerson() {
		assertThat(bus.addPerson(person)).isTrue();
		assertThat(bus.addPerson(person)).isFalse();
		assertThat(bus.addPerson(person1)).isTrue();

		PersonVisitor personVisitor = new PersonVisitor();
		bus.accept(personVisitor);
		assertThat(personVisitor.getAccumulator()).toIterable().containsExactly(person, person1);
	}

	@Test
	public void testEquals() {
		addBuildings(bus);
		addPerson(bus);

		assertThat(bus).isNotEqualTo(bus1);
		assertThat(bus).isNotEqualTo(gate);

		addBuildings(bus1);
		addPerson(bus1);
		assertThat(bus).isEqualTo(bus1);

	}

	@Test
	public void testGetComponent() {
		addBuildings(bus);

		assertThat(bus.getComponent().iterator()).toIterable().containsExactly(gate, airstrip);
	}

	@Test
	public void testGetPerson() {
		addPerson(bus);
		addPerson(bus1);

		assertThat(bus.getPerson()).isEqualTo(bus1.getPerson());
	}

	@Test
	public void testRemove() {
		addBuildings(bus);

		assertThat(bus.remove(airstrip)).isTrue();
		assertThat(bus.remove(airstrip)).isFalse();
		assertThat(bus.remove(gate)).isTrue();
		assertThat(bus.getComponent().iterator()).toIterable().containsExactly();
	}

	@Test
	public void testRemovePeople() {
		addPerson(bus);
		bus.removePeople();

		assertThat(bus.getPerson().hasNext()).isFalse();

	}

	private void addBuildings(BusComposite bus) {
		bus.add(gate);
		bus.add(airstrip);
	}

	private void addPerson(BusComposite bus) {
		bus.addPerson(person);
		bus.addPerson(person1);
	}

}
