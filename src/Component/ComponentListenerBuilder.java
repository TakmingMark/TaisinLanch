package Component;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import java.util.function.Consumer;

public class ComponentListenerBuilder {
	static final Consumer<ComponentEvent> nullConsumer = e -> {
	};

	private Consumer<ComponentEvent> componentShown = nullConsumer;
	private Consumer<ComponentEvent> componentResized = nullConsumer;
	private Consumer<ComponentEvent> componentMoved = nullConsumer;
	private Consumer<ComponentEvent> componentHidden = nullConsumer;

	public ComponentListenerBuilder componentShown(Consumer<ComponentEvent> componentShown) {
		this.componentShown = componentShown;
		return this;
	}

	public ComponentListenerBuilder componentResized(Consumer<ComponentEvent> componentResized) {
		this.componentResized = componentResized;
		return this;
	}

	public ComponentListenerBuilder componentMoved(Consumer<ComponentEvent> componentMoved) {
		this.componentMoved = componentMoved;
		return this;
	}

	public ComponentListenerBuilder componentHidden(Consumer<ComponentEvent> componentHidden) {
		this.componentHidden = componentHidden;
		return this;
	}

	public ComponentListener build() {
		return new ComponentListener() {
			@Override
			public void componentHidden(ComponentEvent e) {
				componentHidden.accept(e);
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				componentMoved.accept(e);
			}

			@Override
			public void componentResized(ComponentEvent e) {
				componentResized.accept(e);
			}

			@Override
			public void componentShown(ComponentEvent e) {
				componentShown.accept(e);
			}
		};
	}
}
