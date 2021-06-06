package mp.progetto.pattern.adapter;

import mp.progetto.buildings.concrete.ControlTowerComposite;
import mp.progetto.buildings.concrete.GarageComposite;
import mp.progetto.pattern.visitor.Visitor;
import mp.progetto.person.Person;
import mp.progetto.vehicle.concrete.CarLeaf;
import mp.progetto.vehicle.concrete.MotorBikeLeaf;

public abstract class PersonVisitorAdapter implements Visitor<Person> {
	@Override
	public void visit(CarLeaf car) {
	}

	@Override
	public void visit(MotorBikeLeaf bike) {
	}

	@Override
	public void visit(ControlTowerComposite controltower) {
	}

	@Override
	public void visit(GarageComposite garage) {
	}
}
