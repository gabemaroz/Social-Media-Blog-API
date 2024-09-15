package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.*;

/**
 * The MessageService class provides services related to Message objects,
 * including operations such as adding, updating, retrieving, and deleting messages.
 * It acts as a bridge between the controller layer and the data access layer (DAO).
 */
public class MessageService {
    private MessageDAO messageDAO;

    /**
     * No-arguments constructor.
     * Initializes the MessageDAO for data operations.
     */
    public MessageService() {
        messageDAO = new MessageDAO();
    }
    /**
     * Constructor that allows for dependency injection of a MessageDAO instance.
     * 
     * @param messageDAO the data access object used to perform message-related database operations.
     */
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    /**
     * Adds a new message to the system. Ensures that the message text
     * length is between 1 and 255 characters.
     *
     * @param message the Message object to be added.
     * @return the added Message if successful, or null if the message text is invalid.
     */
    public Message addMessage(Message message) {
        if (message.getMessage_text().length() > 255 || message.getMessage_text().length() < 1) {
            return null;
        }
        return messageDAO.addMessage(message);
    }

    /**
     * Retrieves all messages from the system.
     * 
     * @return a List of Message objects.
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /**
     * Retrieves a message by its unique messageId.
     * 
     * @param messageId the unique identifier of the message.
     * @return the Message object if found, or null if not found.
     */
    public Message getMessageByMessageId(int messageId) {
        return messageDAO.getMessageByMessageId(messageId);
    }

    /**
     * Retrieves all messages for a specific account by accountId.
     * 
     * @param accountId the unique identifier of the account.
     * @return a List of Message objects associated with the given account.
     */
    public List<Message> getAllMessageByAccountId(int accountId) {
        return messageDAO.getAllMessagesByAccountId(accountId);
    }

    /**
     * Updates an existing message. Ensures that the message text
     * length is between 1 and 255 characters.
     * 
     * @param message the Message object with updated information.
     * @return the updated Message if successful, or null if the message text is invalid.
     */
    public Message updateMessage(Message message) {
        if (message.getMessage_text().length() > 255 || message.getMessage_text().length() < 1) {
            return null;
        }
        return messageDAO.updateMessage(message);
    }

    /**
     * Deletes a message by its unique messageId.
     * 
     * @param messageId the unique identifier of the message to be deleted.
     * @return the deleted Message object, or null if the message was not found.
     */
    public Message deleteMessageByMessageId(int messageId) {
        return messageDAO.deleteMessageByMessageId(messageId);
    }
    
}
