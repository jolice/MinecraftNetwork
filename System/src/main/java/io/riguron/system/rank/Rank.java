package io.riguron.system.rank;

import io.ebean.annotation.Cache;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@Setter(AccessLevel.NONE)
@Cache(naturalKey = "name")
@RequiredArgsConstructor
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private int id;

    @NonNull
    @Column
    private boolean operator;

    @Column(unique = true, nullable = false)
    @NonNull
    private String name;

    /**
     * Rank prefix is displayed in the chat, scoreboards and so on.
     * It is typically the same with Rank's name.
     */
    @Column(unique = true, nullable = false)
    @NonNull
    private String prefix;

    /**
     * List of rank's permissions.
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @NonNull
    private Set<String> permissions;

    /**
     * Ranks in a system form a hierarchy. The higher is a Rank,
     * the more permissions it has. Each Rank has it's own permission
     * and permissions of his child (i.e of all Ranks lower in the hierarchy).
     */
    @Column
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    private Rank child;

    /**
     * Checks whether the Rank has specific permission. The Rank X has permission Y
     * if Y is included in the list of permissions of X or child of X has permission Y
     * (recursive check).
     *
     * @param permission target permission
     * @return whether the rank has a permission
     */
    public boolean hasPermission(String permission) {
        return operator || permissions.contains(permission) || child != null && child.hasPermission(permission);
    }


}
