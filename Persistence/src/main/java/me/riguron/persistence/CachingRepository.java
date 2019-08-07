package me.riguron.persistence;

/**
 * Represents a cache-capable repository.
 *
 * @param <I> type of the primary key
 * @param <T> type of the object stored in the repository.
 */
public interface CachingRepository<I, T> {

    void invalidate(I id);

    T get(I id);

    void put(I id, T t);

}
