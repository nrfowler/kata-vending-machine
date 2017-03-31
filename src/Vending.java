import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Vending {

	String display;

	BigDecimal balance;

	SnackEnum selection;

	int[] numProducts;

	// coins that the user has deposited that can be returned
	ArrayList<Coin> coins;

	// coins that are currently in the change bin
	ArrayList<Coin> changeBin;

	public String getDisplay() {
		// TODO Auto-generated method stub
		return display;
	}

	public void updateDisplaywithBalance() {
		display = "BALANCE: " + NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(this.getBalance());
	}

	// Display msg for 4 seconds, then return the display to previous message
	public void timeoutDisplay(String msg, String msg2) {
		if (msg2 == null)
			msg2 = display;
		display = msg;
		Thread thread = new Thread(new TimeoutDisplay(this, msg2));
		thread.start();
	}

	public BigDecimal getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}

	public Vending() {
		balance = bigDecimal(0d);
		display = "INSERT COIN";
		numProducts = new int[3];
		coins = new ArrayList<Coin>();
		changeBin = new ArrayList<Coin>();
		numProducts[0] = 10;
		numProducts[1] = 10;
		numProducts[2] = 10;
	}

	public Coin addCoin(Coin coin) {
		if (coin.getWeight() > 5.9d && coin.getWeight() < 6.1d && coin.getDiameter() > .9d
				&& coin.getDiameter() < 1.1d) {
			balance = balance.add( bigDecimal(.25));
			updateDisplaywithBalance();
			coin.setValue(bigDecimal(.25));
			coins.add(coin);
			return null;
		} else if (coin.getWeight() > 4.9d && coin.getWeight() < 5.1d && coin.getDiameter() > .7d
				&& coin.getDiameter() < .9d) {
			balance = balance.add( bigDecimal(.05));
			updateDisplaywithBalance();
			coin.setValue(bigDecimal(.05));
			coins.add(coin);

			return null;
		} else if (coin.getWeight() > 1.9d && coin.getWeight() < 2.1d && coin.getDiameter() > .6d
				&& coin.getDiameter() < .8d) {
			balance = balance.add( bigDecimal(.10));
			updateDisplaywithBalance();
			coin.setValue(bigDecimal(.1));
			coins.add(coin);

			return null;
		} else {
			timeoutDisplay("COIN NOT VALID", null);

			return coin;

		}

	}

	public SnackEnum select(SnackEnum snack) {
		SnackEnum returnedSnack = null;
		if (numProducts[snack.ordinal()] > 0) {
			System.out.println("comparing"+snack.getCost()+ " to "+balance);
			if (snack.getCost().compareTo(balance) <= 0) {
				numProducts[snack.ordinal()]--;
				returnedSnack = snack;
				makeChange(snack.getCost());
				timeoutDisplay("THANK YOU", "INSERT COIN");
			} else {
				timeoutDisplay(
						"PRICE " + NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(snack.getCost()),
						"INSERT COIN");

			}
		} else {
			if (getBalance().compareTo(bigDecimal(0)) > 0)
				timeoutDisplay("SOLD OUT", null);
			else
				timeoutDisplay("SOLD OUT", "INSERT COIN");
		}
		return returnedSnack;
	}

	public void makeChange(BigDecimal cost) {
		Collections.sort(coins);
		BigDecimal value = bigDecimal(0);
		int i = 0;
		do {
			value = BigDecimal.valueOf(0d);
			for (Coin coin : coins) {
				value=value.add(coin.getValue());
			}
			if (value.subtract(coins.get(i).getValue()).compareTo(cost)>= 0) {
				changeBin.add(coins.remove(i));
			} else
				i++;
		} while (cost.compareTo(value) < 0 && i < coins.size());
		balance = bigDecimal( 0d);
		coins = new ArrayList<Coin>();
	}

	public BigDecimal getChangeBinValue() {
		BigDecimal value = bigDecimal(.00);

		for (Coin coin : changeBin) {
			value = value.add(coin.getValue());
		}
		return value;
	}

	public void emptyChangeBin() {
		changeBin = new ArrayList<Coin>();
	}

	public void setBalance(BigDecimal d) {
		balance = d;

	}

	public ArrayList<Coin> addCoinList(ArrayList<Coin> coinList) {
		ArrayList<Coin> rejects = new ArrayList<Coin>();
		int rejectnum = 0;
		for (int i = 0; i < coinList.size(); i++) {
			Coin reject = addCoin(coinList.get(i));
			if (reject != null)
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

	public static BigDecimal bigDecimal(Double d) {
		// TODO Auto-generated method stub
		return new BigDecimal(d).setScale(2,BigDecimal.ROUND_HALF_EVEN);
	}
	public static BigDecimal bigDecimal(Integer d) {
		// TODO Auto-generated method stub
		return new BigDecimal(d).setScale(2,BigDecimal.ROUND_HALF_EVEN);
	}

}
