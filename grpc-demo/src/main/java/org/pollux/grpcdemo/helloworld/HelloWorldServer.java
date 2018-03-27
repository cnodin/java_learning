package org.pollux.grpcdemo.helloworld;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.pollux.grpcdemo.helloworld.api.GreeterGrpc;
import org.pollux.grpcdemo.helloworld.api.HelloReply;
import org.pollux.grpcdemo.helloworld.api.HelloRequest;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/8/17
 * Time: 16:41
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
public class HelloWorldServer {

	private Server server;

	public static void main (String[] args)
					throws InterruptedException, IOException {
		final HelloWorldServer server = new HelloWorldServer();
		server.start();
		server.blockUntilShutdown();
	}

	private void start() throws IOException {
		int port = 50501;

		server = ServerBuilder.forPort(port)
													.addService(new GreeterImpl())
													.build()
													.start();

		log.info("Server started, listening on " + port);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.err.println("*** shutting down gRPC server since JVM is shutting down");
			HelloWorldServer.this.stop();
			System.err.println("*** server shut down");
		}));
	}

	private void stop() {
		if (server != null){
			server.shutdown();
		}
	}

	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	private  class GreeterImpl extends GreeterGrpc.GreeterImplBase {
		@Override
		public void sayHello (HelloRequest request, StreamObserver<HelloReply> responseObserver) {
			HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();

			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}
	}
}
