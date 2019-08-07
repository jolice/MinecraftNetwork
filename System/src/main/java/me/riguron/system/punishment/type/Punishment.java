package me.riguron.system.punishment.type;

import me.riguron.system.punishment.param.PunishmentParameters;

public interface Punishment {

    PunishResult punish(PunishmentParameters punishmentParameters);
}
