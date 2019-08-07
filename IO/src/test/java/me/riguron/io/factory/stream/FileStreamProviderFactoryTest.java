package me.riguron.io.factory.stream;

import me.riguron.io.factory.DataFormat;
import me.riguron.io.provider.InputStreamProvider;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import static org.junit.Assert.assertNotNull;

public class FileStreamProviderFactoryTest {

    @Test(expected = UncheckedIOException.class)
    public void whenExternalResource() {
        StreamProviderFactory streamProviderFactory = new FileStreamProviderFactory(
                DataFormat.JSON
        );
        streamProviderFactory.newInputStreamProvider("in.txt").getInputStream();

    }

    @Test
    public void whenClassPathResource() {

        StreamProviderFactory streamProviderFactory = new FileStreamProviderFactory(
                DataFormat.JSON
        );

        InputStreamProvider inputStreamProvider = streamProviderFactory.newInputStreamProvider("classpath:in.txt");
        try (InputStream inputStream= inputStreamProvider.getInputStream()) {
            assertNotNull(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenOutputStreamToClasspath() {
        StreamProviderFactory streamProviderFactory = new FileStreamProviderFactory(
                DataFormat.JSON
        );
        streamProviderFactory.newOutputStreamProvider("classpath:out.txt");
    }
}