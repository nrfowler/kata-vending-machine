import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class VendingTest {
	@Test
	public void addQuartertoBalance() {
		Vending vend = new Vending();
		Coin quarter = new Coin(6d, 1d);
		vend.addCoin(quarter);
		assertEquals(Vending.bigDecimal(.25), vend.getBalance());
		vend = new Vending();
		Coin falseQuarter = new Coin(5.8d, 1.11d);
		vend.addCoin(falseQuarter);
		assertEquals(Vending.bigDecimal(0), vend.getBalance());
	}

	@Test
	public void addNickeltoBalance() {
		Vending vend = new Vending();
		Coin Nickel = new Coin(5d, .8d);
		vend.addCoin(Nickel);
		assertEquals(Vending.bigDecimal(.05), vend.getBalance());
		vend = new Vending();
		vend.addCoin(new Coin(5.2d, .8d));
		assertEquals(Vending.bigDecimal(0), vend.getBalance());
		vend = new Vending();
		vend.addCoin(new Coin(5d, .9d));
		assertEquals(Vending.bigDecimal(0), vend.getBalance());

	}

	@Test
	public void addDimetoBalance() {
		Vending vend = new Vending();
		vend.addCoin(new Coin(2d, .7d));
		assertEquals(Vending.bigDecimal(.1), vend.getBalance());
		vend = new Vending();
		vend.addCoin(new Coin(2.2d, .7d));
		assertEquals(Vending.bigDecimal(0), vend.getBalance());
		vend = new Vending();
		vend.addCoin(new Coin(2d, .8d));
		assertEquals(Vending.bigDecimal(0), vend.getBalance());
		vend = new Vending();
		vend.addCoin(new Coin(2.05d, .75d));
		assertEquals(Vending.bigDecimal(.1), vend.getBalance());

	}

	// If the coin or object is not a quarter, dime, or nickel, do not update
	// the balance,
	// reject the coin, and display a "coin not valid" message
	@SuppressWarnings("deprecation")
	@Test
	public void addPennyorOthertoMachine() {
		// add a penny
		Vending vend = new Vending();
		Coin penny = new Coin(2.5d, .75d);
		Coin reject = vend.addCoin(penny);
		assertEquals(Vending.bigDecimal(0), vend.getBalance());
		assertEquals("COIN NOT VALID", vend.getDisplay());
		assertEquals(penny, reject);
		// add a half dollar
		vend = new Vending();
		Coin halfdollar = new Coin(11d, 1.25d);
		reject = vend.addCoin(halfdollar);
		assertEquals(Vending.bigDecimal(0), vend.getBalance());
		assertEquals("COIN NOT VALID", vend.getDisplay());
		assertEquals(halfdollar, reject);
		// add a valid coin
		vend = new Vending();
		Coin dime = new Coin(2d, .7d);
		reject = vend.addCoin(new Coin(2d, .7d)); // add dime
		assertEquals(Vending.bigDecimal(.1), vend.getBalance());
		assertNotEquals("COIN NOT VALID", vend.getDisplay());
		assertNotEquals(dime, reject);

	}

	@Test
	public void updateDisplayMessage() {
		Vending vend = new Vending();
		assertEquals("INSERT COIN", vend.getDisplay());
		vend.addCoin(new Coin(2d, .7d)); // add dime
		assertEquals("BALANCE: $0.10", vend.getDisplay());
	}

	@Test
	public void sumCoinsAndCheckDisplay() {
		Vending vend = new Vending();
		vend.addCoin(new Coin(2d, .7d)); // add dime
		assertEquals("BALANCE: $0.10", vend.getDisplay());
		vend.addCoin(new Coin(2d, .7d)); // add dime
		assertEquals("BALANCE: $0.20", vend.getDisplay());
		vend.addCoin(new Coin(2d, .7d)); // add dime
		assertEquals("BALANCE: $0.30", vend.getDisplay());
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		assertEquals("BALANCE: $0.55", vend.getDisplay());
		vend.addCoin(new Coin(5d, .8d)); // add nickel
		assertEquals("BALANCE: $0.60", vend.getDisplay());
		vend.addCoin(new Coin(5d, .8d)); // add nickel
		assertEquals("BALANCE: $0.65", vend.getDisplay());
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		assertEquals("BALANCE: $0.90", vend.getDisplay());
	}

	// Display will render messages for a defined length of time and return to
	// default message
	@Test
	public void displayTimeout() throws InterruptedException {
		Vending vend = new Vending();
		vend.addCoin(new Coin(2d, .7d));
		assertEquals("BALANCE: $0.10", vend.getDisplay());
		vend.addCoin(new Coin(0d, 0d));
		assertEquals("COIN NOT VALID", vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("BALANCE: $0.10", vend.getDisplay());

	}

	@Test
	public void makeSelection() throws InterruptedException {
		Vending vend = new Vending();
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		// If sufficient funds for a cola, display THANK YOU and then INSERT
		// COIN. Return a COLA enum from vend.select
		SnackEnum product = vend.select(SnackEnum.COLA);
		assertEquals(SnackEnum.COLA, product);
		assertEquals("THANK YOU", vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("INSERT COIN", vend.getDisplay());
		assertEquals(Vending.bigDecimal(0), vend.getBalance());
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		vend.addCoin(new Coin(2d, .7d)); // add dime
		vend.addCoin(new Coin(5d, .8d)); // add nickel
		// If sufficient funds for a CANDY, display THANK YOU and then INSERT
		// COIN. Return a candy enum from vend.select
		product = vend.select(SnackEnum.CANDY);
		assertEquals(SnackEnum.CANDY, product);
		assertEquals("THANK YOU", vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("INSERT COIN", vend.getDisplay());
		assertEquals(Vending.bigDecimal(0), vend.getBalance());
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		// If sufficient funds for CHIPS, display THANK YOU and then INSERT
		// COIN. Return a COLA enum from vend.select
		product = vend.select(SnackEnum.CHIPS);
		assertEquals(SnackEnum.CHIPS, product);
		assertEquals("THANK YOU", vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("INSERT COIN", vend.getDisplay());
		assertEquals(Vending.bigDecimal(0), vend.getBalance());
		// if insufficient funds, display PRICE X, then after five seconds,
		// INSERT COIN
		product = vend.select(SnackEnum.COLA);
		assertEquals(null, product);
		assertEquals(
				"PRICE " + NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(SnackEnum.COLA.getCost()),
				vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("INSERT COIN", vend.getDisplay());
		// if numProducts[0] is 0, display SOLD OUT , do not return a snack, and
		// then display balance
		vend.numProducts[0] = 0;
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		vend.addCoin(new Coin(6d, 1d)); // add quarter
		product = vend.select(SnackEnum.COLA);
		assertEquals(null, product);
		assertEquals("SOLD OUT", vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("BALANCE: $1.00", vend.getDisplay());
		// if numProducts[0] is 0 and balance is 0, display SOLD OUT, do not
		// return a snack and display INSERT COIN
		vend.setBalance(Vending.bigDecimal(0d));
		product = vend.select(SnackEnum.COLA);
		assertEquals(null, product);
		assertEquals("SOLD OUT", vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("INSERT COIN", vend.getDisplay());

	}

	@Test
	public void returnCoins() {
		ArrayList<Coin> coins = new ArrayList<Coin>(
				Arrays.asList(new Coin(6d, 1d), new Coin(6.05d, 1d), new Coin(6d, 1.05d), new Coin(0d, 0d)));
		Vending vend = new Vending();
		ArrayList<Coin> rejects = vend.addCoinList(coins);
		ArrayList<Coin> expectReject = new ArrayList<Coin>(Arrays.asList(new Coin(0d, 0d)));
		// check that proper coins are rejected
		assertEquals(expectReject, rejects);
		// press return button and check proper coins are returned and displays
		// INSERT COIN
		ArrayList<Coin> returns = vend.returnCoins();
		ArrayList<Coin> expectedReturns = new ArrayList<Coin>(
				Arrays.asList(new Coin(6d, 1d), new Coin(6.05d, 1d), new Coin(6d, 1.05d)));
		assertEquals(expectedReturns, returns);
		assertEquals("INSERT COIN", vend.getDisplay());

	}

	// When a product is selected that costs less than the
	// amount of money in the machine, then the remaining amount is placed in
	// the coin return.
	@Test
	public void getChange() {
		Vending vend = new Vending();
		// add $1.25 and buy a cola, get $.25 back
		vend.addCoinList(new ArrayList<Coin>(Arrays.asList(new Coin(6d, 1d), new Coin(6.05d, 1d), new Coin(6d, 1.05d),
				new Coin(6d, 1.05d), new Coin(6d, 1.05d), new Coin(0d, 0d))));
		vend.select(SnackEnum.COLA);
		// check the value of coins in the change bin
		assertEquals(Vending.bigDecimal(.25d), vend.getChangeBinValue());
		// remove the change from the change bin
		vend.emptyChangeBin();
		// add $1.25 and buy chips, get $.75 back
		vend.addCoinList(new ArrayList<Coin>(Arrays.asList(new Coin(6d, 1d), new Coin(6.05d, 1d), new Coin(6d, 1.05d),
				new Coin(6d, 1.05d), new Coin(6d, 1.05d), new Coin(0d, 0d))));
		vend.select(SnackEnum.CHIPS);
		assertEquals(Vending.bigDecimal(.75d), vend.getChangeBinValue());
		vend.emptyChangeBin();
		// add $0.65 and buy chips, get $.15 back
		vend.addCoinList(new ArrayList<Coin>(Arrays.asList(new Coin(6d, 1d), new Coin(6.05d, 1d), new Coin(5d, .8d),
				new Coin(2d, .7d), new Coin(0d, 0d))));
		vend.select(SnackEnum.CHIPS);
		assertEquals(Vending.bigDecimal(.15), vend.getChangeBinValue().setScale(2, BigDecimal.ROUND_HALF_EVEN));
		vend.emptyChangeBin();
		// add $0.80 and buy chips, get $.30 back
		vend.addCoinList(new ArrayList<Coin>(Arrays.asList(new Coin(6d, 1d), new Coin(6.05d, 1d), new Coin(5d, .8d),
				new Coin(2d, .7d), new Coin(5d, .8d), new Coin(2d, .7d), new Coin(0d, 0d))));
		vend.select(SnackEnum.CHIPS);
		assertEquals(Vending.bigDecimal(.30d), vend.getChangeBinValue());
		vend.emptyChangeBin();
		// add $0.65 and buy candy, get $.00 back
		vend.addCoinList(new ArrayList<Coin>(Arrays.asList(new Coin(5d, .8d), new Coin(5d, .8d), new Coin(5d, .8d),
				new Coin(5d, .8d), new Coin(5d, .8d), new Coin(2d, .7d), new Coin(2d, .7d), new Coin(2d, .7d),
				new Coin(2d, .7d), new Coin(0d, 0d))));
		assertEquals(SnackEnum.CANDY, vend.select(SnackEnum.CANDY));
		assertEquals(Vending.bigDecimal(.00d), vend.getChangeBinValue());
		vend.emptyChangeBin();
		// add $0.60 and buy candy, get $.00 back
		System.out.println("balance is " + vend.balance);
		vend.addCoinList(new ArrayList<Coin>(
				Arrays.asList(new Coin(5d, .8d), new Coin(5d, .8d), new Coin(5d, .8d), new Coin(5d, .8d),
						new Coin(2d, .7d), new Coin(2d, .7d), new Coin(2d, .7d), new Coin(2d, .7d), new Coin(0d, 0d))));
		vend.select(SnackEnum.CANDY);
		assertEquals(Vending.bigDecimal(.00d), vend.getChangeBinValue());
		vend.emptyChangeBin();
	}
}
