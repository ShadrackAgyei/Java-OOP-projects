/**
 * This program generates a random password based on user-specified requirements
 * and checks if the generated password meets the specified criteria.
 *
 * It includes methods to generate random characters, including uppercase letters,
 * lowercase letters, digits, and special characters.
 *
 * The main method takes user input for password requirements, generates a password,
 * and then checks its validity based on the specified criteria.
 *
 * @author Shadrack Agyei Nti
 */

import java.util.Scanner;

public class RandomPasswordGenerator {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        // Takes user input for password requirements
        System.out.println("Enter your password requirements below👇🏿👇🏿");
        System.out.print("Minimum password length: ");
        int minPasswordLength = input.nextInt();
        System.out.print("Minimum number of uppercase characters: ");
        int mimNoOfUppercase = input.nextInt();
        System.out.print("minimum number of digits: ");
        int minNoOfDigits = input.nextInt();
        System.out.print("minimum number of special characters: ");
        int minNoOfSpecialChars = input.nextInt();
        System.out.print("String indicating the allowed special characters: ");
        String allowedSpecialChars = input.next();

        // Generate and display the password
        String generatedPassword = generatePassword(minPasswordLength,mimNoOfUppercase,minNoOfDigits,
                minNoOfSpecialChars,allowedSpecialChars);
        System.out.println(generatedPassword);

        // Check if the generated password is valid
        boolean checkPassword = checkPassword(generatedPassword,minPasswordLength,mimNoOfUppercase,minNoOfDigits,
                minNoOfSpecialChars,allowedSpecialChars);
        if (checkPassword) System.out.println("Password is Valid✅");
        else System.out.println("Password is not Valid❌");

    }

    /**
     * Generates a random password based on user-specified requirements.
     *
     * @param minPasswordLength     Minimum length of the password.
     * @param minNoOfUppercase      Minimum number of uppercase characters.
     * @param minNoOfDigits         Minimum number of digits.
     * @param minNoOfSpecialChars   Minimum number of special characters.
     * @param allowedSpecialChars   String indicating the allowed special characters.
     * @return                      The generated password.
     */
    public static String generatePassword(int minPasswordLength, int minNoOfUppercase, int minNoOfDigits,
                                          int minNoOfSpecialChars, String allowedSpecialChars){
        String generatedPassword = "";

        // Generate required number of uppercase letters
        for (int i = 0; i < minNoOfUppercase; i++){
            generatedPassword += getRandomUpperCaseLetter();
        }

        // Generate required number of digits
        for (int i = 0; i < minNoOfDigits; i++){
            generatedPassword += getRandomDigitCharacter();
        }

        // Generate required number of special characters
        for (int i = 0; i < minNoOfSpecialChars; i++){
            generatedPassword += getRandomSpecialCharacter(allowedSpecialChars);
        }

        // Generate remaining characters
        int remainingLength = minPasswordLength - generatedPassword.length();
        for (int i = 0; i < remainingLength; i++){
            generatedPassword += getRandomLowerCaseLetter();
        }

        generatedPassword = shufflePassword(generatedPassword);
        return generatedPassword;
    }

    /**
     * Checks if the generated password meets the specified criteria.
     *
     * @param generatedPassword    The generated password.
     * @param minPasswordLength    Minimum length of the password.
     * @param minNoOfUppercase     Minimum number of uppercase characters.
     * @param minNoOfDigits        Minimum number of digits.
     * @param minNoOfSpecialChars  Minimum number of special characters.
     * @param allowedSpecialChars  String indicating the allowed special characters.
     * @return                     True if the password is valid, false otherwise.
     */
    public static boolean checkPassword(String generatedPassword,int minPasswordLength, int minNoOfUppercase, int minNoOfDigits,
                                       int minNoOfSpecialChars, String allowedSpecialChars){

        boolean validationMessage = false;
        int noOfUpperCase = 0, noOfDigits = 0, noOfSpecial = 0;

        for (int i = 0; i<generatedPassword.length();i++) {
            char letter = generatedPassword.charAt(i);
            if (Character.isUpperCase(letter)) noOfUpperCase ++;
            else if (Character.isDigit(letter)) noOfDigits++;
            else if (allowedSpecialChars.indexOf(letter) != -1) noOfSpecial++;
        }

        // Check if the password meets the specified criteria
        if (generatedPassword.length() < minPasswordLength) {
            validationMessage = false;
        } else if (noOfUpperCase<minNoOfUppercase || noOfDigits<minNoOfDigits || noOfSpecial <minNoOfSpecialChars) {
            validationMessage = false;
        } else
            validationMessage = true;

        return validationMessage;
    }
    public static char getRandomCharacter(char ch1, char ch2) {
        //  Generate a random character between ch1 and ch2
        return (char)(ch1 + Math.random() * (ch2 - ch1 + 1));
    }

    // This method utilizes the getRandomCharacter method to return a random lowercase letter.
    public static char getRandomLowerCaseLetter() {
        return getRandomCharacter('a', 'z');
    }

    // This method utilizes the getRandomCharacter method to return a random uppercase letter.
    public static char getRandomUpperCaseLetter() {
        return getRandomCharacter('A', 'Z');
    }

    // This method utilizes the getRandomCharacter method to return a random digit character.
    public static char getRandomDigitCharacter() {
        return getRandomCharacter('0', '9');
    }
    public static char getRandomSpecialCharacter(String allowedSpecialCharacters) {
        char randomSpecialCharacter = 0;
        for (int i = 0; i < allowedSpecialCharacters.length(); i++){
            int randomIndex = (int) (Math.random() * allowedSpecialCharacters.length()) ;
            randomSpecialCharacter = allowedSpecialCharacters.charAt(randomIndex);
        }
        return randomSpecialCharacter;
    }
    public static String shufflePassword(String password) {
        String shuffledPassword = "";
        int[] randomIndexList = new int[password.length()];

        // Initialize the array with consecutive indices
        for (int i = 0; i < password.length(); i++) {
            randomIndexList[i] = i;
        }

        // Shuffle the indexes randomly
        for (int i = 0; i < password.length(); i++) {
            int randomIndex = (int) (Math.random() * password.length());
            int tempIndex = randomIndexList[i];
            randomIndexList[i] = randomIndexList[randomIndex];
            randomIndexList[randomIndex] = tempIndex;
        }

        // Create the shuffled password based on the shuffled indexes
        for (int i = 0; i < password.length(); i++) {
            shuffledPassword += password.charAt(randomIndexList[i]);
        }

        return shuffledPassword;
    }
}