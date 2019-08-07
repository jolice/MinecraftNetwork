package me.riguron.io.factory.stream;

import me.riguron.io.provider.InputStreamProvider;
import me.riguron.io.provider.OutputStreamProvider;

public interface StreamProviderFactory {

    InputStreamProvider newInputStreamProvider(String source);

    OutputStreamProvider newOutputStreamProvider(String target);


}
