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

	public Coin addCoin(Coin coin){
		if (coin.getWeight()> 5.9d && coin.getWeight() < 6.1d && coin.getDiameter() > .9d && coin.getDiameter() < 1.1d)
		{
			balance += .25;
			updateDisplaywithBalance();
			coins.add(coin);
			return null;
		}
		else if (coin.getWeight()> 4.9d && coin.getWeight() < 5.1d && coin.getDiameter() > .7d && coin.getDiameter() < .9d)
		{
			balance += .05;
			updateDisplaywithBalance();
			coins.add(coin);
			
			return null;
		}
		else if (coin.getWeight()> 1.9d && coin.getWeight() < 2.1d && coin.getDiameter() > .6d && coin.getDiameter() < .8d)
		{
			balance += .1;
			updateDisplaywithBalance();
			coins.add(coin);
			
			return null;
		}
		else{
			timeoutDisplay("COIN NOT VALID", null);
			
			return coin;
			
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
