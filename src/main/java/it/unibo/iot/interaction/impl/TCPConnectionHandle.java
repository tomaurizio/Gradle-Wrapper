package it.unibo.iot.interaction.impl;

import it.unibo.iot.interaction.interfaces.ConnectionHandle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPConnectionHandle implements ConnectionHandle {
    private Socket s;

    public TCPConnectionHandle(Socket s) {
        this.s = s;
    }

    @Override
    public void send(String line) throws IOException {
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println(line);
    }

    @Override
    public String receive() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        return input.readLine();
    }
}
