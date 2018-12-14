package it.unibo.iot.interaction.impl;

import it.unibo.iot.interaction.interfaces.Connection;
import it.unibo.iot.interaction.interfaces.ConnectionHandle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPConnection implements Connection {
    private Socket s;

    @Override
    public ConnectionHandle connectAsClient(String host, int port) throws IOException {
        s = new Socket(host, port);
        return new TCPConnectionHandle(s);
    }

    @Override
    public ConnectionHandle connectAsServer(int port) throws IOException {
        ServerSocket ss = new ServerSocket(port);
        s = ss.accept();
        return new TCPConnectionHandle(s);
    }

    @Override
    public void closeConnection() throws IOException {
        s.close();
    }
}
