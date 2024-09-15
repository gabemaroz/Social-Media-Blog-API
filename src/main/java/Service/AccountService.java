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

    public boolean logInAccount(Account account) {
        return accountDAO.logInAccount(account);
    }

    public Account createAccount(Account account) {
        return accountDAO.createAccount(account);
    }
    
}
