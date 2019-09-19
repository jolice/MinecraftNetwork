package io.riguron.command.arguments;

import java.util.List;

/**
 * Represents a set of arguments supplied by user
 * on command execution. This interface may be thought
 * of as a wrapper over an list, but with some additional
 * convenience methods.
 *
 * @see ListArgumentSet
 * @see ValidatingArgumentSet
 */
public interface ArgumentSet {

    /**
     * Returns an argument string at specified index. Indexing, as usual,
     * starts from 0.
     *
     * @param index index of argument
     * @return argument at specified index
     */
    String get(int index);

    /**
     * Total count of arguments.
     *
     * @return length of argument array
     */
    int length();

    /**
     * Returns all argument as a list.
     *
     * @return argument array wrapped in a list
     */
    List<String> getAll();

    /**
     * Alias for {@link #length() == 0}
     *
     * @return whether there are no arguments at all, i.e
     * command is supplied without any arguments.
     */
    boolean isEmpty();

    /**
     * Checks the presence of a specific argument. This function
     * is case-sensitive.
     *
     * @param argument target argument
     * @return whether the argument list contains a specific string
     */
    boolean has(String argument);

    /**
     * Concatenates all arguments in one string using a single
     * whitespace as a separator. Sample translation:
     * <p>
     * [arg0, arg1, arg2] -> "arg0 arg1 arg2"
     *
     * @return arguments joined with whitespace.
     */
    String join();

    /**
     * Returns an argument array itself. Basically,
     * it's an alias for {@link #getAll()}
     *
     * @return argument list represented as a plain java
     * array.
     */
    String[] toArray();

}
