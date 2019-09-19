package io.riguron.system.plugin;

import com.google.inject.Module;

import java.util.List;

/**
 * Interface implemented by custom server plugins to provide
 * hooks that used by the system to activate the plugin.
 */
public interface PluginConfiguration {

    /**
     * All Guice modules involved in the plugin.
     *
     * @return plugin's DI modules
     */
    List<Module> modules();

    /**
     * JPA entities operated by the plugin.
     *
     * @return list of plugin's entities.
     */
    List<Class<?>> entities();


}
