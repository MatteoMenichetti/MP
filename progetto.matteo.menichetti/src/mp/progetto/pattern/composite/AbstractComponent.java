package mp.progetto.pattern.composite;

import mp.progetto.pattern.visitor.Visitor;

public interface AbstractComponent {

	public<T> void accept(Visitor<T> visitor);

	@Override
	public boolean equals(Object obj);

}
