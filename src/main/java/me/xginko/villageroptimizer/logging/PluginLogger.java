package me.xginko.villageroptimizer.logging;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server-neutral logger that keeps Adventure components out of optional SLF4J service loading.
 */
public final class PluginLogger {
    private static final PlainTextComponentSerializer PLAIN_TEXT = PlainTextComponentSerializer.plainText();

    private final Logger delegate;

    public PluginLogger(@NotNull Logger delegate) {
        this.delegate = delegate;
    }

    public void info(@NotNull String message) {
        delegate.info(message);
    }

    public void info(@NotNull Component message) {
        info(PLAIN_TEXT.serialize(message));
    }

    public void info(@NotNull String format, @NotNull Object argument) {
        info(format(format, argument));
    }

    public void warn(@NotNull String message) {
        delegate.warning(message);
    }

    public void warn(@NotNull String format, @NotNull Object argument) {
        warn(format(format, argument));
    }

    public void error(@NotNull String message) {
        delegate.severe(message);
    }

    public void error(@NotNull String message, @NotNull Throwable throwable) {
        delegate.log(Level.SEVERE, message, throwable);
    }

    public void error(@NotNull String format, @NotNull Object argument) {
        error(format(format, argument));
    }

    public void error(@NotNull String format, @NotNull Object argument, @NotNull Throwable throwable) {
        error(format(format, argument), throwable);
    }

    public void error(@NotNull Component message, @NotNull Throwable throwable) {
        error(PLAIN_TEXT.serialize(message), throwable);
    }

    private static @NotNull String format(@NotNull String format, @NotNull Object argument) {
        return format.replace("{}", String.valueOf(argument));
    }
}