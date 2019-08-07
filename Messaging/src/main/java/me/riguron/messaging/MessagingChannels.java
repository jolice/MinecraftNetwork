package me.riguron.messaging;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessagingChannels {

    /**
     * The channel that all proxies listen to
     */
    public static final String PROXY = "Proxy";

}
