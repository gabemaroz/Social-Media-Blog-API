package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    /**
     * No-arguments constructor.
     */
    public AccountService() {
        accountDAO = new AccountDAO();
    }

    /**
     * Dependency injection constructor.
     */
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account createAccount(Account account) {
        if (account.getUsername().length() < 1 || account.getPassword().length() < 4) {
            return null;
        }
        return accountDAO.createAccount(account);
    }

    public Account logInAccount(Account account) {
        return accountDAO.logInAccount(account);
    }

    public boolean containsAccountWithId(int accountId) {
        return accountDAO.containsAccountWithId(accountId);
    }
    
}
