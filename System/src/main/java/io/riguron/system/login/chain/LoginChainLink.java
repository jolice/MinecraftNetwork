package io.riguron.system.login.chain;

import io.riguron.system.login.LoginDetails;
import io.riguron.system.login.LoginDetails;

/**
 * This class is involved in the implementation of chain of responsibility pattern. Login chain is a
 * set of consequent operations that are performed one by one before the player joins server. Each operation is
 * encapsulated in the LoginChainLink implementation and typically fetches some data associated with the player from
 * the database (though link may perform any arbitrary action). Player is logged in successfully only if all operations
 * in the chain are passed. If one is not, the player is disconnected before the actual join.
 */
public abstract class LoginChainLink {

    private LoginChainLink next;

    /**
     * Sets next chain element.
     *
     * @param next chain element that follows the current one.
     * @return current chain link instance (fluent api for convenience).
     */
    public LoginChainLink linkWith(LoginChainLink next) {
        this.next = next;
        return this;
    }

    /**
     * Logic performed at this link. Processing failure must be indicated by throwing the
     * LoginProcessingException in order to break the chain execution and prevent player from
     * logging in.
     *
     * @param loginDetails information about the player that is being logged in
     */
    protected abstract void proceed(LoginDetails loginDetails) throws LoginProcessingException;

    /**
     * Method involved in the COR (Chain Of Responsibility) pattern implementation. It must be called
     * by the abstract {@link #proceed(LoginDetails)} proceed} method in order to proceed to the execution
     * of the next chain node.
     *
     * @param loginDetails information about the player that is being logged in
     */
    protected void proceedNext(LoginDetails loginDetails) {
        if (next != null) {
            next.proceed(loginDetails);
        }
    }


}
