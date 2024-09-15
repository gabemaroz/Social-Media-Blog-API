package DAO;

import Util.ConnectionUtil;
import Model.Message;

import java.util.*;
import java.sql.*;

public class MessageDAO {

    public boolean addMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        return false;
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
