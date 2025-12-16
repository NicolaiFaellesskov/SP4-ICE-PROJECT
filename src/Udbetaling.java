import java.util.Scanner;

public class Udbetaling {
    Scanner scanner = new Scanner(System.in);
    private User user;
    private DatabaseIO db;

    public Udbetaling(User user, DatabaseIO db) {
        this.user = user;
        this.db = db;
    }

    public void udbetaling() throws Exception {
        System.out.println("Udbetalings menuen!");
        System.out.println("1) MobilePay");
        System.out.println("2) Bankoverførsel");
        System.out.println("3) PayPal");

        char choice = scanner.next().charAt(0);

        System.out.print("Indtast beløb: ");
        int amount = scanner.nextInt();

        if (user.getSaldo() < amount) {
            System.out.println("Ugyldigt beløb");
        } else {
            db.removeSaldo(user.getId(), amount);
            user.setSaldo(user.getSaldo() - amount);
            System.out.println("Du har udbetalt " + amount + " kr");
            System.out.println("Ny saldo: " + user.getSaldo());
        }
    }
}