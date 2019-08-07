package me.riguron.game.listener.restrictions;

import lombok.RequiredArgsConstructor;
import me.riguron.game.config.advanced.AdvancedGameOptions;
import me.riguron.game.listener.state.active.ActiveStateListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

@RequiredArgsConstructor
public class ChangeListener extends ActiveStateListener {

    private final AdvancedGameOptions advancedGameOptions;

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if (!advancedGameOptions.getChangingOptions().isWeatherChange()) {
            if (e.toWeatherState()) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e) {
        if (!advancedGameOptions.getChangingOptions().isFoodChange()) {
            e.setCancelled(true);
        }
    }

}
