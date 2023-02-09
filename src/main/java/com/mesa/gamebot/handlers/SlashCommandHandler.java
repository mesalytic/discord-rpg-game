package com.mesa.gamebot.handlers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SlashCommandHandler {
    private final JDA jda;
    public static final Map<String, Command> slashCommandMap = new HashMap<>();

    public SlashCommandHandler(JDA jda) {
        this.jda = jda;
    }

    public void addCommands() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Guild guild = jda.getGuildById("418433461817180180");

        CommandListUpdateAction globalUpdate = jda.updateCommands();
        assert guild != null;
        CommandListUpdateAction guildUpdate = guild.updateCommands();

        Reflections reflections = new Reflections("com.mesa.gamebot.commands");
        Set<Class<? extends Command>> commands = reflections.getSubTypesOf(Command.class);

        for (Class<? extends Command> commandClass : commands) {
            if (Modifier.isAbstract(commandClass.getModifiers())) continue;

            Command command = commandClass.getConstructor().newInstance();

            if (command.isDev()) guildUpdate.addCommands(command.getCommandData());
            else globalUpdate.addCommands(command.getCommandData());

            slashCommandMap.put(command.getName(), command);
        }

        globalUpdate.queue();
        guildUpdate.queue();
    }

    public Map<String, Command> getCommandMap() {
        return slashCommandMap;
    }
}
