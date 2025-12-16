import java.util.List;
import java.util.Scanner;

import static java.lang.Math.random;

public class BlackJack {
    Scanner scanner = new Scanner(System.in);
    Option op = new Option();

    private int dealerCardNumber;
    private int playerCardNumber;
    DatabaseIO databaseIO = new DatabaseIO("jdbc:sqlite:userData.sqlite");

    private List<Cards> deck;

    public void playBlackJack() {

    }

    public void play() throws Exception {
        this.deck = databaseIO.cardReader();

        if(!deck.isEmpty()) {
            Cards playerCard1 = getNewCard();
            Cards playerCard2 = getNewCard();


            this.dealerCardNumber += getNewCard().getCardNumber();
            UI.msg("Dealeren har: " + getNewCard() + "   ||  KORT ER IKKE VENDT ENDNU");
            UI.msg("_________________________________________________________");

            UI.msg("Du har: " + playerCard1 + " || OG || " + playerCard2);
            UI.msg("_________________________________________________________");

            Cards playerHitCard1 = op.blackJackOption();

            UI.msg("Du har: " + playerCard1 + " || OG || " + playerCard2 + " || OG || " + playerHitCard1);
            UI.msg("_________________________________________________________");



        }throw new Exception ("There is no cards on deck!");

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
}
