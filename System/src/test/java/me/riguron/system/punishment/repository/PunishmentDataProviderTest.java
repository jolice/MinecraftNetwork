package me.riguron.system.punishment.repository;

import me.riguron.system.punishment.model.PunishmentData;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class PunishmentDataProviderTest {

    @Test
    public void whenDataExistsThenReturnedExisting() {

        PunishmentDataRepository repository = mock(PunishmentDataRepository.class);
        UUID uuid = UUID.randomUUID();
        PunishmentData punishmentData = mock(PunishmentData.class);
        when(repository.findPunishmentData(eq(uuid))).thenReturn(Optional.of(punishmentData));

        PunishmentDataProvider punishmentDataProvider = new PunishmentDataProvider(repository);
        PunishmentData data = punishmentDataProvider.getPunishmentsFor(uuid);

        assertNotNull(data);
        verify(repository, times(0)).save(any());
    }

    @Test
    public void whenDataDoesNotExistThenCreatedNew() {

        PunishmentDataRepository repository = mock(PunishmentDataRepository.class);
        UUID uuid = UUID.randomUUID();
        when(repository.findPunishmentData(eq(uuid))).thenReturn(Optional.empty());

        PunishmentDataProvider provider = new PunishmentDataProvider(repository);


        PunishmentData data = provider.getPunishmentsFor(uuid);

        assertNotNull(data);
        verify(repository).save(eq(data));


    }
}