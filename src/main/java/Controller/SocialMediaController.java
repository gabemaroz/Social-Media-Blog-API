package Controller;

import Service.*;
import Model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * The SocialMediaController class handles API requests for account registration, login, and message operations.
 * It uses Javalin to define the API endpoints and processes requests using services such as MessageService 
 * and AccountService.
 */
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;

    /**
     * Default constructor initializes the MessageService and AccountService instances
     */
    public SocialMediaController() {
        this.messageService = new MessageService();
        this.accountService = new AccountService();
    }

    /**
     * Starts the Javalin API and defines all the routes for the application.
     * 
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("register", this::registerAccountHandler);
        app.post("login", this::logInAccountHandler);
        app.post("messages", this::createMessageHandler);
        app.get("messages", this::retrieveAllMessagesHandler);
        app.get("messages/{messageId}", this::retrieveMessageByMessageIdHandler);
        app.delete("messages/{messageId}", this::deleteMessageByMessageIdHandler);
        app.patch("messages/{messageId}", this::updateMessageByMessageIdHandler);
        app.get("accounts/{accountId}/messages", this::retrieveAllMessagesByAccountIdHandler);
        ;
        return app;
    }

    /**
     * Handles the registration of a new account.
     * It reads the account details from the request body, creates the account, and sends a response.
     * 
     * @param context The Javalin context object containing the request and response data.
     * @throws JsonProcessingException if the request body cannot be parsed into an Account object.
     */
    private void registerAccountHandler(Context context) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Account account = null;
        account = objectMapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.createAccount(account);
        if (addedAccount != null) {
            context.json(addedAccount);
        } else {
            context.status(400);
        }
    }

    /**
     * Handles the login of an account.
     * It verifies the account credentials and returns the account data if login is successful.
     * 
     * @param context The Javalin context object containing the request and response data.
     * @throws JsonProcessingException if the request body cannot be parsed into an Account object.
     */
    private void logInAccountHandler(Context context) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Account account = null;
        account = objectMapper.readValue(context.body(), Account.class);
        Account registeredAccount = accountService.logInAccount(account);
        if (registeredAccount != null) {
            context.json(registeredAccount);
        } else {
            context.status(401);
        }
    }

    /**
     * Creates a new message.
     * It verifies the account ID associated with the message and then adds the message to the service.
     * 
     * @param context The Javalin context object containing the request and response data.
     * @throws JsonProcessingException if the request body cannot be parsed into a Message object.
     */
    private void createMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = null;
        message = objectMapper.readValue(context.body(), Message.class);
        if (accountService.containsAccountWithId(message.getPosted_by())) {
            Message savedMessage = messageService.addMessage(message);
            if (savedMessage != null) {
                context.json(savedMessage);
            } else {
                context.status(400);
            }
        } else {
            context.status(400);
        }
    }

    /**
     * Retrieves all messages.
     * It returns a list of all messages stored in the system.
     * 
     * @param context The Javalin context object containing the request and response data.
     */
    private void retrieveAllMessagesHandler(Context context) {
        context.json(messageService.getAllMessages());
    }

    /**
     * Retrieves a message by its message ID.
     * It fetches a specific message using the ID provided in the URL path.
     * 
     * @param context The Javalin context object containing the request and response data.
     */
    private void retrieveMessageByMessageIdHandler(Context context) {
        Message message = null;
        try {
            int messageId = Integer.parseInt(context.pathParam("messageId"));
            message = messageService.getMessageByMessageId(messageId);
        } catch (NumberFormatException e) {
            System.out.println(e);
        } finally {
            if (message != null) {
                context.json(message);
            }
        }
    }

    /**
     * Deletes a message by its message ID.
     * It removes a message from the system using the ID provided in the URL path.
     * 
     * @param context The Javalin context object containing the request and response data.
     */
    private void deleteMessageByMessageIdHandler(Context context) {
        Message message = null;
        try {
            int messageId = Integer.parseInt(context.pathParam("messageId"));
            message = messageService.deleteMessageByMessageId(messageId);
        } catch (NumberFormatException e) {
            System.out.println(e);
        } finally {
            if (message != null) {
                context.json(message);
            }
        }
    }

    /**
     * Updates a message by its message ID.
     * It modifies an existing message based on the provided ID and request body.
     * 
     * @param context The Javalin context object containing the request and response data.
     * @throws JsonProcessingException if the request body cannot be parsed into a Message object.
     */
    private void updateMessageByMessageIdHandler(Context context) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = objectMapper.readValue(context.body(), Message.class);
        try {
            int messageId = Integer.parseInt(context.pathParam("messageId"));
            message.setMessage_id(messageId);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Message updatedMessage = messageService.updateMessage(message);
        if (updatedMessage != null) {
            context.json(updatedMessage);
        } else {
            context.status(400);
        }
    }

    /**
     * Retrieves all messages posted by a specific account.
     * It fetches all messages related to the account ID provided in the URL path.
     * 
     * @param context The Javalin context object containing the request and response data.
     */
    private void retrieveAllMessagesByAccountIdHandler(Context context) {
        int accountId = -1;
        try {
            accountId = Integer.parseInt(context.pathParam("accountId"));
        } catch (NumberFormatException e) {
            System.out.println(e);
        } finally {
            context.json(messageService.getAllMessageByAccountId(accountId));
        }
    }
    
}