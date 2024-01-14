package view;

import entity.Menu;

import entity.Order;
import service.MenuService;
import service.OrderService;
import util.InputUtil;

import java.util.List;

public class MenuView {
    private MenuService menuService;
    private OrderService orderService;

    public MenuView(MenuService menuService, OrderService orderService) {
        this.menuService = menuService;
        this.orderService = orderService;
    }

    public void showMenu() {
        try {
            List<Menu> menu = menuService.getAllMenu();

            System.out.println("==========================");
            System.out.println("Selamat datang di BinarFud");
            System.out.println("==========================");
            System.out.println(" ");

            for (int idx = 0; idx < menu.size(); idx++) {
                int number = idx + 1;
                System.out.println(number + ". " + menu.get(idx).getName() + " | " + menu.get(idx).getPrice());
            }

            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar aplikasi");

            int input = InputUtil.input("");

            switch (input) {
                case 99:
                    selectedMenu();
                    break;
                case 0:
                    System.exit(1);
                    break;
                default:
                    selectMenu(input);
                    break;
            }
        } catch (Error err) {
            System.out.println("==========================");
            System.out.println(err.getMessage());
            System.out.println("==========================");

            System.out.println("=================================");
            System.out.println("Mohon masukkan input pilihan anda");
            System.out.println("=================================");
            System.out.println("(Y) untuk lanjut");
            System.out.println("(n) untuk keluar");

            String input = InputUtil.inputString("");

            if (input == "y" || input == "Y") {
                showMenu();
            } else {
                System.exit(1);
            }
        }
    }
    public void selectMenu(int menuNumber) {
        Menu selectedMenu = this.menuService.getDetailMenu(menuNumber);
        System.out.println("===================");
        System.out.println("Berapa pesanan anda");
        System.out.println("===================");
        System.out.println(" ");

        System.out.println(selectedMenu.getName() + " | " + selectedMenu.getPrice());
        System.out.println("(input 0 untuk kembali)");

        int qty = InputUtil.input("qty");

        if (qty != 0) {
            orderService.addOrder(menuNumber, qty);
        }

        showMenu();
    }

    public void selectedMenu() {
        List<Order> order = this.orderService.showOrder();
        System.out.println("=======================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("=======================");
        System.out.println(" ");

        for(int idx = 0; idx < order.size(); idx++) {
            System.out.println(order.get(idx).getName() + " " + order.get(idx).getQuantity() + " " + order.get(idx).getPrice());
        }

        int totalQty = 0;
        int totalPrice = 0;
        for (int idx = 0; idx < order.size(); idx++) {
            totalQty += order.get(idx).getQuantity();
            totalPrice += order.get(idx).getQuantity() * order.get(idx).getPrice();
        }

        System.out.println("----------------------+");
        System.out.println("Total" + " " + totalQty + " " + totalPrice);

        System.out.println(" ");
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");

        int input = InputUtil.input("");

        if (input == 1) {
            this.orderService.confirmOrder();
        } else if (input == 2) {
            showMenu();
        } else if (input == 0) {
            System.exit(1);
        } else {
            throw new Error("Pilihan tidak valid!");
        }
    }
}
