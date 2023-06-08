package lol.waifuware;

import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Commands.CommandManager;
import lol.waifuware.Util.ChatUtil;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import net.fabricmc.api.ModInitializer;

import java.lang.invoke.MethodHandles;

public class Waifuhax implements ModInitializer
{
    public static final IEventBus EVENT_BUS = new EventBus();
    @Override
    public void onInitialize()
    {
        new ChatUtil();
        ChatUtil.Log("Hello World !");

        EVENT_BUS.registerLambdaFactory(this.getClass().getPackage().getName() , (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
        EVENT_BUS.subscribe(this);

        new ModuleManager();
        new CommandManager();
    }

}
