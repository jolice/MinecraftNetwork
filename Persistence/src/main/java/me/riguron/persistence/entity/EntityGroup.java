package me.riguron.persistence.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class EntityGroup {

    private final List<Class<?>> entities;

    public List<Class<?>> getEntities() {
        return Collections.unmodifiableList(entities);
    }

}
