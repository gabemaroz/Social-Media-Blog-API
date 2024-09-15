package DAO;

import Util.ConnectionUtil;
import Model.Account;

import java.sql.*;

/**
 * The AccountDAO class handles the database operations related to the Account entity, 
 * such as creating accounts, logging in, and checking for existing accounts.
 */
public class AccountDAO {

    /**
     * Checks if an account with the specified username exists in the database.
     *
     * @param username The username to check for existence.
     * @return true if the account exists, false otherwise.
     */
    private boolean containsAccountWithUsername(String username) {
        Connection connection = ConnectionUtil.getConnection();
        if (username != null) {
            try {
                String sql = "select * from account where username = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return false;
    }

    /**
     * Checks if an account with the specified account ID exists in the database.
     *
     * @param accountId The ID of the account to check for existence.
     * @return true if the account exists, false otherwise.
     */
    public boolean containsAccountWithId(int accountId) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "select * from account where account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Creates a new account in the database. If the username already exists, the account is not created.
     *
     * @param account The Account object containing the username and password for the new account.
     * @return The created Account object with the generated account ID, or null if the account creation failed.
     */
    public Account createAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        if (!containsAccountWithUsername(account.getUsername())) {
            try {
                String sql = "insert into account (username, password) values (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, account.getUsername());
                preparedStatement.setString(2, account.getPassword());
                preparedStatement.executeUpdate();
                ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
                if (pkeyResultSet.next()) {
                    int accountId = (int) pkeyResultSet.getLong(1);
                    return new Account(accountId, account.getUsername(), account.getPassword());
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    /**
     * Logs in an account by verifying the username and password. 
     * If the credentials match, the corresponding Account object is returned.
     *
     * @param account The Account object containing the username and password to log in.
     * @return The Account object if login is successful, or null if the login failed.
     */
    public Account logInAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "select * from account where username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Account(resultSet.getInt("account_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
