package cash_register;

import java.util.*;

public class MakeChange {
	public static double itemPrice;
	public static double amountPaid;

	public static void main(String[] args) {
		run();
	}

	/**
	 * Begins the program.
	 * 
	 * @author jon edwards
	 */

	public static void run() {
		Scanner kb = new Scanner(System.in);

		askHowMuch(kb); // Story 1: Prompt user asking for item price
		askMoneyGiven(kb); // Story 2: Prompt user to ask how much customer gave
		checkMoneyGiven(kb); // Make sure money was enough
		giveChangeDue(); // Story 4: Display change with highest denom possible
		restart(kb);

	}

	/**
	 * Asks the user how much the item costs, and receives input.
	 * 
	 * @author jon edwards
	 * @param kb
	 *            gets the item price.
	 */

	public static void askHowMuch(Scanner kb) {
		System.out.print("The customer tells you how much the item cost. How much was it? >> ");
		itemPrice = kb.nextDouble();
		System.out.println("\nThe customer let you know that the item cost $" + itemPrice);
	}

	/**
	 * Asks the user how much money was given. Then takes input from user.
	 * 
	 * @param kb
	 *            gets the amount given.
	 * @author jon edwards
	 */

	public static void askMoneyGiven(Scanner kb) {
		System.out.println("\nThe customer hands you money...");
		System.out.print("\nHow much did he give you? >> ");
		amountPaid = kb.nextDouble();
		System.out.println("\nYou take $" + amountPaid + ", and say, \"Thanks for the cash, just hold on while I check the change machine.\"");
	}

	/**
	 * Logic to see whether the customer gave too little, or if no change is
	 * due. If no change is due, method calls restart(), if not enough money was
	 * given method calls notEnough().
	 * 
	 * @param kb
	 */

	public static void checkMoneyGiven(Scanner kb) {
		if (amountPaid < itemPrice) {
			System.out.println("\nThe customer short changed you...");
			notEnough(amountPaid, kb);
		} else if (amountPaid == itemPrice) {
			System.out.println("\nThere is no change due...");
			restart(kb);
		}
	}

	/**
	 * This method calculates change. First the amount of change is calculated
	 * by subtracting amountPaid from itemPrice, then it prints the rounded
	 * amount of change due. Then an algorithm is used to loop through an
	 * ordered array high to low of bill denominations. If denomination >
	 * change, the denomination is not printed, otherwise it is printed. If the
	 * denomination prints denomination is subtracted from change and the i
	 * value is decremented and checks once again whether that same denomination
	 * value is greater than change.
	 * 
	 * @author jon edwards
	 */

	public static void giveChangeDue() {
		int aggregateCounter = 0;
		double change = amountPaid - itemPrice;
		double total = round(change);
		double[] denominations = { 100, 50, 20, 10, 5, 1, 0.25, 0.10, 0.05, 0.01 };
		String[][] denominationNames = {
				{ "Hundred", "Fifty", "Twenty", "Ten", "Five", "One", "Quarter", "Dime", "Nickel", "Penny" },
				{ "Hundreds", "Fifties", "Twenties", "Tens", "Fives", "Ones", "Quarters", "Dimes", "Nickles",
						"Pennies" } };
		cashMachineTop();
		for (int i = 0; i < denominations.length; i++) {
			int iPrevValue = i; // Set k equal to the value of the loop
								// incrementer
			if (round(denominations[i]) <= round(change)) { // if value in array
				round(change -= denominations[i]);
				i--;
			}
			if (i != iPrevValue) {
				aggregateCounter++;
			} else if (aggregateCounter != 0) {
				if (aggregateCounter > 1) {
					System.out.println(" > " +aggregateCounter + " " + denominationNames[1][i]);
				} else {
					System.out.println(" > " +aggregateCounter + " " + denominationNames[0][i]);
				}
				aggregateCounter = 0; // reset aggregateCounter
			}

		}
		cashMachineBottom(total);
		System.out.println("You hand the customer $" + total + " and he briskly walks out the door...but...");

	}

	/**
	 * If not enough money was given the difference between amountPaid and the
	 * itemPrice is determined and printed to the screen. Then the customer
	 * gives the user more money and the user enters it into the prompt. Then
	 * amountPaid is added to what the customer gave. Then checkMoneyGiven is
	 * called to make sure the customer isn't short changing the clerk.
	 * 
	 * @param custPaid
	 *            The total amount the customer has given
	 * @param kb
	 *            User input.
	 * @author jon edwards
	 */
	public static void notEnough(double custPaid, Scanner kb) {
		double difference = itemPrice - amountPaid;

		System.out.print("\nYou look back at the customer, \"Dude, I need at least $" + round(difference) + " more.");
		System.out.println();
		System.out.println("\nThe customer hands you more money...");
		System.out.print("\nHow much did they give you? >> ");
		double moreMoney = round(kb.nextDouble());
		amountPaid = round(amountPaid + moreMoney);

		checkMoneyGiven(kb);

	}

	/**
	 * Rounding algorithm. Takes a number and rounds it to the one-hundredth
	 * decimal place. This does so by multiplying the number by one-hundred then
	 * finding the decimal portion left over. Once that is finished, number is
	 * cast to an int into the variable intNumber. If the decimal portion is
	 * greater than or equal to 0.5, add one to intNumber, cast intNumber to a
	 * double, and divide by one-hundred to bring it back down to scale, assign
	 * this to number. If the decimal is less than 0.5, then intNumber is cast
	 * as a double and divided by one-hundred to bring it back down to scale and
	 * stored into number.
	 * 
	 * @author jon edwards
	 * @param number
	 * @return returns a rounded double
	 * 
	 */

	public static double round(double number) {
		number *= 100;
		double decimal = number - (int) number;
		int intNumber = (int) number;
		if (decimal >= 0.5) {
			intNumber++;
			number = (double) intNumber / 100.0;
			return number;
		} else {
			number = (double) intNumber / 100.0;
			return number;
		}

	}

	/**
	 * Restarts program using user input and a simple (y/n) switch.
	 * 
	 * @author jon edwards
	 * @param kb
	 */

	public static void restart(Scanner kb) {
		System.out.print("\nThere is another customer in line. Should you help him? (y/n) >> ");
		String askRestart = "ninja";

		askRestart = kb.next().toLowerCase();

		switch (askRestart) {
		case "n":
			System.out.println("\nYou punch the cash register and walk out the store.");
			System.out.println("You can feel the customer's eyes staring...judging...");
			System.out.println("Your boss is yelling at you.");
			System.out.println("\"GET BACK IN HERE!!!\" he screams --");
			System.out.println("But you don't care.");
			System.out.println("YOU QUIT!");
			break;
		case "y":
			System.out.println();
			run();
			break;
		default:
			System.out.println("\nI don't understand...");
			restart(kb);
			break;
		}
	}
	
	/**
	 * Top banner for cash Machine
	 * @author jon edwards
	 */
	public static void cashMachineTop() {
		System.out.println("\n**************************************");
		System.out.println("**************************************");
		System.out.println("    C C       A       SSS   HH   HH   ");
		System.out.println("   CC	     A A      S     HH   HH   ");
		System.out.println("   CC       A   A     SSS   HHHHHHH   ");
		System.out.println("   CC      A A A A      S   HH   HH   ");
		System.out.println("    C C   A       A   SSS   HH   HH   ");
		System.out.println("**************************************");
	}	
	/**
	 * Bottom banner from cash machine. Includes a total row.
	 * @param total total change due
	 */
	public static void cashMachineBottom(double total) {
		System.out.println("**************************************");
		System.out.println(" > Total: $" + total);
		System.out.println("**************************************\n");
	}
}
