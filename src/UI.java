import java.util.Scanner;

public class UI {
    private static Scanner scanner = new Scanner(System.in);

    public static void msg(String msg){
        System.out.println(msg);
    }

    public static int askInt(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }
}

