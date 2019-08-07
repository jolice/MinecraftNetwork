package me.riguron.persistence.specification;

import io.ebean.Query;

/**
 * Specification pattern adapted for the Ebean ORM.
 * See https://en.wikipedia.org/wiki/Specification_pattern for more details.
 */
public interface Specification {

    <T> Query<T> append(Query<T> query);

}
