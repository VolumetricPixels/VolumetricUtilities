package com.volumetricpixels.utils.tcp;

import java.net.Socket;

import com.volumetricpixels.utils.tcp.event.EventManager;

/**
 * Client for DziNeIT's TCP socket library
 *
 * @author DziNeIT
 */
public class Client {
    private Socket socket;
    private SocketHandler handler = new SocketHandler(this);
    private String host;
    private int port;
    private EventManager em = new EventManager();

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        try {
            socket = new Socket(host, port);
            handler.setSocket(socket);
            handler.setID(0);
            handler.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        handler.sendMessage(message);
    }

    public void disconnect() {
        handler.disconnect();
    }

    public SocketHandler getHandler() {
        return handler;
    }

    public Socket getSocket() {
        return socket;
    }
    
    public EventManager getEventManager() {
        return em;
    }
}
