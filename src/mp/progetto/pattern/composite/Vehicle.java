package mp.progetto.pattern.composite;

import java.util.Objects;

public abstract class Vehicle implements AbstractComponent {

	private final String targa;

	public Vehicle(String targa) {
		this.targa = targa;
	}

	public String getTarga() {
		return targa;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Vehicle) {
			Vehicle vehicle = (Vehicle) obj;
			return Objects.equals(vehicle.targa, targa);
		}
		return false;
	}
	
}