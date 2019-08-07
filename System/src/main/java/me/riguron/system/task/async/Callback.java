package me.riguron.system.task.async;

public interface Callback<T> {

    void call(T t);
}
