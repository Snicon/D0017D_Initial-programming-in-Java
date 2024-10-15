package MarkedAssignments;

import java.util.Scanner;

/**
 * The program handles user input for calculating area and volume calculations,
 * as well as fraction simplifications and printing.
 *
 * PSEUDOCODE:
 * 1. Print header for area and volume methods.
 * 2. Loop:
 *    - Get radius input using input().
 *    - If radius is QUIT, break the loop.
 *    - Get height input using input().
 *    - If height is QUIT, break the loop.
 *    - Print radius and height.
 *    - Print circle area using area(radius).
 *    - Print cone area using area(radius, height).
 *    - Print cone volume using volume(radius, height).
 * 3. Print header for fractional methods.
 * 4. Loop:
 *    - Get numerator input using input().
 *    - If numerator is QUIT, break the loop.
 *    - Get denominator input using input().
 *    - If denominator is QUIT, break the loop.
 *    - Print fraction using printFraction(fraction(numerator, denominator)).
 *
 * @author Sixten Peterson (sixpet-4)
 * @version 1.0
 */
public class MarkedAssignment3 {
    //Creation of scanner object.
    private static Scanner userInputScanner = new Scanner(System.in);

    //Constants
    static final int QUIT = -1;

    /**
     * This method should be used only for unit testing on CodeGrade. Do not change this method!
     * Do not remove this method!
     * Swaps userInputScanner with a custom scanner object bound to a test input stream
     *
     * @param inputScanner - test scanner object
     */
    public static void injectInput(final Scanner inputScanner) {
        userInputScanner = inputScanner;
    }


    // Untouched from submission template
    public static void main(final String[] args) {
        int radius = 0;
        int height = 0;
        int numerator = 0;
        int denominator = 0;

        //Print the header of the program for area and volume.
        System.out.println("----------------------------------");
        System.out.println("# Test of area and volume methods");
        System.out.println("----------------------------------");

        // While loop that runs until user enters "q" for area and volume.

        while (true) {

            radius = input();
            if (radius == QUIT) {
                break;
            }

            height = input();
            if (height == QUIT) {
                break;
            }

            System.out.println("r = " + radius + ", h = " + height);
            System.out.printf("Circle area: %.2f %n", area(radius));
            System.out.printf("Cone area: %.2f %n", area(radius, height));
            System.out.printf("Cone volume: %.2f %n%n", volume(radius, height));
        }

        //Print the header of the program for area and volume.
        System.out.println("----------------------------------");
        System.out.println("# Test of the fractional methods");
        System.out.println("----------------------------------");


        // While loop that runs until user enters "q" for the fraction part
        while (true) {

            numerator = input();
            if (numerator == QUIT) {
                break;
            }

            denominator = input();
            if (denominator == QUIT) {
                break;
            }

            System.out.printf("%d/%d = ", numerator, denominator);
            printFraction(fraction(numerator, denominator));
        }
    }

    /** The method returns the area of a circle as a double based on the radius of the circle
     *
     * @param radius the radius of the circle
     * @return area of the circle as a double
     */
    public static double area(final int radius) {
        return Math.PI * Math.pow(radius, 2.0);
    }

    /** The method returns the lateral surface area of a cone as a double based on the radius of the base and the height.
     *  Calling the pythagoras method helps us to calculate the hypotenuse. The hypotenuse is then used to calculate
     *  the area by multiplying it with the radius and pi.
     *
     * @param radius the radius of the base of the cone
     * @param height the height of the cone
     * @return lateral surface area of the cone as a double
     */
    public static double area(final int radius, final int height) {
        return Math.PI * radius * pythagoras(radius, height);
    }

    /** The method returns the hypotenuse according to the Pythagoras' theorem.
     *  The method simply calculates the hypotenuse by doing the square root of a^2 + b^2.
     *  (Sometimes I wish all math could be this simple.)
     *
     * @param sideA the length of one side of the triangle
     * @param sideB the length of another side of the triangle
     * @return the hypotenuse of the triangle as a double
     */
    public static double pythagoras(final int sideA, final int sideB) {
        return Math.sqrt(Math.pow(sideA, 2.0) + Math.pow(sideB, 2.0));
    }

    /** The method returns the volume of a cone according to very basic calculations.
     *  (PI * r^2 * h) / 3 => volume
     *
     * @param radius the radius of the base of the cone
     * @param height the height of the cone
     * @return the volume of the cone as a double
     */
    public static double volume(final int radius, final int height) {
        return (Math.PI * Math.pow(radius, 2.0) * height) / 3;
    }

    /**
     * This method calculates the whole part and the remainder of a fraction, and simplifies the remainder if possible.
     * It returns an array containing three elements:
     * - The whole part of the fraction (numerator divided by denominator).
     * - The remainder after division.
     * - The denominator of the simplified fraction.
     *
     * If the denominator is 0, the method returns null, indicating an undefined fraction.
     * If the numerator is 0, the method returns an array representing the fraction 0 (i.e., {0, 0, 0}).
     * If there is a remainder, the method simplifies the fraction by calculating the greatest common divisor (GCD)
     * of the remainder and the denominator and returning the simplified form.
     *
     * @param numerator   the numerator of the fraction
     * @param denominator the denominator of the fraction
     * @return an array of three integers representing the whole part, remainder, and denominator of the fraction.
     *         Returns null if the denominator is 0 (undefined fraction).
     */
    public static int[] fraction(final int numerator, final int denominator) {
        int[] resultArr = new int[] {0, 0, 0};

        if (denominator == 0) {
            return null; // Undefined fractions (denominator is equal 0), return null.
        } else if (numerator == 0) {
            return resultArr; // return [0, 0, 0] if numerator is equal to 0.
        }

        resultArr[0] = numerator / denominator; // Whole part
        resultArr[1] = numerator % denominator; // Remainder
        resultArr[2] = denominator;             // Denominator

        if (resultArr[1] != 0) { // Should only simplify if there is a remainder
            int gcd = gcd(resultArr[1], resultArr[2]);
            resultArr[1] = resultArr[1] / gcd; // Simplify remainder
            resultArr[2] = resultArr[2] / gcd; // Simplify denominator
        }

        return resultArr; // Return the result as an array
    }

    /**
     * This method calculates the greatest common divisor (GCD) of two integers using the iterative
     * implementation of the Euclidean algorithm. The GCD is the largest positive integer that divides
     * both numbers without leaving a remainder. Takes you way back to math class, doesn't it? :P
     *
     * The method continues to divide the larger number by the smaller number and uses the remainder
     * for subsequent divisions until the remainder is 0. At that point, the divisor is the GCD.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the greatest common divisor of a and b as an integer
     */
    public static int gcd(final int a, final int b) {
        int tempA = a;
        int tempB = b;
        int tempC = 0;

        while (tempB != 0) {
            tempC = tempB;
            tempB = tempA % tempB;
            tempA = tempC;
        }

        return tempA;
    }

    /**
     * This method prints a fraction based on the provided array of integer parts.
     * The array contains three elements:
     * - The whole part of the fraction.
     * - The remainder (numerator of the fractional part).
     * - The denominator of the fraction.
     *
     * The method the following cases:
     * - If the array is null (denominator is 0), it prints "Error".
     * - If both the whole part and remainder are 0, it prints 0 (fraction is zero).
     * - If there is only a whole part (no remainder), it prints the whole part.
     * - If there is only a fraction part (no whole part), it prints the fraction in the form "numerator/denominator".
     * - If both the whole part and fraction part exist, it prints the mixed fraction in the form "whole remainder/denominator".
     *
     * @param parts an array of three integers representing the whole part, remainder, and denominator of the fraction.
     */
    public static void printFraction(final int[] parts) {
        // Denominator us equal to 0, return null
        if (parts == null) {
            System.out.println("Error");
        // Both numerator and remainder are 0, fraction is 0
        } else if (parts[0] == 0 && parts[1] == 0 && parts[2] == 0) {
            System.out.println(0);
        // There only exists a whole part, there is no remainder
        } else if (parts[1] == 0) {
            System.out.println(parts[0]);
        // Only fraction part exists, there is no whole part
        } else if (parts[0] == 0) {
            System.out.printf("%d/%d%n", parts[1], parts[2]);
        // Both whole and fraction parts exist, mixed fraction
        } else {
            System.out.printf("%d %d/%d%n", parts[0], parts[1], parts[2]);
        }
    }

    /**
     * This method handles user input, either returning an integer or recognizing a quit command ("q"/"Q").
     * It continuously prompts for input until valid data is provided:
     * - If the input is an integer, it returns the absolute value of that integer (converts negative numbers to positive).
     * - If the input is the letter "q" (no matter if its upper or lower case), it returns -1 to indicate a quit command.
     * - The loop continues until a valid integer or quit command is received.
     *
     * @return the absolute value of the entered integer, or -1 if the user enters "q" to quit.
     */
    public static int input() {
        int num = 0;

        while (true) {
            // Handle ints
            if (userInputScanner.hasNextInt()) {
                // Make the num absolute (-1 => 1, 1 => 1, etc)
                num = Math.abs(userInputScanner.nextInt());
                if (num >= 0) {
                    break;
                }
            // Handle strings
            } else if (userInputScanner.hasNext()) {
                String inputString = userInputScanner.next();

                if (inputString.equalsIgnoreCase("q")) {
                    num = QUIT;
                    break;
                }
            }
        }

        return num;
    }

}
