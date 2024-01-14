package service;

import entity.Menu;
import entity.Order;
import repository.MenuRepository;
import repository.OrderRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private MenuRepository menuRepository;

    public OrderServiceImpl(OrderRepository orderRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public void addOrder(int menuNumber, int quantity) {
        Menu menu = this.menuRepository.getDetailMenuByMenuNumber(menuNumber);

        Order order = new Order();
        order.setNumber(menu.getNumber());
        order.setName(menu.getName());
        order.setQuantity(quantity);
        order.setPrice(menu.getPrice());

        this.orderRepository.addOrder(order);
    }

    @Override
    public List<Order> showOrder() {
        return this.orderRepository.getAll();
    }

    @Override
    public void confirmOrder() {
        List<Order> order = this.orderRepository.getAll();
        try {
            long currentTimeMillis = System.currentTimeMillis();

            String invoiceName = "invoice" + currentTimeMillis + ".txt";

            File invoice = new File(invoiceName);

            if(!invoice.exists()) {
                invoice.createNewFile();

                FileWriter writer = new FileWriter(invoiceName);
                writer.write("===============\n");
                writer.write("BinarFud\n");
                writer.write("===============\n");
                writer.write("\n");
                writer.write("Terima kasih sudah memesan di BinarFud\n");
                writer.write("\n");
                writer.write("Dibawah ini adalah pesanan anda\n");

                int totalQty = 0;
                int totalPrice = 0;
                for(Order orderedMenu: order) {
                    totalQty += orderedMenu.getQuantity();
                    totalPrice += orderedMenu.getPrice();
                    writer.write(orderedMenu.getName() + " " + orderedMenu.getQuantity() + " " + orderedMenu.getPrice() + "\n");
                }

                writer.write("----------------------+\n");
                writer.write("Total" + " " + totalQty + " " + totalPrice + "\n");

                writer.write("\n");
                writer.write("Pembayaran : BinarCash\n");
                writer.write("\n");
                writer.write("=========================================\n");
                writer.write("Simpan struk ini sebagai bukti pembayaran\n");
                writer.write("=========================================\n");

                writer.close();
            }

            Scanner reader = new Scanner(invoice);

            while(reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
