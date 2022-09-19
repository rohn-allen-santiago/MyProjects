/* SELF ASSESSMENT 

1. ResolveBet
I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type 
[Mark out of 7: 7].
Comment: I correctly defined ResolveBet and it takes the bet type and the wallet object, and returns nothing
My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet 
[Mark out of 8: 8].
Comment: The amount in the wallet is presented and it asks the user how much they are willing to bet
My program ensures the bet amount is not greater than the cash in the wallet 
[Mark out of 5: 5].
Comment: It ensures the bet amount is not greater than the cash in the wallet
My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned 
[Mark out of 15: 15]..
Comment: It creates three dice, rolls them and creates a variable which stores the sum.
My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet 
[Mark out of 20: 20].
Comment: It determines the winnings correctly
My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses 
[Mark out of 10: 10].
Comment: The results are outputted and the winnings or losses are correctly handled

2. Main
I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it 
[Mark out of 15: 15]
Comment: The user inputs their cash and a wallet is created with that cash
My program loops continuously until the user either enters quit or the cash in the wallet is 0 
[Mark out of 5: 5]
Comment: It loops continuously until the user enters quit or until they run out of money
I ask the user to enter any of the four bet types or quit 
[Mark out of 5: 5].
Comment: I give the bet options and the option to type quit
My program calls resolveBet for each bet type entered 
[Mark out of 5: 5].
Comment: ResolveBet is called for each bet type
At the end of the game my program presents a summary message regarding winnings and losses 
[Mark out of 5: 5]
Comment: A summary message is presented which shows how much was lost or gained

 Total Mark out of 100 (Add all the previous marks):100
*/

import java.util.Scanner;
import java.util.ArrayList;

public class ChuckALuck {
	static Scanner input = new Scanner(System.in);
	final static String BET_TRIPLE = "Triple";
	final static String BET_FIELD = "Field";
	final static String BET_HIGH = "High";
	final static String BET_LOW = "Low";
	final static int TRIPLE_ODDS = 30;
	
	public static void ResolveBet(String betType, Wallet wallet) {
		System.out.println("There is currently €" + wallet.check() + " left in your wallet.");
		boolean running = true;
		boolean win = false;;
		double bet = 0;
		while(running) {
			System.out.print("How much would you like to bet? ");
			if(input.hasNextDouble()) {
				bet = input.nextDouble();
				if(bet < 0 || bet > wallet.check()) {
					System.out.println("That is not valid. Try again.");
					input.nextLine();
				}
				else
					running = false;
			}
			else {
				System.out.println("That is not valid. Try again.");
				input.nextLine();
			}
		}
		ArrayList<Dice> myDice = new ArrayList<Dice>();
		int sum = 0;
		for(int count = 0; count < 3; count++) {
			myDice.add(new Dice());
			sum += myDice.get(count).roll();
		}
		if(betType == BET_TRIPLE) {
			if(myDice.get(0).topFace() == myDice.get(1).topFace() && 
					myDice.get(0).topFace() == myDice.get(2).topFace()) {
					if(myDice.get(0).topFace() != 1 && myDice.get(0).topFace() != 6) {
						win = true;
					}
			}
		}
		else if(betType == BET_FIELD) {
			if(sum < 8 || sum > 12)
				win = true;
		}
		else if(betType == BET_HIGH) {
			if(sum <= 10) {
				
			}
			else {
				if(myDice.get(0).topFace() == myDice.get(1).topFace() && 
					myDice.get(0).topFace() == myDice.get(2).topFace()) {
					if(myDice.get(0).topFace() < 4) {
						win = true;
					}
				}
				else {
					win = true;
				}
			}
		}
		else if(betType == BET_LOW) {
			if(sum >= 11) {
				
			}
			else {
				if(myDice.get(0).topFace() == myDice.get(1).topFace() && 
					myDice.get(0).topFace() == myDice.get(2).topFace()) {
					if(myDice.get(0).topFace() > 3) {
						win = true;
					}
				}
				else {
					win = true;
				}
			}
		}
		System.out.println("The roll was " + myDice.get(0).topFace() + " + " + myDice.get(1).topFace() + " + " + myDice.get(2).topFace() + " = " + sum);
		if(win) {
			System.out.println("You win!");
			if(betType == BET_TRIPLE) 
				wallet.put(bet*TRIPLE_ODDS);
			else
				wallet.put(bet);
		}
		else {
			System.out.println("You lose.");
			wallet.get(bet);
		}
	}

	public static void main(String[] args) {
		double money = 0;
		boolean running = true;
		while(running) {
			System.out.print("How much cash do you have? ");
			if(input.hasNextDouble()) {
				money = input.nextDouble();
				if(money <= 0) {
					System.out.println("That is not valid. Try again.");
				}
				else
					running = false;
			}
			else {
				System.out.println("That is not valid. Try again.");
				input.nextLine();
			}
		}
		Wallet userWallet = new Wallet();
		userWallet.put(money);
		running = true;
		while(running) {
			if(userWallet.check() > 0) {
				System.out.print("What bet would you like to make (Triple/Field/High/Low) (Or 'Quit' to stop playing)? ");
				String userBet = input.next();
				boolean isTriple = userBet.equalsIgnoreCase(BET_TRIPLE);
				boolean isField = userBet.equalsIgnoreCase(BET_FIELD);
				boolean isHigh = userBet.equalsIgnoreCase(BET_HIGH);
				boolean isLow = userBet.equalsIgnoreCase(BET_LOW);
				boolean isQuit = userBet.equalsIgnoreCase("Quit");
				
				if(isQuit) {
					System.out.println("The game has ended");
					running = false;
				}
				else if(isTriple)
					ResolveBet(BET_TRIPLE, userWallet);
				else if(isField)
					ResolveBet(BET_FIELD, userWallet);
				else if(isHigh)
					ResolveBet(BET_HIGH, userWallet);
				else if(isLow)
					ResolveBet(BET_LOW, userWallet);
				else
					System.out.println("That is not a valid bet. Please try again.");
			}
			else {
				System.out.println("You have run out of money.");
				running = false;
			}		
		}
		double difference = userWallet.check() - money;
		if(difference > 0)
			System.out.println("You gained €" + difference + ".");
		else if(difference < 0)
			System.out.println("You lost €" + (difference * -1) + ".");
		else
			System.out.println("You gained nothing.");
			
	}
}
