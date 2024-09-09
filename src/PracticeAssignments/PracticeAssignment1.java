package PracticeAssignments;

/**
 * The program prints the string "Hello world!" to the console as an output. For more details see the list below:
 * <ol>
 *     <li>Defines a constant for the string "Hello world!".</li>
 *     <li>Run the {@link PracticeAssignment1#printHelloWorld} function.</li>
 *     <li>The constant is then printed as an output in the terminal.</li>
 * </ol>
 *
 * @author Sixten Peterson (sixpet-4)
 * @version 1.0
 */
public class PracticeAssignment1 {
    static final String HELLO_WORLD = "Hello world!";

    public static void main(final String[] args) {
        printHelloWorld();
    }

    /**
     * The function prints the string "Hello world!" in the terminal.
     * <ol>
     *     <li>The constant {@link PracticeAssignment1#HELLO_WORLD} is printed
     *      as an output in the terminal.</li>
     * </ol>
     *
     * I've opted for a constant since I find it cleaner to look at and is
     * easily understood by others reading the code, all while making the
     * string easy to edit at any time before compiling.
     *
     * @author Sixten Peterson (sixpet-4)
     */

    public static void printHelloWorld() {
        System.out.println(HELLO_WORLD);
    }
}
