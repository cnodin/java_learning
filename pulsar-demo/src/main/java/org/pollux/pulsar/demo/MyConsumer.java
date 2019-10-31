package org.pollux.pulsar.demo;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

/**
 * @author wangbinbin
 * @version MyConsumer, 2019/10/31 10:14 wangbinbin Exp
 */
public class MyConsumer {

  private static String localClusterUrl = "pulsar://192.168.5.59:6650";
  private static String topicName = "persistent://my-tenant/my-namespace/my-topic";
  private static String subscriptionName = "my-subscription";

  public static void main(String[] args) throws PulsarClientException {
    Consumer<byte[]> consumer = getClient().newConsumer().topic(topicName).subscriptionName(subscriptionName).subscribe();
    while (true) {
      Message message = consumer.receive();
      System.out.printf("consumer-message received: %s.\n", new String(message.getData()));

      consumer.acknowledge(message);
    }
  }

  public static PulsarClient getClient() throws PulsarClientException {
    PulsarClient client = PulsarClient.builder().serviceUrl(localClusterUrl).build();
    return client;
  }
}
