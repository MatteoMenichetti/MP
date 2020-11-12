package mp.progetto.pattern.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.Test;

import mp.progetto.person.Person;
import mp.progetto.vehicle.concrete.TrainComposite;

public class PersonIteratorTest {
	TrainComposite train = new TrainComposite("Train", new LinkedList<>(), 2);
	Person person = new Person("Nome", "Cognome");
	Person person1 = new Person("Nom1", "Cognome1");

	public PersonIteratorTest() {
		train.addPerson(person);
		train.addPerson(person1);
	}

	@Test
	public void testHasNext() {
		assertThat(train.getPerson().hasNext()).isTrue();
		train.removePeople();
		assertThat(train.getPerson().hasNext()).isFalse();
	}

	@Test
	public void testNext() {
		Iterator<Person> people = train.getPerson();
		assertThat(people.next()).isEqualTo(person);
		assertThat(people.next()).isEqualTo(person1);
		assertThatThrownBy(() -> people.next()).isInstanceOf(NoSuchElementException.class);
	}

}
