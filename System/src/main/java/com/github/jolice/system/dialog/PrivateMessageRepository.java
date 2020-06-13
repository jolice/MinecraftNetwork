package com.github.jolice.system.dialog;

import io.ebean.EbeanServer;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class PrivateMessageRepository {

    private final EbeanServer ebeanServer;

    public void save(PrivateMessageRecord privateMessageRecord) {
        ebeanServer.save(privateMessageRecord);
    }

    /**
     * Retrieves the history of dialog between two players.
     *
     * @param me        first dialog participant
     * @param companion second dialog participant
     * @param page page of the history to be displayed
     * @param count count of items per the page
     * @return
     */
    public List<PrivateMessageProjection> getHistory(UUID me, String companion, int page, int count) {
        return ebeanServer.findDto(PrivateMessageProjection.class,
                "select " +
                        "sender.name f, receiver.name t, m.text, m.date " +
                        "from private_message_record m" +
                        " inner join players sender on sender.id = m.from_id" +
                        " inner join players receiver on receiver.id = m.to_id" +
                        " where " +
                        "sender.uuid = :me" +
                        " and receiver.name = :companion" +
                        " or" +
                        " sender.name = :companion " +
                        " and receiver.uuid = :me" +
                        " order by date")
                .setParameter("me", me)
                .setParameter("companion", companion)
                .setMaxRows(count)
                .setFirstRow(page * count)
                .findList();
    }


}
