import java.util.Scanner;

public class Option {
    BlackJack bJ = new BlackJack(); //BlackJack bruger Option class til at give brugeren en Option.
    Roulette roulette;

    Scanner scanner = new Scanner(System.in);
    private Udbetaling ub;
    private Indbetaling ib;
    private User loggedInUser;
    public DatabaseIO sql = new DatabaseIO("jdbc:sqlite:userData.sqlite");


    public void startMenu() throws Exception {


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
                        Indbetaling ib = new Indbetaling(loggedInUser, sql);
                        roulette = new Roulette(loggedInUser, sql);
                        UI.msg("Du er logget ind som: " + loggedInUser.getUsername());
                        running = false;
                        gameMenu();


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
        while (running) {
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
                    BlackJack blackjack = new BlackJack();
                    blackjack.play();


                    break;
                }
                case ('2'): {
                    System.out.println("Du har valgt Roulette!");
                    roulette.play();
                    break;
                }
                case ('3'): {
                    if (ib != null) {
                        ib.indbetaling();
                    } else {
                        UI.msg("Du skal være logget ind først!");
                    }
                    break;
                }
                case ('4'): {
                    if (ub != null) {
                        ub.udbetaling();
                    } else {
                        UI.msg("Du skal være logget ind først!");
                    }
                    break;
                }
                case ('5'): {
                    UI.msg("Afsluttet!");
                    running = false;
                }
                break;
                default: {
                    UI.msg("Ugyldigt valg!");
                }
            }
        }
    }

    public Cards blackJackOption() throws Exception {

        while (true) {
            System.out.println("::::::::::: Vil du hit, stand or split? :::::::::::");
            System.out.println("1) Hit");
            System.out.println("2) Stand");
            System.out.println("3) Split");
            char inputNumber = scanner.next().charAt(0);
            switch (inputNumber) {
                case ('1'): {
                    try {
                        return bJ.getNewCard();
                    } catch (Exception e) {
                        throw new Exception("Der opstod en fejl med throw" + e.getMessage());
                    }
                }

                case ('2'): {


                    UI.msg("Du stod");
                    return null;


                }
                case ('3'): {

                    throw new Exception("JUST WAIT, SPLIT NOT DONE");

                }
                default:
                    System.out.println("Ugyldigt valg, prøv igen."); // informer bruger


            }


        }

    }
}



