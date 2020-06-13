package com.github.jolice.system.internalization;

import org.junit.Test;

import java.util.Collections;
import java.util.Locale;

import static org.junit.Assert.*;

public class InternalizationServiceTest {

    @Test
    public void whenMessageExists() {

        InternalizationService internalizationService = new InternalizationService();
        internalizationService
                .add(Locale.ENGLISH,
                        () -> Collections.singletonMap("some.message.key", "Message in English: %s")
                );

        String translation = internalizationService.getMessage(() -> Locale.ENGLISH, new Message("some.message.key", "test"));
        assertEquals("Message in English: test", translation);
    }

    @Test
    public void whenMessageDoesNotExist() {

        InternalizationService internalizationService = new InternalizationService();
        assertEquals("a.b.c", internalizationService.getMessage(() -> Locale.ENGLISH, new Message("a.b.c")));

    }
}