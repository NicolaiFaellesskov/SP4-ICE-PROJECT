import java.util.Scanner;

public class Option {
    Udbetaling ub = new Udbetaling();
    Indbetaling ib = new Indbetaling();
    Scanner scanner = new Scanner(System.in);
    private User loggedInUser;


    public void startMenu() throws Exception {

        DatabaseIO sql = new DatabaseIO("jdbc:sqlite:userData.sqlite");
        boolean running = true;

        while (running) {
            System.out.println("::::::::::___StartMenu___::::::::::");
            System.out.println("1) Login");
            System.out.println("2) Opret bruger");
            System.out.println("3) Afslut");

            char uNumber = scanner.next().charAt(0);

            switch (uNumber) {

                case '1': {
                    System.out.println("::::::::::___LOGIN___::::::::::");
                    System.out.println("Skriv dit brugernavn: ");
                    String brugernavn = scanner.next();

                    System.out.println("Skriv din kode: ");
                    String password = scanner.next();

                    try {
                        loggedInUser = sql.login(brugernavn, password);
                        UI.msg("Du er logget ind som: " + loggedInUser.getUsername());

                        running = false;
                    } catch (Exception e) {
                        UI.msg("Noget gik galt med login");
                    }
                    break;
                }

                case '2': {
                    System.out.println("::::::::::___OPRET BRUGER___::::::::::");

                    System.out.println("Skriv dit brugernavn: ");
                    String brugernavn = scanner.next();

                    System.out.println("Skriv din Email: ");
                    String email = scanner.next();

                    System.out.println("Skriv din kode: ");
                    String password = scanner.next();

                    sql.makeUser(brugernavn, email, password);

                    UI.msg("Du har nu oprettet en bruger som: " + brugernavn);
                    break;
                }

                case '3': {
                    UI.msg("Afsluttet");
                    running = false;
                    break;
                }

                default: {
                    UI.msg("Ugyldigt valg");
                    break;
                }
            }
        }
    }

    public void gameMenu() throws Exception {
        boolean running = true;
        while (running){
            System.out.println("::::::::::: Vælg et spil :::::::::::");
            System.out.println("1) Blackjack");
            System.out.println("2) Roulette");
            System.out.println("3) Indbetaling");
            System.out.println("4) Udbetaling");
            System.out.println("5) Afslut");


            char cNumber = scanner.next().charAt(0);
            switch (cNumber) {
                case ('1'): {
                    System.out.println("Du har valgt Blackjack!");
                }
                case ('2'): {
                    System.out.println("Du har valgt Roulette!");
                }
                case ('3'): {
                    ib.indbetaling();
                }
                case ('4'): {
                    ub.udbetaling();
                }
                case ('5'): {
                    UI.msg("Afsluttet!");
                    running = false;
                }
                default: {
                    UI.msg("Ugyldigt valg!");
                }
            }
        }
    }
}



