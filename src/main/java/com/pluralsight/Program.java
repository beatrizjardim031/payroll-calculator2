package com.pluralsight;
import java.io.*;
import java.util.Scanner;

public class Program {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.print("Enter the name of the file to process: ");
        String userInput = input.nextLine();

        try {
            FileReader fileReader = new FileReader(userInput);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            System.out.print("Enter the name of the payroll file to create: ");
            userInput = input.nextLine();

            FileWriter fileWriter = new FileWriter(userInput);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            while(line != null) {
                Employee employee = loadEmployee(line);
                writeEmployee(employee, bufferedWriter);
                System.out.printf("%d | %-20s | $%.2f%n",employee.getEmployeeID(), employee.getName(), employee.getGrossPay());
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            bufferedWriter.close();

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

        return new Employee(employeeID, name, hoursWorked, payRate);
    }
    public static void writeEmployee(Employee employee, BufferedWriter bufferedWriter){
        try {
            bufferedWriter.write(String.format("%d | %-20s | $%.2f%n", employee.getEmployeeID(), employee.getName(), employee.getGrossPay()));
        } catch (IOException e) {
            System.out.println("Oh no! Something is wrong... Try again later.");
        }
    }
}
