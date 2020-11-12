package mp.progetto.pattern.iterator;

import mp.progetto.person.Person;

public abstract class PersonIterator implements Iterator<Person> {

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PersonIterator) {
			PersonIterator iterator = (PersonIterator) obj;
			while (iterator.hasNext() && this.hasNext())
				if (!iterator.next().equals(this.next()))
					break;
			if (iterator.hasNext() == this.hasNext())
				return true;
			return false;
		}

		return false;
	}
}
