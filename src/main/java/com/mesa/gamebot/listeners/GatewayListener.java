package com.mesa.gamebot.listeners;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatewayListener extends ListenerAdapter {
    private static final Logger log = LoggerFactory.getLogger(GatewayListener.class);

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        log.info("Bot has successfully started !");
    }
}
