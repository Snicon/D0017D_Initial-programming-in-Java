package PracticeAssignments;

/**
 * The program prints a table with calculated values for how long it takes to charge an electrical car according to the
 * requirements of the Practice Assignment 1. For details on the process of the program read the ordered list below.
 *
 * <ol>
 *     <li>Define constants (Battery capacity, KW in W, Currents, Voltage, etc).</li>
 *     <li>Initiate variables for chargingPower1 through 5 and chargingTime1 through 5.</li>
 *     <li>Calculate power (230V and 400V).</li>
 *     <li>Calculate time based on the calculated power and battery capacity.</li>
 *     <li>Round power and time values to two decimals.</li>
 *     <li>Print the table one line at a time utilizing the variables and constants.</li>
 * </ol>
 *
 * @author Sixten Peterson (sixpet-4)
 * @version 1.0
 */
public class PracticeAssignment1 {

    // Battery capacity and watt conversion
    public static final double BATTERY_CAPACITY = 35.8;
    public static final double KW_IN_W = 1000;

    // Currents
    public static final double CURRENT_10 = 10.0;
    public static final double CURRENT_16 = 16.0;
    public static final double CURRENT_32 = 32.0;

    // Voltages
    public static final double VOLTAGE_230 = 230.0;
    public static final double VOLTAGE_400 = 400.0;

    // Rounding related constants
    public static final int DECIMALS = 2;
    public static final int SQUARE_ROOT_MULTIPLIER = 3;
    public static final double scale = Math.pow(10, DECIMALS);

    public static void main(final String[] args) {

        // Charging power vars
        double chargingPower1 = 0.0;
        double chargingPower2 = 0.0;
        double chargingPower3 = 0.0;
        double chargingPower4 = 0.0;
        double chargingPower5 = 0.0;

        // Charging time vars
        double chargingTime1 = 0.0;
        double chargingTime2 = 0.0;
        double chargingTime3 = 0.0;
        double chargingTime4 = 0.0;
        double chargingTime5 = 0.0;

        // CALCULATE POWER
        // Single-phase (230V)
        chargingPower1 = CURRENT_10 * VOLTAGE_230 / KW_IN_W;
        chargingPower2 = CURRENT_16 * VOLTAGE_230 / KW_IN_W;
        // Three-phase (400V)
        chargingPower3 = CURRENT_10 * VOLTAGE_400 * Math.sqrt(SQUARE_ROOT_MULTIPLIER) / KW_IN_W;
        chargingPower4 = CURRENT_16 * VOLTAGE_400 * Math.sqrt(SQUARE_ROOT_MULTIPLIER) / KW_IN_W;
        chargingPower5 = CURRENT_32 * VOLTAGE_400 * Math.sqrt(SQUARE_ROOT_MULTIPLIER) / KW_IN_W;

        // Calculate charging time
        chargingTime1 = BATTERY_CAPACITY / chargingPower1;
        chargingTime2 = BATTERY_CAPACITY / chargingPower2;
        chargingTime3 = BATTERY_CAPACITY / chargingPower3;
        chargingTime4 = BATTERY_CAPACITY / chargingPower4;
        chargingTime5 = BATTERY_CAPACITY / chargingPower5;

        // ROUND DOUBLE VARIABLES TO 2 DECIMALS
        // Charging powers
        chargingPower1 = Math.round(chargingPower1 * scale) / scale;
        chargingPower2 = Math.round(chargingPower2 * scale) / scale;
        chargingPower3 = Math.round(chargingPower3 * scale) / scale;
        chargingPower4 = Math.round(chargingPower4 * scale) / scale;
        chargingPower5 = Math.round(chargingPower5 * scale) / scale;
        // Charging times
        chargingTime1 = Math.round(chargingTime1 * scale) / scale;
        chargingTime2 = Math.round(chargingTime2 * scale) / scale;
        chargingTime3 = Math.round(chargingTime3 * scale) / scale;
        chargingTime4 = Math.round(chargingTime4 * scale) / scale;
        chargingTime5 = Math.round(chargingTime5 * scale) / scale;

        // Print the table with the rounded data.
        System.out.printf("Battery: %.1f (kwh)%n", BATTERY_CAPACITY);
        System.out.printf("%-15s %-15s %-19s %-15s %n", "Current(A)", "Voltage(V)", "Charging Power(kW)", "Charging Time(h)");
        System.out.printf("%-15.1f %-15.1f %-19.2f %-15.2f %n", CURRENT_10, VOLTAGE_230, chargingPower1, chargingTime1);
        System.out.printf("%-15.1f %-15.1f %-19.2f %-15.2f %n", CURRENT_16, VOLTAGE_230, chargingPower2, chargingTime2);
        System.out.printf("%-15.1f %-15.1f %-19.2f %-15.2f %n", CURRENT_10, VOLTAGE_400, chargingPower3, chargingTime3);
        System.out.printf("%-15.1f %-15.1f %-19.2f %-15.2f %n", CURRENT_16, VOLTAGE_400, chargingPower4, chargingTime4);
        System.out.printf("%-15.1f %-15.1f %-19.2f %-15.2f %n", CURRENT_32, VOLTAGE_400, chargingPower5, chargingTime5);
    }
}
