package me.riguron.server;

import lombok.Getter;

public enum ServerType implements ServerGroup {

    BEDWARS("bw"), SKYWARS("sw"), LOBBY("lobby"), ARCADE("arcade");

    @Getter
    private String tag;

    ServerType(String tag) {
        this.tag = tag;
    }

    public boolean matches(String name) {
        return name.startsWith(tag);
    }

    @Override
    public String getName() {
        return tag;
    }
}
