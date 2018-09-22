package org.pollux.kafkademo.controller;

import org.apache.kafka.clients.producer.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2018/7/30
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
@RequestMapping("/msg")
public class MessageController {

    @GetMapping("/send")
    public String sendeMessage() {

        Properties props = initConfig();
        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>("my-topic1", Integer.toString(i), Integer.toString(i)), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println("message send successfully, offset: " + metadata.offset());
                }
            });
        }

        producer.close();

        return String.format("Send %s messages", 100);
    }

    @GetMapping("/partition")
    public String sendMultiPartitionMessage() throws ExecutionException, InterruptedException {
        Properties props = initConfig();
        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        String topic = "test-partition-topic1";
        ProducerRecord nonKeyRecord = new ProducerRecord(topic, "non-key record");
        ProducerRecord auditRecord = new ProducerRecord(topic, "audit","audit record");
        ProducerRecord nonAuditRecord = new ProducerRecord(topic, "other","non-key record");

        producer.send(nonKeyRecord).get();
        producer.send(nonAuditRecord).get();
        producer.send(auditRecord).get();
        producer.send(nonKeyRecord).get();
        producer.send(nonAuditRecord).get();

        producer.close();
        return "is ok";
    }

    private Properties initConfig() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.26:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "-1");
        props.put("retries", 3);
        props.put("batch.size", 323840);
        props.put("linger.ms", 10);
        props.put("buffer.memory", 33554432);
        props.put("max.block.ms", 3000);

        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        //props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "org.pollux.kafkademo.config.AuditPartitioner");

        return props;
    }

//    public String consume
}
