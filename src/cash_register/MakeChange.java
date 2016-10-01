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
		
	}
}
