package repository;

import entity.Order;

import java.util.List;

public interface OrderRepository {
    void addOrder(Order order);
    List<Order> getAll();
}
