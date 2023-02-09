package com.mesa.gamebot.handlers;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.List;

public interface Command {
    public String getName();

    public SlashCommandData getCommandData();

    public List<Permission> getPermissions();

    public boolean isDev();

    public void execute(SlashCommandInteractionEvent event);
}
