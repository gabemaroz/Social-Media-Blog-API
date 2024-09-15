package Controller;

import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    MessageService messageService;
    AccountService AccountService;

    public SocialMediaController() {
        this.messageService = new MessageService();
        this.AccountService = new AccountService();
    }
    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("register", this::registerUser);
        app.post("login", this::logInUser);
        app.post("messages", this::createMessage);
        app.get("messages", this::retrieveAllMessages);
        app.get("messages/{messageId}", this::retrieveMessageByMessageId);
        app.delete("messages/{messageId}", this::deleteMessageByMessageId);
        app.patch("messages/{messageId}", this::updateMessageByMessageId);
        app.get("accounts/{accountId}/messages", this::retrieveAllMessagesByAccountId);;
        return app;
    }

    /**
     * 
     * @param context
     */
    private void registerUser(Context context) {

    }

    /**
     * 
     * @param context
     */
    private void logInUser(Context context) {

    }

    /**
     * 
     * @param context
     */
    private void createMessage(Context context) {

    }

    /**
     * 
     * @param context
     */
    private void retrieveAllMessages(Context context) {
        
    }

    /**
     * 
     * @param context
     */
    private void retrieveMessageByMessageId(Context context) {

    }

    /**
     * 
     * @param context
     */
    private void deleteMessageByMessageId(Context context) {

    }

    /**
     * 
     * @param context
     */
    private void updateMessageByMessageId(Context context) {

    }

    /**
     * 
     * @param context
     */
    private void retrieveAllMessagesByAccountId(Context context) {

    }

}