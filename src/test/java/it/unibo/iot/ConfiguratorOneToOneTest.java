package it.unibo.iot;

import com.google.common.collect.Streams;
import it.unibo.iot.domain.impl.ConcurrentQueueBuffer;
import it.unibo.iot.domain.impl.ConsumerActivity;
import it.unibo.iot.domain.impl.ProducerActivity;
import it.unibo.iot.domain.interfaces.Buffer;
import org.junit.Assert;
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
import java.util.stream.Collectors;

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

        List<String> producedStuff = lines.stream()
                .filter(s -> s.contains("Produce"))
                .map(s -> s.substring(s.lastIndexOf(" ")))
                .collect(Collectors.toList());
        List<String> consumedStuff = lines.stream()
                .filter(s -> s.contains("Consume"))
                .map(s -> s.substring(s.lastIndexOf(" ")))
                .collect(Collectors.toList());

        Assert.assertTrue(producedStuff.size()==consumedStuff.size() &&
                Streams.zip(producedStuff.stream(), consumedStuff.stream(), (a, b) -> a.equals(b)).allMatch(x -> x));
    }

}
