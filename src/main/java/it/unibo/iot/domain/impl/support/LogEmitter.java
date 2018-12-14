package it.unibo.iot.domain.impl.support;

import it.unibo.iot.domain.interfaces.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogEmitter implements Emitter {
    private Logger L;

    public LogEmitter(String name){
        L = LoggerFactory.getLogger(name);
    }

    @Override
    public void emit(String event) {
        L.info(event);
    }
}
