import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Roulette {
    ArrayList<Integer> rouletteNumbers = new ArrayList<>();
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    public void RouletteNumbers (){

        int i = 0;

        while (i < 37){
            rouletteNumbers.add(i);
            i++;
        }
    }

    public void rouletteGame() {
        RouletteNumbers();

        System.out.println("Sæt dit bet (0-36):");
        int bet = scanner.nextInt();

        int winningNumber = rouletteNumbers.get(rand.nextInt(37));

        System.out.println("Roulette nummeret er: " + winningNumber);

        if (bet == winningNumber) {
            System.out.println("Tilykke du vandt!");
        } else {
            System.out.println("Du tabte!");
        }
    }
}

