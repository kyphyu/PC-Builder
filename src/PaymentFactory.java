public class PaymentFactory {

    public static PaymentStrategy createPaymentMethod(int choice) {
        switch (choice) {
            case 1:
                return new Cash();
            case 2:
                return new CreditCard();
            case 3:
                return new Installment();
            case 4:
                return new PromptPay();
            default:
                System.out.println("Invalid choice. Defaulting to Cash.");
                return new Cash();
        }
    }
}