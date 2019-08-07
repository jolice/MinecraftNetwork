package me.riguron.bungee.data;

import me.riguron.server.repository.GlobalOnlineRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalOnlineTest {

    @Test
    public void getGlobalOnline() {
        GlobalOnlineRepository globalOnlineRepository = mock(GlobalOnlineRepository.class);
        when(globalOnlineRepository.getGlobalOnline()).thenReturn(10);
        GlobalOnline globalOnline = new GlobalOnline(globalOnlineRepository);
        globalOnline.update();

        new Thread(() -> assertEquals(10, globalOnline.getGlobalOnline())).start();
    }
}