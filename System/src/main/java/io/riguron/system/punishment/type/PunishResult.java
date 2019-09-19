package io.riguron.system.punishment.type;

import io.riguron.system.punishment.model.PunishmentRecord;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import io.riguron.system.punishment.model.PunishmentRecord;

import java.util.function.Consumer;

@Data
@Setter(AccessLevel.NONE)
public class PunishResult {

    public static final PunishResult NOT_FOUND = new PunishResult(PunishResultType.NOT_FOUND);

    private PunishmentRecord punishmentRecord;
    private PunishResultType resultType;

    private PunishResult(PunishResultType resultType) {
        this.resultType = resultType;
    }

    public PunishResult(PunishmentRecord record, PunishResultType resultType) {
        this.resultType = resultType;
        this.punishmentRecord = record;
    }

    public PunishResult ifPresent(Consumer<PunishmentRecord> action) {
        if (resultType == PunishResultType.OK) {
            action.accept(punishmentRecord);
        }
        return this;
    }
}
