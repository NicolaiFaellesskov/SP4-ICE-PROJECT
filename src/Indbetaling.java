import java.util.Scanner;

public class Indbetaling {
    Scanner scanner = new Scanner(System.in);
    private User user;
    private DatabaseIO db;

    public Indbetaling(User user, DatabaseIO db) {
        this.user = user;
        this.db = db;
    }

    public void indbetaling() throws Exception {
        System.out.println("Indbetalings menuen!");
        System.out.println("1) MobilePay");
        System.out.println("2) Bankoverførsel");
        System.out.println("3) PayPal");

        char choice = scanner.next().charAt(0);

        System.out.print("Indtast beløb: ");
        int amount = scanner.nextInt();

        db.addSaldo(user.getId(), amount);
        user.setSaldo(user.getSaldo() + amount);

        System.out.println("Du har indbetalt " + amount + " kr");
        System.out.println("Ny saldo: " + user.getSaldo());
    }
}

