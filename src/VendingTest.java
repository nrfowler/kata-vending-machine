import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
public class VendingTest {
	@Test
	public void addQuartertoBalance(){
		Vending vend = new Vending();
		vend.addCoin(6d,1d);
		assertEquals(Double.valueOf(.25),vend.getBalance());
		vend = new Vending();
		vend.addCoin(5.8d,1.11d);
		assertEquals(Double.valueOf(0),vend.getBalance());
	}
	@Test
	public void addNickeltoBalance(){
		Vending vend = new Vending();
		vend.addCoin(5d,.8d);
		assertEquals(Double.valueOf(.05),vend.getBalance());
		vend = new Vending();
		vend.addCoin(5.2d,.8d);
		assertEquals(Double.valueOf(0),vend.getBalance());
		vend = new Vending();
		vend.addCoin(5d,.9d);
		assertEquals(Double.valueOf(0),vend.getBalance());
		
	}
	@Test
	public void addDimetoBalance(){
		Vending vend = new Vending();
		vend.addCoin(2d,.7d);
		assertEquals(Double.valueOf(.1),vend.getBalance());
		vend = new Vending();
		vend.addCoin(2.2d,.7d);
		assertEquals(Double.valueOf(0),vend.getBalance());
		vend = new Vending();
		vend.addCoin(2d,.8d);
		assertEquals(Double.valueOf(0),vend.getBalance());
		vend = new Vending();
		vend.addCoin(2.05d,.75d);
		assertEquals(Double.valueOf(.1),vend.getBalance());
		
	}
	//If the coin or object is not a quarter, dime, or nickel, do not update the balance, 
	//reject the coin, and display a "coin not valid" message
	@SuppressWarnings("deprecation")
	@Test
	public void addPennyorOthertoMachine(){
		//add a penny
		Vending vend = new Vending();
		double[] penny = {2.5d,.75d};
		double[] reject = vend.addCoin(2.5d,.75d);
		assertEquals(Double.valueOf(0),vend.getBalance());
		assertEquals("COIN NOT VALID", vend.getDisplay());
		if(reject!=null){	
			assertEquals(Double.valueOf(penny[0]),Double.valueOf(reject[0]));
			assertEquals(Double.valueOf(penny[1]),Double.valueOf(reject[1]));
		}
		//add a half dollar
		vend = new Vending();
		double[] halfdollar = {11d,1.25d};
		reject = vend.addCoin(11d,1.25d);
		assertEquals(Double.valueOf(0),vend.getBalance());
		assertEquals("COIN NOT VALID", vend.getDisplay());
		if(reject!=null){	
			assertEquals(Double.valueOf(halfdollar[0]),Double.valueOf(reject[0]));
			assertEquals(Double.valueOf(halfdollar[1]),Double.valueOf(reject[1]));		
		}
		//add a valid coin
		vend = new Vending();
		double[] dime = {2d,.7d};
		reject = vend.addCoin(2d, .7d); //add dime
		assertEquals(Double.valueOf(.1),vend.getBalance());
		assertNotEquals("COIN NOT VALID", vend.getDisplay());
		if(reject!=null){			
			assertEquals(Double.valueOf(dime[0]),Double.valueOf(reject[0]));
			assertEquals(Double.valueOf(dime[1]),Double.valueOf(reject[1]));
		}

	}
	@Test
	public void updateDisplayMessage(){
		Vending vend = new Vending();
		assertEquals("INSERT COIN", vend.getDisplay());
		vend.addCoin(2d, .7d); //add dime
		assertEquals("BALANCE: $0.10", vend.getDisplay());
	}
	@Test
	public void sumCoinsAndCheckDisplay(){
		Vending vend = new Vending();
		vend.addCoin(2d, .7d); //add dime
		assertEquals("BALANCE: $0.10", vend.getDisplay());
		vend.addCoin(2d, .7d); //add dime
		assertEquals("BALANCE: $0.20", vend.getDisplay());
		vend.addCoin(2d, .7d); //add dime
		assertEquals("BALANCE: $0.30", vend.getDisplay());
		vend.addCoin(6d,1d); //add quarter
		assertEquals("BALANCE: $0.55", vend.getDisplay());
		vend.addCoin(5d,.8d); //add nickel
		assertEquals("BALANCE: $0.60", vend.getDisplay());
		vend.addCoin(5d,.8d); //add nickel
		assertEquals("BALANCE: $0.65", vend.getDisplay());
		vend.addCoin(6d,1d); //add quarter
		assertEquals("BALANCE: $0.90", vend.getDisplay());
	}
	//Display will render messages for a defined length of time and return to default message
	@Test
	public void displayTimeout() throws InterruptedException{
		Vending vend = new Vending();
		vend.addCoin(2d, .7d);
		assertEquals("BALANCE: $0.10", vend.getDisplay());
		vend.addCoin(0d,0d);
		assertEquals("COIN NOT VALID", vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("BALANCE: $0.10", vend.getDisplay());

		
	}
	@Test
	public void makeSelection() throws InterruptedException{
		Vending vend = new Vending();
		vend.addCoin(6d,1d); //add quarter
		vend.addCoin(6d,1d); //add quarter
		vend.addCoin(6d,1d); //add quarter
		vend.addCoin(6d,1d); //add quarter
		//If sufficient funds for a cola, display THANK YOU and then INSERT COIN. Return a COLA enum from vend.select
		SnackEnum product = vend.select(SnackEnum.COLA);
		assertEquals(SnackEnum.COLA, product);
		assertEquals("THANK YOU", vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("INSERT COIN", vend.getDisplay());
		assertEquals(Double.valueOf(0), vend.getBalance());
		vend.addCoin(6d,1d); //add quarter
		vend.addCoin(6d,1d); //add quarter
		vend.addCoin(2d, .7d); //add dime
		vend.addCoin(5d,.8d); //add nickel
		//If sufficient funds for a CANDY, display THANK YOU and then INSERT COIN. Return a COLA enum from vend.select
		product = vend.select(SnackEnum.CANDY);
		assertEquals(SnackEnum.CANDY, product);
		assertEquals("THANK YOU", vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("INSERT COIN", vend.getDisplay());
		assertEquals(Double.valueOf(0), vend.getBalance());
		vend.addCoin(6d,1d); //add quarter
		vend.addCoin(6d,1d); //add quarter
		//If sufficient funds for CHIPS, display THANK YOU and then INSERT COIN. Return a COLA enum from vend.select
		product = vend.select(SnackEnum.CHIPS);
		assertEquals(SnackEnum.CHIPS, product);
		assertEquals("THANK YOU", vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("INSERT COIN", vend.getDisplay());
		assertEquals(Double.valueOf(0), vend.getBalance());
		//if insufficient funds, display PRICE X, then after five seconds, INSERT COIN
		product = vend.select(SnackEnum.COLA);
		assertEquals(null, product);
		assertEquals("PRICE "+NumberFormat.getCurrencyInstance(new Locale("en", "US"))
	        .format(SnackEnum.COLA.getCost()), vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("INSERT COIN", vend.getDisplay());
		//if numProducts[0] is 0, display SOLD OUT , do not return a snack, and then display balance
		vend.numProducts[0] = 0;
		vend.addCoin(6d,1d); //add quarter
		vend.addCoin(6d,1d); //add quarter
		vend.addCoin(6d,1d); //add quarter
		vend.addCoin(6d,1d); //add quarter
		product = vend.select(SnackEnum.COLA);
		assertEquals(null, product);
		assertEquals("SOLD OUT", vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("BALANCE: $1.00", vend.getDisplay());
		//if numProducts[0] is 0 and balance is  0, display SOLD OUT, do not return a snack and display INSERT COIN	
		vend.setBalance(0d);
		product = vend.select(SnackEnum.COLA);
		assertEquals(null, product);
		assertEquals("SOLD OUT", vend.getDisplay());
		TimeUnit.SECONDS.sleep(5);
		assertEquals("INSERT COIN", vend.getDisplay());

		

		}

}
