package org.pollux.kafka.nativedemo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2019-02-05
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class JConsumerSubscribe extends Thread {
    private final static String TOPIC_NAME = "test_kafka_11";

    private final static String BOOTSTRAP_SERVERS = "192.168.1.26:9092,192.168.1.29:9092,192.168.1.49:9092";

    @Override
    public void run() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configure());
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        boolean flag = true;

        while (flag) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("topic=%s, partition=%s,offset=%d,customer=%s,country=%s\n",
                        record.topic(),
                        record.partition(),
                        record.offset(),
                        record.key(),
                        record.value());
            }
        }

        consumer.close();
    }

    private Properties configure() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("group.id", "test_kafka_11_gid");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return props;
    }

    public static void main(String[] args) {
        JConsumerSubscribe consumer = new JConsumerSubscribe();
        consumer.start();
    }
}
