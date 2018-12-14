package it.unibo.iot.interaction.impl;

import it.unibo.iot.interaction.interfaces.Connection;
import it.unibo.iot.interaction.interfaces.ConnectionFactory;

public class TCPConnectionFactory implements ConnectionFactory {
    @Override
    public Connection connection() {
        return new TCPConnection();
    }
}
