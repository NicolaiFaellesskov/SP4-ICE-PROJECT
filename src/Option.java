import java.util.Scanner;

public class Option {

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
                case ('1'): { //Bruger tuborg klamme her for at sørge for at mine variabler ikke kan ses i de andre cases.
                    System.out.println("::::::::::___LOGIN___::::::::::");
                    System.out.println("Skriv dit brugernavn: ");
                    String brugernavn = scanner.next();

                    System.out.println("Skriv din kode: ");
                    String password = scanner.next();

                    try {
                        loggedInUser = sql.login(brugernavn, password);
                        UI.msg("Du er logget ind som: " + loggedInUser.getUsername());

                    } catch (Exception e) {
                        UI.msg("Noget gik galt med login");
                    }
                    break;
                }

                case ('2'): {
                    System.out.println("::::::::::___OPRET BRUGER___::::::::::");

                    System.out.println("Skriv dit brugernavn: ");
                    String brugernavn = scanner.next();

                    System.out.println("Skriv din Email: ");
                    String email = scanner.next();

                    System.out.println("Skriv din kode: ");
                    String password = scanner.next();

                    sql.makeUser(brugernavn, email, password);

                    UI.msg("Du  har nu oprettet en bruger som: " + brugernavn);
                }
                case ('3'): {

                    UI.msg("Afsluttet");
                    running = false;
                }
                default: {
                    UI.msg("Ugyldigt valg");
                }


            }


        }


    }
}



