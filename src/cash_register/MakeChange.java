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
	 * @author Jon Edwards
	 */

	public static void run() {
		Scanner kb = new Scanner(System.in);

		// Story 1: Prompt user asking for item price
		askHowMuch(kb);
		// Story 2: Prompt user to ask how much customer gave
		askMoneyGiven(kb);
		// Story 3: Tell user if too little given, or exact amount
		checkMoneyGiven(kb);
		// Story 4: Display change due using largest denoms possible
		giveChangeDue();

		restart(kb);

	}

	/**
	 * Asks the user how much the item costs, and receives input.
	 * 
	 * @author Jon Edwards
	 * @param kb
	 *            gets the item price.
	 */

	public static void askHowMuch(Scanner kb) {
		System.out.print("The customer tells you how much the item cost. How much was it? >> ");
		itemPrice = kb.nextDouble();
		System.out.println(itemPrice);
	}

	/**
	 * Asks the user how much money was given. Then takes input from user.
	 * 
	 * @param kb
	 *            gets the amount given.
	 * @author Jon Edwards
	 */

	public static void askMoneyGiven(Scanner kb) {
		System.out.print("The customer hands you money. How much did he give you? >> ");
		amountPaid = kb.nextDouble();
		System.out.println(amountPaid);
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
			System.out.println("The customer short changed you...");
			notEnough(amountPaid, kb);
		} else if (amountPaid == itemPrice) {
			System.out.println("No change due...");
			System.out.println("The customer walks out the door with his item...");
			restart(kb);
		}
	}

	/**
	 * This method calculates change. First the amount of change is calculated
	 * by subtracting amountPaid from itemPrice, then it prints the rounded
	 * amount of change due. Then an algorithm is used to loop through an ordered
	 * array high to low of bill denominations. If denomination > change, the
	 * denomination is not printed, otherwise it is printed. If the denomination
	 * prints denomination is subtracted from change and the i value is
	 * decremented and checks once again whether that same denomination value is
	 * greater than change.
	 * 
	 * @author Jon Edwards
	 */

	public static void giveChangeDue() {
		System.out.println("...Calculating change...");
		double change = amountPaid - itemPrice;
		System.out.println("You tell them $" + round(change) + " is due back.");
		double[] denominations = { 100, 50, 20, 10, 5, 1, 0.5, 0.25, 0.10, 0.05, 0.01 };
		String[] denominationNames = {"Hundred", "Fifty", "Twenty", "Ten", "Five", "One", "Fifty-cent", "Quarter", "Dime", "Nickel", "Penny"};
		for (int i = 0; i < denominations.length; i++) {

			if (round(denominations[i]) <= round(change)) {
				System.out.println(denominations[i] + "\t" + denominationNames[i]);
				round(change -= denominations[i]);

				i--;
			}

		}

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
	 * @author Jon Edwards
	 */
	public static void notEnough(double custPaid, Scanner kb) {
		double difference = itemPrice - amountPaid;

		System.out.print("They better give you at least $" + round(difference));
		System.out.println("more moneys, that isn't enough! >> ");
		System.out.println();
		System.out.println("The customer hands you more money...");
		System.out.println("How much did they give you? >> ");
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
	 * @author Jon Edwards
	 * @param kb
	 */

	public static void restart(Scanner kb) {
		System.out.println("There is another customer in line. Should you help him? (y/n): ");
		String askRestart = "ninja";

		askRestart = kb.next().toLowerCase();

		switch (askRestart) {
		case "n":
			System.out.println("Thank you, come again!");
			break;
		case "y":
			run();
			break;
		default:
			System.out.println("I don't understand...");
			restart(kb);
			break;
		}
	}

}
