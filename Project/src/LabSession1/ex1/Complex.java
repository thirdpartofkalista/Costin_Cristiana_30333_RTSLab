package LabSession1.ex1;

import java.util.Scanner;

public class Complex {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define the complex numbers
        ComplexNumber num1 = new ComplexNumber(2, 5);
        ComplexNumber num2 = new ComplexNumber(4, -1);

        // Display menu
        System.out.println("Complex Number Calculator Menu:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.print("Choose 1, 2 or 3: ");
        int choice = scanner.nextInt();

        // Perform operation based on user's choice
        switch(choice) {
            case 1:
                System.out.println("Result: " + num1.add(num2));
                break;
            case 2:
                System.out.println("Result: " + num1.subtract(num2));
                break;
            case 3:
                System.out.println("Result: " + num1.multiply(num2));
                break;
            default:
                System.out.println("Invalid choice!");
        }

// Prompt user for two complex numbers
        System.out.print("Enter the real part of the first complex number: ");
        double real1 = scanner.nextDouble();
        System.out.print("Enter the imaginary part of the first complex number: ");
        double imag1 = scanner.nextDouble();
        ComplexNumber number1 = new ComplexNumber(real1, imag1);

        System.out.print("Enter the real part of the second complex number: ");
        double real2 = scanner.nextDouble();
        System.out.print("Enter the imaginary part of the second complex number: ");
        double imag2 = scanner.nextDouble();
        ComplexNumber number2 = new ComplexNumber(real2, imag2);

        System.out.print("Choose 1, 2 or 3: ");
        int choice1 = scanner.nextInt();
        // Perform operation based on user's choice
        switch(choice1) {
            case 1:
                System.out.println("Result: " + number1.add(number2));
                break;
            case 2:
                System.out.println("Result: " + number1.subtract(number2));
                break;
            case 3:
                System.out.println("Result: " + number1.multiply(number2));
                break;
            default:
                System.out.println("Invalid choice!");
        }
        scanner.close();
    }
}

class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber add(ComplexNumber other) {
        double realSum = this.real + other.real;
        double imagSum = this.imaginary + other.imaginary;
        return new ComplexNumber(realSum, imagSum);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        double realDiff = this.real - other.real;
        double imagDiff = this.imaginary - other.imaginary;
        return new ComplexNumber(realDiff, imagDiff);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double realProduct = (this.real * other.real) - (this.imaginary * other.imaginary);
        double imagProduct = (this.real * other.imaginary) + (this.imaginary * other.real);
        return new ComplexNumber(realProduct, imagProduct);
    }

    public String toString() {
        return real + " + " + imaginary + "i";
    }
}
