package domain.account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository {

	private final Map<AccountId, Account> store;
	private final Map<AccountId, Integer> versions;
	
	public InMemoryAccountRepository() {
		this.store = new HashMap<>();
		this.versions = new HashMap<>();
	}
	
	@Override
	public Optional<Account> findById(
			final AccountId id) {
		if(store.get(id) == null)
			return Optional.empty();
		Account account2 = new Account(
				store.get(id).getId(), 
				store.get(id).getBalance());
		return Optional.ofNullable(account2);
	}

	@Override
	public void save(
			final Account account, 
			final int expectedVersion) {
		
		int currentVersion = versions.getOrDefault(account.getId(), 0);
		if(currentVersion != expectedVersion) {
			String msg = "Version mismatch";
			throw new ConcurrencyException(msg);
		}
		account.incrementVersion();
		store.put(account.getId(), account);
		versions.put(account.getId(), account.getVersion());
	}

	public void add(
			final Account account) {
		Account account2 = new Account(account.getId(), account.getBalance());
		this.store.put(account2.getId(), account2);
		this.versions.put(account2.getId(), account2.getVersion());
	}
	
}
