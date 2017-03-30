
public class Vending {
	
	String display;
	
	Double balance;
	
	public String getDisplay() {
		// TODO Auto-generated method stub
		return display;
	}
	
	public Double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}
	
	public Vending(){
		balance = 0d;
	}

	public void addCoin(Double weight, Double diameter) {
		if (weight> 5.9d && weight < 6.1d && diameter > .9d && diameter < 1.1d)
			balance += .25;
	}


}
