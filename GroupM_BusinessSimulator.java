import java.util.Scanner;

public class GroupM_BusinessSimulator {

    // total for one item
    public static double calculateItemTotal(int qty, double price, int itemIndex) {
        //Calculate the total price 
        double total = qty * price;

        if (itemIndex == 0) { // Mango - 10+ gets 5% off
            if (qty >= 10) {
                total = total * 0.95;
            }
        } else if (itemIndex == 1) { // Banana - never discounted
            // no discount
        } else if (itemIndex == 2) { // Orange - 12+ gets 1000 off
            if (qty >= 12) {
                total = total - 1000;
            }
        } else if (itemIndex == 3) { // Pineapple - 4+ gets 10% off
            if (qty >= 4) {
                total = total * 0.90;
            }
        }
        return total;
    }

    // reason for discount
    public static String getDiscountNote(int qty, int itemIndex) {
        if (itemIndex == 0) {
            return (qty >= 10)? "(5% discount applied)" : "(no discount - fewer than 10)";
        } else if (itemIndex == 1) {
            return "(no discount - never discounted)";
        } else if (itemIndex == 2) {
            return (qty >= 12)? "(UGX 1000 off applied)" : "(no discount - fewer than 12)";
        } else {
            return (qty >= 4)? "(10% discount applied)" : "(no discount - fewer than 4)";
        }
    }

    // loops and receipts
    public static void printReceipt(String[] names, double[] prices, int[] qtys) {
        System.out.println("\n----- RECEIPT -----");
        double grandTotal = 0;

        for (int i = 0; i < names.length; i++) {
            double itemTotal = calculateItemTotal(qtys[i], prices[i], i);
            grandTotal += itemTotal;
            System.out.printf("%s x%d = UGX %.2f %s\n", names[i], qtys[i], itemTotal, getDiscountNote(qtys[i], i));
        }

        System.out.println("--------------------------------");
        System.out.printf("TOTAL = UGX %.2f\n", grandTotal);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ARRAYS 
        // Store the names and prices of all fruits
        String[] itemNames = {"Mango", "Banana", "Orange", "Pineapple"};
        // Store the price of each fruit
        double[] itemPrices = {1000, 500, 800, 3000};
        // Store the quantity entered by the customer
        int[] quantities = new int[4];

        // fruits
        System.out.println("==== FRESH FRUIT MARKET (Group M) ====");
        for (int i = 0; i < itemNames.length; i++) {
            System.out.printf("%d. %s UGX %.2f\n", i+1, itemNames[i], itemPrices[i]);
        }

        // for users
        System.out.println("\nEnter quantities:");
        for (int i = 0; i < itemNames.length; i++) {
            System.out.print(itemNames[i] + " qty: ");
            quantities[i] = sc.nextInt();
        }

        printReceipt(itemNames, itemPrices, quantities);

 
    }
}
