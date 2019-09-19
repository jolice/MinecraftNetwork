package io.riguron.system.rank;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RankTest {

    @Test
    public void whenHierarchyHasPermission() {

        Rank a = createRank("a");
        Rank b = createRank("b");
        Rank c = createRank("c");
        Rank d = createRank("d");

        d.setChild(c);
        c.setChild(b);
        b.setChild(a);

        assertTrue(d.hasPermission("perm.a.a"));
        assertTrue(c.hasPermission("perm.a.b"));

        assertTrue(d.hasPermission("perm.d.a"));

        assertTrue(b.hasPermission("perm.a.a"));
        assertTrue(b.hasPermission("perm.a.b"));

        assertFalse(b.hasPermission("perm.c.a.a"));
        assertFalse(b.hasPermission("perm.c.a.b"));

    }

    @Test
    public void whenOpHasPermission() {

        Rank op = new Rank(true, "name", "pref", Collections.emptySet());
        assertTrue(op.hasPermission("ANY"));
    }

    private Rank createRank(String name) {
        return new Rank(false, name, name, new HashSet<>(
                Arrays.asList("perm." + name + ".a",
                        "perm." + name + ".b")
        ));
    }
}