import java.math.BigDecimal;

public class Coin implements Comparable<Coin>{

	private final Double weight;
	private final Double diameter;
	private BigDecimal value;
	Coin(Double weight,Double diameter){
		this.weight = weight;
		this.diameter = diameter;
		this.value = Vending.bigDecimal(0);
	}
	
	@Override
	public String toString() {
		return "Coin [weight=" + weight + ", diameter=" + diameter + ", value=" + value + "]";
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Double getWeight() {
		return weight;
	}
	public Double getDiameter() {
		return diameter;
	}
	public int compareTo(Coin other) {
	    return  other.value.compareTo(this.value) ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diameter == null) ? 0 : diameter.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coin other = (Coin) obj;
		if (diameter == null) {
			if (other.diameter != null)
				return false;
		} else if (!diameter.equals(other.diameter))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	
	
	
}
