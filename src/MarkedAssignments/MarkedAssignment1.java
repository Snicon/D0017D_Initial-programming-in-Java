package MarkedAssignments;

import java.util.Scanner;
import java.util.Random;

/**
 * The program is a game where the player will roll 3 dice to get a total
 * sum of 12 in order to win.
 *
 * 1. Constant declaration.
 * 2. Variable declaration.
 * 3. Creating an instance of the Scanner object for handling user input.
 * 4. Creating an instance of the Random object for creation of random
 *    numbers.
 * 5. Print the GAME_START message to welcome to user.
 * 6. Enter the game state/loop.
 * 7. Handle user input for actions (1, 2, 3. q/Q) via switch statement
 *  - ACTION - 1 through 3: Determine if the dice has been rolled
 *    previously in this round. If not, "roll the dice" by generating
 *    a number. Mark the dice as rolled. Increase the amount of dice
 *    rolls by one. Calculate the sum. If dice rolls are equal to max
 *    dice rolls, determine win/loss/tie and print info, then reset for
 *    the next round. Otherwise, print game status. Start over via the
 *    loop.
 *  - ACTION - q/Q: Exit the game state/loop. Print the game summary,
 *    GAME_OVER message, close the scanner instance and let the program
 *    complete.
 *  - ACTION - invalid input: Inform the user of invalid input and start
 *    over via the loop.
 *
 * @author Sixten Peterson (sixpet-4)
 */
class MarkedAssignment1 {
    // Declaration of constants
    static final String GAME_START = "Welcome to dice game 12. You must roll 1-3 dice and try to get the sum of 12 ...\n";
    static final String CHOOSE_DICE = "Enter which dice you want to roll [1,2,3] (exit with q): ";
    static final String ROUND_WON = "You won!!";
    static final String ROUND_LOST = "You lost!!";
    static final String ROUND_TIE = "You neither won nor lost the round.";
    static final String NEXT_ROUND = "Next round!";
    static final String GAME_OVER = "Game Over!";
    static final String ALREADY_SELECTED_DICE = "Sorry, you have already rolled that dice. Try again";
    static final String INVALID_ENTRY = "Sorry, that is an invalid entry. Try again. Valid entries are 1, 2, 3, and q\n";
    static final String AMOUNT_WIN_STRING = "#win: ";
    static final String AMOUNT_LOST_STRING = " #loss: ";
    static final String SUM_STRING = " sum: ";
    static final int MAX_DICE_VALUE = 6;
    static final int MIN_DICE_VALUE = 1;
    // There are three dice, hence a player should not be able to roll dice
    // 4, etc
    static final int MAX_DICE = 3;
    static final int DICE_SUM_TARGET_VALUE = 12;

    static final String ROLL_DICE_1_ACTION = "1";
    static final String ROLL_DICE_2_ACTION = "2";
    static final String ROLL_DICE_3_ACTION = "3";
    static final String QUIT_ACTION_UPPERCASE = "Q";
    static final String QUIT_ACTION_LOWERCASE = "q";

    public static void main(final String[] args) {

        int dice1Value = 0; // The value of the first dice
        // Whether the first dice is rolled or not
        boolean isDice1Rolled = false;

        int dice2Value = 0; // The value of the second dice
        // Whether the second dice is rolled or not
        boolean isDice2Rolled = false;

        int dice3Value = 0; // The value of the third dice
        // Whether the third dice is rolled or not
        boolean isDice3Rolled = false;

        int sum = 0; // The sum of the dice values
        int winCounter = 0; // The number of wins
        int lossCounter = 0; // The number of losses
        int diceNumber = 0; // The chosen dice
        int numOfRolls = 0; // The number of rolled dice

        boolean isPlaying = true; // Whether the game is being played or not

        Scanner userInput = new Scanner(System.in); // Scanner instance
        Random randomNumber = new Random(); // Random instance

        System.out.println(GAME_START); // Print GAME_START message

        while (isPlaying) {
            System.out.print(CHOOSE_DICE); // Print CHOOSE_DICE message

            if (userInput.hasNextLine()) {
                String input = userInput.nextLine();
                switch (input) {
                    case ROLL_DICE_1_ACTION:
                        if (isDice1Rolled) {
                            System.out.println(ALREADY_SELECTED_DICE);
                        } else {
                            // Generates random int from 0 to 5 and then adds 1 to make the number
                            // 1 to 6 meaning we are assigning "legal" dice values.
                            dice1Value = randomNumber.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE;
                            isDice1Rolled = true; // Mark the dice as rolled for this round
                            numOfRolls++; // Increase the amount of dice rolled by one
                            sum = dice1Value + dice2Value + dice3Value; // Calculate sum

                            if (numOfRolls == MAX_DICE) {
                                if (sum == DICE_SUM_TARGET_VALUE) { // win
                                    winCounter++;
                                    System.out.printf("%d %d %d%s%d %s%d%s%d%n", dice1Value, dice2Value, dice3Value, SUM_STRING, sum, AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter);
                                    System.out.println(ROUND_WON);
                                } else if (sum > DICE_SUM_TARGET_VALUE) { // loss
                                    lossCounter++;
                                    System.out.printf("%d %d %d%s%d %s%d%s%d%n", dice1Value, dice2Value, dice3Value, SUM_STRING, sum, AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter);
                                    System.out.println(ROUND_LOST);
                                } else { // tie
                                    System.out.printf("%d %d %d%s%d %s%d%s%d%n", dice1Value, dice2Value, dice3Value, SUM_STRING, sum, AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter);
                                    System.out.println(ROUND_TIE);
                                }

                                // Always run when three dice rolls have been completed. Reset for next round.
                                sum = 0;
                                dice1Value = 0;
                                isDice1Rolled = false;
                                dice2Value = 0;
                                isDice2Rolled = false;
                                dice3Value = 0;
                                isDice3Rolled = false;
                                numOfRolls = 0;

                                System.out.printf("%n%s%n%n", NEXT_ROUND);
                            } else {
                                // Print without resetting
                                System.out.printf("%d %d %d%s%d %s%d%s%d%n", dice1Value, dice2Value, dice3Value, SUM_STRING, sum, AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter);
                            }
                        }
                        break;
                    case ROLL_DICE_2_ACTION:
                        if (isDice2Rolled) {
                            System.out.println(ALREADY_SELECTED_DICE);
                        } else {
                            dice2Value = randomNumber.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE; // Generates random int from 0 to 5 and then adds 1 to make the number 1 to 6 meaning we are assigning "legal" dice values.
                            isDice2Rolled = true; // Mark the dice as rolled for this round
                            numOfRolls++; // Increase the amount of dice rolled by one
                            sum = dice1Value + dice2Value + dice3Value; // Update the sum, will be display in next print

                            if (numOfRolls == MAX_DICE) {
                                if (sum == DICE_SUM_TARGET_VALUE) { // win
                                    winCounter++;
                                    System.out.printf("%d %d %d%s%d %s%d%s%d%n", dice1Value, dice2Value, dice3Value, SUM_STRING, sum, AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter);
                                    System.out.println(ROUND_WON);
                                } else if (sum > DICE_SUM_TARGET_VALUE) { // lose
                                    lossCounter++;
                                    System.out.printf("%d %d %d%s%d %s%d%s%d%n", dice1Value, dice2Value, dice3Value, SUM_STRING, sum, AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter);
                                    System.out.println(ROUND_LOST);
                                } else { // tie
                                    System.out.printf("%d %d %d%s%d %s%d%s%d%n", dice1Value, dice2Value, dice3Value, SUM_STRING, sum, AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter);
                                    System.out.println(ROUND_TIE);
                                }

                                // Always run when three dice rolls have been completed. Reset for next round.
                                sum = 0;
                                dice1Value = 0;
                                isDice1Rolled = false;
                                dice2Value = 0;
                                isDice2Rolled = false;
                                dice3Value = 0;
                                isDice3Rolled = false;
                                numOfRolls = 0;

                                System.out.printf("%n%s%n%n", NEXT_ROUND);
                            } else {
                                // Print without resetting
                                System.out.printf("%d %d %d%s%d %s%d%s%d%n", dice1Value, dice2Value, dice3Value, SUM_STRING, sum, AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter);
                            }
                        }
                        break;
                    case ROLL_DICE_3_ACTION:
                        if (isDice3Rolled) {
                            System.out.println(ALREADY_SELECTED_DICE);
                        } else {
                            dice3Value = randomNumber.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE; // Generates random int from 0 to 5 and then adds 1 to make the number 1 to 6 meaning we are assigning "legal" dice values.
                            isDice3Rolled = true; // Mark the dice as rolled for this round
                            numOfRolls++; // Increase the amount of dice rolled by one
                            sum = dice1Value + dice2Value + dice3Value; // Update the sum, will be display in next print

                            if (numOfRolls == MAX_DICE) {
                                if (sum == DICE_SUM_TARGET_VALUE) { // win
                                    winCounter++;
                                    System.out.printf("%d %d %d%s%d %s%d%s%d%n", dice1Value, dice2Value, dice3Value, SUM_STRING, sum, AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter);
                                    System.out.println(ROUND_WON);
                                } else if (sum > DICE_SUM_TARGET_VALUE) { // lose
                                    lossCounter++;
                                    System.out.printf("%d %d %d%s%d %s%d%s%d%n", dice1Value, dice2Value, dice3Value, SUM_STRING, sum, AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter);
                                    System.out.println(ROUND_LOST);
                                } else { // tie
                                    System.out.printf("%d %d %d%s%d %s%d%s%d%n", dice1Value, dice2Value, dice3Value, SUM_STRING, sum, AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter);
                                    System.out.println(ROUND_TIE);
                                }

                                // Always run when three dice rolls have been completed. Reset for next round.
                                sum = 0;
                                dice1Value = 0;
                                isDice1Rolled = false;
                                dice2Value = 0;
                                isDice2Rolled = false;
                                dice3Value = 0;
                                isDice3Rolled = false;
                                numOfRolls = 0;

                                System.out.printf("%n%s%n%n", NEXT_ROUND);
                            } else {
                                // Print without resetting
                                System.out.printf("%d %d %d%s%d %s%d%s%d%n", dice1Value, dice2Value, dice3Value, SUM_STRING, sum, AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter);
                            }
                        }
                        break;
                    case QUIT_ACTION_LOWERCASE:
                    case QUIT_ACTION_UPPERCASE: // Also allows for "Q" rather than limiting the player
                        isPlaying = false; // Break out of the playing state/loop
                        System.out.printf("%s%d%s%d%n", AMOUNT_WIN_STRING, winCounter, AMOUNT_LOST_STRING, lossCounter); // Print game summary
                        System.out.println(GAME_OVER);
                        break;
                    default:
                        System.out.println(INVALID_ENTRY); // Inform player of invalid entry
                        break;
                }
            }
        }
        userInput.close(); // Close scanner object instance
    }
}