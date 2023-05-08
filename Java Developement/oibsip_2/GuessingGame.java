import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args)
    {
        System.out.println("Let's play a game.  I'll pick a number between");
        System.out.println("1 and 100, and you try to guess it.");
        Scanner sc = new Scanner(System.in);
        boolean playAgain= true;

        do {
            playGame();  // call subroutine to play one game
            System.out.print("\nDo you want to play again? (yes or no): ");
            String answer = sc.next();
            if (answer.equalsIgnoreCase("no")) {
                playAgain = false;
            } else {
                playGame();
            }
        } while(playAgain);
        System.out.println("Thanks for playing.Goodbye.");

    }

    static void playGame() {
        int computersNumber;
        int usersGuess;
        int guessCount;
        computersNumber = (int)(100 * Math.random()) + 1;
        guessCount = 0;
        System.out.println();
        System.out.println("What is your first guess?");
        Scanner sc= new Scanner(System.in);
        while (true) {
            usersGuess = sc.nextInt();
            guessCount++;
            if (usersGuess == computersNumber) {
                System.out.println("You got it in " + guessCount
                        + " guesses!  My number was " + computersNumber);
                System.out.println("Your final score is " + (11-guessCount)*10);
                break;
            }
            if (guessCount == 10) {
                System.out.println("You didn't get the number in 10 guesses.");
                System.out.println("You lose.  My number was " + computersNumber);
                break;
            }
            if (usersGuess < computersNumber){
                if((computersNumber - usersGuess) >= 10)
                    System.out.println("That's too low.  Try again:");
                else
                    System.out.println("That's little low.  Try again:");
            }
            else if (usersGuess > computersNumber){
                if((usersGuess - computersNumber) > 10)
                    System.out.println("That's too high.  Try again:");
                else
                    System.out.println("That's little high.  Try again:");
            }
        }
        System.out.println();
    }

}