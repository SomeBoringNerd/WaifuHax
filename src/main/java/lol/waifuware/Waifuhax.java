package lol.waifuware;

import lol.waifuware.Modules.ModuleManager;
import lol.waifuware.Commands.CommandManager;
import lol.waifuware.Util.ChatUtil;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import net.fabricmc.api.ModInitializer;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Waifuhax implements ModInitializer
{
    public static final IEventBus EVENT_BUS = new EventBus();

    public static final List<String> NO_JUNE_UUIDS = List.of("64a7dadab6d4433d9b803c96f91ed370", "3a16450f8b124335acf3dc7413c76a99", "80320fa7120a4ed0ad195e449a8c0023");

    @Override
    public void onInitialize()
    {
        // new ChatUtil();
        ChatUtil.Log("Hello World !");

        EVENT_BUS.registerLambdaFactory(this.getClass().getPackage().getName() , (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
        EVENT_BUS.subscribe(this);

        new ModuleManager();
        new CommandManager();
    }

}
