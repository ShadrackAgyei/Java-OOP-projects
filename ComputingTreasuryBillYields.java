/**
 * This program computes the total value of an investment and the interest earned over a period
 * It takes inputs such as principal amount, yearly interest rate, investment period, deposit amount,
 * and computes the total value of the investment and the total interest earned.
 *
 * @author: Shadrack Agyei Nti
*/
import java.util.Scanner;

public class ComputingTreasuryBillYields {
    public final static int PERCENT = 100;
    public final static char CEDI = (char) 0x20B5;


    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        // Asks user for inputs: principal, yearly rate, periods per year, total periods and
        // recurring additional deposit after initial deposit
        System.out.print("Enter your principal amount: ");
        double principal = input.nextDouble();
        System.out.print("Enter the yearly rate: ");
        double rate = input.nextDouble();
        System.out.println("Chose between a 364-day bill (1 year), a 182-day bill (6 months) or a 91-day bill (3 months)");
        System.out.print("Enter the number of periods per year: 1-(1year) or 2-(6 months) or 4-(3 months)): ");
        int periodsPerYear = input.nextInt();
        System.out.print("Enter deposit amount you wish to add in each period after initial amount: ");
        double deposit = input.nextDouble();
        System.out.print("Enter the total number of periods: ");
        int totalPeriods = input.nextInt();

        // Calculate number of years
        double numOfYears = (double) totalPeriods /periodsPerYear;
        System.out.println(numOfYears+" years");

        // Calculate total value of investment and total interest earned
        double totalValueOfInvestment = computeInvestmentValue(principal,rate,periodsPerYear,deposit,totalPeriods);
        double totalInterestEarned = (totalValueOfInvestment - principal) - (deposit * (totalPeriods -1));

        // Prints the results
        System.out.printf("Total value of Investment: %c%.2f\n", CEDI,totalValueOfInvestment);
        System.out.printf("Total interest earned: %c%.2f\n", CEDI,totalInterestEarned);

    }

    /**
     * Computes the total value of an investment over a specified period.
     *
     * @param principal       The principal amount of the investment.
     * @param rate            The yearly interest rate.
     * @param periodsPerYear  The number of compounding periods per year.
     * @param deposit         The deposit amount added in each period after the initial amount.
     * @param totalPeriods    The total number of periods.
     * @return                The total value of the investment.
     */
    public static double computeInvestmentValue(double principal,double rate, int periodsPerYear, double deposit, int totalPeriods){

        double periodRate = rate/periodsPerYear;

        // calculate first compound interest value using formula principal * (1 + rate/100)
        double previousValue = principal * (1 + periodRate/PERCENT);
        double totalValueOfInvestment = 0;

        // Loop to calculate the total value of investment over the specified period
        // subtract 1 from total periods because the first compounding for the first year has already been done
        for (int i =0; i<totalPeriods-1;i++){
            totalValueOfInvestment = (previousValue + deposit) * (1 + periodRate/PERCENT);
            previousValue = totalValueOfInvestment;
        }

        return totalValueOfInvestment;
    }
}