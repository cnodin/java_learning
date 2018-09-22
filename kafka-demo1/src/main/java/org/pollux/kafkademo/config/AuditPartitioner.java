package org.pollux.kafkademo.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: spock.wang
 * Date: 2018/8/2
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class AuditPartitioner implements Partitioner {

    private Random random;

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        String strKey = (String)key;
        List<PartitionInfo> partitionInfoList = cluster.availablePartitionsForTopic(topic);
        int partionCount = partitionInfoList.size();
        int auditPartion = partionCount - 1;

        return strKey == null || strKey.isEmpty() || !strKey.contains("audit") ? random.nextInt(partionCount - 1) : partionCount;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {
        random = new Random();
    }
}
