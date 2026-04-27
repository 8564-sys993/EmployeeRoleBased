package com.EmployeeDataRoleBased.demo.DummyData;

import java.util.ArrayList;
import java.util.List;

import com.EmployeeDataRoleBased.demo.Entity.Employee;

public class Data {

	 public static List<Employee> getEmployees() {
	        List<Employee> employees = new ArrayList<>();

	        employees.add(new Employee(101L, "Nihal", "Engineering", "nihal@company.com", "9876543210", "EMPLOYEE", "nihal123"));
	        employees.add(new Employee(102L, "Varun", "Marketing",   "varun@company.com", "9876543211", "EMPLOYEE", "varun123"));
	        employees.add(new Employee(103L, "Sneha", "Design",      "sneha@company.com", "9876543213", "EMPLOYEE", "sneha123"));
	        employees.add(new Employee(104L, "Arjun", "Finance",     "arjun@company.com", "9876543214", "EMPLOYEE", "arjun123"));
	        employees.add(new Employee(201L, "Rahul", "HR",          "rahul@company.com", "9876543212", "MANAGER",  "rahul123"));
	        employees.add(new Employee(202L, "Priya", "Engineering", "priya@company.com", "9876543215", "MANAGER",  "priya123"));

	        return employees;
	    }
}
