package com.github.jolice.system.task.async;

public interface Callback<T> {

    void call(T t);
}
