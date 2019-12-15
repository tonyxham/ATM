import java.io.FileNotFoundException;

interface ATM_Interface {

	
	public void start() throws FileNotFoundException;
	public void login() throws FileNotFoundException;
	public void changePin() throws FileNotFoundException;
	public void getBalance() throws FileNotFoundException;
	public void deposit() throws FileNotFoundException;
	public void withdraw() throws FileNotFoundException;
	public void exit() throws FileNotFoundException;
	public void initialize() throws FileNotFoundException;
	
		
}
