package it.unibo.iot.domain.impl.support;

import it.unibo.iot.domain.interfaces.Emitter;
import it.unibo.iot.interaction.impl.ZMQConnectionFactory;
import it.unibo.iot.interaction.interfaces.Connection;
import it.unibo.iot.interaction.interfaces.ConnectionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EventEmitter implements Emitter {
    private ConnectionHandle handle;

    public EventEmitter(String name) throws IOException {
        handle = new ZMQConnectionFactory().connection().connectAsClient(GlobalConfig.EventServiceHost, GlobalConfig.EventServicePort);
    }

    @Override
    public void emit(String event) {
        try {
            handle.send(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
