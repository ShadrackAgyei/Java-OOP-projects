/**
 * This code works by asking the user to enter the total cost of items
 * a customer has purchased and the amount the customer has paid.
 * It computes the change to give to the customer and, to help the cashier,
 * a breakdown of the denominations of notes and coins (in Ghana Cedis) to give to the customer.
 * @author: Shadrack Agyei Nti
 */

import java.util.Scanner;

public class ComputeChange {
    public static final int PESWAS_PER_CEDI = 100;
    public final static char CEDI = (char) 0x20B5;

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        // Input total cost and amount paid
        System.out.print("Enter your total cost: ");
        double totalCost = input.nextDouble();
        System.out.print("Enter the total amount customer has paid: ");
        double amountPaid = input.nextDouble();

        // Calculate change and display the result
        double change = computeChangeBreakdown(totalCost,amountPaid);
        System.out.printf("Total change: %c%.2f",CEDI,change);
    }

    /**
     * Computes the change to give back to the customer and provides a breakdown
     * of the denominations of notes and coins.
     *
     * @param totalCost   The total cost of items.
     * @param amountPaid  The total amount paid by the customer.
     * @return            The calculated change to give back to the customer.
     */
    public static double computeChangeBreakdown(double totalCost,double amountPaid){
        // Calculate the change
        double change = amountPaid - totalCost;
        System.out.printf("Total change: %c%.2f\n",CEDI,change);

        int numberOfOneCedis,remainingAmount = 0;

        double changeInPeswas =  Math.round(change * 100);
        numberOfOneCedis = (int) changeInPeswas / PESWAS_PER_CEDI;
        double remainingPeswas = (changeInPeswas % PESWAS_PER_CEDI);

        // Array of Cedi denominations
        int[] cediDenominations = {200, 100, 50, 20, 10, 5, 2, 1};
        int[] countOfCediDenominations = new int[cediDenominations.length];

        // Loop to calculate the number of each Cedi denomination
        for (int i = 0; i<cediDenominations.length;i++){
            // count number of denominations for 200 cedis separately because it is the highest
            int noOfDenomination;
            if (cediDenominations[i] == 200){
                noOfDenomination = numberOfOneCedis / 200;
                countOfCediDenominations[i] = noOfDenomination;
                remainingAmount = numberOfOneCedis % 200;
            }else {
                noOfDenomination = remainingAmount/cediDenominations[i];
                countOfCediDenominations[i] = noOfDenomination;
                remainingAmount = remainingAmount % cediDenominations[i];
            }

            // condition to make sure to print a particular denomination if the required number is more than 0.
            if (countOfCediDenominations[i] > 0) {
                System.out.printf("%d %c%d note(s) \n",countOfCediDenominations[i],CEDI,cediDenominations[i]);
            }
        }

        // Array of Peswas denominations
        int[] peswasDenominations = {50,20,10};
        int[] countOfPeswasDenominations = new int[peswasDenominations.length];

        // Loop to calculate the number of each Peswas denomination
        for (int i =0; i<peswasDenominations.length;i++){
            double noOfDenomination = remainingPeswas / peswasDenominations[i];
            countOfPeswasDenominations[i] = (int) noOfDenomination;
            remainingPeswas = remainingPeswas % peswasDenominations[i];

            // condition to make sure to print a particular denomination if the required number is more than 0.
            if (countOfPeswasDenominations[i] > 0) {
                System.out.printf("%d %dp coin(s) \n",countOfPeswasDenominations[i],peswasDenominations[i]);
            }
        }

        // Return the total change
        return change;
    }
}