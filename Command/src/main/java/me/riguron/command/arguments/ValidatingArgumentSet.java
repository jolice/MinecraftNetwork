package me.riguron.command.arguments;

import java.util.List;

/**
 * Validating decorator for any implementation of ArgumentSet.
 */
public class ValidatingArgumentSet implements ArgumentSet {

    private ArgumentSet argumentSet;

    public ValidatingArgumentSet(ArgumentSet argumentSet) {
        this.argumentSet = argumentSet;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= length()) {
            throw new IndexOutOfBoundsException("Out of bounds: " + index);
        }
        return argumentSet.get(index);
    }

    @Override
    public int length() {
        return argumentSet.length();
    }

    @Override
    public List<String> getAll() {
        return argumentSet.getAll();
    }

    @Override
    public boolean isEmpty() {
        return argumentSet.isEmpty();
    }

    @Override
    public boolean has(String argument) {
        if (argument == null) {
            throw new IllegalArgumentException("Argument can not be null");
        }
        return argumentSet.has(argument);
    }

    @Override
    public String join() {
        return argumentSet.join();
    }

    @Override
    public String[] toArray() {
        return argumentSet.toArray();
    }

}
