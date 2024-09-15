package DAO;

import Util.ConnectionUtil;
import Model.Message;

import java.util.*;
import java.sql.*;

/**
 * The MessageDAO class provides methods to interact with the `message` table in the database. 
 * It includes operations to add, retrieve, update, and delete messages.
 */
public class MessageDAO {

    /**
     * Adds a new message to the database.
     * 
     * @param message The Message object containing the details to be added to the database.
     * @return The newly added Message object, including its generated ID, or null if an error occurs.
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
     * Retrieves all messages from the database.
     * 
     * @return A List of Message objects containing all messages from the database, or an empty list if none are found.
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
     * Retrieves a message by its message ID.
     * 
     * @param messageId The ID of the message to retrieve.
     * @return The Message object with the specified ID, or null if no message is found with that ID.
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

    /**
     * Retrieves all messages posted by a specific account.
     * 
     * @param accountId The ID of the account whose messages are to be retrieved.
     * @return A List of Message objects posted by the specified account, or an empty list if no messages are found.
     */
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
     * Updates an existing message in the database.
     * 
     * @param message The Message object containing the updated details.
     * @return The updated Message object, or null if an error occurs.
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
     * Deletes a message from the database by its message ID.
     * 
     * @param messageId The ID of the message to delete.
     * @return The deleted Message object, or null if no message was found with the specified ID.
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
