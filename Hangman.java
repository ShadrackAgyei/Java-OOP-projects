/**
 * This program is a hangman game that randomly generates a word and prompts
 * the user to guess one letter at a time
 * When the user makes a correct guess, the actual letter is then displayed.
 * When the user finishes a word, display the number of misses and ask the user whether to
 * continue to play with another word.
 *
 * @author Shadrack Agyei Nti
 */

import java.util.Scanner;

public class Hangman{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Array of words to be guessed
        String[] words = {"write", "that", "attack", "love", "valentine", "brook", "sick", "pick"};

        // Use command line parameters as array of words when provided
        if (args.length > 0) {
            words = args;
        }

        // condition to check for play again status and run the game
        boolean playAgain = true;
        String playAgainAnswer;
        while (playAgain){
            playGame(words);
            playAgainAnswer= input.next().toLowerCase();
            if (playAgainAnswer.charAt(0) == 'n') playAgain = false;
        }

    }

    /**
     * This method runs the hangman game
     * @param words array containing words to be guessed
     */
    public static void playGame(String[] words){
        Scanner input = new Scanner(System.in);

        // Select a random word
        int randomIndex = (int) (Math.random() * words.length);
        String secretWord = words[randomIndex];

        // Places asterisks in place of each letter in word
        char[] listOfSecretChars = new char[secretWord.length()];
        for (int i = 0; i < secretWord.length(); i++) {
            listOfSecretChars[i] = '*';
        }

        // create a covered string of the secret word with asterisks(*) and initialize it
        String secretWordCovered = "";
        for (char letter : listOfSecretChars) secretWordCovered += letter;

        // counter for number of misses
        int noOfMisses = 0;
        String guess;

        // A loop that run until the correct word is fully guessed
        do {
            System.out.print("(Guess) Enter a letter in word " + secretWordCovered + " > ");
            guess = input.next().toLowerCase();

            // checks if guess already in list containing correctly guessed letters
            for (char listOfSecretChar : listOfSecretChars) {
                if (guess.charAt(0) == listOfSecretChar) {
                    System.out.printf("%c is already in the word\n", guess.charAt(0));
                    break;
                }
            }
            // condition to check if secret word contains the guess and place the guess
            // at the corresponding position
            if (secretWord.contains(guess)) {
                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == guess.charAt(0)) {
                        listOfSecretChars[i] = guess.charAt(0);
                    }
                }
            }
            else {
                // increment the counter for number of misses if word not in secret word
                noOfMisses++;
                System.out.printf("%c is not in the word", guess.charAt(0));
                System.out.println();
            }

            // reset the string to an empty string before appending the elements in the array
            // containing guessed letters
            secretWordCovered = "";
            for (char letter : listOfSecretChars) secretWordCovered += letter;


        } while (!secretWord.equals(secretWordCovered));

        // messages to be printed after each correctly guessed word
        System.out.print("The word is "+ secretWord + ".");
        System.out.printf("You missed %d time\n", noOfMisses);
        System.out.println("Do you want to guess another word? Enter y or n >: ");
    }
}