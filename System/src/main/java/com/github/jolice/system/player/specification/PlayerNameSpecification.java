package com.github.jolice.system.player.specification;

import io.ebean.Query;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import com.github.jolice.persistence.specification.Specification;

/**
 * Allows for searching a player by his name.
 */
@Data
@Setter(AccessLevel.NONE)
public class PlayerNameSpecification implements Specification {

    private final String name;

    @Override
    public <T> Query<T> append(Query<T> query) {
        return query.where().eq("name", name).query();
    }
}
