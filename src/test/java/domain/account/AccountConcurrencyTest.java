package domain.account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccountConcurrencyTest {

	@Test
	void shouldFailWhenVersionMismatch() {
		AccountId id = AccountId.newId();
		Account account = new Account(id, Money.of(100));
		
		InMemoryAccountRepository accountRepository = new InMemoryAccountRepository();
		accountRepository.add(account);
		
		// concurrent
		Account copy1 = accountRepository.findById(id).get();
		Account copy2 = accountRepository.findById(id).get();
		
		// first update
		copy1.debit(Money.of(20));
		accountRepository.save(copy1, copy1.getVersion());
		
		// second update
		copy2.debit(Money.of(10));
		
		assertThrows(ConcurrencyException.class, () -> {
			accountRepository.save(copy2, copy2.getVersion());
		});
	}

}
