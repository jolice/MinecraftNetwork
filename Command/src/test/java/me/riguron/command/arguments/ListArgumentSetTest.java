package me.riguron.command.arguments;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// Rank level
public class ListArgumentSetTest {

    private static final List<String> BACKING_LIST = Arrays.asList("a", "b", "c", "d");

    private ArgumentSet listArgumentSet;

    @Before
    public void setUp() {
        listArgumentSet = new ListArgumentSet(BACKING_LIST);
    }

    @Test
    public void get() {
        assertEquals(listArgumentSet.get(1), "b");
    }

    @Test
    public void length() {
        assertEquals(4, listArgumentSet.length());
    }

    @Test
    public void getAll() {
        assertEquals(BACKING_LIST, listArgumentSet.getAll());
    }

    @Test
    public void isEmpty() {
        assertFalse(listArgumentSet.isEmpty());
        listArgumentSet = new ListArgumentSet(emptyList());
        assertTrue(listArgumentSet.isEmpty());
    }

    @Test
    public void has() {
        assertTrue(listArgumentSet.has("a"));
        assertTrue(listArgumentSet.has("c"));
        assertFalse(listArgumentSet.has("e"));
    }

}