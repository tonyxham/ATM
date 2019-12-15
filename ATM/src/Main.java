import java.util.Scanner;
import java.io.BufferedReader; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main implements ATM_Interface{
	static Main main;
	User user;
	static int bankaccount;
	static String pin;
	static double balance;
	public void start() throws FileNotFoundException {
	    System.out.println("Press 1 to check your balance \nPress 2 to deposit \nPress 3 to withdraw \nPress 4 to change pin \nPress 5 to exit");
	    
		Scanner userInputScanner = new Scanner(System.in);
		int menu = userInputScanner.nextInt();
		if (menu == 1) {
			
			main.getBalance();
		}
		else if (menu == 2) {
			
			main.deposit();
		}
		else if (menu == 3) {
					
			main.withdraw();
		}
		else if (menu == 4) {
			
			main.changePin();
		}
		else if (menu == 5) {
			
			main.exit();
		}
		else {
			System.out.println("You must enter the numbers 1,2,3,4, or 5.");
			main.start();
		}
	}

	public void login() throws FileNotFoundException {
	    Scanner userInputScanner = new Scanner(System.in);
		 try {     
			 	System.out.println("\n Please enter your bank account number");
		        bankaccount = userInputScanner.nextInt();
		        if (bankaccount < 100000000 || bankaccount > 999999999) {
		        	System.out.println("You must enter a 9 digit account number. It cannot begin with 0");
		        	main.login();
		        }
		        System.out.println("\n Please enter your PIN number for the account");
		        pin = userInputScanner.next();
		        if (Double.parseDouble(pin) < 1000 || Double.parseDouble(pin) > 9999) {
		        	System.out.println("You must enter a 4 digit pin");
		        	main.login();
		        }
		        //success or fail
	            FileReader fileReader = new FileReader("C:/Users/sesa544794/eclipse-workspace/ATM/src/accounts.txt");     
	            BufferedReader bufferedreader = new BufferedReader(fileReader);           
	            StringBuffer sb = new StringBuffer();
	            String strLine;            
	           while ((strLine = bufferedreader.readLine()) != null) {
	                sb.append(strLine + " ");
	                sb.append("\n");        
	            }         
	           fileReader.close();
	           String[] x = sb.toString().split(" ");
	           char[] chars = ("" + bankaccount).toCharArray();
	           for (int i = 0; i < x.length; i = i + 2) {
	        	   if (x[i].equals(String.valueOf(bankaccount))) {
	        		   char end = x[i+1].charAt(x[i+1].length() - 1);
	        		   /*if (end == ' ') {
	        			   System.out.println("yes");
	        			   x[i+1] = x[i+1].substring(0, x[i+1].length() - 1);
	        			   System.out.println(x[i+1]);
	        		   }
	        		   */
	        		   if (x[i+1].equals(pin)) {
	        			   System.out.println("Login Successful!");
	        			   balance = Double.parseDouble(x[i+2]);
	        			   user = new User(Integer.parseInt(pin), bankaccount, Double.parseDouble(x[i+2]));
	        			   main.start();
	        		   }
		        	   else {
		        		   System.out.println("Invalid pin. Please try again");
		        		   main.login();
		        	   }
	        	   }
	        	   else {
	        		   System.out.println("Invalid bank account. Please try again");
	        		   main.login();
	        	   }
	           }
	           
	         } catch (IOException e) {
	            e.printStackTrace();     
	   }
        
	}
	public void changePin() throws FileNotFoundException {
		user.changePIN();
		pin = String.valueOf(user.PIN);
		main.start();
		
	}
	public void getBalance() throws FileNotFoundException {
		
		System.out.println("\nYour balance is " + user.bankacct.checkBalance());

		main.start();
	}
	public void deposit() throws FileNotFoundException {
		Scanner userInputScanner = new Scanner(System.in);
		try {
			System.out.println("\n Enter your deposit amount");
	        double deposit = userInputScanner.nextDouble();  
	        user.bankacct.balance = user.bankacct.balance + deposit;
	        System.out.println("Deposit of $" + deposit + " is successful");
	        main.getBalance();
	        main.start();
		} catch (NumberFormatException e) {
			
			System.out.println("\n Enter your deposit amount");
	        double deposit = userInputScanner.nextDouble();  
	        System.out.println("Deposit of $" + deposit + " is successful");
		}
	}
	public void withdraw() throws FileNotFoundException {
		Scanner userInputScanner = new Scanner(System.in);
		try {
			System.out.println("\n Enter your withdraw amount");
	        double withdraw = userInputScanner.nextDouble();  
	        while (withdraw > user.bankacct.balance) {
				System.out.println("\n Enter a withdraw amount that is less or equal to your balance");
		        withdraw = userInputScanner.nextDouble();  
	        }
	        user.bankacct.balance = user.bankacct.balance - withdraw;
	        balance = user.bankacct.balance;
	        System.out.println("Withdraw of $" + withdraw + " is successful");
	        
	        main.getBalance();
	        main.start();
		} catch (NumberFormatException e) {
			
			System.out.println("\n Enter your withdraw amount");
	        double withdraw = userInputScanner.nextDouble();  
	        System.out.println("Withdraw of $" + withdraw + " is successful");
		}
	}
	public void exit() throws FileNotFoundException {
		
		System.out.println("Thank you for using the ATM!");
		File file = new File("C:/Users/sesa544794/eclipse-workspace/ATM/src/accounts.txt");
		try {
			file = new File("C:/Users/sesa544794/eclipse-workspace/ATM/src/accounts.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter writer = new PrintWriter(file);
		writer.print(bankaccount + " " + pin + " " + balance);
		writer.close();
		main.login();
	}
	
	public void initialize() throws FileNotFoundException {
		Scanner userInputScanner = new Scanner(System.in);
		System.out.println("Enter an account number for your account");
		try {
		bankaccount = userInputScanner.nextInt();
	        if (bankaccount < 100000000 || bankaccount > 999999999) {
	        	System.out.println("You must enter a 9 digit account number. It cannot begin with 0");
	        }
		} catch (NumberFormatException e) {
			System.out.println("Must be a 9 digit number not beginning with 0");
			main.initialize();
		}
		 System.out.println("\n Please enter your PIN number for the account");
		 try {
	         pin = userInputScanner.next();
	         if (Double.parseDouble(pin) < 1000 || Double.parseDouble(pin) > 9999) {
	        	System.out.println("You must enter a 4 digit pin. It must not start with a 0");
	        	main.login();
	         }
		} catch (NumberFormatException e) {
			System.out.println("Must be a 4 digit number that doesnt start with 0");
			main.initialize();
		}
		 System.out.println("Account created!");
		 File file = new File("C:/Users/sesa544794/eclipse-workspace/ATM/src/accounts.txt");
		 try {
			file = new File("C:/Users/sesa544794/eclipse-workspace/ATM/src/accounts.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 PrintWriter writer = new PrintWriter(file);
		 writer.print(bankaccount + " " + pin + " " + 0);
		 writer.close();
		 main.login();
	}
	public static void main(String[] args)  throws FileNotFoundException{
        main = new Main();
        StringBuffer sb = new StringBuffer();
        sb.append("");
        try {
	        FileReader fileReader = new FileReader("C:/Users/sesa544794/eclipse-workspace/ATM/src/accounts.txt");     
	        BufferedReader bufferedreader = new BufferedReader(fileReader);           
	        sb = new StringBuffer();
	        String strLine;
	        while ((strLine = bufferedreader.readLine()) != null) {
	            sb.append(strLine + " ");
	            sb.append("\n");        
	        }   
        } catch (Exception e) {
        	
        }
    	System.out.print(sb);
    	String x = sb.toString();
        if (x.equals("")) {

    		main.initialize();
        }
        else {
        	main.login();
        }

	}
}
