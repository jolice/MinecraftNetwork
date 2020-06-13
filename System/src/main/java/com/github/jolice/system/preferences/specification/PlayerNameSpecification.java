package com.github.jolice.system.preferences.specification;

import io.ebean.Query;
import lombok.RequiredArgsConstructor;
import com.github.jolice.persistence.specification.Specification;

@RequiredArgsConstructor
public class PlayerNameSpecification implements Specification {

    private final String name;

    @Override
    public <T> Query<T> append(Query<T> query) {
        return query.where().eq("profile.name", name).query();
    }
}
