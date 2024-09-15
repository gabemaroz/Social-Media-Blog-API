package Controller;

import Service.*;
import Model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    AccountService accountService;

    public SocialMediaController() {
        this.messageService = new MessageService();
        this.accountService = new AccountService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in
     * the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * 
     * @return a Javalin app object which defines the behavior of the Javalin
     *         controller.
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
     * 
     * @param context
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
     * 
     * @param context
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
     * 
     * @param context
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
     * 
     * @param context
     */
    private void retrieveAllMessagesHandler(Context context) {
        context.json(messageService.getAllMessages());
    }

    /**
     * 
     * @param context
     */
    private void retrieveMessageByMessageIdHandler(Context context) {

    }

    /**
     * 
     * @param context
     */
    private void deleteMessageByMessageIdHandler(Context context) {

    }

    /**
     * 
     * @param context
     */
    private void updateMessageByMessageIdHandler(Context context) {

    }

    /**
     * 
     * @param context
     */
    private void retrieveAllMessagesByAccountIdHandler(Context context) {

    }

}