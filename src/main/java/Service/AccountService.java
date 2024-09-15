package Service;

import DAO.AccountDAO;
import Model.Account;

/**
 * The AccountService class provides business logic for handling 
 * operations related to Account objects. This service layer interacts 
 * with the data access layer through AccountDAO.
 */
public class AccountService {
    private AccountDAO accountDAO;

    /**
     * No-arguments constructor.
     * Initializes a new instance of AccountService with a default AccountDAO.
     */
    public AccountService() {
        accountDAO = new AccountDAO();
    }

    /**
     * Dependency injection constructor.
     * Allows an AccountDAO to be injected into the AccountService.
     * 
     * @param accountDAO the data access object to use for account-related operations
     */
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    /**
     * Creates a new account if the provided account meets validation criteria.
     * Username must be non-empty and the password must be at least 4 characters long.
     * 
     * @param account the account to be created
     * @return the created Account object, or null if validation fails
     */
    public Account createAccount(Account account) {
        if (account.getUsername().length() < 1 || account.getPassword().length() < 4) {
            return null;
        }
        return accountDAO.createAccount(account);
    }

    /**
     * Attempts to log in the account based on the provided credentials.
     * 
     * @param account the account containing login credentials
     * @return the logged-in Account object, or null if authentication fails
     */
    public Account logInAccount(Account account) {
        return accountDAO.logInAccount(account);
    }

    /**
     * Checks if an account exists with the given account ID.
     * 
     * @param accountId the ID of the account to check for
     * @return true if the account exists, false otherwise
     */
    public boolean containsAccountWithId(int accountId) {
        return accountDAO.containsAccountWithId(accountId);
    }
    
}
