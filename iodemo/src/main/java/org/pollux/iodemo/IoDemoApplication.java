package org.pollux.iodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.ByteBuffer;

//@SpringBootApplication
public class IoDemoApplication {

	public static void main(String[] args) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(6);
		byteBuffer.put((byte)3);

		byteBuffer.position(0);
		byteBuffer.limit(1);

		Byte b = byteBuffer.get();
		System.out.println(b);

		//SpringApplication.run(IoDemoApplication.class, args);
	}
}
