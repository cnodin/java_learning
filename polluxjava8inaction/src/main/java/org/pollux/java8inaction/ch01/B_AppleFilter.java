package org.pollux.java8inaction.ch01;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * use function to filter apples
 *
 */
public class B_AppleFilter {


	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
																					new Apple(155, "green"),
																					new Apple(120, "red"));
		List<Apple> greenApples = filterGreenApples(inventory);
		System.out.println(greenApples);

		List<Apple> redApples = filterApplesByColor(inventory, "red");
		System.out.println(redApples);

		List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
		System.out.println(greenApples2);

		List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
		System.out.println(heavyApples);

		List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
		System.out.println(redAndHeavyApples);

		List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
			public boolean test(Apple apple) {
				return apple.getColor().equals("red");
			}
		});
		System.out.println(redApples2);

		List<Apple> redApples3 = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
		System.out.println(redApples3);

	}

	/**
	 * 过滤绿色苹果
	 *
	 * @param inventory 苹果库存
	 * @return 所有绿色的苹果
	 */
	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList();
		for(Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	/**
	 * 根据颜色来过滤苹果
	 *
	 * @param inventory 苹果库存
	 * @param color 苹果颜色
	 * @return 符合条件的苹果
	 */
	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList();
		for(Apple apple : inventory) {
			if (color.equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	/**
	 * 根据重量来过滤苹果
	 *
	 * @param inventory 苹果库存
	 * @param weight	苹果重量
	 * @return 符合条件的苹果
	 */
	public static List<Apple> filterApplesByWeight(List<Apple> inventory, Integer weight) {
		List<Apple> result = new ArrayList();
		for(Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	/**
	 * 将条件代码作为参数传递到方法中
	 * @param inventory
	 * @param p
	 * @return
	 */
	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList();
		for(Apple apple : inventory){
			if (p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}

	public static class Apple{

		private Integer weight = 0;
		private String color;

		public Apple(Integer weight, String color){
			this.weight = weight;
			this.color = color;
		}

		public static boolean isGreenApple(Apple apple){
			return "green".equals(apple.getColor());
		}

		public static boolean isHeavyApple(Apple apple){
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

	interface ApplePredicate {
		boolean test(Apple apple);
	}

	static class AppleWeightPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return apple.getWeight() > 150;
		}
	}

	static class AppleColorPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "green".equals(apple.getColor());
		}
	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "red".equals(apple.getColor()) && apple.getWeight() > 150;
		}
	}



}
