package org.pollux.pulsar.demo;

import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

/**
 * @author wangbinbin
 * @version MyProducer, 2019/10/31 9:24 wangbinbin Exp
 */
public class MyProducer {

  private static String localClusterUrl = "pulsar://192.168.5.59:6650";
  private static String topicName = "persistent://my-tenant/my-namespace/my-topic";
  private static String producerName = "my-producer";

  public static void main(String[] args) throws PulsarClientException {
    Producer<byte[]> producer = getProducer();
    String msg = "hello, pulsar";

    Long start = System.currentTimeMillis();
    MessageId messageId = producer.send(msg.getBytes());
    System.out.printf("spend=%s; send a message msgId=%s", (System.currentTimeMillis() - start), messageId.toString());
  }

  public static Producer<byte[]> getProducer() throws PulsarClientException {
    PulsarClient client = PulsarClient.builder().serviceUrl(localClusterUrl).build();
    Producer<byte[]> producer = client.newProducer().topic(topicName).producerName(producerName).create();
    return producer;
  }
}
