package mp.progetto.pattern.composite;

import java.util.stream.Stream;

public interface AbstractComposite extends AbstractComponent {

	public boolean add(AbstractComponent component);

	public boolean remove(AbstractComponent component);

	public Stream<AbstractComponent> getComponent();
}
