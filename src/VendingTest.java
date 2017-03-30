import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
	@Test
	public void addPennyorOthertoMachine(){
		//add a penny
		Vending vend = new Vending();
		vend.addCoin(2.5d,.75d);
		assertEquals(Double.valueOf(0),vend.getBalance());
		assertEquals("COIN NOT VALID", vend.getDisplay());
		assertEquals("REJECT", vend.getAction());
		//add a half dollar
		vend = new Vending();
		vend.addCoin(11d,1.25d);
		assertEquals(Double.valueOf(0),vend.getBalance());
		assertEquals("COIN NOT VALID", vend.getDisplay());
		assertEquals("REJECT", vend.getAction());
		//add a valid coin
		vend = new Vending();
		vend.addCoin(2d,.7d);
		assertEquals(Double.valueOf(.1),vend.getBalance());
		assertNotEquals("COIN NOT VALID", vend.getDisplay());
		assertNotEquals("REJECT", vend.getAction());

	}
	@Test
	public void updateDisplayMessage(){
		Vending vend = new Vending();
		assertEquals("INSERT COIN", vend.getDisplay());
		vend.addCoin(2d, .7d);
		assertEquals("BALANCE: $0.10", vend.getDisplay());
	}
	@Test
	public void sumCoinsAndCheckDisplay(){
		Vending vend = new Vending();
		vend.addCoin(2d,.7d);
		assertEquals("BALANCE: $0.10", vend.getDisplay());
		vend.addCoin(2d, .7d);
		assertEquals("BALANCE: $0.20", vend.getDisplay());
		vend.addCoin(2d, .7d);
		assertEquals("BALANCE: $0.30", vend.getDisplay());
		vend.addCoin(6d,1d);
		assertEquals("BALANCE: $0.55", vend.getDisplay());
		vend.addCoin(5d,.8d);
		assertEquals("BALANCE: $0.60", vend.getDisplay());
		vend.addCoin(5d,.8d);
		assertEquals("BALANCE: $0.65", vend.getDisplay());
		vend.addCoin(6d,1d);
		assertEquals("BALANCE: $0.90", vend.getDisplay());
		


	}


}
