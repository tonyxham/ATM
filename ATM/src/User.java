import java.util.Scanner;

public class User {
	
	public int PIN;
	public int checkings_number;
	public int savings_number;
	public BankAcct bankacct;
	
	public User(int PIN, int checkings_number, double balance) {
		this.PIN = PIN;
		this.checkings_number = checkings_number;
		this.bankacct = new BankAcct(balance);
	}
	
	public void changePIN() {
		
        Scanner userInputScanner = new Scanner(System.in);

        // Testing nextLine();
        System.out.println("\n Enter your new PIN");
        String newPin = userInputScanner.next();
        while (newPin.length() != 4 || newPin.charAt(0) == '0') {
            System.out.println("\n Pin must be a 4 digit number and not begin with 0. Try again");
            newPin = userInputScanner.next();
        }
        

        try {
        	this.PIN = Integer.parseInt(newPin);
        } catch (Exception e) {
            System.out.println("\n Pin must be a number. Enter a 4 digit number.");
            newPin = userInputScanner.next();
            this.PIN = Integer.parseInt(newPin);

        }
        System.out.println("Congrats! Your new pin is " + newPin);
	}
}
