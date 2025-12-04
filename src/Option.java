import java.util.Scanner;

public class Option {
    public boolean running = true;
    Scanner scanner = new Scanner(System.in);


    public String startMenu() {
        System.out.println("::::::::::___StartMenu___::::::::::");
        System.out.println("1) Login");
        System.out.println("2) Opret bruger");
        System.out.println("3) Afslut");


        while (running) {
            char uNumber = scanner.next().charAt(0);
            switch (uNumber) {
                case ('1'): { //Bruger tuborg klamme her for at sørge for at mine variabler ikke kan ses i de andre cases.
                    System.out.println("::::::::::___LOGIN___::::::::::");
                    System.out.println("Skriv dit brugernavn: ");
                    String brugernavn = scanner.next();
                    System.out.println("Skriv din kode: ");
                    String password = scanner.next();

                    Login login = new Login(brugernavn, password);
                    running = false;
                    return "Du er logget ind som: " + brugernavn;

                }

                case ('2'): {
                    System.out.println("::::::::::___OPRET BRUGER___::::::::::");
                    SQL_IO read = new SQL_IO("jdbc:sqlite:userData.sqlite");
                    System.out.println("Skriv dit brugernavn: ");
                    String brugernavn = scanner.next();
                    System.out.println("Skriv din Email: ");
                    String email = scanner.next();
                    System.out.println("Skriv din kode: ");
                    String password = scanner.next();
                    read.makeUser(brugernavn, email, password);
                    running = false;
                    return "Du er har oprettet en bruger som: " + brugernavn;
                }
                case('3'): {
                    running = false;
                    return "Afsluttet";
                }
                default:{
                    return "Ugyldigt valg";
                }


            }


        }
        return ("HEJ");


    }

}

