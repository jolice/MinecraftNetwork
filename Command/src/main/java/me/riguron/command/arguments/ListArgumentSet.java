package me.riguron.command.arguments;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Basic implementation of an ArgumentSet that simply
 * wraps a list and delegates most of methods to it.
 */
public class ListArgumentSet implements ArgumentSet {

    private List<String> arguments;

    public ListArgumentSet(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public String get(int index) {
        return arguments.get(index);
    }

    @Override
    public int length() {
        return arguments.size();
    }

    @Override
    public List<String> getAll() {
        return Collections.unmodifiableList(arguments);
    }

    @Override
    public boolean isEmpty() {
        return arguments.isEmpty();
    }

    @Override
    public boolean has(String argument) {
        return arguments.contains(argument);
    }

    @Override
    public String join() {
        return arguments.stream().collect(Collectors.joining());
    }

    @Override
    public String[] toArray() {
        return arguments.toArray(new String[0]);
    }

}
