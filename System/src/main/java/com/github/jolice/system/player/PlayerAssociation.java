package com.github.jolice.system.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PlayerAssociation {

    PREFERENCES("playerPreferences"), PURCHASES("purchases.data");

    @Getter
    private final String path;

}
