
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
		else if (weight> 4.9d && weight < 5.1d && diameter > .7d && diameter < .9d)
			balance += .05;
		else if (weight> 1.9d && weight < 2.1d && diameter > .6d && diameter < .8d)
			balance += .1;
	}


}
