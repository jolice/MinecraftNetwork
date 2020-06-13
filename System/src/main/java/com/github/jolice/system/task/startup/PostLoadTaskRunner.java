package com.github.jolice.system.task.startup;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Class responsible for executing post-load tasks.
 */
@Slf4j
public class PostLoadTaskRunner {

    @Inject(optional = true)
    public void run(List<PostLoadTask> tasks) {
        log.info("Running {} post-load tasks", tasks.size());
        tasks.forEach(PostLoadTask::run);
    }
}
