package com.mesa.gamebot;

import com.mesa.gamebot.handlers.SlashCommandHandler;
import com.mesa.gamebot.listeners.GatewayListener;
import com.mesa.gamebot.listeners.SlashListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static JDA jda;
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        jda = JDABuilder.createDefault(Config.get("TOKEN"))
                .enableIntents(GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS)
                .addEventListeners(new GatewayListener())
                .build()
                .awaitReady();

        jda.getPresence().setActivity(Activity.playing("cc"));

        SlashCommandHandler slashCommandHandler = new SlashCommandHandler(jda);
        jda.addEventListener(new SlashListener(slashCommandHandler));

        slashCommandHandler.addCommands();
        log.info("Slash commands registered.");
    }
}