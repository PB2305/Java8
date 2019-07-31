package com.yash.practice;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsDemo {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(10, 20, 30, 11, 20, 33, 4, 44, 55, 20);

		// collect the result of a Stream into Set
		Set<Integer> set = numbers.stream().collect(Collectors.toSet());
		System.out.println("Set : " + set);

		// collect the result of a Stream into list
		List<Integer> list = numbers.stream().collect(Collectors.toList());
		System.out.println("List : " + list);

		// create Map from the elements of Stream (first remove the duplicates)
		Map<Integer, Integer> map = set.stream().collect(Collectors.toMap(Function.identity(), Integer::valueOf));
		System.out.println("Map : " + map);

		// find summary statistics from a Stream of numbers
		IntSummaryStatistics sum = numbers.stream().collect(Collectors.summarizingInt(Integer::valueOf));
		System.out.println("Sum: " + sum.getSum());

		// partition the result of Stream in two parts i.e., odd and even
		Map<Boolean, List<Integer>> oddAndEvenNumsMap = numbers.stream()
				.collect(Collectors.partitioningBy(i -> i % 2 == 0));
		System.out.println("Even number partition :" + oddAndEvenNumsMap.get(true));
		System.out.println("Odd number partition :" + oddAndEvenNumsMap.get(false));

		// create comma separated string from numbers
		String commaSeparatedStr = numbers.stream().map(String::valueOf).collect(Collectors.joining(","));
		System.out.println("Comma Separated String : " + commaSeparatedStr);
	}
}
