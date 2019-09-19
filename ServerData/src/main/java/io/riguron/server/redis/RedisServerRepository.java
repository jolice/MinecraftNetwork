package io.riguron.server.redis;

import io.riguron.server.mapper.ServerMapper;
import io.riguron.server.repository.ServerFieldType;
import io.riguron.server.repository.ServerRepository;
import io.riguron.server.type.Server;
import lombok.RequiredArgsConstructor;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RedisServerRepository implements ServerRepository {

    private static final String PREFIX = "server";

    private final RedisTransactions redisTransactions;

    @Override
    public <T extends Server> T get(String serverName, ServerMapper<T> mapper) {
        return redisTransactions.runTransaction(jedis -> {
            return mapper.deserialize(jedis.hgetAll(this.redisHashName(serverName)));
        });
    }

    @Override
    public <T> void set(String serverName, ServerFieldType type, T value) {
        redisTransactions.runTransaction(jedis -> {
            jedis.hset(this.redisHashName(serverName), type.getFieldName(), value.toString());
        });
    }

    @Override
    public <T extends Server> List<T> getAll(List<String> names, ServerMapper<T> mapper) {
        return redisTransactions.runTransaction(jedis -> {

            Pipeline pipelined = jedis.pipelined();
            final List<Response<Map<String, String>>> responses = names
                    .stream()
                    .map(pipelined::hgetAll)
                    .collect(Collectors.toList());
            pipelined.sync();

            return responses
                    .stream()
                    .map(Response::get)
                    .map(mapper::deserialize)
                    .collect(Collectors.toList());
        });
    }

    @Override
    public void incrementOnline(String serverName) {
        redisTransactions.runTransaction(jedis -> {
            jedis.hincrBy(this.redisHashName(serverName), ServerFieldType.ONLINE.getFieldName(), 1L);
        });
    }

    @Override
    public void decrementOnline(String serverName) {
        redisTransactions.runTransaction(jedis -> {
            jedis.hincrBy(this.redisHashName(serverName), ServerFieldType.ONLINE.getFieldName(), -1L);
        });
    }

    @Override
    public <T extends Server> void addServer(T server, ServerMapper<T> mapper) {
        redisTransactions.runTransaction(jedis -> {
            jedis.hmset(this.redisHashName(server.getName()), mapper.serialize(server));
        });
    }

    private String redisHashName(String originalName) {
        return PREFIX + ":" + originalName;
    }

}
