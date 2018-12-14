package it.unibo.iot;

import it.unibo.iot.domain.impl.ConcurrentQueueBuffer;
import it.unibo.iot.domain.impl.ConsumerActivity;
import it.unibo.iot.domain.impl.ProducerActivity;
import it.unibo.iot.domain.interfaces.Buffer;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConfiguratorOneToOneTest {

    @Before public void setup() throws InterruptedException {
        final ExecutorService es = Executors.newCachedThreadPool();
        final Buffer buffer = new ConcurrentQueueBuffer(10);
        es.execute(new ProducerActivity(buffer));
        es.execute(new ConsumerActivity(buffer));
        es.shutdown();
        es.awaitTermination(2, TimeUnit.SECONDS);
    }

    @Test public void basicTest() throws IOException {
        List<String> lines = Files.readAllLines(new File("prodcons.log").toPath(), StandardCharsets.UTF_8);

        ProducerConsumerAssertions.assertOrderedConsumptionOnLog(lines);
    }

}
