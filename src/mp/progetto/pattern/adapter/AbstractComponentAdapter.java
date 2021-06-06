package mp.progetto.pattern.adapter;

import mp.progetto.pattern.composite.AbstractComponent;
import mp.progetto.pattern.visitor.Visitor;

public abstract class AbstractComponentAdapter implements AbstractComponent {

	@Override
	public <T> void accept(Visitor<T> visitor) {}

}
