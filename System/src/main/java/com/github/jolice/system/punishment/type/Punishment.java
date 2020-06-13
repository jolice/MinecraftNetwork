package com.github.jolice.system.punishment.type;

import com.github.jolice.system.punishment.param.PunishmentParameters;

public interface Punishment {

    PunishResult punish(PunishmentParameters punishmentParameters);
}
