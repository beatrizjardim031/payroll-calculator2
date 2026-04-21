package com.pluralsight;
import java.io.*;
import java.util.Scanner;

public class Program {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Enter the name of the file to process: ");
        String userInput = input.nextLine();

        try {
            FileReader fileReader = new FileReader(userInput);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while(line != null) {
                Employee employee = loadEmployee(line);
                System.out.printf("%d | %-20s | $%.2f%n",employee.getEmployeeID(), employee.getName(), employee.getGrossPay());
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Wrong file. Please try again.");
        }
    }
    //methods
    public static Employee loadEmployee(String line){
        String[] tokens = line.split("\\|");
        int employeeID = Integer.parseInt(tokens[0]);
        String name = tokens[1];
        double hoursWorked = Double.parseDouble(tokens[2]);
        double payRate = Double.parseDouble(tokens[3]);

       Employee employee = new Employee(employeeID, name, hoursWorked, payRate);
       return employee;
    }
}
