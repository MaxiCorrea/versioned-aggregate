package domain.account;

public class Account {

	private final AccountId id;
	private Money balance;
	private int version;
	
	public Account(
			final AccountId id,
			final Money initialBalance) {
		this.id = id;
		this.balance = initialBalance;
		this.version = 0;
	}
	
	public void debit(
			final Money amount) {
		this.balance = balance.subtract(amount);
	}
	
	public void credit(
			final Money amount) {
		this.balance = balance.add(amount);
	}
	
	public AccountId getId() {
		return id;
	}
	
	public Money getBalance() {
		return balance;
	}
	
	public int getVersion() {
		return version;
	}

	public void incrementVersion() {
		++version;
	}
	
}
