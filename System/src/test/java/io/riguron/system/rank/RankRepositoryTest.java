package io.riguron.system.rank;

import io.ebean.EbeanServer;
import io.riguron.system.test.RepositoryTest;
import io.riguron.system.test.RepositoryTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RankRepositoryTest extends RepositoryTest<RankRepository> {

    @Test
    public void save() {

        Rank owner = new Rank(true, "Admin", "Admin", Collections.emptySet());
        Rank vip = new Rank(false, "Vip", "Vip", new HashSet<>(Arrays.asList("vip.a", "vip.b")));
        Rank mvp = new Rank(false, "MVP", "MVP", new HashSet<>(Arrays.asList("mvp.a", "mvp.b")));
        Rank moderator = new Rank(false, "Moderator", "Moderator", new HashSet<>(Arrays.asList("moder.ban", "moder.kick")));

        owner.setChild(moderator);
        moderator.setChild(mvp);
        mvp.setChild(vip);

        repository.save(vip);
        repository.save(mvp);
        repository.save(moderator);
        repository.save(owner);

        List<Rank> ranks = repository.findAll();

        assertTrue(ranks.contains(owner));
        assertTrue(ranks.contains(vip));
        assertTrue(ranks.contains(mvp));
        assertTrue(ranks.contains(moderator));

        assertEquals(moderator, owner.getChild());
        assertEquals(mvp, moderator.getChild());
        assertEquals(vip, mvp.getChild());

    }

    @Override
    protected RankRepository createRepository(EbeanServer server) {
        return new RankRepository(server);
    }
}