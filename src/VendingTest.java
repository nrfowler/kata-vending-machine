import static org.junit.Assert.assertEquals;

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


}
