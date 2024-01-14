package repository;

import entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private List<Order> order = new ArrayList<>();
    @Override
    public void addOrder(Order order) {
        int index = order.getNumber() - 1;

        // If the index is below that order size, it means the data is already exist
        if (index < this.order.size()) {
            this.order.set(index, order);
        } else {
            this.order.add(order);
        }
    }

    @Override
    public List<Order> getAll() {
        return this.order;
    }
}
