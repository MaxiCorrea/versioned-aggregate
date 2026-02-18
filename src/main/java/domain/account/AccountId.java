package domain.account;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.UUID;

public final class AccountId {

	public static AccountId newId() {
		return new AccountId(UUID.randomUUID());
	}
	
	private final UUID value;
	
	public AccountId(
			final UUID value) {
		this.value = requireNonNull(value);
	}
	
	public UUID getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass())
			return false;
		AccountId other = (AccountId) obj;
		return Objects.equals(value, other.value);
	}
		
}
