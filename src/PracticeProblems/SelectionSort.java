package PracticeProblems;

import java.util.Random;

public class SelectionSort {

    public static void main(final String[] args) {
        // Declare the array
        int[] unsortedArr = new int[30];
        // New random object for generating pseudo-random numbers
        Random rand = new Random();

        // Loop through the array to assign random numbers for each item in the array
        for (int i = 0; i < unsortedArr.length; i++) {
            unsortedArr[i] = rand.nextInt(99) + 1;
            System.out.println((i+1) + ": " + unsortedArr[i]);
        }

        // Implementation of the selection sort algorithm
        for (int i = 0; i < unsortedArr.length - 1; i++) {
            int max = i;
            for (int k = i+1; k < unsortedArr.length; k++) {
                if(unsortedArr[k] > unsortedArr[max]) {
                    max = k;
                }
            }

            int temp = unsortedArr[i];
            unsortedArr[i] = unsortedArr[max];
            unsortedArr[max] = temp;
        }

        // Let the user know that we are going to print the sorted array.
        System.out.println("Sorted array (biggest to smallest):");

        // Print out the array.
        for (int i = 0; i < unsortedArr.length; i++) {
            System.out.println((i+1) + ": " + unsortedArr[i]);
        }
    }

}
