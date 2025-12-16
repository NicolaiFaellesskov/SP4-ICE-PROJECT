import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Roulette {

    private User user;
    private DatabaseIO db;
    private Random rand = new Random();

    public Roulette(User user, DatabaseIO db) {
        this.user = user;
        this.db = db;
    }

    public void play() throws Exception{

        System.out.println("Din saldo er: " + user.getSaldo());

        int totalBet = UI.askInt("Indtast dit samlet spille beløb");
        if(totalBet <= 0 || totalBet > user.getSaldo()) {
            System.out.println("Ugyldigt Beløb");
            return;
        }

       int amountOfNumbers = UI.askInt("Hvor mange tal vil du bette på?");
        if(amountOfNumbers <= 0 || amountOfNumbers > 36) {
            System.out.println("Ugyldigt antal");
        }

        ArrayList<Integer> chosenNumbers = new ArrayList<>();

        for (int i = 0; i < amountOfNumbers; i++) {
            int number = UI.askInt("Indtast tal " + (i + 1) + "(0-36)");
            if(number < 0 || number > 36 || chosenNumbers.contains(number)) {
                System.out.println("Ugyldig eller allerede valgt tal");
                i--;
            } else {
                chosenNumbers.add(number);
            }
        }

        int betPerNumber = totalBet / chosenNumbers.size();

        if (betPerNumber == 0){
            System.out.println("Bet beløbet for lavt");
        }

        db.removeSaldo(user.getId(),totalBet);
        user.setSaldo(user.getSaldo() - totalBet);

        int winningNumber = rand.nextInt(37);
        System.out.println("Roulette nummer: " + winningNumber);

        if (chosenNumbers.contains(winningNumber)) {
            int winAmount = betPerNumber * 35;
            db.addSaldo(user.getId(),winAmount);
            user.setSaldo(user.getSaldo() + winAmount);

            System.out.println("Du vandt: " + winAmount);
        } else {
            System.out.println("errrrr aw dang it, errrrr aw dang it. (du tabte)");
        }
        System.out.println("Ny saldo: " + user.getSaldo());
    }
}

