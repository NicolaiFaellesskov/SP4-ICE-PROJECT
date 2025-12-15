public class Cards {
    private String cardName;
    private int cardNumber;
    private String color;
    private String symbol;
    public Cards(String cardName, int cardNumber, String color, String symbol) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.color = color;
        this.symbol = symbol;
    }
    public int getCardNumber(){
        return cardNumber;
    }
    public String toString (){
        return cardName +" "+  cardNumber + " "+ color + " " + symbol;


    }
}
