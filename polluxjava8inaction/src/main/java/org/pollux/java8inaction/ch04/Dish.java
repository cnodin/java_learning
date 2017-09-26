package org.pollux.java8inaction.ch04;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dish {
	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;

	public enum Type {MEAT, FISH, OTHER}

	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}
}
