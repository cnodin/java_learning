package org.pollux.java8inaction.ch02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 17/10/14
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class AppleFilter01 {

	//req1:过滤绿色苹果
	public static List<Apple> filterGreenApples(List<Apple> inventory){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if ("green".equals(apple.getColor())){
				result.add(apple);
			}
		}

		return result;
	}

	//req2:根据颜色过滤苹果
	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if (color.equals(apple.getColor())){
				result.add(apple);
			}
		}

		return result;
	}

	//req2:根据重量过滤苹果
	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if (apple.getWeight() > weight){
				result.add(apple);
			}
		}

		return result;
	}

	//req3:合并颜色和重量，通过flag来区分对颜色或重量的查询
	//调用:filterApples(inventory, "green", 0, true)
	//		 filterApples(inventory, "", 150, false)
	public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if ((flag && apple.getColor().equals(color)) ||
							(!flag && apple.getWeight() > weight)){
				result.add(apple);
			}
		}

		return result;
	}
}
