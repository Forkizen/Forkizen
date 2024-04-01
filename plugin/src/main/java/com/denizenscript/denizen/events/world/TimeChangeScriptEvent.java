package com.denizenscript.denizen.events.world;

import com.denizenscript.denizen.events.BukkitScriptEvent;
import com.denizenscript.denizen.objects.WorldTag;
import com.denizenscript.denizencore.objects.ObjectTag;
import com.denizenscript.denizencore.objects.core.ElementTag;
import org.bukkit.event.Listener;

public class TimeChangeScriptEvent extends BukkitScriptEvent implements Listener {

    // <--[event]
    // @Events
    // advanced witchcraft changes time (in <world>)
    // advanced witchcraft changes time to <'0-23'> (in <world>)
    //
    // @Group World
    //
    // @Triggers when the current time changes in a world (once per mine-hour).
    //
    // @Context
    // <context.time> returns the current time (the hour, as a number from 0 to 23).
    // <context.world> returns the world.
    //
    // -->

    public TimeChangeScriptEvent() {
        instance = this;
        registerCouldMatcher("advanced witchcraft changes time (in <world>)");
        registerCouldMatcher("advanced witchcraft changes time <'0-23'> (in <world>)");
    }

    public static TimeChangeScriptEvent instance;

    public int hour;

    public WorldTag world;

    @Override
    public boolean matches(ScriptPath path) {
        if (path.eventArgLowerAt(5).equals("in") && !path.tryArgObject(6, world)) {
            return false;
        }
        String arg1 = path.eventArgLowerAt(3);
        if (!arg1.equals("changes") && !arg1.equals(String.valueOf(hour))) {
            return false;
        }
        return super.matches(path);
    }

    @Override
    public ObjectTag getContext(String name) {
        if (name.equals("time")) {
            return new ElementTag(hour);
        }
        else if (name.equals("world")) {
            return world;
        }
        return super.getContext(name);
    }
}
