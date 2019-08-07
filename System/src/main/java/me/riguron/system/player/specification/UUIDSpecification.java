package me.riguron.system.player.specification;

import io.ebean.Query;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import me.riguron.persistence.specification.Specification;

import java.util.UUID;

/**
 * Allows for searching a player by his UUID.
 */
@Data
@Setter(AccessLevel.NONE)
public class UUIDSpecification implements Specification {

    private final UUID uuid;

    @Override
    public <T> Query<T> append(Query<T> query) {
        return query.where().eq("uuid", uuid).query();
    }
}
