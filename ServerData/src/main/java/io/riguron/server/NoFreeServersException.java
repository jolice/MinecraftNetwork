package io.riguron.server;

/**
 * Exception to indicate the case when there are no servers that a given player
 * may join at the moment.
 */
public class NoFreeServersException extends RuntimeException {
}
