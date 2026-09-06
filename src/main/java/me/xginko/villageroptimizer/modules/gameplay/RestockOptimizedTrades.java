package me.xginko.villageroptimizer.modules.gameplay;

import me.xginko.villageroptimizer.VillagerOptimizer;
import me.xginko.villageroptimizer.modules.VillagerOptimizerModule;
import me.xginko.villageroptimizer.struct.enums.Permissions;
import me.xginko.villageroptimizer.utils.KyoriUtil;
import me.xginko.villageroptimizer.utils.LocationUtil;
import me.xginko.villageroptimizer.utils.Util;
import me.xginko.villageroptimizer.wrapper.WrappedVillager;
import net.kyori.adventure.text.TextReplacementConfig;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class RestockOptimizedTrades extends VillagerOptimizerModule implements Listener {

    private final SortedSet<Long> restockDayTimes;
    private final boolean log_enabled, notify_player;

    public RestockOptimizedTrades() {
        super("gameplay.restock-optimized-trades");
        this.restockDayTimes = new TreeSet<>(Comparator.reverseOrder());

        final List<Long> defaults = Arrays.asList(1000L, 13000L);
        final List<?> rawRestockTimes = config.master().getList(configPath + ".restock-times", new ArrayList<>(defaults));
        for (Object raw : rawRestockTimes) {
            if (raw instanceof Number number) {
                restockDayTimes.add(number.longValue());
                continue;
            }

            if (raw instanceof String str) {
                try {
                    restockDayTimes.add(Long.parseLong(str));
                } catch (NumberFormatException ignored) {
                    // ignore invalid config values
                }
            }
        }

        if (restockDayTimes.isEmpty()) {
            restockDayTimes.addAll(defaults);
        }

        config.master().addDefault(configPath + ".restock-times", new ArrayList<>(defaults));
        this.notify_player = config.getBoolean(configPath + ".notify-player", true,
                "Sends the player a message when the trades were restocked on a clicked villager.");
        this.log_enabled = config.getBoolean(configPath + ".log", false);
    }

    @Override
    public void enable() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void disable() {
        HandlerList.unregisterAll(this);
    }

    @Override
    public boolean shouldEnable() {
        return true;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() != EntityType.VILLAGER) return;

        WrappedVillager wrapped = wrapperCache.get((Villager) event.getRightClicked(), WrappedVillager::new);
        if (!wrapped.isOptimized()) return;

        // Nothing has been traded since the last restock, so there is nothing to replenish.
        // Returning here keeps the restock window available instead of burning it on a villager
        // whose trades are already full, which is what vanilla does via Villager#needsToRestock().
        if (!wrapped.needsRestock()) return;

        if (event.getPlayer().hasPermission(Permissions.Bypass.RESTOCK_COOLDOWN.get())) {
            wrapped.restock();
            return;
        }

        long lastRestockFullTimeTicks = wrapped.getLastRestockFullTime();
        long currentFullTimeTicks = wrapped.currentFullTimeTicks();
        long currentDayTimeTicks = wrapped.currentDayTimeTicks();

        long currentDay = currentFullTimeTicks - currentDayTimeTicks;
        long ticksTillRestock = (24000 + currentDay + restockDayTimes.first()) - currentFullTimeTicks;

        boolean restocked = false;

        for (Long restockDayTime : restockDayTimes) {
            long restockTimeToday = currentDay + restockDayTime;

            if (currentFullTimeTicks < restockTimeToday || lastRestockFullTimeTicks >= restockTimeToday) {
                ticksTillRestock = Math.min(ticksTillRestock, restockTimeToday - currentFullTimeTicks);
                continue;
            }

            if (!restocked) {
                wrapped.restock();
                wrapped.saveRestockTime();
                restocked = true;
            }
        }

        if (!restocked) return;

        if (notify_player) {
            final TextReplacementConfig timeLeft = TextReplacementConfig.builder()
                    .matchLiteral("%time%")
                    .replacement(Util.formatDuration(Duration.ofMillis(ticksTillRestock * 50L)))
                    .build();
            VillagerOptimizer.getLang(event.getPlayer().locale()).trades_restocked
                    .forEach(line -> KyoriUtil.sendMessage(event.getPlayer(), line.replaceText(timeLeft)));
        }

        if (log_enabled) {
            info("Restocked optimized villager at " + LocationUtil.toString(wrapped.villager.getLocation()));
        }
    }
}
