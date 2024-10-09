package MarkedAssignments;

import java.util.Random;
import java.util.Scanner;

/**
 * This program generates random numbers, separates them into even and odd, sorts them,
 * and then displays the results with a summary.
 *
 * 1. Set up constants for messages and maximum random number.
 * 2. Ask the user how many random numbers they want.
 * 3. Try to read the user's input as a number.
 *    - If it's not a valid number or less than 1, show an error and stop the program.
 * 4. Generate the requested amount of random numbers.
 *    - Count how many are even and how many are odd.
 * 5. Separate the even and odd numbers into their own arrays.
 * 6. Sort the even numbers in increasing order and the odd numbers in decreasing order.
 * 7. Display the original random numbers.
 * 8. Display the sorted even and odd numbers, separated by a dash.
 *    - If there are no even or odd numbers, show a message.
 * 9. Show a summary of the total, even, and odd numbers.
 * 10. If there's not enough memory for large arrays, display a memory error.
 *
 * @author Sixten Peterson (sixpet-4)
 * @version 1.0
 */

class MarkedAssignment2 {

    static final String USER_INPUT_PROMPT = "How many random numbers in the range 0 - 999 are desired?";
    static final String RANDOM_NUMBERS_LIST_MESSAGE = "Here are the random numbers:";
    static final String RANDOM_NUMBERS_SORTED_MESSAGE = "Here are the random numbers arranged:";
    static final String NO_ODD_NUMBERS_MESSAGE = "No Odd Numbers";
    static final String NO_EVEN_NUMBERS_MESSAGE = "No Even Numbers";
    static final String INVALID_INPUT_MESSAGE = "Invalid Input";

    static final int MAX_RANDOM_NUMBER = 999;

    public static void main(final String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int[] randArr;
        int randomCount = 0;

        int[] evenArr;
        int evenCount = 0;

        int[] oddArr;
        int oddCount = 0;

        System.out.printf("%s ", USER_INPUT_PROMPT);

        // Try parsing an int from the string provided by the user, if unable inform the user of invalid input.
        try {
            String userInput = input.nextLine();
            randomCount = Integer.parseInt(userInput);
        } catch (NumberFormatException exception) {
            System.out.println(INVALID_INPUT_MESSAGE);
        }

        // Don't allow 0, -1 etc as these are not valid input.
        if (randomCount <= 0) {
            System.out.println(INVALID_INPUT_MESSAGE);
            System.exit(0);
        }

        // Handle possible OutOfMemoryError
        try {
            randArr = new int[randomCount];

            // Generate numbers
            for (int i = 0; i < randomCount; i++) {
                int randNum = rand.nextInt(MAX_RANDOM_NUMBER);
                randArr[i] = randNum;

                // Determine the amount of even and odd numbers for further use later.
                if (randNum % 2 == 0) {
                    evenCount++;
                } else {
                    oddCount++;
                }
            }

            // Initialize the arrays for even and odd numbers
            evenArr = new int[evenCount];
            oddArr = new int[oddCount];

            // Used for keeping track of the index rather than looping over the same data twice and using i as index in each loop.
            int evenIndex = 0;
            int oddIndex = 0;

            // Insert the num into the correct array elements depending on if they are even or odd as well as the array indexes.
            for (int i = 0; i < randomCount; i++) {

                if (randArr[i] % 2 == 0) {
                    evenArr[evenIndex] = randArr[i];
                    evenIndex++;
                } else {
                    oddArr[oddIndex] = randArr[i];
                    oddIndex++;
                }
            }

            /* My implementation of selection sort based on the pseudocode on Canvas (Practice problems module 3),
               but modified for sorting increasingly rather than the other way around */
            for (int i = 0; i < evenArr.length - 1; i++) {
                int min = i;
                for (int k = i + 1; k < evenArr.length; k++) {
                    if (evenArr[k] < evenArr[min]) {
                        min = k;
                    }
                }

                int temp = evenArr[i];
                evenArr[i] = evenArr[min];
                evenArr[min] = temp;
            }

            for (int i = 0; i < oddArr.length - 1; i++) {
                int max = i;
                for (int k = i + 1; k < oddArr.length; k++) {
                    if (oddArr[k] > oddArr[max]) {
                        max = k;
                    }
                }

                int temp = oddArr[i];
                oddArr[i] = oddArr[max];
                oddArr[max] = temp;
            }

            /* Hacky way of implementing the same principle of StringBuilder for appending to a string.
               I.e create a String, append to the String for each number. */
            String randNums;
            randNums = "";

            for (int num: randArr) {
                randNums = randNums + num + " ";
            }

            // Remove the last space from the string for better format by trimming the String.
            randNums = randNums.trim();

            System.out.printf("%n%s%n", RANDOM_NUMBERS_LIST_MESSAGE);
            // Print out the array elements as string
            System.out.println(randNums);

            // Rather than hacking my own implementation of StringBuilder again I've opted to simply print each part
            // on the same line by using loops. Both works well.
            System.out.printf("%n%s%n", RANDOM_NUMBERS_SORTED_MESSAGE);
            if (evenArr.length == 0) {
                System.out.printf("%s ", NO_EVEN_NUMBERS_MESSAGE);
            } else {
                for (int even: evenArr) {
                    System.out.printf("%d ", even);
                }
            }

            System.out.print("-");

            if (oddArr.length == 0) {
                System.out.printf(" %s", NO_ODD_NUMBERS_MESSAGE);
            } else {
                for (int odd: oddArr) {
                    System.out.printf(" %d", odd);
                }
            }

            // Print the program summary
            System.out.printf("%n%nOf the above %d numbers, %d were even and %d odd", randomCount, evenCount, oddCount);

        } catch (OutOfMemoryError error) {
            System.out.println("The program ran out of memory.");
        }


    }
}
