package org.pollux.java8inaction.ch02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 17/10/14
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class AppleFilter02 {

	public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if (p.test(apple)){
				result.add(apple);
			}
		}

		return result;
	}

	public interface ApplePredicate {
		boolean test (Apple apple);
	}

	public class AppleHeavyWeightPredicate implements ApplePredicate{
		@Override
		public boolean test (Apple apple) {
			return apple.getWeight() > 150;
		}
	}

	public class AppleGreenColorPredicate implements ApplePredicate{
		@Override
		public boolean test (Apple apple) {
			return "green".equals(apple.getColor());
		}
	}


}
