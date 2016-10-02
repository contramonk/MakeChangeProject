package cash_register;

import java.util.*;

public class MakeChange {
	public static double itemPrice;
	public static double amountPaid;

	public static void main(String[] args) {
		run();
	}

	public static void run() {
		Scanner kb = new Scanner(System.in);

		// Story 1: Prompt user asking for item price
		askHowMuch(kb);
		// Story 2: Prompt user to ask how much customer gave
		askMoneyGiven(kb);
		// Story 3: Tell user if too little given, or exact amount
		checkMoneyGiven();
		// Story 4: Display change due using largest denoms possible
		giveChangeDue();

		restart(kb);

	}

	public static void askHowMuch(Scanner kb) {
		System.out.print("How much money is the item? ");
		itemPrice = kb.nextDouble();
		System.out.println(itemPrice);
	}

	public static void askMoneyGiven(Scanner kb) {
		System.out.print("How much money did the customer give? ");
		amountPaid = kb.nextDouble();
		System.out.println(amountPaid);
	}

	public static void checkMoneyGiven() {
		if (amountPaid < itemPrice) {
			System.out.println("The customer did not give you enough");
		} else if (amountPaid == itemPrice) {
			System.out.println("No change due.");
		} else {
			System.out.println("Calculating change");
		}
	}

	public static void giveChangeDue() {
		double change = amountPaid - itemPrice;
		//change = round(change);
		System.out.println(round(change));
		double[] denominations = { 100, 50, 20, 10, 5, 1, 0.5, 0.25, 0.10, 0.05, 0.01 };
		for (int i = 0; i < denominations.length; i++) {

			if (round(denominations[i]) <= round(change)) {
				System.out.println(denominations[i]);
				round(change -= denominations[i]);

				i--;
			}

		}

	}

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

	public static void restart(Scanner kb) {
		System.out.println("Would you look to restart (t/f): ");
		String askRestart = kb.next();
		if (askRestart.equals("t")) {
			run();
		} else {
			askRestart = "f";
		}
	}

}
