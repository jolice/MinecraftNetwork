package me.riguron.system.login.chain;

/**
 * Exception that indicates the login failure, i.e that player that is being
 * logged in can not be processed for some reason (he is banned, database error has occurred, etc.)
 */
public class LoginProcessingException extends RuntimeException {

    public LoginProcessingException(Throwable cause) {
        super(cause);
    }

    public LoginProcessingException(String message) {
        super(message);
    }
}
