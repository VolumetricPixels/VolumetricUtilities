package com.volumetricpixels.utils.tcp.event;

import java.util.EventObject;

import com.volumetricpixels.utils.tcp.SocketHandler;

@SuppressWarnings("serial")
/**
 * Event fired when a socket handler is ready
 */
public class SocketHandlerReadyEvent extends EventObject {
	private SocketHandler handler;

	public SocketHandlerReadyEvent(Object source, SocketHandler handler) {
		super(source);
		this.handler = handler;
	}

	public SocketHandler getHandler() {
		return handler;
	}
}
