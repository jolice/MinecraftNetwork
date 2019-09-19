package io.riguron.system.login;

import lombok.Value;

import java.util.UUID;

/**
 * Simple value class holding user's ID and name
 */
@Value
public class LoginDetails {

    private UUID uuid;
    private String name;
}
