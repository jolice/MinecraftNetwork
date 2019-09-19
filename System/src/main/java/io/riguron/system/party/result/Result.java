package io.riguron.system.party.result;

/**
 * Represents generic interface of party actions results containing
 * convenient methods for the presentation layer.
 */
public interface Result {

    /**
     * A description of the result for an internalization. Given result:
     * <p>
     * InviteResult.DISABLED, then this method should return "disabled".
     * This method is actually implemented by the enumeration.
     *
     * @return actual unique description of the result
     */
    String name();

    /**
     * A root path of internalization key. For example, a key is:
     * <p>
     * "party.invite.disabled"
     * <p>
     * then this method should return "invite".
     *
     * @return root part of an internalization key
     */
    String path();

    default String getDescription() {
        return name().toLowerCase();
    }


}
