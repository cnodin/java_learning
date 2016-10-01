package org.pollux.nettythedefinitiveguid.ch02_nio_starting.bogus_aio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pollux on 16/8/28.
 */
public class TimeServer {

  public static void main(String[] args) throws IOException {
    int port = 8080;
    if (args != null && args.length > 0){
      try{
        port = Integer.valueOf(args[0]);
      }catch (NumberFormatException e){}
    }

    ServerSocket server = null;

    try{
      server = new ServerSocket(port);
      System.out.println("The time server is start in port:" + port);

      Socket socket = null;

      TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);

      while (true){
        socket = server.accept();
        singleExecutor.execute(new TimeServerHandler(socket));
      }
    }finally {
      if (server != null){
        System.out.printf("The time server close");
        server.close();
        server = null;
      }
    }
  }

}
