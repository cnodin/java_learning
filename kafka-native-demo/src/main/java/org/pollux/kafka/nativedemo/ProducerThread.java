package org.pollux.kafka.nativedemo;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-02-05
 * Time: 23:10
 * To change this template use File | Settings | File Templates.
 * Description:
 */
//@Slf4j
public class ProducerThread extends Thread {

    private final static int MAX_THREAD_SIZE = 6;

    private final static String TOPIC_NAME = "test_kafka_11";

    private final static String BOOTSTRAP_SERVERS = "192.168.1.26:9092,192.168.1.29:9092,192.168.1.49:9092";

    public Properties configure() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("acks", "1");
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
        props.put("retries", 3);
        props.put("request.timeout.ms", 300);
        props.put("compression.type", "snappy");
        props.put("max.in.flight.requests.per.connection", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return props;
    }

    @Override
    public void run() {
        Producer<String, String> producer = new KafkaProducer<>(configure());
        for (int i = 0; i < 100; i++) {
            JSONObject json = new JSONObject();
            json.put("id", i);
            json.put("ip", "192.168.0." + i);
            json.put("date", System.currentTimeMillis());
            String k = "key" + i;
            producer.send(new ProducerRecord<String, String>(TOPIC_NAME, k, json.toJSONString()),
                    new Callback() {

                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception e) {

                            if (e != null) {
                                //log.error("Send error, msg is " + e.getMessage());
                                System.out.println("Send error, msg is " + e.getMessage());
                            }{
                                //log.info("The offset of the reacord we just is: " + metadata.offset());
                                System.out.println("The partition is " + metadata.partition() + ";The offset of the " +
                                        "reacord we just is: " + metadata.offset() + ";value: " + json.toJSONString());
                            }
                        }
                    });
        }
        try{
            TimeUnit.SECONDS.sleep(3);  //间隔3秒
        }catch (InterruptedException e) {
//            log.error("Interrupted thread error, msg is " + e.getMessage());
        }

        producer.close();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_SIZE);
        executorService.submit(new ProducerThread());
        executorService.shutdown();
//        log.info("finished...");
        System.out.println("finished...");
    }

}
