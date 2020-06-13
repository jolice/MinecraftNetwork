package io.riguron.io.factory.stream;

import io.riguron.io.provider.InputStreamProvider;
import io.riguron.io.provider.OutputStreamProvider;

public interface StreamProviderFactory {

    InputStreamProvider newInputStreamProvider(String source);

    OutputStreamProvider newOutputStreamProvider(String target);


}
