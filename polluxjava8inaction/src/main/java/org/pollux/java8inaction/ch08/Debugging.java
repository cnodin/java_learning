package org.pollux.java8inaction.ch08;

import java.util.Arrays;
import java.util.List;

public class Debugging {

	public static void main(String[] args) {
		List<Point> points = Arrays.asList(new Point(1,2), null);
		points.stream().map(p -> p.getX()).forEach(System.out::println);
	}

	static class Point{
		private Integer x;
		private Integer y;

		public Point(Integer x, Integer y){
			this.x = x;
			this.y = y;
		}

		public Integer getX() {
			return x;
		}

		public void setX(Integer x) {
			this.x = x;
		}

		public Integer getY() {
			return y;
		}

		public void setY(Integer y) {
			this.y = y;
		}
	}

}
