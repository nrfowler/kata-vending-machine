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


}
