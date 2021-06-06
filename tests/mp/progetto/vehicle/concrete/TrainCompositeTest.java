package mp.progetto.vehicle.concrete;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

import org.junit.Test;

import mp.progetto.buildings.concrete.RailComposite;
import mp.progetto.buildings.concrete.StationComposite;
import mp.progetto.pattern.visitor.PersonVisitor;
import mp.progetto.pattern.visitor.VehicleVisitor;
import mp.progetto.person.Person;

public class TrainCompositeTest {
	TrainComposite train = new TrainComposite("targa", new LinkedList<>(), 3);
	TrainComposite train1 = new TrainComposite("targa", new LinkedList<>(), 3);

	RailComposite rail = new RailComposite(1);
	RailComposite rail1 = new RailComposite(2);
	Person person = new Person("Nome", "Cognome");
	Person person1 = new Person("Nome1", "Cognome1");

	@Test
	public void testAccept() {
		VehicleVisitor vehicleVisitor = new VehicleVisitor();
		PersonVisitor personVisitor = new PersonVisitor();

		addPerson(train);

		train.accept(vehicleVisitor);
		assertThat(vehicleVisitor.getAccumulator()).toIterable().containsExactly(train);

		train.accept(personVisitor);
		assertThat(personVisitor.getAccumulator()).toIterable().containsExactly(person, person1);

	}

	@Test
	public void testAdd() {
		assertThat(train.add(rail)).isTrue();
		assertThat(train.add(new StationComposite(new LinkedList<>(), 1))).isFalse();
		assertThat(train.add(rail1)).isTrue();
		assertThat(train.add(rail1)).isFalse();

		assertThat(train.getComponent()).containsExactly(rail, rail1);
	}

	@Test
	public void testAddPerson() {
		assertThat(train.addPerson(person)).isTrue();
		assertThat(train.addPerson(person)).isFalse();
		assertThat(train.addPerson(person1)).isTrue();

		PersonVisitor personVisitor = new PersonVisitor();
		train.accept(personVisitor);
		assertThat(personVisitor.getAccumulator()).toIterable().containsExactly(person, person1);
	}

	@Test
	public void testEquals() {
		addBuildings(train);

		assertThat(train).isNotEqualTo(train1);
		assertThat(train).isNotEqualTo(rail);

		addBuildings(train1);
		assertThat(train).isEqualTo(train1);
	}

	@Test
	public void testGetComponent() {
		addBuildings(train);

		assertThat(train.getComponent().iterator()).toIterable().containsExactly(rail, rail1);
	}

	@Test
	public void testGetPerson() {
		addPerson(train);
		addPerson(train1);

		assertThat(train.getPerson()).isEqualTo(train1.getPerson());
	}

	@Test
	public void testRemove() {
		addBuildings(train);

		assertThat(train.remove(rail)).isTrue();
		assertThat(train.remove(rail)).isFalse();
		assertThat(train.remove(rail1)).isTrue();
		assertThat(train.getComponent().iterator()).toIterable().containsExactly();
	}

	@Test
	public void testRemovePeople() {
		addPerson(train);
		train.removePeople();

		assertThat(train.getPerson().hasNext()).isFalse();
	}

	private void addBuildings(TrainComposite train) {
		train.add(rail);
		train.add(rail1);
	}

	private void addPerson(TrainComposite train) {
		train.addPerson(person);
		train.addPerson(person1);
	}

}
