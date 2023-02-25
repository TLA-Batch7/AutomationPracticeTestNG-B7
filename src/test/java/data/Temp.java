package data;

import utils.ConfigReader;

public class Temp {
    public static void main(String[] args) {
//        Employee employee1 = new Employee("John Doe",1, "IT");
//
//        System.out.println(employee1.toString());
//        System.out.println(employee1.getDepartment());
//        System.out.println(employee1.getName());
//
//        employee1.setName("John Adams");
//        System.out.println(employee1.getName());

        String url = ConfigReader.readProperty("configuration.properties","url");
        System.out.println(url);
    }
}
