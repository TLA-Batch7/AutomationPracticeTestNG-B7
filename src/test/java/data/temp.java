package data;

import data.pojo.Employee;

public class temp {
    public static void main(String[] args) {
        Employee employee1 = new Employee("John Doe",1, "IT");

        System.out.println(employee1.toString());
        System.out.println(employee1.getDepartment());
        System.out.println(employee1.getName());

        employee1.setName("John Adams");
        System.out.println(employee1.getName());
    }
}
