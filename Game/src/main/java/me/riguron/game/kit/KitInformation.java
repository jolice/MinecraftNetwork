package me.riguron.game.kit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.bukkit.Material;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
public class KitInformation {

    /**
     * Name of the game that introduces the kit.
     */
    @Column(nullable = false)
    private String gameName;

    @Column
    private long price;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private Material icon;

    /**
     * Whether the kit is default (i.e given to the player
     * if he has not selected a kit explicitly).
     */
    @Column
    public boolean defaultKit;

}
