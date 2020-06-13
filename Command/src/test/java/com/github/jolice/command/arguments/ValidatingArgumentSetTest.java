package io.riguron.command.arguments;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ValidatingArgumentSetTest {

    private ArgumentSet delegate;
    private ArgumentSet validatingArguments;

    @Before
    public void setUp() throws Exception {
        this.delegate = mock(ArgumentSet.class);
        this.validatingArguments = new ValidatingArgumentSet(delegate);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenNegativeIndex() {
        validatingArguments.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexGreaterThanLength() {
        when(delegate.length()).thenReturn(3);
        validatingArguments.get(5);
    }

    @Test
    public void get() {
        when(delegate.length()).thenReturn(5);
        validatingArguments.get(3);
        verify(delegate).get(eq(3));
    }

    @Test
    public void length() {
        validatingArguments.length();
        verify(delegate).length();
    }

    @Test
    public void getAll() {
        validatingArguments.getAll();
        verify(delegate).getAll();
    }

    @Test
    public void isEmpty() {
        validatingArguments.isEmpty();
        verify(delegate).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNullArgumentThanHasThrowsError() {
        validatingArguments.has(null);
    }

    @Test
    public void has() {
        validatingArguments.has("example");
        verify(delegate).has(eq("example"));
    }
}