import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Vending {
	
	String display;
	
	Double balance;
	
	SnackEnum selection;
	
	int[] numProducts;

	//coins that the user has deposited that can be returned
	ArrayList<Coin> coins;
	
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
		coins = new ArrayList<Coin>();

		numProducts[0] = 10;
		numProducts[1] = 10;
		numProducts[2] = 10;
	}

	public Coin addCoin(Double weight, Double diameter) {
		if (weight> 5.9d && weight < 6.1d && diameter > .9d && diameter < 1.1d)
			{
			balance += .25;
			updateDisplaywithBalance();
			coins.add(new Coin(weight,diameter));
			return null;
			}
		else if (weight> 4.9d && weight < 5.1d && diameter > .7d && diameter < .9d)
			{
			balance += .05;
			updateDisplaywithBalance();
			coins.add(new Coin(weight,diameter));

			return null;
		}
		else if (weight> 1.9d && weight < 2.1d && diameter > .6d && diameter < .8d)
			{
			balance += .1;
			updateDisplaywithBalance();
			coins.add(new Coin(weight,diameter));

			return null;
		}
		else{
			timeoutDisplay("COIN NOT VALID", null);
			Coin rejected = new Coin(weight,diameter);
			return rejected;
			
		}
		
	}

	public Coin addCoin(Coin coin){
		return addCoin(coin.getWeight(),coin.getDiameter());
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
	public ArrayList<Coin> addCoinList(ArrayList<Coin> coinList){
		ArrayList<Coin> rejects = new ArrayList<Coin>();
		int rejectnum = 0;
		for(int i = 0; i < coinList.size(); i++){
			Coin reject = addCoin(coinList.get(i) );
			if (reject !=null)
				rejects.add(reject);
		}
		return rejects;
	}
	public ArrayList<Coin> returnCoins() {
		// TODO Auto-generated method stub
		ArrayList<Coin> returns = coins;
		coins = null;
		display = "INSERT COIN";
		return returns;
	}

}
