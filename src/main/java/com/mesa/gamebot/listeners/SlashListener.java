package com.mesa.gamebot.listeners;

import com.mesa.gamebot.handlers.Command;
import com.mesa.gamebot.handlers.SlashCommandHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SlashListener extends ListenerAdapter {
    private final SlashCommandHandler slashHandler;

    private final static Logger log = LoggerFactory.getLogger(SlashListener.class);

    public SlashListener(SlashCommandHandler slashHandler) {
        this.slashHandler = slashHandler;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getGuild() == null) {
            event.reply("Commands do not work in DMs.").queue();
            return;
        }

        String commandName = event.getName();
        Map<String, Command> commands = slashHandler.getCommandMap();

        if (commands.containsKey(commandName)) {
            Command command = commands.get(commandName);

            command.execute(event);
        }
    }
}
