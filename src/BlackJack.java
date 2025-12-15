import java.util.List;

import static java.lang.Math.random;

public class BlackJack {
    private int dealerCardNumber;
    private int playerCardNumber;
    DatabaseIO databaseIO = new DatabaseIO("jdbc:sqlite:userData.sqlite");

    private List<Cards> deck;

    public void playBlackJack() {

    }

    public void play() throws Exception {
        this.deck = databaseIO.cardReader();
        Cards card = getCard();

        this.dealerCardNumber += card.getCardNumber();
        UI.msg("Dealeren har: " + card + "   ||  KORT ER IKKE VENDT ENDNU");
        UI.msg("_________________________________________________________");




    }

    public List<Cards> getDeck() {
        return deck;
    }

    public Cards getCard() throws Exception {

        //int randomIndexNumber = (int)(Math.random()* 52)+ 1));// Ville kunne give et OutOfIndexBounce. da den enlig prøver at lede efter kort 53. pågrund af index start 0.

        int randomIndexNumber = (int) (Math.random() * deck.size());
        deck.remove(randomIndexNumber); //fjerner kortet efter det blev trukket
        return deck.get(randomIndexNumber); //tager et tilfældigt kort fra listen af kort
    }
}
