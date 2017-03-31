public enum SnackEnum {
    COLA(1), CHIPS(.5), CANDY(.65);
	private final double cost;
	SnackEnum(double cost){
		this.cost = cost;
	}
	public double getCost() {
		return cost;
	}
}