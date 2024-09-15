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
        app.post("register", this::registerNewUser);
        app.post("login", this::logInUser);
        app.post("messages", this::submitNewPost);
        app.get("messages", this::retrieveAllMessages);
        app.get("messages/{message_id}", this::retrieveMessageById);
        app.delete("messages/{message_id}", this::deleteMessageById);
        app.patch("messages/{message_id}", this::updateMessageById);
        app.get("accounts/{account_id}/messages", this::retrieveAllMessagesByAccount);;
        app.start(8000);

        return app;
    }

    /**
     * 
     * @param context
     */
    private void registerNewUser(Context context) {

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
    private void submitNewPost(Context context) {

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
    private void retrieveMessageById(Context context) {

    }

    /**
     * 
     * @param context
     */
    private void deleteMessageById(Context context) {

    }

    /**
     * 
     * @param context
     */
    private void updateMessageById(Context context) {

    }

    /**
     * 
     * @param context
     */
    private void retrieveAllMessagesByAccount(Context context) {

    }

}