import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BinarFud {
    static boolean isRunning = true;
    static Scanner input = new Scanner(System.in);

    static String[] menuName = {"Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk"};
    static int[] menuPrice = {15000, 13000, 18000, 3000, 5000};

    static ArrayList<Integer> orderedMenu = new ArrayList<>();
    static ArrayList<Integer> qtyOrderedMenu = new ArrayList<>();

    public static void main(String[] args) {
        while(isRunning) {
            displayMenu();
        }
    }

    private static void displayMenu() {
        System.out.println("==========================");
        System.out.println("Selamat datang di BinarFud");
        System.out.println("==========================");
        System.out.println(" ");

        for (int idx = 0; idx < menuName.length; idx++) {
            int number = idx + 1;
            System.out.println(number + ". " + menuName[idx] + " | " + menuPrice[idx]);
        }

        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar aplikasi");

        System.out.print("=> ");
        int selectedMenu = input.nextInt();

        if(selectedMenu == 99) {
            orderConfirmation();
        } else if (selectedMenu == 0) {
            isRunning = false;
        } else {
            orderMenu(selectedMenu);
        }
    }

    private static void orderMenu(int menuNumber) {
        System.out.println("===================");
        System.out.println("Berapa pesanan anda");
        System.out.println("===================");
        System.out.println(" ");

        System.out.println(menuName[menuNumber - 1] + " | " + menuPrice[menuNumber - 1]);
        System.out.println("(input 0 untuk kembali)");

        System.out.print("qty => ");
        int qty = input.nextInt();

        orderedMenu.add(menuNumber - 1);
        qtyOrderedMenu.add(qty);
    }

    private static void orderConfirmation() {
        int totalQty = 0;
        int totalPrice = 0;

        for (int idx = 0; idx < orderedMenu.toArray().length; idx++) {
            totalQty += qtyOrderedMenu.get(idx);
            totalPrice += (qtyOrderedMenu.get(idx) * menuPrice[orderedMenu.get(idx)]);
        }

        System.out.println("=======================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("=======================");
        System.out.println(" ");

        int idx = 0;
        for(int menuNumber: orderedMenu) {
            System.out.println(menuName[menuNumber] + " " + qtyOrderedMenu.get(idx) + " " + menuPrice[menuNumber]);
            idx += 1;
        }

        System.out.println("----------------------+");
        System.out.println("Total" + " " + totalQty + " " + totalPrice);

        System.out.println(" ");
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");

        System.out.print("=> ");
        int selectedMenu = input.nextInt();

        switch(selectedMenu) {
            case 1:
                generateInvoice(totalQty, totalPrice);
                readInvoice();
                break;
            case 0:
                System.exit(1);
                break;
            default:
                break;
        }
    }

    private static void generateInvoice(int totalQty, int totalPrice) {
        try {
            createFile();

            FileWriter writer = new FileWriter("invoice.txt");
            writer.write("===============\n");
            writer.write("BinarFud\n");
            writer.write("===============\n");
            writer.write("\n");
            writer.write("Terima kasih sudah memesan di BinarFud\n");
            writer.write("\n");
            writer.write("Dibawah ini adalah pesanan anda\n");

            int idx = 0;
            for(int menuNumber: orderedMenu) {
                writer.write(menuName[menuNumber] + " " + qtyOrderedMenu.get(idx) + " " + menuPrice[menuNumber] + "\n");
                idx += 1;
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
            isRunning = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readInvoice() {
        try {
            File invoice = new File("invoice.txt");
            Scanner reader = new Scanner(invoice);

            while(reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void createFile() {
        try {
            File invoice = new File("invoice.txt");

            if(!invoice.exists()) {
                invoice.createNewFile();
            } else {
                invoice.delete();
                invoice.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
