package util;

import java.util.Scanner;

public class InputUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static int input(String info) {
        System.out.print(info + " => ");
        int input = scanner.nextInt();
        return input;
    }

    public static String inputString(String info) {
        System.out.print(info + " => ");
        String input = scanner.nextLine();
        return input;
    }
}
