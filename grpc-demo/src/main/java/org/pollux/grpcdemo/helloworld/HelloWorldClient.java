package org.pollux.grpcdemo.helloworld;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.pollux.grpcdemo.helloworld.api.GreeterGrpc;
import org.pollux.grpcdemo.helloworld.api.HelloReply;
import org.pollux.grpcdemo.helloworld.api.HelloRequest;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/8/17
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
public class HelloWorldClient {

	private final ManagedChannel channel;
	private final GreeterGrpc.GreeterBlockingStub blockingStub;

	public HelloWorldClient(String host, int port){
		this(ManagedChannelBuilder.forAddress(host, port)
								.usePlaintext(true)
								.build());
	}

	HelloWorldClient(ManagedChannel channel){
		this.channel = channel;
		blockingStub = GreeterGrpc.newBlockingStub(channel);
	}

	public void greet(String name) {
		log.info("Will try to greet " + name + " ...");
		HelloRequest request = HelloRequest.newBuilder().setName(name).build();
		HelloReply reply = blockingStub.sayHello(request);
		log.info("Greeting: " + reply.getMessage());
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public static void main (String[] args) throws InterruptedException {
		HelloWorldClient helloWorldClient = new HelloWorldClient("localhost", 50502);
		String user = "world";
		if (args.length > 0) {
			user = args[0];
		}
		helloWorldClient.greet(user);
		helloWorldClient.shutdown();
	}

}
