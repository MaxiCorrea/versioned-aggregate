package domain.account;

import java.math.BigDecimal;
import java.util.Objects;

public final class Money {

	public static Money of(
			final BigDecimal value) {
		return new Money(value);
	}
	
	public static Money of(
			final int value) {
		return new Money(BigDecimal.valueOf(value));
	}
	
	private final BigDecimal amount;
	
	public Money(
			final BigDecimal amount) {
		ensurePositiveAmountCreate(amount);
		this.amount = amount;
	}

	private void ensurePositiveAmountCreate(
			final BigDecimal amount) {
		if(amount.signum() == -1) {
			String msg = "Amount cannot be negative";
			throw new IllegalArgumentException(msg);
		}
	}
	
	public Money subtract(
			final Money other) {
		BigDecimal result = this.amount.subtract(other.amount);
		ensurePositiveAmount(result);
		return new Money(result);
	}

	public Money add(
			final Money other) {
		BigDecimal result = this.amount.add(other.amount);
		ensurePositiveAmount(result);
		return new Money(result);
	}
	
	private void ensurePositiveAmount(
			final BigDecimal amount) {
		if(amount.signum() == -1) {
			String msg = "Insufient funds";
			throw new IllegalArgumentException(msg);
		}
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Money other = (Money) obj;
		return Objects.equals(amount, other.amount);
	}
	
}
