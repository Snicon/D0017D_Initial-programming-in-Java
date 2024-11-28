package MarkedAssignments;

import java.util.Random;
import java.util.Scanner;
import java.util.Date;

/**
 * The program acts as a very basic cash register that temporarily stores data on sales and items(stock).
 * This data can be sorted and outputted or added to by selecting an action. All data in handled in memory.
 * For more detailed information of the program I encourage you to read the great many comments.
 *
 * @author Sixten Peterson (sixpet-4)
 * @version 1.0
 */
public class MarkedAssignment4 {

    // Constants for the item array
    public static final int ITEM_ID = 0;
    public static final int ITEM_COUNT = 1;
    public static final int ITEM_PRICE = 2;
    public static final int ITEM_COLUMN_SIZE = 3;
    public static final int INITIAL_ITEM_SIZE = 10;

    // Constants for the sales array
    public static final int SALE_ITEM_ID = 0;
    public static final int SALE_ITEM_COUNT = 1;
    public static final int SALE_ITEM_PRICE = 2;
    public static final int SALE_COLUMN_SIZE = 3;
    public static final int MAX_SALES = 1000;

    // Menu selection constants
    public static final int MENU_ITEM_1 = 1;
    public static final int MENU_ITEM_2 = 2;
    public static final int MENU_ITEM_3 = 3;
    public static final int MENU_ITEM_4 = 4;
    public static final int MENU_ITEM_5 = 5;
    public static final int MENU_ITEM_6 = 6;
    public static final int MENU_ITEM_Q = -1;

    public static final int INITIAL_ITEM_NUMBER = 999;

    // String constants
    public static final String INSERT_ITEMS_OPTION = "1. Insert items";
    public static final String REMOVE_AN_ITEM_OPTION = "2. Remove an item";
    public static final String DISPLAY_LIST_OF_ITEMS_OPTION = "3. Display a list of items";
    public static final String REGISTER_SALE_OPTION = "4. Register a sale";
    public static final String DISPLAY_SALES_HISTORY_OPTION = "5. Display sales history";
    public static final String SORT_AND_DISPLAY_SALES_HISTORY_OPTION = "6. Sort and display sales history table";
    public static final String QUIT_OPTION = "q. Quit";
    public static final String SELECTION_PROMPT = "Your Selection: ";

    public static final String AMOUNT_TO_ADD_PROMPT = "How many items do you want to add?";
    public static final String ITEMS_ADDED_INFO = " items added!";
    public static final String ITEM_NUMBER = "Item number";
    public static final String COUNT = "Count";
    public static final String PRICE = "Price";
    public static final String ITEM_TO_REMOVE_PROMPT = "Specify an item id to remove:";
    public static final String ITEM_NOT_FOUND_ERROR = "Could not find item ";
    public static final String SUCCESSFUL_REMOVAL_OF_ITEM_INFO = "Successfully removed item ";
    public static final String SELL_ITEM_AND_AMOUNT_PROMPT = "Enter item ID and number of items to be sold:";
    public static final String FAILED_TO_SELL_ITEM_PART1 = "Failed to sell specified amount, only ";
    public static final String FAILED_TO_SELL_ITEM_PART2 = " units available!";

    public static final String INVALID_MENU_OPTION_ERROR = "Invalid selection. Only numbers 1-6 or \"q\" allowed!";
    public static final String INVALID_INPUT_ERROR = "Invalid input. Only integers or \"q\" allowed!";
    public static final String TERMINATING = "Terminating...";

    // Constants for handling random
    public static final int RANDOM_PRICE_BOUND = 900;
    public static final int MINIMUM_PRICE = 100;

    // Constant for indicating something went wrong, often used for methods or validation.
    public static final int ERROR_INDICATION_INT = -1;

    private static Scanner userInputScanner = new Scanner(System.in);

    /**
     * This method should be used only for unit testing on CodeGrade. Do not change this method!
     * Swaps userInputScanner with a custom scanner object bound to a test input stream
     *
     * @param inputScanner test scanner object
     */
    public static void injectInput(final Scanner inputScanner) {
        userInputScanner = inputScanner;
    }

    public static void main(final String[] args) {
        int[][] items = new int[INITIAL_ITEM_SIZE][ITEM_COLUMN_SIZE]; // Data structure to store items
        int[][] sales = new int[MAX_SALES][SALE_COLUMN_SIZE]; // Data structure to store sales
        Date[] saleDates = new Date[MAX_SALES]; // Data structure to store sale dates
        int lastItemNumber = INITIAL_ITEM_NUMBER; // Keep track of last added ItemNumber
        int selection;

        while (true) {
            selection = menu();

            switch (selection) {
                case MENU_ITEM_1:
                    System.out.println(AMOUNT_TO_ADD_PROMPT);
                    int noOfItems = input();
                    items = insertItems(items, lastItemNumber, noOfItems);
                    lastItemNumber = lastItemNumber + noOfItems;
                    System.out.println(noOfItems + ITEMS_ADDED_INFO);
                    break;
                case MENU_ITEM_2:
                    System.out.println(ITEM_TO_REMOVE_PROMPT);
                    int itemId = input();

                    // Exit if q is pressed.
                    if (itemId == MENU_ITEM_Q) {
                        System.exit(0);
                    }

                    int itemRemovalStatus = removeItem(items, itemId);

                    if (itemRemovalStatus == ERROR_INDICATION_INT) {
                        System.out.printf("%s%d%n", ITEM_NOT_FOUND_ERROR, itemId);
                    } else if (itemRemovalStatus == 0) {
                        System.out.printf("%s%d%n", SUCCESSFUL_REMOVAL_OF_ITEM_INFO, itemId);
                    }
                    break;
                case MENU_ITEM_3:
                    printItems(items);
                    break;
                case MENU_ITEM_4:
                    System.out.println(SELL_ITEM_AND_AMOUNT_PROMPT);
                    int sellItemId = input();
                    int sellAmount = input();

                    // Exit if q is pressed.
                    if (sellItemId == MENU_ITEM_Q || sellAmount == MENU_ITEM_Q) {
                        System.exit(0);
                    }

                    int sellStatus = sellItem(sales, saleDates, items, sellItemId, sellAmount);

                    // Sale went through
                    if (sellStatus == 0) {
                        System.out.printf("Sold %d units of %d%n", sellAmount, sellItemId);

                    // Sale failed due to too low stock
                    } else if (sellStatus > 0) {
                        System.out.printf("%s%d%s%n", FAILED_TO_SELL_ITEM_PART1, sellStatus, FAILED_TO_SELL_ITEM_PART2);

                    // Sale failed due to not finding the specified item
                    } else {
                        System.out.printf("%s%d%n", ITEM_NOT_FOUND_ERROR, sellItemId);
                    }
                    break;
                case MENU_ITEM_5:
                    printSales(sales, saleDates);
                    break;
                case MENU_ITEM_6:
                    sortedTable(sales, saleDates);
                    break;
                case MENU_ITEM_Q:
                    System.out.println(TERMINATING);
                    System.exit(0);
                    break;
                default:
                    // Default to going back to the "menu screen"
                    menu();
            }
        }
    }

    /**
     * Displays the available menu options/alternatives to the user as well as prompts the user to choose an option.
     * It also validates the input according to what is needed for the menu. Upon invalid input the user is prompted
     * to once again select an option.
     * @return The selection of the user as an integer, valid cases: 1-6, q
     */
    public static int menu() {

        String[] menuOptions = {INSERT_ITEMS_OPTION, REMOVE_AN_ITEM_OPTION, DISPLAY_LIST_OF_ITEMS_OPTION,
                                REGISTER_SALE_OPTION, DISPLAY_SALES_HISTORY_OPTION,
                                SORT_AND_DISPLAY_SALES_HISTORY_OPTION, QUIT_OPTION};

        for (String option: menuOptions) {
            System.out.println(option);
        }
        System.out.println(SELECTION_PROMPT);

        int selection = input();

        // Validate input for the menu options specifically
        if ((selection < MENU_ITEM_1 || selection > MENU_ITEM_6) && selection != ERROR_INDICATION_INT) {
            System.out.println(INVALID_MENU_OPTION_ERROR);
            return menu();
        }

        return selection;
    }

    /**
     * This method handles user input by validating it and returning an int for further usage.
     * Logic breakdown:
     *  - Is there an int?
     *      - Return it
     *  - Is there a String?
     *      - Make sure it is "q" or "Q" and then return -1 to initiate quitting the program. Otherwise, continue
     *  - Return menu() in order to allow the user to redo input.
     * @return an int, -1 if the user decided to quit or otherwise the integer specified by the user.
     */
    public static int input() {
        if (userInputScanner.hasNextInt()) {
            return userInputScanner.nextInt();
        } else if (userInputScanner.hasNext()) {
            String uInput = userInputScanner.next();
            if (uInput.equalsIgnoreCase("q")) {
                return MENU_ITEM_Q;
            }

            System.out.println(INVALID_INPUT_ERROR);
        }

        return menu();
    }

    /**
     * This method counts and returns the number of empty rows in the provided items array. A row is considered empty
     * if the item ID in the first column is zero since all items have an ID.
     * @param items The 2d array of items/inventory
     * @return The count of empty rows in the items array.
     */
    public static int calculateEmptyRows(final int[][] items) {
        // Keep track of empty rows
        int emptyRows = 0;

        // Go through each row and check if the ID col is empty or not
        for (int[] item : items) {
            int col = item[0];

            // If the ID col is empty assume the row is empty.
            if (col == 0) {
                emptyRows++;
            }
        }

        return emptyRows;
    }

    /**
     * Checks if the inventory/items array has enough empty rows to accommodate the specified number of new items.
     * If there is enough space it returns false and if there is not enough space it returns true, indicating it is full.
     * @param items The 2d array of items/inventory
     * @param noOfItems The number of new items to add into the items array
     * @return True if there is not enough room, false if there is enough room.
     */
    public static boolean checkFull(final int[][] items, final int noOfItems) {
        int emptyRows = calculateEmptyRows(items);

        // If there are less empty rows than there are items it returns true, otherwise false
        // Meaning: true  -> array is full
        //          false -> enough room in array
        return emptyRows < noOfItems;
    }

    /**
     * Extends the passed in array in order to make room for specified amount of items
     * @param items the array to extend
     * @param noOfItems the amount of new rows to add (items)
     * @return the new array adjusted to the specifications passed in.
     */
    public static int[][] extendArray(final int[][] items, final int noOfItems) {
        int[][] newItemsArr = new int[items.length + noOfItems][ITEM_COLUMN_SIZE];

        // Copy items to new arr, then return the new arr.
        for (int i = 0; i < items.length; i++) {
            newItemsArr[i][ITEM_ID] = items[i][ITEM_ID];
            newItemsArr[i][ITEM_COUNT] = items[i][ITEM_COUNT];
            newItemsArr[i][ITEM_PRICE] = items[i][ITEM_PRICE];
        }

        return newItemsArr;
    }

    /**
     * Inserts new items with stock, price and an id into the imaginary inventory.
     * Price and count/stock is randomly generated and ids are generated based on the last id.
     * If there is a need for more room in the array, the array is extended
     *
     * @param items The array that stores all the inventory/items
     * @param lastItemId The last itemId used in the inventory
     * @param noOfItems the number of items to add.
     * @return The updated item array after insertion of new data/items
     */
    public static int[][] insertItems(final int[][] items, final int lastItemId, final int noOfItems) {
        Random rand = new Random();
        int[][] workingArray = items;
        int nextItemId = lastItemId + 1; // Local variable to track the next item ID

        if (checkFull(items, noOfItems)) {
            int emptyRows = calculateEmptyRows(items);
            int newSlotsRequired = noOfItems - emptyRows;

            workingArray = extendArray(items, newSlotsRequired);
        }

        // Insert new items
        for (int i = 0; i < noOfItems; i++) {
            // Find the first available slot in the array (where ITEM_ID is 0)
            int currentItemIndex = 0;
            while (currentItemIndex < workingArray.length && workingArray[currentItemIndex][ITEM_ID] != 0) {
                currentItemIndex++; // Move to the next row
            }

            // If we found an empty row, set the new item's details
            if (currentItemIndex < workingArray.length) {
                workingArray[currentItemIndex][ITEM_ID] = nextItemId; // Use nextItemId
                workingArray[currentItemIndex][ITEM_COUNT] = rand.nextInt(INITIAL_ITEM_SIZE) + 1; // Random count (1-10)
                workingArray[currentItemIndex][ITEM_PRICE] = rand.nextInt(RANDOM_PRICE_BOUND) + MINIMUM_PRICE; // Random price (100-1000)
                nextItemId++; // Increment for the next insertion
            } else {
                // If no empty slots are available, break the loop
                break;
            }
        }

        // Return the updated array
        return workingArray;
    }

    /**
     * This method handles removing items from the inventory by setting the ID, count and price to 0.
     * THe method searches for the item based on the provided item ID and returns a status indicating
     * whether the removal was successful or not.
     * @param items The 2D array of inventory/items.
     * @param itemId The ID of the item that is to be removed from the inventory.
     * @return 0 if the item was removed successfully, -1 if the item with the specified ID was not found.
     */
    public static int removeItem(final int[][] items, final int itemId) {
        // Find the item to remove
        for (int[] item: items) {
            if (item[ITEM_ID] == itemId) {
                item[ITEM_ID] = 0;
                item[ITEM_COUNT] = 0;
                item[ITEM_PRICE] = 0;
                return 0;
            }
        }

        return ERROR_INDICATION_INT;
    }

    /**
     * This method handles printing of items to the console as follows:
     * 1. Sorts the array by the itemId's in ascending order.
     * 2. Prints the table header (item id/number, count, price).
     * 3. Loops through the array with an enhanced for loop. For each item in the array it checks
     *    if the item is empty/removed, if not it prints the item.
     * @param items the array to sort and print
     */
    public static void printItems(final int[][] items) {
        int[][] sortedArr = bubbleSortArr(items);

        System.out.printf("%-15s %-10s %-10s%n", ITEM_NUMBER, COUNT, PRICE);
        for (int[] item: sortedArr) {
            if (item[ITEM_ID] != 0 && item[ITEM_COUNT] != 0 && item[ITEM_PRICE] != 0) {
                System.out.printf("%-15s %-10s %-10s%n", item[0], item[1], item[2]);
            }
        }
    }

    /**
     * Sorts the specified array by the item ID in a new working array rather than directly in the original array.
     * @param items the items array which wil be sorted
     * @return the sorted array
     */
    public static int[][] bubbleSortArr(final int[][] items) {
        int[][] workingArr = items.clone();

        for (int i = workingArr.length - 1; 0 < i; i--) {
            for (int j = workingArr.length - 1 - i; 0 < j; j--) {
                if (workingArr[j][ITEM_ID] > workingArr[j + 1][ITEM_ID]) {
                    // Reorder by swapping the rows
                    int[] temp = workingArr[j];
                    workingArr[j] = workingArr[j + 1];
                    workingArr[j + 1] = temp;
                }
            }
        }

        return workingArr;
    }

    /**
     * Processes the sale of a specified number of items by updating the inventory and recording the sale in the sales
     * array. The method checks for sufficient stock and captures the sale date. Returns a status indicating the result
     * of the sale attempt.
     *
     * @param sales The 2D array representing sales records, where each entry includes item ID, quantity sold, and total sale price.
     * @param salesDate The array of dates corresponding to each sale transaction.
     * @param items The 2D array representing current items in the inventory.
     * @param itemIdToSell The ID of the item being sold.
     * @param amountToSell The quantity of the item to sell.
     * @return 0 if the sale was successful, -1 if the item was not found, or a positive number indicating insufficient stock for the requested sale amount.
     */
    public static int sellItem(final int[][] sales, final Date[] salesDate, final int[][] items, final int itemIdToSell, final int amountToSell) {
        // Get the current stock levels (or learn that the item is not found)
        int stock = getStock(items, itemIdToSell);
        // (Try to) reduce the stock levels
        int stockReduction = reduceStock(items, itemIdToSell, amountToSell);

        // VALIDATE SALE
        // Item not found
        if (stock == ERROR_INDICATION_INT) {
            // stock not found, indicating the item was not found
            return -1;
        // The stock is too low to sell
        } else if (stockReduction == ERROR_INDICATION_INT) {
            return stock;
        }

        // Since no other return statements have been run we can assume the sale went through.
        // Insert sale record into the sales array
        int saleIndex = getFirstEmptySaleSlot(sales);  // Find the first available slot in sales array
        if (saleIndex != ERROR_INDICATION_INT) {
            sales[saleIndex][SALE_ITEM_ID] = itemIdToSell;
            sales[saleIndex][SALE_ITEM_COUNT] = amountToSell;
            sales[saleIndex][SALE_ITEM_PRICE] = items[getItemArrPlacementByItemId(items, itemIdToSell)][ITEM_PRICE] * amountToSell;
            salesDate[saleIndex] = new Date();  // Record the current date and time
        }


        return 0;
    }

    /**
     * The method finds the first available (empty) slot in the sales array. Though it would work for any array with the same structure as the sales array.
     * @param sales the sales array to search in
     * @return Either -1 if there is no empty slot or the index of the empty slot in the array
     */
    public static int getFirstEmptySaleSlot(final int[][] sales) {
        for (int i = 0; i < sales.length; i++) {
            if (sales[i][SALE_ITEM_ID] == 0) {  // Assuming 0 means the slot is empty
                return i;  // Return the index of the first empty slot
            }
        }
        return ERROR_INDICATION_INT;  // Return -1 if no empty slot is found
    }

    /**
     * Prints all recoded sale transactions, displaying the ID, quantity of sold goods for said item and the total
     * price for all the goods. The method also ensures that only valid sales are displayed (empty sale slots are ignored)
     *
     * @param sales The 2D array representing sales/transactions, where each entry contains the item ID, quantity sold and the total price.
     * @param salesDate The array of dates corresponding to when each sale occurred.
     */
    public static void printSales(final int[][] sales, final Date[] salesDate) {
        // Print header for the sales table
        System.out.printf("%-15s %-15s %-15s %-25s%n", "Item Number", "Items Sold", "Total Price", "Sale Date");

        // Loop through each sale in the sales array
        for (int i = 0; i < sales.length; i++) {
            if (sales[i][SALE_ITEM_ID] != 0) {  // Only print non-empty sales
                int itemId = sales[i][SALE_ITEM_ID];
                int itemsSold = sales[i][SALE_ITEM_COUNT];
                int totalPrice = sales[i][SALE_ITEM_PRICE];
                Date saleDate = salesDate[i];  // Get the corresponding sale date

                // Print sale information with item ID first, followed by other details
                System.out.printf("%-15d %-15d %-15d %-25s%n",
                        itemId, itemsSold, totalPrice, saleDate.toString());
            }
        }
    }

    /**
     * Sorts the sales based on the item ID in ascending order and prints the sorted sales table. Each entry displays
     * the item ID, quantity sold, total sale amount and the date of when the sales took place. To make sure the sorting
     * does not interfere with the normal printSales method, the table is sorted within a cloned/copied array of sales
     * and array of dates. Maintaining the integrity of the original arrays.
     * @param sales the sales array containing the sales data to sort
     * @param salesDate the array of dates associated with the sales
     */
    public static void sortedTable(final int[][] sales, final Date[] salesDate) {
        // Create a deep copy of the sales array and a copy of the salesDate array
        int[][] salesCopy;
        Date[] salesDateCopy;

        // Clone the original arrays
        salesCopy = sales.clone();
        salesDateCopy = salesDate.clone();

        // Bubble sort implementation to sort sales by item number (SALE_ITEM_ID) on the copied arrays
        for (int i = 0; i < salesCopy.length - 1; i++) {
            for (int j = 0; j < salesCopy.length - 1 - i; j++) {
                // Compare the current item ID with the next one
                if (salesCopy[j][SALE_ITEM_ID] > salesCopy[j + 1][SALE_ITEM_ID]) {
                    // Swap the sales rows in the copied array
                    int[] tempSale = salesCopy[j];
                    salesCopy[j] = salesCopy[j + 1];
                    salesCopy[j + 1] = tempSale;

                    // Swap the corresponding sale dates in the copied array
                    Date tempDate = salesDateCopy[j];
                    salesDateCopy[j] = salesDateCopy[j + 1];
                    salesDateCopy[j + 1] = tempDate;
                }
            }
        }

        // Print the sorted sales table (the copied array)
        printSales(salesCopy, salesDateCopy);
    }


    /**
     * The method retrieves the stock of an item based on its id
     * @param items the array to retrieve the stock from
     * @param itemId the item as id to retrieve the stock from
     * @return -1 if no stock is found, otherwise an int representing the count/stock
     */
    public static int getStock(final int[][] items, final int itemId) {
        int itemPlacementInArr = getItemArrPlacementByItemId(items, itemId);

        if (itemPlacementInArr != ERROR_INDICATION_INT) {
            return items[itemPlacementInArr][ITEM_COUNT];
        }

        return ERROR_INDICATION_INT;
    }

    /**
     * The method reduces the stock of an item by the specified amount.
     * @param items the array to reduce the stock in
     * @param itemId the id of the item to reduce stock for
     * @param amount the amount to reduce the stock by
     * @return an int of -1 if the stock is too low to reduce or an int of the new stock levels if the stock was successfully reduced
     */
    public static int reduceStock(final int[][] items, final int itemId, final int amount) {
        int itemPlacementInArr = getItemArrPlacementByItemId(items, itemId);
        int stock = getStock(items, itemId);

        // We are either trying to sell more than we have in stock or the item was not found
        if (amount > stock || stock == ERROR_INDICATION_INT) {
            return ERROR_INDICATION_INT;
        }

        items[itemPlacementInArr][ITEM_COUNT] -= amount;

        return items[itemPlacementInArr][ITEM_COUNT];
    }

    /**
     * The method finds the placement of an item in the array by its id.
     * @param items the array to loop though for the item
     * @param itemId the id to look for in the array
     * @return Either -1 or the placement (index) depending on the success of the method, for example: 3
     */
    public static int getItemArrPlacementByItemId(final int[][] items, final int itemId) {
        for (int placement = 0; placement < items.length; placement++) {
            if (items[placement][ITEM_ID] == itemId) {
                return placement;
            }
        }

        return ERROR_INDICATION_INT;
    }


}