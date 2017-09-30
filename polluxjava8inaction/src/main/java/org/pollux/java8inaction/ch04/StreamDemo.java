package org.pollux.java8inaction.ch04;

import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamDemo {

	public static List<Dish> initData() {
		return Arrays.asList(
					new Dish("pork", false, 800, Dish.Type.MEAT),
					new Dish("beef", false, 700, Dish.Type.MEAT),
					new Dish("chicken", false, 400, Dish.Type.MEAT),
					new Dish("french fries", true, 5300, Dish.Type.OTHER),
					new Dish("rice", true, 350, Dish.Type.OTHER),
					new Dish("season fruit", true, 120, Dish.Type.OTHER),
					new Dish("pizza", true, 550, Dish.Type.OTHER),
					new Dish("prawns", false, 300, Dish.Type.FISH),
					new Dish("salmon", false, 450, Dish.Type.FISH)
				);
	}

	public static void main(String[] args) {
		/*
		List<Dish> data = initData();
		List<String> threeHighCaloricDishNames = data.stream().filter(d -> d.getCalories() > 300)
							.map(Dish::getName).limit(3).collect(toList());

		threeHighCaloricDishNames.stream()
						.map(w -> w.split(""))
						.flatMap(Arrays::stream)
						.distinct()
						.collect(toList())
						.forEach(System.out::println);
		*/

		Stream.generate(Math::random).limit(5).forEach(System.out::println);
		//threeHighCaloricDishNames.forEach(System.out::println);
	}

}
