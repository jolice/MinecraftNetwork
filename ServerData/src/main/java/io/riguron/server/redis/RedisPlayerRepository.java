package io.riguron.server.redis;

import lombok.RequiredArgsConstructor;
import io.riguron.server.repository.PlayerRepository;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RedisPlayerRepository implements PlayerRepository {

    private static final String SERVER = "server";

    private static final String PROXY = "proxy";

    private static final String TABLE_PREFIX = "player";

    private final RedisTransactions redisTransactions;

    @Override
    public void setPlayerServer(String server, String playerName) {
        redisTransactions.runTransaction(jedis -> {
            jedis.hset(this.playerHashTable(playerName), SERVER, server);
        });
    }

    @Override
    public Map<String, String> getPlayersProxies(Collection<String> playerNames) {
        return redisTransactions.runTransaction(jedis -> {
            Map<String, Response<String>> pipelineResponses = new HashMap<>();
            Pipeline pipeline = jedis.pipelined();
            playerNames.forEach(

                    name -> pipelineResponses.put(name, pipeline.hget(playerHashTable(name), PROXY))
            );
            pipeline.sync();
            return pipelineResponses
                    .entrySet()
                    .stream()
                    .filter(e -> e.getValue().get() != null)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            e -> e.getValue().get()
                    ));

        });
    }

    @Override
    public void setPlayerProxy(String player, String playerName) {
        redisTransactions.runTransaction(jedis -> {
            jedis.hset(this.playerHashTable(playerName), PROXY, player);
        });
    }

    @Override
    public Optional<String> getPlayerServer(String playerName) {
        return redisTransactions.runTransaction(jedis -> {
            return Optional.ofNullable(jedis.hget(this.playerHashTable(playerName), SERVER));
        });
    }

    @Override
    public Optional<String> getPlayerProxy(String playerName) {
        return redisTransactions.runTransaction(jedis -> {
            return Optional.ofNullable(jedis.hget(this.playerHashTable(playerName), PROXY));
        });
    }

    @Override
    public void deletePlayerData(String player) {
        redisTransactions.runTransaction(jedis -> {
            jedis.hdel(this.playerHashTable(player), SERVER, PROXY);
        });
    }

    private String playerHashTable(String player) {
        return TABLE_PREFIX + ":" + player;
    }


}
