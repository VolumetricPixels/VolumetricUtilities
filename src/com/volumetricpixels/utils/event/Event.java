package com.volumetricpixels.utils.event;

public abstract class Event {
	protected Event() {
	}

	public final String getEventName() {
		return getClass().getSimpleName();
	}
}
