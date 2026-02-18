package domain.account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccountTest {

	@Test
	void shouldDebitCorrectly() {
		Account account = new Account(AccountId.newId(), Money.of(100));
		account.debit(Money.of(40));
		assertEquals(Money.of(60), account.getBalance());
	}

	@Test
	void shouldNotAllowNegativeBalance() {
		Account account = new Account(AccountId.newId(), Money.of(100));
		assertThrows(IllegalArgumentException.class, () -> {
			account.debit(Money.of(101));
		});
	}
	
}
