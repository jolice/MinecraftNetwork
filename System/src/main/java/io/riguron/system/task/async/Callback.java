package io.riguron.system.task.async;

public interface Callback<T> {

    void call(T t);
}
