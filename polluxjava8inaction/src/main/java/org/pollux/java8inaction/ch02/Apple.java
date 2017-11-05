package org.pollux.java8inaction.ch02;

import org.pollux.java8inaction.ch01.B_AppleFilter;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 17/10/14
 * Time: 下午3:24
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Apple {

		private Integer weight = 0;
		private String color;

		public Apple(Integer weight, String color){
			this.weight = weight;
			this.color = color;
		}

		public static boolean isGreenApple(B_AppleFilter.Apple apple){
			return "green".equals(apple.getColor());
		}

		public static boolean isHeavyApple(B_AppleFilter.Apple apple){
			return apple.getWeight() > 150;
		}

		@Override
		public String toString() {
			return String.format("Apple{color={%s}, weight={%d}}", color, weight);
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}
}
