package io.riguron.server.redis;

import io.riguron.server.NoFreeServersException;
import io.riguron.server.ServerGroup;
import lombok.RequiredArgsConstructor;
import io.riguron.server.repository.FreeServersQueryOptions;
import io.riguron.server.repository.OnlineIndexingRepository;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.ZAddParams;
import redis.clients.jedis.params.ZIncrByParams;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RedisOnlineIndexingRepository implements OnlineIndexingRepository {

    private static final String ONLINE_POSTFIX = "online";

    private final RedisTransactions redisTransactions;

    @Override
    public String getMostFreeServer(ServerGroup group) {
        return redisTransactions.runTransaction(jedis -> {
            return jedis.zrangeByScoreWithScores(this.redisSetName(group), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0, 1)
                    .stream()
                    .findFirst()
                    .map(Tuple::getElement)
                    .orElseThrow(NoFreeServersException::new);
        });
    }

    @Override
    public List<String> getMostFreeServers(ServerGroup group, FreeServersQueryOptions options) {
        return redisTransactions.runTransaction(jedis -> {
            return new ArrayList<>(jedis.zrangeByScore(this.redisSetName(group), options.getMin(), options.getMax(), 0, options.getCount()));
        });
    }

    @Override
    public void removeServerData(ServerGroup serverGroup, String serverName) {
        redisTransactions.runTransaction(jedis -> {
            jedis.zrem(this.redisSetName(serverGroup), serverName);
        });
    }

    @Override
    public void incrementOnline(ServerGroup serverGroup, String serverName) {
        redisTransactions.runTransaction(jedis -> {
            jedis.zincrby(this.redisSetName(serverGroup), 1.0, serverName, new ZIncrByParams().xx());
        });
    }

    @Override
    public void decrementOnline(ServerGroup serverGroup, String serverName) {
        redisTransactions.runTransaction(jedis -> {
            jedis.zincrby(this.redisSetName(serverGroup), -1.0, serverName, new ZIncrByParams().xx());
        });
    }

    @Override
    public void addServer(ServerGroup serverGroup, String serverName) {
        redisTransactions.runTransaction(jedis -> {
            jedis.zadd(this.redisSetName(serverGroup), 0.0, serverName, new ZAddParams().nx());
        });
    }

    private String redisSetName(ServerGroup group) {
        return group.getName() + ":" + ONLINE_POSTFIX;
    }
}
