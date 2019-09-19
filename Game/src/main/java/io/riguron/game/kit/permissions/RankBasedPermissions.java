package io.riguron.game.kit.permissions;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import io.riguron.system.internalization.Message;
import io.riguron.system.rank.Rank;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RankBasedPermissions extends KitPermissions {

    private final String permission;

    @ManyToOne
    private final Rank requiredRank;

    @Override
    public boolean isAvailableTo(KitChallenger challenger) {
        return challenger.hasPermission(this.permission);
    }

    @Override
    public Message unavailabilityMessage(KitChallenger challenger) {
        return new Message("game.kit.permissions.rank", requiredRank.getPrefix());
    }
}
