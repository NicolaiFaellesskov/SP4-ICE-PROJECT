import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.random;

public class BlackJack {
    Scanner scanner = new Scanner(System.in);
    private User user;
    private DatabaseIO db;


    private int dealerCardNumber;
    private int playerCardNumber;


    private List<Cards> deck;

    public BlackJack(User user, DatabaseIO db) {
        this.user = user;
        this.db = db;

    }

    public void play() throws Exception {
        UI.msg("Din saldo er: " + user.getSaldo());
        this.deck = db.cardReader();

        int totalBet = UI.askInt("Indtast dit samlet spille beløb");
        if (totalBet <= 0 || totalBet > user.getSaldo()) {
            System.out.println("Ugyldigt Beløb");
            return;
        }


        if (!deck.isEmpty()) {

            List<Cards> playerCards = new ArrayList<>();
            List<Cards> dealerCards = new ArrayList<>();

            Cards playerCard1 = getNewCard();
            Cards playerCard2 = getNewCard();
            playerCards.add(playerCard1);
            playerCards.add(playerCard2);
            playerCardNumber = 0;
            this.playerCardNumber += playerCard1.getCardNumber();
            this.playerCardNumber += playerCard2.getCardNumber();
            boolean playerLost = false;

            Cards dealerCard1 = getNewCard();
            Cards dealerCard2 = getNewCard();
            dealerCards.add(dealerCard1);
            dealerCards.add(dealerCard2);
            this.dealerCardNumber = 0;
            this.dealerCardNumber += dealerCard1.getCardNumber();
            this.dealerCardNumber += dealerCard2.getCardNumber();


            UI.msg("Dealeren har: " + dealerCard1 + "   ||  KORT ER IKKE VENDT ENDNU");
            UI.msg("_________________________________________________________");

            UI.msg("Du har: " + playerCard1 + " || OG || " + playerCard2);
            UI.msg("_________________________________________________________");

            while (playerCardNumber < 21) {
                Cards hitCard = blackJackOption();
                if (hitCard != null) {
                    playerCards.add(hitCard);

                    this.playerCardNumber += hitCard.getCardNumber();


                    UI.msg("Du har i alt: " + playerCardNumber);
                } else {
                    break;
                }
            }
            UI.msg("Dine kort efter turen: " + playerCards);
            UI.msg("Din samlede sum er: " + playerCardNumber);

            while (dealerCardNumber < 17) {
                Cards dealerHit = getNewCard();
                dealerCards.add(dealerHit);

                this.dealerCardNumber += dealerHit.getCardNumber();
            }

            UI.msg("Dealers kort efter turen: " + dealerCards);
            UI.msg("Dealers samlede sum er: " + dealerCardNumber);

            UI.msg("DEALER_______________________" + dealerCardNumber + "__________________________________");
            UI.msg("SPILLER_______________________" + playerCardNumber + "__________________________________");
            if (playerCardNumber > 21) {
                UI.msg("BUST, du tabte");
                playerLost = true;
            }
            if (playerCardNumber == dealerCardNumber && !playerLost) {
                UI.msg("TIE, du får penge tilbage");
            }
            if (playerCardNumber == 21 & dealerCardNumber != 21) {
                UI.msg("BLACK JACK, Du vandt");
            }
            if (dealerCardNumber < 22 & dealerCardNumber > playerCardNumber) {
                UI.msg("Dealer vandt, du tabte.");
                playerLost = true;
            }
            if (dealerCardNumber > 21 && playerCardNumber < 21) {
                UI.msg("Dealer BUSTER, du vinder");
            }
            if (playerLost) {
                db.removeSaldo(user.getId(), totalBet);
                user.setSaldo(user.getSaldo() - totalBet);
            } else {
                db.addSaldo(user.getId(), totalBet);
                user.setSaldo(user.getSaldo() + totalBet);
            }
            UI.msg("Du har nu: " + user.getSaldo());
        }
        //throw new

        // Exception("There is no cards on deck!");
    }


    public List<Cards> getDeck() {
        return deck;
    }

    public Cards getNewCard() throws Exception {

        //int randomIndexNumber = (int)(Math.random()* 52)+ 1));// Ville kunne give et OutOfIndexBounce. da den enlig prøver at lede efter kort 53. pågrund af index start 0.

        int randomIndexNumber = (int) (Math.random() * deck.size());

        Cards card = deck.get(randomIndexNumber); //gemmer card, så jeg kan nedenfor slette card efter fra deck.listen
        deck.remove(randomIndexNumber);//fjerner kortet efter det blev trukket

        return card; //tager et tilfældigt kort fra listen af kort
    }

    public Cards blackJackOption() throws Exception { //STØRSTE FEJL AT PRØVE AT LAVE DETTE: DET KALDER SIG SELV UENELIGT


        while (true) {
            System.out.println("::::::::::: Vil du hit, stand or split? :::::::::::");
            System.out.println("1) Hit");
            System.out.println("2) Stand");
            System.out.println("3) Split");
            char inputNumber = scanner.next().charAt(0);
            switch (inputNumber) {
                case ('1'): {
                    try {
                        Cards newCard = getNewCard();
                        System.out.println("Du trak " + newCard);
                        return newCard;
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