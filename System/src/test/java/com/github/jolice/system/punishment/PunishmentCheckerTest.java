package com.github.jolice.system.punishment;

import com.github.jolice.system.punishment.model.ActivePunishmentRecord;
import com.github.jolice.system.punishment.model.ActivePunishmentType;
import com.github.jolice.system.punishment.model.PunishmentData;
import com.github.jolice.system.punishment.repository.PunishmentDataRepository;
import com.github.jolice.system.task.Task;
import com.github.jolice.system.task.TaskFactory;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class PunishmentCheckerTest {

    @Test
    public void whenPunishmentExists() {

        PunishmentDataRepository repository = mock(PunishmentDataRepository.class);
        TaskFactory taskFactory = mock(TaskFactory.class);

        UUID uuid = UUID.randomUUID();

        PunishmentData data = mock(PunishmentData.class);
        when(data.getPunishment(eq(ActivePunishmentType.BAN)))
                .thenReturn(Optional.of(mock(ActivePunishmentRecord.class)));

        when(repository.getPunishmentData(eq(uuid))).thenReturn(
                Optional.of(data)
        );

        PunishmentChecker checker = new PunishmentChecker(repository, taskFactory);

        Optional<ActivePunishmentRecord> optionalResult = checker.checkPunishment(uuid, ActivePunishmentType.BAN);
        assertTrue(optionalResult.isPresent());
    }

    @Test
    public void whenPunishmentDoesNotExist() {
        PunishmentDataRepository repository = mock(PunishmentDataRepository.class);
        TaskFactory taskFactory = mock(TaskFactory.class);

        UUID uuid = UUID.randomUUID();
        PunishmentData data = mock(PunishmentData.class);
        when(data.getPunishment(any()))
                .thenReturn(Optional.empty());

        PunishmentChecker punishmentChecker = new PunishmentChecker(repository, taskFactory);
        Optional<ActivePunishmentRecord> optionalResult = punishmentChecker.checkPunishment(uuid, ActivePunishmentType.MUTE);

        assertFalse(optionalResult.isPresent());
    }

    @Test
    public void whenPunishmentExpired() {
        PunishmentDataRepository repository = mock(PunishmentDataRepository.class);
        TaskFactory taskFactory = mock(TaskFactory.class);

        UUID uuid = UUID.randomUUID();

        PunishmentData data = mock(PunishmentData.class);
        ActivePunishmentRecord expiredRecord = mock(ActivePunishmentRecord.class);
        when(expiredRecord.isExpired()).thenReturn(true);
        when(data.getPunishment(eq(ActivePunishmentType.MUTE)))
                .thenReturn(Optional.of(expiredRecord));

        when(taskFactory.newAsyncTask(any())).thenReturn(mock(Task.class));


        when(repository.getPunishmentData(eq(uuid))).thenReturn(
                Optional.of(data)
        );

        PunishmentChecker checker = new PunishmentChecker(repository, taskFactory);
        assertFalse(checker.checkPunishment(uuid, ActivePunishmentType.MUTE).isPresent());
        verify(data).removePunishment(eq(ActivePunishmentType.MUTE));
    }
}