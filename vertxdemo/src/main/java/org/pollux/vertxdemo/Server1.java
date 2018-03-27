package org.pollux.vertxdemo;

import io.vertx.core.AbstractVerticle;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 12/03/2018
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Server1 extends AbstractVerticle {

    public static void main(String[] args) {
        Server1 server1 = new Server1();
        server1.start();
    }

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
            req.response().putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x");
        }).listen(8080);
    }

}
