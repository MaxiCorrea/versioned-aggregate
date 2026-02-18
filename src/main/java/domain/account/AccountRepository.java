package domain.account;

import java.util.Optional;

public interface AccountRepository {

	Optional<Account> findById(AccountId id);
	
	void save(Account account, int expectedVersion);
	
}
