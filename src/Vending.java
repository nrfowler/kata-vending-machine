
public class Vending {
	
	String display;
	
	Double balance;
	
	String action;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDisplay() {
		// TODO Auto-generated method stub
		return display;
	}
	public void updateDisplaywithBalance(){
		display = "BALANCE: "+this.getBalance().toString();
	}
	public Double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}
	
	public Vending(){
		balance = 0d;
		display = "INSERT COIN";
	}

	public void addCoin(Double weight, Double diameter) {
		if (weight> 5.9d && weight < 6.1d && diameter > .9d && diameter < 1.1d)
			{
			balance += .25;
			updateDisplaywithBalance();
			}
		else if (weight> 4.9d && weight < 5.1d && diameter > .7d && diameter < .9d)
			{
			balance += .05;
			updateDisplaywithBalance();
		}
		else if (weight> 1.9d && weight < 2.1d && diameter > .6d && diameter < .8d)
			{
			balance += .1;
			updateDisplaywithBalance();
		}
		else{
			display = "COIN NOT VALID";
			action = "REJECT";
		}
	}


}
