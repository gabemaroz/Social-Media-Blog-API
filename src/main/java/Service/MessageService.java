package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.*;

public class MessageService {
    private MessageDAO messageDAO;

    /**
     * No-arguments constructor.
     */
    public MessageService() {
        messageDAO = new MessageDAO();
    }

    /**
     * Dependency injection constructor.
     */
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message) {
        if (message.getMessage_text().length() > 255 || message.getMessage_text().length() < 1) {
            return null;
        }
        return messageDAO.addMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageByMessageId(int messageId) {
        return messageDAO.getMessageByMessageId(messageId);
    }

    public List<Message> getAllMessageByAccountId(int accountId) {
        return messageDAO.getAllMessagesByAccountId(accountId);
    }

    public Message updateMessage(Message message) {
        if (message.getMessage_text().length() > 255 || message.getMessage_text().length() < 1) {
            return null;
        }
        return messageDAO.updateMessage(message);
    }

    public Message deleteMessageByMessageId(int messageId) {
        return messageDAO.deleteMessageByMessageId(messageId);
    }
    
}
