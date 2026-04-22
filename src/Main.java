import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("   Welcome to the Custom PC Builder!    ");
        System.out.println("========================================");

        // --- SHOPPING & BUILDING ---
        PC myPC = PCBuilder.buildPC();

        // --- ORDER CREATION ---
        Order order = new Order();
        order.setPC(myPC);

        System.out.println("\n========================================");
        System.out.println("             ORDER SUMMARY              ");
        System.out.println("========================================");
        System.out.println("Final Specs: " + order.getPCDescription());
        System.out.printf("Amount Due:  %s%.2f%n", Order.CURRENCY, order.getTotalPrice());

        // --- PAYMENT ---
        System.out.println("\n--- Select Payment Method ---");
        System.out.println("1. Cash");
        System.out.println("2. Credit Card");
        System.out.println("3. Installment");
        System.out.println("4. PromptPay");

        int paymentChoice = InputHelper.getIntInput("Choice (1-4): ", 1, 4);
        PaymentStrategy selectedPayment = PaymentFactory.createPaymentMethod(paymentChoice);

        order.setPaymentStrategy(selectedPayment);
        System.out.println();
        order.processPayment();

        // --- ORDER TRACKING ---
        System.out.println("\n========================================");
        System.out.println("            ORDER TRACKING              ");
        System.out.println("========================================");

        order.printStatus();

        System.out.print("Press ENTER to update order status...");
        scanner.nextLine();
        order.nextState(); // Pending -> Building

        System.out.print("Press ENTER to update order status...");
        scanner.nextLine();
        order.nextState(); // Building -> Ready

        System.out.print("Press ENTER to update order status...");
        scanner.nextLine();
        order.nextState(); // Ready -> Delivered

        System.out.println("\nThank you for shopping with us!\uD83D\uDE4F");
        scanner.close();
    }
}