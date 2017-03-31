import java.math.BigDecimal;

public enum SnackEnum {
    COLA(1), CHIPS(.5), CANDY(.65);
	private final double cost;
	SnackEnum(double cost){
		this.cost = cost;
	}
	public BigDecimal getCost() {
		return new BigDecimal(cost).setScale(2,BigDecimal.ROUND_HALF_EVEN);
	}
}