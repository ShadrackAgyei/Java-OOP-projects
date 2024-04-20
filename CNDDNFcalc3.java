import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * A program to calculate Sum of Products (SOP) and Product of Sums (POS) expressions
 * based on user input of boolean values for a given number of variables.
 */
public class CNDDNFcalc3 {

    // Define static variables for the variable names and their negations2
    static String var1 = "x";
    static String var2 = "y";
    static String var3 = "z";
    static String var4 = "t";
    static String var1prime = "x'";
    static String var2prime = "y'";
    static String var3prime = "z'";
    static String var4prime = "t'";

    /**
     * Main method to start the program.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        mainProgram();
    }

    /**
     * Main program logic to execute the CNF/DNF calculation.
     */
    public static void mainProgram() {
        String degreeOfVariableGiven = askAndRead();
        int degreeOfVariable = Integer.parseInt(degreeOfVariableGiven);
        Scanner input = new Scanner(System.in);
        if (degreeOfVariable == 1) {
            System.out.println("| x | F |");
        } else if (degreeOfVariable == 2) {
            System.out.println("| x | y | F |");
        } else if (degreeOfVariable == 3) {
            System.out.println("| x | y | z | F |");
        } else if (degreeOfVariable == 4) {
            System.out.println("| x | y | z | t | F |");
        }

        if (degreeOfVariable > 0 && degreeOfVariable < 5) {
            List<Integer> emptyList = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);

            int noOfRows = (int) Math.pow(2, degreeOfVariable);
            int noOfColumns = degreeOfVariable + 1;
            int[][] listOfBooleanValues = new int[noOfRows][noOfColumns];
            fillTable(degreeOfVariable, listOfBooleanValues);
            printTable(degreeOfVariable, listOfBooleanValues);
            System.out.println("Please enter function " + (int) Math.pow(2, degreeOfVariable)
                    + " boolean values for function column, separated by a single space only.");
            System.out.println("Example input: 1 1 0...");
            String takeValues = scanner.nextLine();
            String[] newArray = takeValues.split("\\s+");

            for (String number : newArray) {
                try {
                    int intNumber = Integer.parseInt(number);
                    if (intNumber == 0 || intNumber == 1) {
                        emptyList.add(intNumber);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + number);
                }
            }

            System.out.println(emptyList);
            System.out.println("Do you want to make changes? Press 1 for yes or 0 for no");
            int response1 = scanner.nextInt();
            if (response1 == 1) {
                scanner.nextLine();
                System.out.println("Please enter function " + (int) Math.pow(2, degreeOfVariable)
                        + " boolean values for function column, separated by a single space only.");
                System.out.println("Example input: 1 1 0...");
                emptyList.clear();
                String takeValues1 = scanner.nextLine();
                String[] newArray1 = takeValues1.split("\\s+");

                for (String number : newArray1) {
                    try {
                        int intNumber = Integer.parseInt(number);
                        if (intNumber == 0 || intNumber == 1) {
                            emptyList.add(intNumber);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input: " + number);
                    }
                }
            } else if (response1 == 0) {
                if (emptyList.size() == listOfBooleanValues.length) {
                    for (int i = 0; i < listOfBooleanValues.length; i++) {
                        listOfBooleanValues[i][degreeOfVariable] = emptyList.get(i);
                    }
                    System.out.println();
                    if (degreeOfVariable == 1) {
                        System.out.println("| x | F |");
                    } else if (degreeOfVariable == 2) {
                        System.out.println("| x | y | F |");
                    } else if (degreeOfVariable == 3) {
                        System.out.println("| x | y | z | F |");
                    } else if (degreeOfVariable == 4) {
                        System.out.println("| x | y | z | t | F |");
                    }
                    printTable(degreeOfVariable, listOfBooleanValues);

                    // Calculate sum of products
                    String sumOfProductsExpression = sumOfProductsCalc(listOfBooleanValues, degreeOfVariable);
                    System.out.print("Sum of products: ");
                    System.out.println(sumOfProductsExpression);

                    // Calculate product of sums
                    String productOfSumsExpression = productOfSumsCalc(listOfBooleanValues, degreeOfVariable);
                    System.out.print("Product of sums: ");
                    System.out.println(productOfSumsExpression);
                    playAgain();
                } else {
                    System.out.println("You must enter " + (int) Math.pow(2, degreeOfVariable)
                            + " boolean values. You entered " + emptyList.size());
                    mainProgram();
                }
            }

            if (emptyList.size() == listOfBooleanValues.length) {
                for (int i = 0; i < listOfBooleanValues.length; i++) {
                    listOfBooleanValues[i][degreeOfVariable] = emptyList.get(i);
                }
                System.out.println();
                if (degreeOfVariable == 1) {
                    System.out.println("| x | F |");
                } else if (degreeOfVariable == 2) {
                    System.out.println("| x | y | F |");
                } else if (degreeOfVariable == 3) {
                    System.out.println("| x | y | z | F |");
                } else if (degreeOfVariable == 4) {
                    System.out.println("| x | y | z | t | F |");
                }
                printTable(degreeOfVariable, listOfBooleanValues);

                // Calculate sum of products
                String sumOfProductsExpression = sumOfProductsCalc(listOfBooleanValues, degreeOfVariable);
                System.out.print("Sum of products: ");
                System.out.println(sumOfProductsExpression);

                // Calculate product of sums
                String productOfSumsExpression = productOfSumsCalc(listOfBooleanValues, degreeOfVariable);
                System.out.print("Product of sums: ");
                System.out.println(productOfSumsExpression);
                playAgain();
            } else {
                System.out.println("You must enter " + (int) Math.pow(2, degreeOfVariable)
                        + " boolean values. You entered " + emptyList.size());
                mainProgram();
            }
        }
        else {mainProgram();}
    }

    /**
     * Calculate the sum of products expression based on the boolean values table.
     *
     * @param listOfBooleanValues 2D array representing the boolean values table
     * @param degreeOfVariable    Degree of the variable
     * @return Sum of products expression
     */
    public static String sumOfProductsCalc(int[][] listOfBooleanValues, int degreeOfVariable) {
        // Initialize StringBuilder for the sum of products expression
        StringBuilder sumOfProducts = new StringBuilder();
        for (int i = 0; i < listOfBooleanValues.length; i++) {
            if (listOfBooleanValues[i][degreeOfVariable] == 1) {
                StringBuilder productTerm = new StringBuilder();
                for (int k = 0; k < degreeOfVariable; k++) {
                    if (listOfBooleanValues[i][k] == 1) {
                        switch (k) {
                            case 0:
                                productTerm.append(var1);
                                break;
                            case 1:
                                productTerm.append(var2);
                                break;
                            case 2:
                                productTerm.append(var3);
                                break;
                            case 3:
                                productTerm.append(var4);
                                break;
                        }
                    } else {
                        switch (k) {
                            case 0:
                                productTerm.append(var1prime);
                                break;
                            case 1:
                                productTerm.append(var2prime);
                                break;
                            case 2:
                                productTerm.append(var3prime);
                                break;
                            case 3:
                                productTerm.append(var4prime);
                                break;
                        }
                    }
                }
                if (sumOfProducts.length() > 0) {
                    sumOfProducts.append(" + ");
                }
                sumOfProducts.append(productTerm);
            }
        }
        return sumOfProducts.toString();
    }

    /**
     * Calculate the product of sums expression based on the boolean values table.
     *
     * @param listOfBooleanValues 2D array representing the boolean values table
     * @param degreeOfVariable    Degree of the variable
     * @return Product of sums expression
     */
    public static String productOfSumsCalc(int[][] listOfBooleanValues, int degreeOfVariable) {
        // Initialize StringBuilder for the product of sums expression
        StringBuilder productOfSums = new StringBuilder();
        for (int i = 0; i < listOfBooleanValues.length; i++) {
            // Check if the row is relevant for POS (considering the change in logic)
            if (listOfBooleanValues[i][degreeOfVariable] == 0) {
                StringBuilder sumTerm = new StringBuilder("(");
                for (int k = 0; k < degreeOfVariable; k++) {
                    if (listOfBooleanValues[i][k] == 0) {
                        // Append the variable if it's 0 in POS, because we need the negation to make the term true
                        switch (k) {
                            case 0:
                                sumTerm.append(var1);
                                break;
                            case 1:
                                sumTerm.append(var2);
                                break;
                            case 2:
                                sumTerm.append(var3);
                                break;
                            case 3:
                                sumTerm.append(var4);
                                break;
                        }
                    } else {
                        // Append the negated variable if it's 1
                        switch (k) {
                            case 0:
                                sumTerm.append(var1prime);
                                break;
                            case 1:
                                sumTerm.append(var2prime);
                                break;
                            case 2:
                                sumTerm.append(var3prime);
                                break;
                            case 3:
                                sumTerm.append(var4prime);
                                break;
                        }
                    }
                    if (k < degreeOfVariable - 1) {
                        sumTerm.append(" + ");
                    }
                }
                sumTerm.append(")");
                if (productOfSums.length() > 0) {
                    productOfSums.append(" * ");
                }
                productOfSums.append(sumTerm);
            }
        }
        return productOfSums.toString();
    }

    /**
     * Print the boolean values table.
     *
     * @param  degreeOfvariable    Degree of the variable
     * @param listOfBooleanValues 2D array representing the boolean values table
     */
    public static void printTable(int degreeOfvariable, int[][] listOfBooleanValues) {
        int noOfRows = (int) Math.pow(2, degreeOfvariable);
        int noOfColumns = degreeOfvariable + 1;

        for (int i = 0; i < noOfRows; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                System.out.print("| " + listOfBooleanValues[i][j] + " ");
            }
            System.out.println("|");
        }
    }

    /**
     * Fill the boolean values table according to the CNF/DNF logic.
     *
     * @param degreeOfvariable    Degree of the variable
     * @param listOfBooleanValues 2D array representing the boolean values table
     */
    public static void fillTable(int degreeOfvariable, int[][] listOfBooleanValues) {
        int columnsToFill = listOfBooleanValues[0].length - 1;
        int rowsToFill = listOfBooleanValues.length;

        // Initialize toggleAfter for the first column
        int toggleAfter = rowsToFill / 2;

        // Iterate over each column
        for (int col = 0; col < degreeOfvariable; col++) {
            // Initialize the value to be set in the current column
            int value = 1;

            // Iterate over each row for the current column
            for (int row = 0; row < rowsToFill; row++) {
                // Assign the value to the cell
                listOfBooleanValues[row][col] = value;

                // Check if we've reached the toggle point
                if ((row + 1) % toggleAfter == 0) {
                    // Toggle the value
                    value = 1 - value;
                }
            }

            // Update toggleAfter for the next column: half the current toggleAfter
            toggleAfter /= 2;
        }
    }

    /**
     * Ask the user to enter the degree of the variable.
     *
     * @return Degree of the variable entered by the user
     */
    public static String askAndRead() {
        System.out.print("Enter degree of variable: ");
        Scanner input = new Scanner(System.in);
        String inputMade = input.next();

        //These lines checks for good inputs
        try {
            int degreeOfVariable = Integer.parseInt(inputMade);
        } catch (NumberFormatException nfe) {
            inputMade = askAndRead();
        }
        return inputMade;
    }

    /**
     * Ask the user if they want to play again and restart the program if needed.
     */
    public static void playAgain() {
        Scanner input1 = new Scanner(System.in);
        System.out.println("Do you want to do another calculation? Press 1 for yes or 0 for no");
        int response = input1.nextInt();
        if (response == 1) {
            mainProgram();
        } else if (response == 0) {
            System.out.println("Goodbye.");
            System.exit(0);
        } else {
            playAgain();

        }
    }

}