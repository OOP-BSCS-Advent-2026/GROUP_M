# GROUP_13
// total for one item
    public static double calculateItemTotal(int qty, double price, int itemIndex) {
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
