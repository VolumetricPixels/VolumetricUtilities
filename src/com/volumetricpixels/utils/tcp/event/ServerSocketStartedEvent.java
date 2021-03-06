package com.volumetricpixels.utils.tcp.event;

import java.util.EventObject;

@SuppressWarnings("serial")
/**
 * Event fired when a server socket is started in the TCP socket library
 */
public class ServerSocketStartedEvent extends EventObject {
	public ServerSocketStartedEvent(Object source) {
		super(source);
	}
}
