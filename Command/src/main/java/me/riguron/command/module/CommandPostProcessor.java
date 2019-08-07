package me.riguron.command.module;

import com.google.inject.Inject;
import me.riguron.command.repository.CommandRegistration;
import me.riguron.command.repository.CommandRepository;

import java.util.Set;

/**
 * Class responsible of registering commands defined in the guice modules.
 */
public class CommandPostProcessor {

    @Inject(optional = true)
    public void postConfigure(CommandRepository commandRepository, Set<CommandRegistration> registrations) {
        registrations.forEach(commandRepository::addCommand);
    }
}
