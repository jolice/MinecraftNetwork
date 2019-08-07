package me.riguron.io.factory.stream;

import lombok.RequiredArgsConstructor;
import me.riguron.io.factory.DataFormat;
import me.riguron.io.file.ResourceInputStreamProvider;
import me.riguron.io.provider.InputStreamProvider;
import me.riguron.io.provider.OutputStreamProvider;
import me.riguron.io.provider.file.FileInputStreamProvider;
import me.riguron.io.provider.file.FileOutputStreamProvider;

import java.nio.file.Paths;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class FileStreamProviderFactory implements StreamProviderFactory {

    private static final String CLASSPATH = "classpath:";

    private final DataFormat dataFormat;
    private final StreamProviderFactory resourceFactory = new ResourceStreamProviderFactory();
    private final StreamProviderFactory fileFactory = new FileSystemStreamProviderFactory();

    @Override
    public InputStreamProvider newInputStreamProvider(String target) {
        return this.delegateToFactory(target, StreamProviderFactory::newInputStreamProvider);
    }

    @Override
    public OutputStreamProvider newOutputStreamProvider(String target) {
        return this.delegateToFactory(target, StreamProviderFactory::newOutputStreamProvider);
    }

    private <T> T delegateToFactory(String target, BiFunction<StreamProviderFactory, String, T> mapper) {
        StreamProviderFactory targetFactory;
        String targetResource = target.contains(".") ? target : target + "." + this.dataFormat.getExtension();
        if (targetResource.startsWith(CLASSPATH)) {
            targetResource = targetResource.replace(CLASSPATH, "");
            targetFactory = resourceFactory;
        } else {
            targetFactory = fileFactory;
        }
        return mapper.apply(targetFactory, targetResource);
    }

    private class ResourceStreamProviderFactory implements StreamProviderFactory {

        @Override
        public InputStreamProvider newInputStreamProvider(String source) {
            return new ResourceInputStreamProvider(source);
        }

        @Override
        public OutputStreamProvider newOutputStreamProvider(String target) {
            throw new UnsupportedOperationException();
        }
    }

    private class FileSystemStreamProviderFactory implements StreamProviderFactory {

        @Override
        public InputStreamProvider newInputStreamProvider(String source) {
            return new FileInputStreamProvider(Paths.get(source));
        }

        @Override
        public OutputStreamProvider newOutputStreamProvider(String target) {
            return new FileOutputStreamProvider(Paths.get(target));
        }
    }
}
