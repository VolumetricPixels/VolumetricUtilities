package com.volumetricpixels.utils.event;

import java.util.ArrayList;

public class EventSender {
	private ArrayList<EventListener> listeners;
	private Class<? extends Event> listenerEventClass;
	private Object listenersLock;

	public EventSender(Class<? extends Event> listenerEventClass) {
		listeners = new ArrayList<EventListener>();
		this.listenerEventClass = listenerEventClass;
		listenersLock = new Object();
	}

	public boolean addListener(EventListener listener) {
		synchronized (listenersLock) {
			return listeners.add(listener);
		}
	}

	public boolean removeListener(EventListener listener) {
		synchronized (listenersLock) {
			return listeners.remove(listener);
		}
	}

	public EventListener[] getListeners() {
		return listeners.toArray(new EventListener[listeners.size()]);
	}

	public <T extends Event> T sendEvent(T event) {
		synchronized (listenersLock) {
			if (listenerEventClass.isInstance(event)) {
				for (EventListener<T> listener : listeners) {
					listener.onEvent(event);
				}
			}
		}
		return event;
	}

	public Class<? extends Event> getListenerEventClass() {
		return listenerEventClass;
	}
}
