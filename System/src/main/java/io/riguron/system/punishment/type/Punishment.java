package io.riguron.system.punishment.type;

import io.riguron.system.punishment.param.PunishmentParameters;

public interface Punishment {

    PunishResult punish(PunishmentParameters punishmentParameters);
}
