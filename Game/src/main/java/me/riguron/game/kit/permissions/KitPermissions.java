package me.riguron.game.kit.permissions;

import me.riguron.system.internalization.Message;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class KitPermissions  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public abstract boolean isAvailableTo(KitChallenger challenger);

    public abstract Message unavailabilityMessage(KitChallenger challenger);




}
