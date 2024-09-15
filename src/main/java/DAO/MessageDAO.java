package DAO;

import Util.ConnectionUtil;
import Model.Message;

import java.util.*;
import java.sql.*;

public class MessageDAO {

    public Message addMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "insert into message (posted_by, message_text, time_posted_epoch) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if (pkeyResultSet.next()) {
                int messageId = (int) pkeyResultSet.getLong(1);
                return new Message(messageId, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        return messages;
    }

    public Message getMessageByMessageId(int messageId) {
        Connection connection = ConnectionUtil.getConnection();
        Message message = new Message();
        return message;
    }

    public List<Message> getAllMessagesByAccountId(int accountId) {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        return messages;
    }

    public Message updateMessageByMessageId(int messageId) {
        Connection connection = ConnectionUtil.getConnection();
        Message message = new Message();
        return message;
    }

    public boolean deleteMessageByMessageId(int messageId) {
        Connection connection = ConnectionUtil.getConnection();
        return false;
    }

}
