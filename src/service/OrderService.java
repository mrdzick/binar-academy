package service;

import entity.Order;

import java.util.List;

public interface OrderService {
    void addOrder(int menuNumber, int quantity);
    List<Order> showOrder();
    void confirmOrder();
}
