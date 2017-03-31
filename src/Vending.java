import java.text.NumberFormat;
import java.util.Locale;

public class Vending {
	
	String display;
	
	Double balance;
	
	String action;
	
	SnackEnum selection;
	
	int[] numProducts;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDisplay() {
		// TODO Auto-generated method stub
		return display;
	}
	public void updateDisplaywithBalance(){
		display = "BALANCE: "+NumberFormat.getCurrencyInstance(new Locale("en", "US"))
		        .format(this.getBalance());
	}
	//Display msg for 4 seconds, then return the display to previous message
	public void timeoutDisplay(String msg, String msg2){
		if (msg2 == null)
				msg2 = display;
		display = msg;
		Thread thread = new Thread (new TimeoutDisplay(this, msg2));
		thread.start();
	}
	public Double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}
	
	public Vending(){
		balance = 0d;
		display = "INSERT COIN";
		numProducts = new int[3];
		numProducts[0] = 10;
		numProducts[1] = 10;
		numProducts[2] = 10;
	}

	public void addCoin(Double weight, Double diameter) {
		if (weight> 5.9d && weight < 6.1d && diameter > .9d && diameter < 1.1d)
			{
			balance += .25;
			updateDisplaywithBalance();
			}
		else if (weight> 4.9d && weight < 5.1d && diameter > .7d && diameter < .9d)
			{
			balance += .05;
			updateDisplaywithBalance();
		}
		else if (weight> 1.9d && weight < 2.1d && diameter > .6d && diameter < .8d)
			{
			balance += .1;
			updateDisplaywithBalance();
		}
		else{
			timeoutDisplay("COIN NOT VALID", null);
			action = "REJECT";
		}
	}

	public SnackEnum select(SnackEnum snack) {
		SnackEnum returnedSnack = null;
		if(numProducts[snack.ordinal()] >0){
			if(snack.getCost() <= balance){
			numProducts[snack.ordinal()]--;
			returnedSnack = snack;
			balance = (double) 0;
			timeoutDisplay("THANK YOU", "INSERT COIN");
			}
			else{
				timeoutDisplay("PRICE "+NumberFormat.getCurrencyInstance(new Locale("en", "US"))
		        .format(snack.getCost()), "INSERT COIN");

			}
		}
		else{
			if(getBalance()>0)
				timeoutDisplay("SOLD OUT", null);
			else
				timeoutDisplay("SOLD OUT","INSERT COIN");
		}
		return returnedSnack;
	}

	public void setBalance(double d) {
		balance = d;
		
	}


}
