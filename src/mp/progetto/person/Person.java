package mp.progetto.person;

public class Person {
	private String name;
	private String surname;

	public Person(String name, String surname) {
		this.name = name;
		this.surname = surname;

	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj instanceof Person) {
			Person person = (Person) obj;
			return person.name.equals(name) && person.surname.equals(surname);
		}
		return false;
	}

}
