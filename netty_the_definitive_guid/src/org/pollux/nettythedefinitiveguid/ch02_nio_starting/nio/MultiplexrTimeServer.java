package org.pollux.nettythedefinitiveguid.ch02_nio_starting.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by pollux on 16/8/29.
 */
public class MultiplexrTimeServer implements Runnable {

  private Selector selector;

  private ServerSocketChannel serverSocketChannel;

  private volatile boolean stop;

  public MultiplexrTimeServer(int port){
    try {
      selector = Selector.open();
      serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.configureBlocking(false);
      serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      System.out.println("The time server is start in port : " + port);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void stop(){
    this.stop = true;
  }

  private void handleInput(SelectionKey key) throws IOException {
    if (key.isValid()){
      //处理accept请求
      if (key.isAcceptable()){
        ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);
      }

      //处理读事件
      if (key.isReadable()){
        SocketChannel sc = (SocketChannel)key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int readBytes = sc.read(readBuffer);
        if (readBytes > 0){
          readBuffer.flip();
          byte[] bytes = new byte[readBuffer.remaining()];
          readBuffer.get(bytes);
          String body = new String(bytes, "utf-8");
          System.out.println("The time server receive order : " + body);

          String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                  new Date(System.currentTimeMillis()).toString() :
                  "BAD ORDER";
          doWrite(sc, currentTime);
        }else if (readBytes < 0){
          //对端链路关闭
          key.cancel();
          sc.close();
        }else{
          //读到0字节，忽略
        }
      }
    }
  }

  private void doWrite(SocketChannel channel, String response) throws IOException {
    if (response != null && response.trim().length() > 0){
      byte[] bytes = response.getBytes();
      ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
      byteBuffer.put(bytes);
      byteBuffer.flip();
      channel.write(byteBuffer);
    }
  }

  @Override
  public void run() {
    while(!stop){
      try {
        selector.select(1000);
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> it = selectionKeys.iterator();
        SelectionKey key = null;
        while (it.hasNext()){
          key = it.next();
          it.remove();
          try {
            handleInput(key);
          }catch (Exception e){
            if (key != null){
              key.cancel();
              if (key.channel() != null){
                key.channel().close();
              }
            }
          }
        }
      } catch (Throwable t) {
        t.printStackTrace();
      }
    }

    if (selector != null){
      try {
        selector.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }
}
