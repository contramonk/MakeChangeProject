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
		checkMoneyGiven(kb);
		// Story 4: Display change due using largest denoms possible
		giveChangeDue();

		restart(kb);

	}

	public static void askHowMuch(Scanner kb) {
		System.out.print("The customer tells you how much the item cost. How much was it? >> ");
		itemPrice = kb.nextDouble();
		System.out.println(itemPrice);
	}

	public static void askMoneyGiven(Scanner kb) {
		System.out.print("The customer hands you money. How much did he give you? >> ");
		amountPaid = kb.nextDouble();
		System.out.println(amountPaid);
	}

	public static void checkMoneyGiven(Scanner kb) {
		if (amountPaid < itemPrice) {
			System.out.println("The customer short changed you...");
			notEnough(amountPaid, kb);
		} else if (amountPaid == itemPrice) {
			System.out.println("No change due...");
			System.out.println("The customer walks out the door with his item...");
			restart(kb);
		} else {
			System.out.println("...Calculating change...");
		}
	}

	public static void giveChangeDue() {
		double change = amountPaid - itemPrice;
		System.out.println("You tell them $" + round(change) + " is due back.");
		double[] denominations = { 100, 50, 20, 10, 5, 1, 0.5, 0.25, 0.10, 0.05, 0.01 };
		for (int i = 0; i < denominations.length; i++) {

			if (round(denominations[i]) <= round(change)) {
				System.out.println(denominations[i]);
				round(change -= denominations[i]);

				i--;
			}

		}

	}

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
				default : 
					System.out.println("I don't understand...");
					restart(kb);
					break;
			}
	}

}
