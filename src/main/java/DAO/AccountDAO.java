package DAO;

import Util.ConnectionUtil;
import Model.Account;

import java.sql.*;

public class AccountDAO {

    public boolean logInAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        return false;
    }

    public Account createAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        return account;
    }
    
}
