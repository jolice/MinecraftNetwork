package com.github.jolice.game.kit.permissions;

import io.riguron.system.internalization.Message;

import javax.persistence.Entity;

@Entity
public class AnyoneKitPermissions extends KitPermissions {

    @Override
    public boolean isAvailableTo(KitChallenger challenger) {
        return true;
    }

    @Override
    public Message unavailabilityMessage(KitChallenger challenger) {
        throw new KitAvailabilityException("This kit is available to anyone");
    }
}
