package LabSession1.ex3;

import java.util.Arrays;
import java.util.Random;

public class RandomNum {

    public static void main(String[] args) {
        int[] numbers = new int[10];
        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }

        System.out.println("Original array:");
        displayArray(numbers);

        Arrays.sort(numbers);

        System.out.println("Sorted array:");
        displayArray(numbers);
    }

    public static void displayArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

