package com.yash.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeTest {

	public static void main(String[] args) {
		Employee employee1 = new Employee("Yash", 20, new Address("1234"),
				Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));

		Employee employee2 = new Employee("Ram", 20, new Address("1235"),
				Arrays.asList(new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));

		Employee employee3 = new Employee("Sita", 20, new Address("1236"),
				Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

		List<Employee> employees = Arrays.asList(employee1, employee2, employee3);

		// Get employee with exact match name "Yash", if not found print "Not
		// found".
		Employee e = employees.stream().filter(emp -> emp.getName().equals("Yash")).findAny().orElse(null);
		if (e == null)
			System.out.println("Not Found exact match name - Yash ");
		else
			System.out.println("Found exact match name - Yash : " + e);

		// Get employee with matching address "1235"
		System.out.println("Employee with matching address 1235 : ");
		employees.stream().filter(e1 -> e1.getAddress().getZipcode() == "1235").forEach(e1 -> System.out.println(e1));

		// Get all employee having mobile numbers 3333.
		Predicate<Employee> empPredicate = emp -> {
			List<MobileNumber> mobileNumbers = emp.getMobileNumbers();
			for (MobileNumber mobileNumber : mobileNumbers) {
				if (mobileNumber.getNumber().equals("3333"))
					return true;
			}
			return false;
		};
		System.out.println("1. Employee having mobile numbers 3333 : ");
		List<Employee> resultEmployees = employees.stream().filter(empPredicate).collect(Collectors.toList());
		resultEmployees.forEach(System.out::println);

		System.out.println("2. Employee having mobile numbers 3333 : ");
		List<Employee> mobEmpList = employees.stream()
				.filter(e4 -> e4.getMobileNumbers().stream().anyMatch(mob -> mob.getNumber().equals("3333")))
				.collect(Collectors.toList());
		mobEmpList.forEach(System.out::println);

		// Convert List<Employee> to List<String> of employee name
		List<String> strList = employees.stream().map(e2 -> e2.getName()).collect(Collectors.toList());
		System.out.println("Employee to String list : " + strList);

		// Collect all the names of employees in a string separated by ||
		String str = employees.stream().map(e2 -> e2.getName()).collect(Collectors.joining("||"));
		System.out.println("Employee names separated by || : " + str);

		// Change the case of List<String>
		List<String> lowercaseList = strList.stream().map(s1 -> s1.toLowerCase()).collect(Collectors.toList());
		System.out.println("Lower case name list : " + lowercaseList);

		List<String> uppercaseList = strList.stream().map(s1 -> s1.toUpperCase()).collect(Collectors.toList());
		System.out.println("Upper case name list : " + uppercaseList);

		// Sort List<String>
		List<String> sortedStrList = strList.stream().sorted().collect(Collectors.toList());
		System.out.println("Sorted name list : " + sortedStrList);

		// sort List<Employee> based on name
		List<Employee> sortedEmpList = employees.stream().sorted(Comparator.comparing(Employee::getName))
				.collect(Collectors.toList());
		System.out.println("Sorted employee list : ");
		for (Employee employee : sortedEmpList) {
			System.out.println(employee);
		}
	}
}
