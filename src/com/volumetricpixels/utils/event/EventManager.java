package com.volumetricpixels.utils.event;

import java.util.ArrayList;

public class EventManager {
	private ArrayList<EventSender> eventSenders;
	private Object eventSendersLock = new Object();

	public EventManager() {
		eventSenders = new ArrayList<EventSender>();
	}

	public void addListener(Class<? extends Event> eventClass, EventListener listener) {
		synchronized (eventSendersLock) {
			for (EventSender sender : eventSenders) {
				if (eventClass.equals(sender.getListenerEventClass())) {
					sender.addListener(listener);
					return;
				}
			}
			EventSender sender = new EventSender(eventClass);
			eventSenders.add(sender);
			sender.addListener(listener);
		}
	}

	public void removeListener(Class<? extends Event> eventClass, EventListener listener) {
		synchronized (eventSendersLock) {
			for (EventSender sender : eventSenders) {
				if (eventClass.equals(sender.getListenerEventClass())) {
					sender.removeListener(listener);
					if (sender.getListeners().length == 0) {
						eventSenders.remove(sender);
					}
					break;
				}
			}
		}
	}

	public void clearListeners() {
		synchronized (eventSendersLock) {
			eventSenders.clear();
		}
	}

	public <T extends Event> T sendEvent(T event) {
		synchronized (eventSendersLock) {
			for (EventSender sender : eventSenders) {
				if (sender.getListenerEventClass().isInstance(event)) {
					sender.sendEvent(event);
				}
			}
		}
		return event;
	}

	public EventListener[] getListeners(Class<? extends Event> eventClass) {
		synchronized (eventSendersLock) {
			for (EventSender sender : eventSenders) {
				if (eventClass.isAssignableFrom(sender.getListenerEventClass())) {
					return sender.getListeners();
				}
			}
			return new EventListener[0];
		}
	}
}
