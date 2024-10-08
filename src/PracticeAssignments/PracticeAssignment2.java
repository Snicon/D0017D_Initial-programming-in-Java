package PracticeAssignments;

import java.util.Scanner;

/**
 * Describe briefly your program in steps.
 *
 @author Sixten Peterson (sixpet-4)
 */
class PracticeAssignment2 {

    // Number constants
    static final int NUM_OF_PANELS = 26;
    static final int PANEL_HEIGHT = 1;
    static final int SOLAR_RADIATION = 166;
    static final double PANEL_WIDTH = 1.7;
    static final double PANEL_AREA = PANEL_HEIGHT * PANEL_WIDTH;
    static final double EFFICIENCY = 0.2;
    static final double ELECTRIC_PRICE = 0.9;
    static final int DAYS_IN_JUNE_MAX = 30;
    static final int DAYS_IN_JULY_MAX = 31;

    // Error messages constants
    static final String ERROR = "Error! ";
    static final String INVALID_MONTH = ERROR + "Invalid month.";
    static final String INVALID_DAY = ERROR + "Invalid day.";
    static final String INVALID_TIME_HOURS = ERROR + "Invalid time. Hours must be between 0 and 23.";
    static final String INVALID_TIME_MINUTES = ERROR + "Invalid time. Minutes must be between 0 and 59.";
    static final String INVALID_DATE_FORMAT = ERROR + "Invalid date format.";
    static final String INVALID_TIME_FORMAT = ERROR + "Invalid time format.";
    static final String INVALID_SUNRISE = ERROR + "Sunrise must be before sunset.";

    public static void main(final String[] args) {

        // Declare variables
        // Declare date variables
        int month = 0;
        int day = 0;

        // Initiate new scanner instance
        Scanner userInput = new Scanner(System.in);
        // Apply delimiter
        userInput.useDelimiter("[-|:|\\s]");

        // Print prompt
        System.out.print("Enter today's date [mm-dd]> ");

        // Get the month
        if (userInput.hasNextInt()) {
            month = userInput.nextInt();
        } else {
            System.out.println(INVALID_DATE_FORMAT);
            System.exit(0);
        }

        // Validate the month
        if(month > 7 || month < 6) {
            System.out.println(INVALID_MONTH);
            System.exit(0);
        }

        // Get the day
        if (userInput.hasNextInt()) {
            day = userInput.nextInt();
        } else {
            System.out.println(INVALID_DATE_FORMAT);
            System.exit(0);
        }

        // Validate the day
        if(month == 6) {
            if (day > DAYS_IN_JULY_MAX || day < 1) {
                System.out.println(INVALID_DAY);
                System.exit(0);
            }
        } else {
            if (day > DAYS_IN_JUNE_MAX || day < 1) {
                System.out.println(INVALID_DAY);
                System.exit(0);
            }
        }

        System.out.print("Enter today's sunrise [hh:mm]> ");

        int sunriseHours = 0;
        int sunriseMinutes = 0;

        // Globox validate as ints
        if (userInput.hasNextInt()) {
            sunriseHours = userInput.nextInt();
        } else {
            System.out.println(INVALID_TIME_FORMAT);
        }

        if (userInput.hasNextInt()) {
            sunriseMinutes = userInput.nextInt();
        } else {
            System.out.println(INVALID_TIME_FORMAT);
        }

        int sunsetHours = 0;
        int sunsetMinutes = 0;

        System.out.print("Enter today's sunset [hh:mm]> ");
        if (userInput.hasNextInt()) {
            sunsetHours = userInput.nextInt();
        } else {
            System.out.println(INVALID_TIME_FORMAT);
        }

        if (userInput.hasNextInt()) {
            sunsetMinutes = userInput.nextInt();
        } else {
            System.out.println(INVALID_TIME_FORMAT);
        }

        if (sunsetHours > 24 || sunsetHours < 0) {
            System.out.println(INVALID_TIME_HOURS);
        }
        if (sunsetMinutes > 60 || sunsetMinutes < 0) {
            System.out.println(INVALID_TIME_MINUTES);
        }

        userInput.close();

        int sunriseInMinutes = (sunriseHours * 60) + sunriseMinutes;
        int sunsetInMinutes = (sunsetHours * 60) + sunsetMinutes;

        if(sunriseInMinutes > sunsetInMinutes) {
            System.out.print(INVALID_SUNRISE);
            System.exit(0);
        }

        // Calculate sun hours: from xx:xx to yy:yy
        double hoursOfSun = (sunsetInMinutes - sunriseInMinutes) / 60.0;

        // Print the visual divider
        System.out.println("==========================================");
        System.out.printf("Sun hours: %.2f hours", hoursOfSun);

        //calculate production in kwh

        double production = SOLAR_RADIATION * EFFICIENCY * PANEL_AREA * hoursOfSun* NUM_OF_PANELS / 1000;

        // Calculate value
        double profit = production * ELECTRIC_PRICE;

        System.out.printf("%nThe production on %d/%d is: %.2f kWh to a value of: SEK %.2f", day, month, production , profit);

    /* The two main math equations are
      * 1) production (in kWh) = SOLAR_RADIATION * EFFICIENCY * PANEL_AREA * hours * NUM_OF_PANELS / 1000; //Note the equation in instruction is in Wh, so we divide by 1000 to get kWh as price is per kWh
        2) value = production * ELECTRIC_PRICE
        where,
        NUM_OF_PANELS = 26;
        PANEL_HEIGHT = 1; //Units is meters, so no conversion is needed as SOLAR_RADIATION is per m^2
        SOLAR_RADIATION = 166; //this is in Wh units, hence divide by 1000 in eq 1 above to convert to kWh
        PANEL_WIDTH = 1.7; //Units is meters, so no conversion is needed as SOLAR_RADIATION is per m^2
        PANEL_AREA = PANEL_WIDTH * PANEL_HEIGHT;
        EFFICIENCY = 0.2; //(instruction says 20 percent efficiency, so, 20/100 = 0.2)
        ELECTRIC_PRICE = 0.9; //this is per kWh, so we calculate production in kWh in eq 1
        DAYS_IN_JUNE_MAX = 30;

      */
    }
}
