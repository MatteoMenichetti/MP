package mp.progetto.buildings.concrete;

import java.util.Collection;

import mp.progetto.pattern.composite.AbstractComponent;
import mp.progetto.pattern.composite.AbstractComposite;
import mp.progetto.pattern.composite.BuildingComposite;
import mp.progetto.pattern.composite.BuildingCompositeVehicle;
import mp.progetto.pattern.visitor.Visitor;

public class AirPortComposite extends BuildingComposite {
	public AirPortComposite(Collection<AbstractComponent> components, int NUMBER) {
		super(components, NUMBER);
	}

	@Override
	public <T> void accept(Visitor<T> visitor) {
		 visitor.visit(this);
	}
	
	@Override
	public boolean add(AbstractComponent component) {
		return component instanceof BuildingComposite || component instanceof BuildingCompositeVehicle
				? super.add(component)
				: false;
	}
	
	@Override
	public boolean remove(AbstractComponent component) {
		return super.remove(component, x-> x instanceof AbstractComposite);
	}
}
