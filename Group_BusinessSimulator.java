import java.util.Scanner;

public class Group1_BusinessSimulator {

    // Total cost calculation for a single item with discount logic
    public static double calculateItemTotal(int qty, double price, int itemIndex) {
        double total = qty * price;

        if (total == 0) return 0; // Return early if quantity is zero

        if (itemIndex == 0) { // Mango: 10+ gets 5% off
            if (qty >= 10) {
                total *= 0.95;
            }
        } else if (itemIndex == 1) { // Banana: Never discounted
            // No discount applied
        } else if (itemIndex == 2) { // Orange: 12+ gets UGX 1000 off
            if (qty >= 12) {
                total = Math.max(0, total - 1000); // Prevents negative total
            }
        } else if (itemIndex == 3) { // Pineapple: 4+ gets 10% off
            if (qty >= 4) {
                total *= 0.90;
            }
        }
        return total;
    }

    // Reason for discount note
    public static String getDiscountNote(int qty, int itemIndex) {
        if (qty == 0) return "(none purchased)";
        
        switch (itemIndex) {
            case 0:
                return (qty >= 10) ? "(5% discount applied)" : "(no discount - fewer than 10)";
            case 1:
                return "(no discount - item ineligible)";
            case 2:
                return (qty >= 12) ? "(UGX 1,000 off applied)" : "(no discount - fewer than 12)";
            case 3:
                return (qty >= 4) ? "(10% discount applied)" : "(no discount - fewer than 4)";
            default:
                return "";
        }
    }

    // Prints itemized receipt and grand total
    public static void printReceipt(String[] names, double[] prices, int[] qtys) {
        System.out.println("\n================= RECEIPT =================");
        double grandTotal = 0;

        for (int i = 0; i < names.length; i++) {
            if (qtys[i] > 0) { // Only print items that were bought
                double itemTotal = calculateItemTotal(qtys[i], prices[i], i);
                grandTotal += itemTotal;
                System.out.printf("%-10s x%-3d = UGX %-10.2f %s\n", 
                                  names[i], qtys[i], itemTotal, getDiscountNote(qtys[i], i));
            }
        }

        System.out.println("-------------------------------------------");
        System.out.printf("GRAND TOTAL = UGX %.2f\n", grandTotal);
        System.out.println("===========================================\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inventory setup
        String[] itemNames = {"Mango", "Banana", "Orange", "Pineapple"};
        double[] itemPrices = {1000, 500, 800, 3000};
        int[] quantities = new int[itemNames.length];

        // Menu Display
        System.out.println("==== FRESH FRUIT MARKET (Group 1) ====");
        for (int i = 0; i < itemNames.length; i++) {
            System.out.printf("%d. %-10s - UGX %.2f\n", i + 1, itemNames[i], itemPrices[i]);
        }

        // Prompting quantities with basic input validation
        System.out.println("\nEnter quantities to purchase:");
        for (int i = 0; i < itemNames.length; i++) {
            int qty = -1;
            while (qty < 0) {
                System.out.print(itemNames[i] + " qty: ");
                if (sc.hasNextInt()) {
                    qty = sc.nextInt();
                    if (qty < 0) System.out.println("Quantity cannot be negative. Try again.");
                } else {
                    System.out.println("Invalid input. Please enter a whole number.");
                    sc.next(); // Clear invalid input
                }
            }
            quantities[i] = qty;
        }

        printReceipt(itemNames, itemPrices, quantities);
        
        sc.close(); // Clean up scanner resource
    }
}
