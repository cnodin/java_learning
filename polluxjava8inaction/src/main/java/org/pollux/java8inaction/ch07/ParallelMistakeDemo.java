package org.pollux.java8inaction.ch07;


import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParallelMistakeDemo {

	public static void main(String[] args) {
		Logger l = Logger.getGlobal();
		l.log(Level.INFO, () -> "yyyy");
	}

}
