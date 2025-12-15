import java.util.Scanner;

public class Udbetaling {
    Scanner scanner = new Scanner(System.in);

    public void udbetaling(){
        System.out.println("Udbetalings menuen!");
        System.out.println("1) MobilePay");
        System.out.println("2) Bankoverførsel");
        System.out.println("3) PayPal");

        char vNumber = scanner.next().charAt(0);
        switch (vNumber){
            case '1': {
                System.out.println("Du har valgt MobilePay");
                System.out.print("Indtast telefon nummer: ");
            }
            break;
            case '2': {
                System.out.println("Du har valgt Bankoverførsel");
                System.out.print("Indtast en fake bankkonto: ");
            }
            break;
            case '3': {
                System.out.println("Du har valgt PayPal");
                System.out.println("Login til PayPal");
            }
            break;
            default:
                System.out.println("Ugyldigt valg!");
        }
    }
}
