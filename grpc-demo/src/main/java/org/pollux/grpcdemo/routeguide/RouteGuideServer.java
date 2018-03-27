package org.pollux.grpcdemo.routeguide;

import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.pollux.grpcdemo.routeguide.api.Feature;
import org.pollux.grpcdemo.routeguide.api.Point;
import org.pollux.grpcdemo.routeguide.api.RouteGuideGrpc;
import org.pollux.grpcdemo.routeguide.api.RouteNote;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/8/17
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
public class RouteGuideServer {

//	private final Server server;

//	private final int port;

	public RouteGuideServer(int port){
//		this.port = port;
	}

	public RouteGuideServer(int port, URL featureFile){

	}

	public RouteGuideServer(ServerBuilder<?> serverBuilder, int port, Collection<Feature> features){
//		this.port = port;
//		server = serverBuilder.addService(new Route)
	}

	private static class RouteGuideService extends RouteGuideGrpc.RouteGuideImplBase {

		private final Collection<Feature> features;
		private final ConcurrentMap<Point, List<RouteNote>> routeNotes = new ConcurrentHashMap<>();

		public RouteGuideService(Collection<Feature> features){
			this.features = features;
		}

		@Override
		public void getFeature (Point request, StreamObserver<Feature> responseObserver) {

		}
	}

}
