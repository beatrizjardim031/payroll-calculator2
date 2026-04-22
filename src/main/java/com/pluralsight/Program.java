package com.pluralsight;
import java.io.*;
import java.util.Scanner;

public class Program {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.print("Enter the name of the file to process: "); // ask the user for the input filename
        String userInput = input.nextLine(); // stores the input

        try { // try the risky code because files can fail
            FileReader fileReader = new FileReader(userInput); // opens the file the user asked for (like opening a book)
            BufferedReader bufferedReader = new BufferedReader(fileReader);// creates efficient reader that goes to the file line by line

            String line = bufferedReader.readLine(); // reads the first line of the file

            System.out.print("Enter the name of the payroll file to create: "); // asks the user for the output filename
            userInput = input.nextLine();

            FileWriter fileWriter = new FileWriter(userInput); // creates or opens the output file for writing
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); // creates the efficient writer

            while(line != null) { // keep looping as long as there are lines to read
                Employee employee = loadEmployee(line); // calls loadEmployee method
                writeEmployee(employee, bufferedWriter); // calls writeEmployee method
                System.out.printf("%d | %-20s | $%.2f%n",employee.getEmployeeID(), employee.getName(), employee.getGrossPay()); // prints employees info
                line = bufferedReader.readLine();
            }
            bufferedReader.close(); // closes when both files are done
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Wrong file. Please try again."); // if anything goes wrong with the file catch the error and prints this
        }
    }
    //methods
    public static Employee loadEmployee(String line){
        String[] tokens = line.split("\\|"); // splits each info into an array using | as a separator
        int employeeID = Integer.parseInt(tokens[0]); // converts a string into an int
        String name = tokens[1];
        double hoursWorked = Double.parseDouble(tokens[2]); // converts a string into a double
        double payRate = Double.parseDouble(tokens[3]); // converts a string into a double

        return new Employee(employeeID, name, hoursWorked, payRate); // returns a new employee object
    }
    public static void writeEmployee(Employee employee, BufferedWriter bufferedWriter){
        try {
            bufferedWriter.write(String.format("%d | %-20s | $%.2f%n", employee.getEmployeeID(), employee.getName(), employee.getGrossPay()));
        } catch (IOException e) {
            System.out.println("Oh no! Something is wrong... Try again later.");
        }
    }
}
