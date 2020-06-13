package com.github.jolice.shop.click;

import java.util.UUID;

public enum NoOpClickHandler implements ClickHandler {

    INSTANCE;

    @Override
    public void onClick(UUID uuid) {
    }

}