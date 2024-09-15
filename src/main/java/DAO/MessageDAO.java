package DAO;

import Util.ConnectionUtil;
import Model.Message;

import java.util.*;
import java.sql.*;

public class MessageDAO {

    /**
     * 
     * @param message
     * @return
     */
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
                return new Message(messageId, message.getPosted_by(), message.getMessage_text(),
                        message.getTime_posted_epoch());
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * 
     * @return
     */
    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "select * from message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                messages.add(new Message(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
                        resultSet.getLong(4)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return messages;
    }

    /**
     * 
     * @param messageId
     * @return
     */
    public Message getMessageByMessageId(int messageId) {
        Connection connection = ConnectionUtil.getConnection();
        Message message = null;
        try {
            String sql = "select * from message where message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, messageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                message = new Message(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
                resultSet.getLong(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return message;
    }

    public List<Message> getAllMessagesByAccountId(int accountId) {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "select * from message where posted_by = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                messages.add(new Message(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
                resultSet.getLong(4)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return messages;
    }

    /**
     * 
     * @param message
     * @return
     */
    public Message updateMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "update message set message_text = ? where message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setInt(2, message.getMessage_id());
            preparedStatement.executeUpdate();
            return getMessageByMessageId(message.getMessage_id());
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }    

    /**
     * 
     */
    public Message deleteMessageByMessageId(int messageId) {
        Connection connection = ConnectionUtil.getConnection();
        Message message = getMessageByMessageId(messageId);
        try {
            String sql = "delete * from message where message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, messageId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return message;
    }

}
