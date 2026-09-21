import java.util.Scanner;
public class GroupM_BusinessSimulator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Mango: 10+ gets 5% off
        // Banana: never discounted
        // Orange: 12+ gets UGX 1000 flat off
        // Pineapple: 4+ gets 10% off
        Item[] items = {
            new PercentDiscountItem("Mango", 1000.00, 10, 5.0),
            new NoDiscountItem("Banana", 500.00),
            new FlatDiscountItem("Orange", 800.00, 12, 1000.00),
            new PercentDiscountItem("Pineapple", 3000.00, 4, 10.0)
        };

        System.out.println("==== FRESH FRUIT MARKET (Group M) ====");
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%d. %s UGX %.2f%n", i + 1, items[i].getName(), items[i].getPrice());
        }

        int[] quantities = new int[items.length];
        System.out.println("\nEnter quantities:");
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i].getName() + " qty: ");
            quantities[i] = sc.nextInt();
        }

        System.out.println("\n----- RECEIPT -----");
        double grandTotal = 0.0;

        for (int i = 0; i < items.length; i++) {
            double lineTotal = items[i].calculateTotal(quantities[i]);
            grandTotal += lineTotal;
            System.out.printf("%s x%d = UGX %.2f%n", items[i].getName(), quantities[i], lineTotal);
        }

        System.out.println("--------------------------------");
        System.out.printf("TOTAL = UGX %.2f%n", grandTotal);

        sc.close();
    }
}