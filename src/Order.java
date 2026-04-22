public class Order {
    private PC pc;
    private PaymentStrategy paymentStrategy;
    private OrderState state;
    public static final String CURRENCY = "฿";

    public Order() {
        this.state = new PendingState();
    }

    // PC (Decorator)
    public void setPC(PC pc) {
        this.pc = pc;
    }

    public double getTotalPrice() {
        return pc.getPrice();
    }

    public String getPCDescription() {
        return pc.getDescription();
    }

    // Payment (Strategy)
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment() {
        if (paymentStrategy == null) {
            System.out.println("No payment method selected.");
            return;
        }
        paymentStrategy.pay(getTotalPrice());
    }

    // State Pattern
    public void setState(OrderState state) {
        this.state = state;
        System.out.println("Order is now: " + state.getStatus());
    }

    public void nextState() {
        state.next(this);
    }

    public void printStatus() {
        System.out.println("Order status: " + state.getStatus());
    }
}
