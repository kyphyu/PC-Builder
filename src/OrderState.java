interface OrderState {
    void next(Order order);
    String getStatus();
}

class PendingState implements OrderState {

    @Override
    public void next(Order order) {
        order.setState(new BuildingState());
    }

    @Override
    public String getStatus() {
        return "Pending";
    }
}

class BuildingState implements OrderState {

    @Override
    public void next(Order order) {
        order.setState(new ReadyState());
    }

    @Override
    public String getStatus() {
        return "Building";
    }
}

class ReadyState implements OrderState {

    @Override
    public void next(Order order) {
        order.setState(new DeliveredState());
    }

    @Override
    public String getStatus() {
        return "Ready";
    }
}

class DeliveredState implements OrderState {

    @Override
    public void next(Order order) {
        System.out.println("Order is delivered.");
    }

    @Override
    public String getStatus() {
        return "Delivered";
    }
}
